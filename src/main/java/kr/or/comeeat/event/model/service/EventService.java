package kr.or.comeeat.event.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.comeeat.event.model.dao.EventDao;
import kr.or.comeeat.event.model.vo.Event;
import kr.or.comeeat.magazine.model.vo.Magazine;

@Service
public class EventService {
	@Autowired
	private EventDao eventDao;

	public int totalCount() {
		int totalCount = eventDao.totalCount();
		return totalCount;
	}
	
	@Transactional
	public int insertEvent(Event e) {
		int result = eventDao.insertEvent(e);
		return result;
	}

	public List selectEventList(int start, int end) {
		List eventList = eventDao.selectEventList(start,end);
		return eventList;
	}
	
	//게시판 상세보기
	public Event selectOneEvent(int eventNo) {
		Event e = new Event();
		e = eventDao.selectOneEvent(eventNo);
		return e;
	}


	//이벤트 종료버튼
	@Transactional
	public int closeEvente(int eventNo, int close) {
		int result = eventDao.closeEvent(eventNo,close);
		return result;
	}
	
	//게시물 수정하기
	@Transactional
	public int updateEvent(Event e) {
		int result = eventDao.updateEvent(e);
		return result;
	}
	
	//게시물 삭제하기
	@Transactional
	public int deleteEvent(int eventNo) {
		int result = eventDao.deleteEvent(eventNo);
		return result;

	}
	
}