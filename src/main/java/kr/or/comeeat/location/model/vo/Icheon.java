package kr.or.comeeat.location.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Icheon {
	private String title;		//title 상호명
	private String info;		//contents 설명
	private String lat;			//lat 위도
	private String lng;			//lng 경도
	private String tel;			//telno 전화번호
	private String addr;		//adres 주소
	private String filepath;	//filePath 파일경로
	private String filename;	//fileNm 파일이름
}
