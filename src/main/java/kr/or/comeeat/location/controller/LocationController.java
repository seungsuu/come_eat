package kr.or.comeeat.location.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.comeeat.location.model.model.dao.LocationService;

@Controller
@RequestMapping(value="/location")
public class LocationController {
	@Autowired
	private LocationService locationService;
	
	@GetMapping(value="/list")
	public String locationList() {
		return "location/location";
	}
}
