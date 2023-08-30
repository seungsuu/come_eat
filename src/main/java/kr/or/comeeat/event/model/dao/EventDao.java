package kr.or.comeeat.event.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.comeeat.event.model.vo.Event;
import kr.or.comeeat.event.model.vo.EventRowMapper;

@Repository
public class EventDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private EventRowMapper eventRowMapper;
	public int totalCount() {
		String query = "select count(*) from event";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}
	public int insertEvent(Event e) {
		String query = "insert into event values(event_seq.nextval,?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'),0)";
		Object[] params = {e.getEventTitle(),e.getEventContent(),e.getMemberNo(),e.getFilepath(),e.getEventSubtitle(),e.getEventDate()};
		int result = jdbc.update(query,params);
		return result;
	}
	public List selectEventList(int start, int end) {
		String query = "select * from (select rownum as rnum, e.* from(select * from event order by 1 desc)e) where rnum between ? and ?";
		List eventList = jdbc.query(query, eventRowMapper, start, end);
		return eventList;
	}

}
