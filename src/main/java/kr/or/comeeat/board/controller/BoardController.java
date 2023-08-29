package kr.or.comeeat.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import kr.or.comeeat.board.model.service.BoardService;
import kr.or.comeeat.board.model.vo.Board;
import kr.or.comeeat.board.model.vo.BoardData;
import kr.or.comeeat.board.model.vo.BoardFile;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Value("${file.root}")
	private String root;
	@Autowired
	private FileUtil fileUtil;
	
	//리스트페이지 이동
	@GetMapping(value="/list")
	public String boardList(int pageNum,Model model) {
		BoardData boardData = boardService.boardList(pageNum);
		model.addAttribute("list", boardData.getList());
		model.addAttribute("navi", boardData.getNavi());
		return "board/boardList";
	}
	
	//글쓰기페이지 이동
	@GetMapping(value="/writeFrm")
	public String boardWriteFrm() {
		return "board/boardWrite";
	}
	
	//글쓰기 취소
	@GetMapping(value="/writeReset")
	public String writeReset(Model model) {
		model.addAttribute("title", "작성취소");
		model.addAttribute("msg", "작성한 글이 취소되었습니다");
		model.addAttribute("icon", "info");
		model.addAttribute("loc", "/board/list?pageNum=1");
		return "common/msg";
	}
	
	//글쓰기
	@PostMapping(value="/write")
	public String boardWrite(Board b, Model model) {
		ArrayList<BoardFile> fileList = null;
		
		//DB에 insert작업
		int result = boardService.insertBoard(b);
		
		if(result>0) {
			//성공인경우
			model.addAttribute("title", "작성완료");
			model.addAttribute("msg", "게시글 작성이 완료되었습니다.");
			model.addAttribute("icon", "success");
		}else {
			//실패인경우
			model.addAttribute("title", "오류");
			model.addAttribute("msg", "관리자에게 문의하세요");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc","/board/list?pageNum=1");
		return "common/msg";
	}
	
	//썸머노트 파일전송세팅
	@ResponseBody
	@PostMapping(value="/summerNote", produces = "plain/text;charset=utf-8")
	public String editorUpload(MultipartFile file) {
		String savepath = root+"board/";
		String filepath = fileUtil.getFilepath(savepath, file.getOriginalFilename());
		File image = new File(savepath+filepath);
		try {
			file.transferTo(image);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/board/"+filepath;
	}
	
	//상세보기 이동
	@GetMapping(value="/boardView")
	public String boardView(int boardNo, Model model){
		List list = boardService.boardView(boardNo);
		model.addAttribute("b",list.get(0));
		return "board/boardView";
	}
	
}
