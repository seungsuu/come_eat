package kr.or.comeeat.board.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kr.or.comeeat.board.model.vo.BoardRowMapper;

@Component
public class BoardDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private BoardRowMapper boardRowMapper;
	
	
	//게시물 조회해오기(10개)
	public List boardList(int start, int end) {
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT * FROM BOARD ORDER BY 1 DESC)N) WHERE RNUM BETWEEN ? AND ?";
		List list = jdbc.query(query,boardRowMapper,start,end);
		return list;
	}

	//천제 게시물 수 조회
	public int selectBoardTotal() {
		String query = "select Count(*) from board";
		int totalList = jdbc.queryForObject(query, Integer.class);
		return totalList;
	}
	
	
}
