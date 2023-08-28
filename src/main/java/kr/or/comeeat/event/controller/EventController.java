package kr.or.comeeat.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.comeeat.event.model.service.EventService;

@Controller
@RequestMapping("/event")
public class EventController {
	@Autowired
	private EventService eventService;

}
