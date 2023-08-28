package kr.or.comeeat.event.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Event {
	private int eventNo;
	private String eventTitle;
	private String eventContent;
	private String regDate;
	private int readCount;
	private String eventWriter;
}
