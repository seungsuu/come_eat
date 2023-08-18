package kr.or.comeeat.board.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BoardRowMapper implements RowMapper<Board>{

	@Override
	public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
		Board b = new Board();
		b.setBoardCount(rs.getInt("board_count"));
		b.setBoardNo(rs.getInt("board_no"));
		b.setBoardTitle(rs.getString("board_Title"));
		b.setBoardContent(rs.getString("board_content"));
		b.setBoardType(rs.getInt("board_type"));
		b.setBoardWriter(rs.getString("board_writer"));
		b.setRegDate(rs.getString("reg_date"));
		return b;
	}
	
}
