package kr.or.comeeat.location.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Busan {
	private String mainTitle;	//MAIN_TITLE 콘텐츠명(가게이름)
	private String info;		//ITEMCNTNTS 설명
	private String lat;			//LAT 위도
	private String lng;			//LNG 경도
	private String addr;		//ADDR1 주소
	private String tel;			//CNTCT_TEL 연락처
	private String time;		//USAGE_DAY_WEEK_AND_TIME 운영시간
	private String menu;		//RPRSNTV_MENU 대표메뉴
	private String thumb;		//MAIN_IMG_THUMB 썸네일
}
