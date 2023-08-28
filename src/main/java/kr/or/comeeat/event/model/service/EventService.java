package kr.or.comeeat.event.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.comeeat.event.model.dao.EventDao;

@Service
public class EventService {
	@Autowired
	private EventDao eventDao;
}
