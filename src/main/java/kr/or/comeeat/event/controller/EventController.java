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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.or.comeeat.FileUtil;
import kr.or.comeeat.event.model.service.EventService;
import kr.or.comeeat.event.model.vo.Event;
import kr.or.comeeat.event.model.vo.EventData;
import kr.or.comeeat.magazine.model.vo.Magazine;
import kr.or.comeeat.member.model.vo.Member;
import kr.or.comeeat.review.model.vo.Review;

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
	public EventData more(int start, int end,@SessionAttribute(required = false) Member m) {
		//List : photo 4개 받아옴.
		List eventList = eventService.selectEventList(start,end);
		int memberLevel = (m == null) ? 0 : m.getMemberLevel();
		EventData enventData = new EventData(eventList,memberLevel);
		return enventData;
	}
	
	//게시판 상세보기
		@GetMapping(value = "/view")
		public String eventView(int eventNo, Model model) {
			Event e = eventService.selectOneEvent(eventNo);
			model.addAttribute("e", e);
			return "event/eventView";
		}
	
	//게시글 수정게시판으로 이동
	@GetMapping(value="/eventUpdateFrm")
	public String updateFrm(int eventNo,Model model) {
		Event e = eventService.selectOneEvent(eventNo);
		model.addAttribute("e", e);
		return "event/eventUpdateFrm";
	}
	

	//이벤트 닫기버튼
	@GetMapping(value="/close")
	public String closeEvent(int eventNo,int close,Model model) {
		int result = eventService.closeEvente(eventNo,close);
		if(result>0) {
			if(close == 0) {
				model.addAttribute("title", "이벤트 오픈");
				model.addAttribute("msg", "이벤트가 오픈됩니다");
				model.addAttribute("icon", "success");
			}else {				
				model.addAttribute("title", "이벤트 종료");
				model.addAttribute("msg", "이벤트가 종료처리 됩니다");
				model.addAttribute("icon", "success");
			}
		}else {
			model.addAttribute("title", "이벤트 종료 오류");
			model.addAttribute("msg", "관리자에게 문의하세요.");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc", "/event/list");
		return "common/msg";
	}


	
	//이벤트게시판 수정

	@PostMapping(value = "/update")
	public String eventUpdate(Event e, MultipartFile updateFile, Model model) {
		if(!updateFile.isEmpty()) {
			String savepath = root + "event/"; // ->업로드 될 파일 경로
			String filepath =  fileUtil.getFilepath(savepath, updateFile.getOriginalFilename());// ->원본파일 중복체크
			e.setFilepath(filepath);// Review객체에 setting
			File upFile = new File(savepath + filepath);
			try {
				updateFile.transferTo(upFile);
			} catch (IllegalStateException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		int result = eventService.updateEvent(e);
		if (result > 0) {
			model.addAttribute("title", "수정 완료");
			model.addAttribute("msg", "게시글이 수정되었습니다.");
			model.addAttribute("icon", "success");
		} else {
			model.addAttribute("tilte", "수정 실패");
			model.addAttribute("msg", "게시글 수정 중 문제가 발생했습니다.");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc","/event/list");
		return "common/msg";
	}
	
	//이벤트 게시판 삭제
	@GetMapping(value="/delete")
	public String deleteEvent(int eventNo, Model model) {
		int result = eventService.deleteEvent(eventNo);
		if(result>0) {
			model.addAttribute("title", "삭제완료");
			model.addAttribute("msg", "게시글이 삭제되었습니다.");
			model.addAttribute("icon", "success");
		}else {
			model.addAttribute("title", "삭제실패");
			model.addAttribute("msg", "게시글 삭제 중 문제가 발생했습니다.");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc", "/event/list");
		return "common/msg";
	}

	
}
	