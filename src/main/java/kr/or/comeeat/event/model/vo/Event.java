package kr.or.comeeat.event.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class Event {
	private int eventNo;
	private String eventTitle;
	private String eventContent;
	private int memberNo;
	private String filepath;
	private String eventSubtitle;
	private String eventDate;
	private String regDate;
	private int close;
}
