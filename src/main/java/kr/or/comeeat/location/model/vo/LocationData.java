package kr.or.comeeat.location.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationData {
	private List list;
	private String navi;
	//소제목 변수 설정
	private String title;
}
