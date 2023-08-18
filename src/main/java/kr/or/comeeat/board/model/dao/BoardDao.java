package kr.or.comeeat.board.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BoardDao {
	@Autowired
	private JdbcTemplate jdbc;
}
