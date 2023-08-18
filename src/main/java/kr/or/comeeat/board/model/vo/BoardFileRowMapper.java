package kr.or.comeeat.board.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BoardFileRowMapper implements RowMapper<BoardFile>{

	@Override
	public BoardFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardFile bf = new BoardFile();
		bf.setBoardFileNo(rs.getInt("board_file_no"));
		bf.setBoardFilepath(rs.getString("board_filepath"));
		bf.setBoardNo(rs.getInt("board_no"));
		return bf;
	}
}
