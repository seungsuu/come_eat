package kr.or.comeeat.magazine.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Magazine {
	private int magazineNo;
	private String magazineTitle;
	private String magazineContent;
	private String magazineDate;
	private int readCount;
	private int memberNo;
	private String magazineSubtitle;
	private List fileList;
	
}
