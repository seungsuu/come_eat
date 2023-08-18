package kr.or.comeeat.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.comeeat.board.model.dao.BoardDao;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
}
