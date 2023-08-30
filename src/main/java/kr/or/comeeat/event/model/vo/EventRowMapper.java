package kr.or.comeeat.event.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class EventRowMapper implements RowMapper<Event> {

	@Override
	public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
		Event e = new Event();
		e.setClose(rs.getInt("close"));
		e.setEventSubtitle("event_subtitle");
		e.setFilepath(rs.getString("filepath"));
		e.setMemberNo(rs.getInt("member_no"));
		e.setEventDate(rs.getString("event_date"));
		e.setRegDate(rs.getString("reg_date"));
		e.setEventContent(rs.getNString("event_content"));
		e.setEventTitle(rs.getNString("event_title"));
		e.setEventNo(rs.getInt("event_no"));
		return e;
	}
}
