package kr.or.comeeat.location.model.model.sevice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.or.comeeat.board.model.vo.BoardData;
import kr.or.comeeat.location.model.model.dao.LocationDao;
import kr.or.comeeat.location.model.vo.Location;
import kr.or.comeeat.location.model.vo.LocationData;

@Service
public class LocationService {
	@Autowired
	private LocationDao locationDao;
	
	//검색조회
	public LocationData searchList(String pageNo, String search) {
		int num = 6; //출력개수
		int end = num * Integer.parseInt(pageNo);//끝번호
		int start = end - num + 1;//시작번호
		
		//DB select
		List list = locationDao.searchList(search,end,start);
		//네비게이션
		String pageNavi = searchNavi(num,Integer.parseInt(pageNo),loCode,lo);
		//소제목설정
		String title = "부산맛집";
		//리스트+네비 data 묶기
		LocationData locationData = new LocationData(bList, pageNavi,title);
		
		return list;
	}


	//지역별 맛집리스트 가져오기
	public LocationData foodList(String pageNo, String lo) {
		
		LocationData locationdata = null;
		switch(lo) {
		case "su":
			break;
		case "kkd":
			break;
		case "ic":
			break;
		case "bs":
			locationdata =	busan(pageNo,lo);
			break;
		case "dg":
			break;
		case "kh":
			break;
		case "jj":  
			break;
		case "jn":
			locationdata = jeonnam(pageNo,lo);
			break;
		case "cb":
			break;
		case "ph":
			break;
		case "yj":
			break;
		}
		return locationdata;
	}
	
	//부산맛집
	@Transactional
	public LocationData busan(String pageNo, String lo) {
		int num = 6; //출력개수
		int end = num * Integer.parseInt(pageNo);//끝번호
		int start = end - num + 1;//시작번호
		String loCode = "BUSAN";//조회할 지역코드(부산)
		
		//DB select
		List bList = locationDao.locationSelect(loCode,end,start);
		
		if(bList.isEmpty()) {
			//공공데이터 파싱
			String url = "http://apis.data.go.kr/6260000/FoodService/getFoodKr";
			String serviceKey = "2ip6zWd3dDjMLBB4jtDqTzBfPLU6UrsyBGtNSy78YNMk1QCbnA7bHFNHSo3wY3kev4HjybG5vgDaPLL4VjiRjw==";
			String numOfRows = "200";
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
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			loCode = list.get(0).getLoCode();
			
			//최초 DB insert
			int result = locationDao.locationInsert(list);
			if(result>0) {				
				//DB select
				bList = locationDao.locationSelect(loCode,end,start);
			}
		}
		
		//네비게이션
		String pageNavi = navi(num,Integer.parseInt(pageNo),loCode,lo);
		//소제목설정
		String title = "부산맛집";
		//리스트+네비 data 묶기
		LocationData locationData = new LocationData(bList, pageNavi,title);
		return locationData;
	}
	
	@Transactional
	public LocationData jeonnam(String pageNo, String lo) {
		int num = 6; //출력개수
		int end = num * Integer.parseInt(pageNo);//끝번호
		int start = end - num + 1;//시작번호
		String loCode = "JEONNAM";//조회할 지역코드(전남)
		
		//DB select
		List jList = locationDao.locationSelect(loCode, end, start);
		
		if(jList.isEmpty()) {
			String url = "http://apis.data.go.kr/6460000/jnFood/getNdfoodList";
			String serviceKey = "8Tqm1xKZLdcdZFYKFmnzlGdwfr9qM8WJ42R%2BAOkOqjI3%2FejSUC0n8KLr7%2BqI67wMkv98ZND0DSUZUjQmf7%2FjYA%3D%3D";
			String pageSize = "596";
			String startPage = "1";
			ArrayList<Location> list = new ArrayList<Location>();
			try {
				Document doc = Jsoup.connect(url)
								.data("serviceKey", serviceKey)
								.data("pageSize", pageSize)
								.data("startPage", startPage)
								.ignoreContentType(true)
								.get()
								;
				Elements elements = doc.select("list");
				for(Element ele : elements) {
					String loTitle = ele.select("foodNm").text();
					String loLat = ele.select("foodYpos").text();
					String loLng = ele.select("foodXpos").text();
					String loAddr = ele.select("foodAddr").text()+ele.select("foodAddrDetail").text();
					String loTel = ele.select("foodTel").text();
					String loThumb = ele.select("foodMainImg").text();
					String loMenu = ele.select("foodMenuNm").text();
					Location j = new Location();
					j.setLoAddr(loAddr);
					j.setLoCode("JEONNAM");
					j.setLoInfo("");
					j.setLoLat(loLat);
					j.setLoLng(loLng);
					j.setLoMenu(loMenu);
					j.setLoTel(loTel);
					j.setLoThumb(loThumb);
					j.setLoTime("09:00 - 22:00");
					j.setLoTitle(loTitle);
					
					if(!(j.getLoLat().isEmpty()||j.getLoLng().isEmpty()||j.getLoAddr().contains("NODATA"))) {
						list.add(j);
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loCode = list.get(0).getLoCode();
			
			//최초 db insert
			int result = locationDao.locationInsert(list);
			if(result>0) {
				jList = locationDao.locationSelect(loCode, end, start);
			}
		}
		//네비게이션
		String pageNavi = navi(num, Integer.parseInt(pageNo), loCode, lo);
		// 소제목설정
		String title = "전남맛집";
		// 리스트+네비 data 묶기
		LocationData locationData = new LocationData(jList, pageNavi, title);
		return locationData;
	}

	//네비게이션 제작 메소드
	public String navi(int num, int pageNo, String loCode, String lo) {
		//네비게이션
		//총 게시물 수 조회
		int total = locationDao.selectTotal(loCode);
		
		//총 페이지 수
		int totalPage = total%num == 0 ? total/num : total/num+1;
		
		//한페이지에 보여줄 네비게이션 개수 지정
		int pageNaviSize = 5;
		int pageNum = ((pageNo-1)/pageNaviSize)*pageNaviSize + 1;
		
		//페이지 네비게이션 제작 시작
		String pageNavi = "<ul>";
		//이전버튼제작
		if(pageNum != 1){
			pageNavi += "<li>";
			pageNavi += "<a href='/location/foodList?pageNo="+(pageNum-1)+"&lo="+lo+"'>";
			pageNavi += "<span class='material-icons'>arrow_back_ios</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		//페이지 숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == pageNum) {
				//현재페이지와 요청페이지가 같은 경우(현재보고있는 페이지버튼에만 class로 백그라운드주기)
				pageNavi += "<li>";
				pageNavi += "<a class='active-page' href='/location/foodList?pageNo="+(pageNum)+"&lo="+lo+"'>";
				pageNavi += pageNum;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}else {
				//현재페이지와 요청페이지가 같지 않은 경우
				pageNavi += "<li>";
				pageNavi += "<a href='/location/foodList?pageNo="+(pageNum)+"&lo="+lo+"'>";
				pageNavi += pageNum;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}
			pageNum++;
			if(pageNum>totalPage) {
				//총 페이지 수 이상의 페이지 버튼은 만들어지지 않게 하기
				break;
			}
		}
		//다음버튼제작
		if(pageNum <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a href='/location/foodList?pageNo="+(pageNum)+"&lo="+lo+"'>";//이미 for문에서 pageNo++; 했기 때문에 +1안함
			pageNavi += "<span class='material-icons'>arrow_forward_ios</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>";
		
		return pageNavi;
	}
	
	//지도위치 전체출력
	public List locationMap(String loCode) {
		List list = locationDao.locationMap(loCode);
		return list;
	}
	
	public List searchAroundPlace(String searchPlace) {
		List list = locationDao.searchAroundPlace(searchPlace);
		return list;
	}

	
}
