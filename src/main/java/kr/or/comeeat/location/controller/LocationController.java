package kr.or.comeeat.location.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.comeeat.location.model.model.sevice.LocationService;
import kr.or.comeeat.location.model.vo.LocationData;
import kr.or.comeeat.member.model.vo.Member;

@Controller
@RequestMapping(value="/location")
public class LocationController {
	@Autowired
	private LocationService locationService;
	
	
	//검색
	@GetMapping(value="/search")
	public String searchList(String pageNo,String search,Model model) {
		LocationData locationData = locationService.searchList(pageNo,search);
		model.addAttribute("list", locationData.getList());
		model.addAttribute("navi", locationData.getNavi());
		model.addAttribute("title", locationData.getTitle());
		return "search/searchList";
	}
	
	@GetMapping(value="/aroundPlace")
	public String aroudPlace() {
		return "location/aroundPlace";
	}
	
	//전체맛집
	@GetMapping(value="/foodList")
	public String busan(String pageNo,String lo, Model model) {

		LocationData locationData = locationService.foodList(pageNo,lo);
		model.addAttribute("list", locationData.getList());
		model.addAttribute("navi", locationData.getNavi());
		model.addAttribute("title", locationData.getTitle());
		
		return "location/location";
	}
	
	//지도전체출력
	@ResponseBody
	@GetMapping(value="/map")
	public List locationMap(String loCode) {
		List list = locationService.locationMap(loCode);
		return list;
	}
	
	@GetMapping(value="/searchAroundPlace")
	public String searchAroundPlace(String searchPlace, Model model) {
		List list = locationService.searchAroundPlace(searchPlace);
		model.addAttribute("searchList", list);
		return "location/aroundPlace";
	}
	
	
	
	//맛집저장
	@ResponseBody
	@GetMapping(value="/savePlace")
	public int selectSavePlace(String loNo,@SessionAttribute(required = false) Member m, Model model) {
		int result = 0;
		if(m != null) {
			result = locationService.selectSavePlace(Integer.parseInt(loNo),m.memberNo);
		}
		System.out.println(result);
		return result;
	}
}
