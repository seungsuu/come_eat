package kr.or.comeeat.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.comeeat.booking.model.service.BookingService;
import kr.or.comeeat.booking.model.vo.Booking;

@Controller
@RequestMapping(value="/booking")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@GetMapping(value="/bookingFrm")
	public String bookingFrm() {
		return "booking/bookingFrm";
	}
	
	
	@PostMapping(value="/book")
	public String book(Booking b, Model model) {
		int result = bookingService.insertBooking(b);
		if(result>0) {
			model.addAttribute("title", "예약");
			model.addAttribute("msg", "예약이 완료되었습니다.");
			model.addAttribute("icon", "success");
		}else {
			model.addAttribute("title", "예약실패");
			model.addAttribute("msg", "관리자에게 문의하세요");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc","/");
		return "common/msg";
	}
}
