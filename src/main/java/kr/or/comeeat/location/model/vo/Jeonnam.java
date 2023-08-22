package kr.or.comeeat.location.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Jeonnam {
	private String foodNm;	//foodNm 상호명
	private String lat;		//foodYpos 위도
	private String lng;		//foodXpos 경도
	private String addr;	//foodAddr 주소
	private String addr2;	//foodAddrDetail  상세주소
	private String thumb;	//foodMainImg 메인이미지
	private String food;	//foodMenuNm 음식분류(한식 중식..)
	private String tel;		//foodTel 전화번호
	private String info;	//foodSimpleinfo 음식점소개
}
