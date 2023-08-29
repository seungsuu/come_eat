package kr.or.comeeat.event.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EventFile {
	private int eFileNo;
	private String eFilePath;
	private int eventNo;
}
