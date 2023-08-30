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
@RequestMapping(value = "/location")
public class LocationController {
	@Autowired
	private LocationService locationService;

	// 검색
	@GetMapping(value = "/search")
	public String searchList(String pageNo, String search, Model model) {
		LocationData locationData = locationService.searchList(pageNo, search);
		model.addAttribute("list", locationData.getList());
		model.addAttribute("navi", locationData.getNavi());
		model.addAttribute("title", locationData.getTitle());
		return "search/searchList";
	}

	@GetMapping(value = "/aroundPlace")
	public String aroudPlace() {
		return "location/aroundPlace";
	}
	
	//전체맛집
	@GetMapping(value="/foodList")
	public String busan(String pageNo,String lo, Model model) {
		if(lo.equals("su")||lo.equals("bs")||lo.equals("jn")) {
			LocationData locationData = locationService.foodList(pageNo,lo);
			model.addAttribute("list", locationData.getList());
			model.addAttribute("navi", locationData.getNavi());
			model.addAttribute("title", locationData.getTitle());
			return "location/location";
		}else {
			//공공데이터 없는거..준비중
			model.addAttribute("title", "coming soon!");
			model.addAttribute("msg", "데이터 준비중인 지역입니다.");
			model.addAttribute("icon", "info");
			model.addAttribute("loc", "/location/foodList?pageNo=1&lo=su");
			return "common/msg";
		}
	}

	// 지도전체출력
	@ResponseBody
	@GetMapping(value = "/map")
	public List locationMap(String loCode) {
		List list = locationService.locationMap(loCode);
		return list;
	}

	@GetMapping(value = "/searchAroundPlace")
	public String searchAroundPlace(String searchPlace, Model model) {
		List list = locationService.searchAroundPlace(searchPlace);
		model.addAttribute("searchList", list);
		return "location/aroundPlace";
	}

	// 맛집저장내역 불러오기
	@ResponseBody
	@GetMapping(value = "/savePlaceSelect")
	public int savePlaceSelect(String loNo, @SessionAttribute(required = false) Member m) {
		int result = 0;
		if (m != null) {
			result = locationService.savePlaceSelect(Integer.parseInt(loNo), m.memberNo);
		}
		return result;
	}

	// 맛집저장
	@ResponseBody
	@GetMapping(value = "/savePlace")
	public int selectSavePlace(String loNo, @SessionAttribute(required = false) Member m) {
		int result = 0;
		if (m != null) {
			result = locationService.selectSavePlace(Integer.parseInt(loNo), m.memberNo);
		}
		return result;
	}

	@ResponseBody
	@GetMapping(value = "/savePlaceMember")
	public List savePlaceMember(@SessionAttribute(required = false) Member m) {
		List list = locationService.savePlaceMember(m.memberNo);
		return list;
	}
	
	//베스트리뷰
	@ResponseBody
	@GetMapping(value="/best")
	public List reviewBest() {
		List list = locationService.reviewBest(8, 1);
		return list;
	}
	
	// 맛집저장 불러오기
	@GetMapping(value = "/mySavePlace")
	public String selectMySavePlace(int memberNo, Model model) {
		// 맛집 저장 누르면 필요한 값들
		// 1. 맛집 상호명 / 2. 맛집 위치 / 3. 맛집 번호
		List list = locationService.selectMySavePlace(memberNo);
		model.addAttribute("list", list);
		return "member/mySavePlace";
	}
	
}
