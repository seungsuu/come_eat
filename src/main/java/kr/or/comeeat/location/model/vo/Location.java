package kr.or.comeeat.location.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Location {
	private int loNo;			//식당번호
	private String loCode;		//지역코드
	private String loTitle;		//가게이름
	private String loInfo;		//설명
	private String loLat;		//위도
	private String loLng;		//경도
	private String loAddr;		//주소
	private String loTel;		//연락처
	private String loTime;		//운영시간
	private String loMenu;		//음식카테고리
	private String loThumb;		//썸네일
	private double starRate;
}
