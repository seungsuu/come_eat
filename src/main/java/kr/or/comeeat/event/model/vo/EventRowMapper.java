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
		e.setEventNo(rs.getInt("event_no"));
		e.setEventTitle(rs.getString("event_title"));
		e.setEventContent(rs.getString("event_content"));
		e.setMemberNo(rs.getInt("member_no"));
		e.setFilepath(rs.getString("filepath"));
		e.setEventSubtitle(rs.getString("event_subtitle"));
		e.setEventDate(rs.getString("event_date"));
		e.setRegDate(rs.getString("reg_date"));
		e.setClose(rs.getInt("close"));
		return e;
	}
}
