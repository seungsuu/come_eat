package kr.or.comeeat.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.comeeat.location.model.vo.Location;
import kr.or.comeeat.review.model.service.ReviewService;

@Controller
@RequestMapping(value="/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	

	
	@GetMapping(value="/detailRestaurant")
	public String detailRestaurant(String loTitle,Model model) {
		Location l = reviewService.selectOneRestaurant(loTitle);
		model.addAttribute("list",l);
		return "review/detailRestaurant";
	}
	
	@GetMapping(value="/reviewWriteFrm")
	public String reviewWriteFrm() {
		return "review/reviewWrite";
	}
	
	@PostMapping(value="/revieWrite")
	public String reviewWrite() {
		return "";
	}
}
