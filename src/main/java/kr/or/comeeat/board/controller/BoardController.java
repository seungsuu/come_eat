package kr.or.comeeat.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.comeeat.board.model.service.BoardService;
import kr.or.comeeat.board.model.vo.BoardData;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
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
	
	//글쓰기
	@PostMapping(value="/write")
	public String boardWrite(String boardTitle, int category, String boardWriter, String boardContent) {
		
	}
	
}
