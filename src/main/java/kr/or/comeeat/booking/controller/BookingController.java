package kr.or.comeeat.booking.controller;

import java.util.List;

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

public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@GetMapping(value="review/booking/bookingFrm")
	public String bookingFrm() {
		return "booking/bookingFrm";
	}
	
	
	@PostMapping(value="booking/book")
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
	
	@ResponseBody
	@GetMapping(value="/booking/bookinginfo")
	public List bookinginfo(String bookingDate) {
		System.out.println(bookingDate);
		System.out.println("컨트롤러 통과1");
		List list = bookingService.allBookingTime(bookingDate);
		System.out.println("컨트롤러 통과2");
		System.out.println(list);
		return list;
	}
}
