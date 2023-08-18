package kr.or.comeeat.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.comeeat.board.model.service.BoardService;
import kr.or.comeeat.board.model.vo.BoardData;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value="/list")
	public String boardList(int pageNum,Model model) {
		BoardData boardData = boardService.boardList(pageNum);
		model.addAttribute("list", boardData.getList());
		model.addAttribute("navi", boardData.getNavi());
		return "board/boardList";
	}
	
}
