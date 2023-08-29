package kr.or.comeeat.event.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.comeeat.event.model.dao.EventDao;
import kr.or.comeeat.event.model.vo.Event;

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
}
