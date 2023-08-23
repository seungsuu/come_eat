package kr.or.comeeat.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.comeeat.booking.model.service.BookingService;

@Controller
@RequestMapping(value="/booking")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@GetMapping(value="/bookingFrm")
	public String bookingFrm() {
		return "booking/bookingFrm";
	}
}
