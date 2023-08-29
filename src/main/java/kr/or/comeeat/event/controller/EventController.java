package kr.or.comeeat.event.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.comeeat.FileUtil;
import kr.or.comeeat.event.model.service.EventService;
import kr.or.comeeat.event.model.vo.Event;

@Controller
@RequestMapping("/event")
public class EventController {
	@Autowired
	private EventService eventService;
	@Autowired
	private FileUtil fileUtil;
	@Value("${file.root}")
	public String root;

	//전체 게시물 수 조회
	@GetMapping(value="/list")
	public String eventList(Model model) {
		int totalCount = eventService.totalCount();
		model.addAttribute("totalCount", totalCount);
		return "event/eventList";
	}
	//글쓰기 페이지로 이동
	@GetMapping(value="/eventFrm")
	public String eventFrm() {
		return "event/eventFrm";
	}
	
	//글작성
	@PostMapping(value="/write")
		//제목,작성자,내용-> Event e / 첨부파일 -> multipartfile imgagefile에 
		public String write(Event e, MultipartFile imageFile, Model model) {
		//저장 경로 지정
		String savepath = root + "event/";
		//중복 파일명 체크
		String filepath = fileUtil.getFilepath(savepath, imageFile.getOriginalFilename());
		//event객체에 셋팅
		e.setFilepath(filepath);
		File upFile = new File(savepath+filepath);
		try {
			imageFile.transferTo(upFile);
		} catch (IllegalStateException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int result = eventService.insertEvent(e);
		if(result > 0) {
			model.addAttribute("title", "작성완료");
			model.addAttribute("msg", "게시글 작성이 완료되었습니다.");
			model.addAttribute("icon", "success");
		}
		else {
			model.addAttribute("title", "작성실패");
			model.addAttribute("msg", "게시글 작성 중 문제가 발생했습니다.");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc", "/event/list");
		return "common/msg";
	}
	
	@ResponseBody
	@PostMapping(value="/more")
	public List more(int start, int end) {
		//List : photo 4개 받아옴.
		List eventList = eventService.selectEventList(start,end);
		return eventList;
	}
}
	
	