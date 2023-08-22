package kr.or.comeeat.location.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.or.comeeat.location.model.model.dao.LocationService;
import kr.or.comeeat.location.model.vo.Busan;
import kr.or.comeeat.location.model.vo.Icheon;
import kr.or.comeeat.location.model.vo.Jeonnam;

@Controller
@RequestMapping(value="/location")
public class LocationController {
	@Autowired
	private LocationService locationService;
	
	@GetMapping(value="/list")
	public String locationList() {
		return "location/location";
	}
	
	//부산맛집
	@ResponseBody
	@GetMapping(value="/busan")
	public ArrayList<Busan> busan(String pageNo) {
		System.out.println(pageNo);
		String url = "http://apis.data.go.kr/6260000/FoodService/getFoodKr";
		String serviceKey = "2ip6zWd3dDjMLBB4jtDqTzBfPLU6UrsyBGtNSy78YNMk1QCbnA7bHFNHSo3wY3kev4HjybG5vgDaPLL4VjiRjw==";
		String numOfRows = "10";
		String resultType = "json";
		
		ArrayList<Busan> list = new ArrayList<Busan>();
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
					String mainTitle = obj.get("MAIN_TITLE").getAsString();
					String info = obj.get("ITEMCNTNTS").getAsString();
					String lat = obj.get("LAT").getAsString();
					String lng = obj.get("LNG").getAsString();
					String addr = obj.get("ADDR1").getAsString();
					String tel = obj.get("CNTCT_TEL").getAsString();
					String time = obj.get("USAGE_DAY_WEEK_AND_TIME").getAsString();
					String menu = obj.get("RPRSNTV_MENU").getAsString();
					String thumb = obj.get("MAIN_IMG_THUMB").getAsString();
					Busan b = new Busan(mainTitle,info,lat,lng,addr,tel,time,menu,thumb);
					list.add(b);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	//전남맛집
	@ResponseBody
	@GetMapping(value="/jeonnam")
	public ArrayList<Jeonnam> jeonnam(String pageNo) {
		System.out.println(pageNo);
		String url = "http://apis.data.go.kr/6460000/jnFood";
		String serviceKey = "2ip6zWd3dDjMLBB4jtDqTzBfPLU6UrsyBGtNSy78YNMk1QCbnA7bHFNHSo3wY3kev4HjybG5vgDaPLL4VjiRjw==";
		String numOfRows = "10";
		String resultType = "json";
		
		ArrayList<Jeonnam> list = new ArrayList<Jeonnam>();
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
					Jeonnam j = new Jeonnam(foodNm,lat,lng,addr,addr2,thumb,food,tel,info);
					list.add(j);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	@ResponseBody
	@GetMapping(value="/icheon")
	public void icheon(){
		String url = "https://www.icheon.go.kr/portal/rssBbsNtt.do";
		String bbsNo = "13";
		String type = "p";
		
		ArrayList<Icheon> list = new ArrayList<Icheon>();
		try {
			String result = Jsoup.connect(url)
					.data("bbsNo", bbsNo)
					.data("type",type)
					.ignoreContentType(true)
					.get()
					.text();
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
