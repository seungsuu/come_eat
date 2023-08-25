package kr.or.comeeat.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SavePlace {
	private int savePlaceNo;
	private int memberNo;
	private int loNo;
}
