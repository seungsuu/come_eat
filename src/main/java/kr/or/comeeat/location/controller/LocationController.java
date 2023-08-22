package kr.or.comeeat.location.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.or.comeeat.location.model.model.sevice.LocationService;
import kr.or.comeeat.location.model.vo.Location;

@Controller
@RequestMapping(value="/location")
public class LocationController {
	@Autowired
	private LocationService locationService;
	
	@GetMapping(value="/list")
	public String locationList() {
		return "location/location";
	}
	
	//서울맛집
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping(value="/busan") public ArrayList<Location> seoul(String pageNo) {
	 * ArrayList<Location> list = new ArrayList<Location>(); return list;
	 * 
	 * }
	 */
	
	//부산맛집
	@GetMapping(value="/busan")
	public String busan(String pageNo, Model model) {
		String url = "http://apis.data.go.kr/6260000/FoodService/getFoodKr";
		String serviceKey = "2ip6zWd3dDjMLBB4jtDqTzBfPLU6UrsyBGtNSy78YNMk1QCbnA7bHFNHSo3wY3kev4HjybG5vgDaPLL4VjiRjw==";
		String numOfRows = "10";
		String resultType = "json";
		
		ArrayList<Location> list = new ArrayList<Location>();
		List bList = null;
			try {
				String result = Jsoup.connect(url)
								.data("serviceKey", serviceKey)
								.data("numOfRows",numOfRows)
								.data("pageNo",pageNo)
								.data("resultType",resultType)
								.ignoreContentType(true)
								.get()
								.text();
				JsonObject object = (JsonObject)JsonParser.parseString(result);
				JsonObject foodKr = object.get("getFoodKr").getAsJsonObject();
				JsonArray item = foodKr.get("item").getAsJsonArray();
				
				for(int i=0;i<item.size();i++) {
					JsonObject obj = item.get(i).getAsJsonObject();
					String loTitle = obj.get("MAIN_TITLE").getAsString();
					String loInfo = obj.get("ITEMCNTNTS").getAsString();
					String loLat = obj.get("LAT").getAsString();
					String loLng = obj.get("LNG").getAsString();
					String loAddr = obj.get("ADDR1").getAsString();
					String loTel = obj.get("CNTCT_TEL").getAsString();
					String loTime = obj.get("USAGE_DAY_WEEK_AND_TIME").getAsString();
					String loMenu = "";
					String loThumb = obj.get("MAIN_IMG_THUMB").getAsString();
					Location b = new Location();
					b.setLoAddr(loAddr);
					b.setLoCode("BUSAN");
					b.setLoInfo(loInfo);
					b.setLoLat(loLat);
					b.setLoLng(loLng);
					b.setLoMenu(loMenu);
					b.setLoTel(loTel);
					b.setLoThumb(loThumb);
					b.setLoTime(loTime);
					b.setLoTitle(loTitle);
					list.add(b);
				}
				
				bList = locationService.busan(list);
				model.addAttribute("list", bList);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "location/location";
	}
	
	//전남맛집
	@ResponseBody
	@GetMapping(value="/jeonnam")
	public ArrayList<Location> jeonnam(String pageNo) {
		String url = "http://apis.data.go.kr/6460000/jnFood";
		String serviceKey = "2ip6zWd3dDjMLBB4jtDqTzBfPLU6UrsyBGtNSy78YNMk1QCbnA7bHFNHSo3wY3kev4HjybG5vgDaPLL4VjiRjw==";
		String numOfRows = "10";
		String resultType = "json";
		
		ArrayList<Location> list = new ArrayList<Location>();
			try {
				String result = Jsoup.connect(url)
								.data("serviceKey", serviceKey)
								.data("numOfRows",numOfRows)
								.data("pageNo",pageNo)
								.data("resultType",resultType)
								.ignoreContentType(true)
								.get()
								.text();
				System.out.println(result);
				JsonObject object = (JsonObject)JsonParser.parseString(result);
				JsonObject foodKr = object.get("getFoodKr").getAsJsonObject();
				JsonArray item = foodKr.get("item").getAsJsonArray();
				
				for(int i=0;i<item.size();i++) {
					JsonObject obj = item.get(i).getAsJsonObject();
					String foodNm = obj.get("foodNm").getAsString();
					String lat = obj.get("foodYpos").getAsString();
					String lng = obj.get("foodXpos").getAsString();
					String addr = obj.get("foodAddr").getAsString();
					String addr2 = obj.get("foodAddrDetail").getAsString();
					String thumb = obj.get("foodMainImg").getAsString();
					String food = obj.get("foodMenuNm").getAsString();
					String tel = obj.get("foodTel").getAsString();
					String info = obj.get("foodSimpleinfo").getAsString();
					Location j = new Location();
					list.add(j);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}

}
