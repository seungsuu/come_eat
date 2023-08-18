package kr.or.comeeat.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {
	private int boardNo;
	private int boardType;
	private String boardWriter;
	private String boardTitle;
	private String boardContent;
	private String regDate;
	private int boardCount;
}
