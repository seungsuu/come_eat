package kr.or.comeeat.event.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class EventFileRowMapper implements RowMapper<EventFile> {

	@Override
	public EventFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		EventFile file = new EventFile();
		file.setEventNo(rs.getInt("event_no"));
		file.setEFilePath(rs.getString("e_file_path"));
		file.setEFileNo(rs.getInt("e_file_no"));
		return file;
	}

}
