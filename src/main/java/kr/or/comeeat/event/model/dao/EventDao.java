package kr.or.comeeat.event.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.comeeat.event.model.vo.Event;
import kr.or.comeeat.event.model.vo.EventFileRowMapper;
import kr.or.comeeat.event.model.vo.EventRowMapper;
import kr.or.comeeat.magazine.model.vo.Magazine;
import kr.or.comeeat.review.model.vo.Review;

@Repository
public class EventDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private EventRowMapper eventRowMapper;
	@Autowired
	private EventFileRowMapper eventFileRowMapper;
	
	
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
	
	//게시판 상세보기
	public Event selectOneEvent(int eventNo) {
		String query = "select * from event where event_no=?";
		List list = jdbc.query(query, eventRowMapper, eventNo);
		return (Event)list.get(0);
	}
	
	//이벤트 종료버튼
	public int closeEvent(int eventNo, int close) {
		String query = "update event set close=? where event_no=?";
		int result = jdbc.update(query, close, eventNo);
		return result;
	}
	

	//이벤트 게시글 수정
	public int updateEvent(Event e) {
		String query = "update event set event_title=?, event_subtitle=?, event_date=?, filepath=?, event_content=? where event_no=?";
		Object[] params = {e.getEventTitle(),e.getEventSubtitle(),e.getEventDate(),e.getFilepath(),e.getEventContent(),e.getEventNo()};
		int result = jdbc.update(query,params);
		return result;
	}
	
	//이벤트 게시판 삭제
	public int deleteEvent(int eventNo) {
		String query="delete from event where event_no=?";
		Object[] params = {eventNo};
		int result = jdbc.update(query, params);
		return result;
	}
	}


