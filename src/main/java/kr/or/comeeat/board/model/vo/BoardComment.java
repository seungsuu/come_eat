package kr.or.comeeat.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardComment {
	private int boardCommentNo;
	private String boardCommentWriter;
	private String boardCommentContent;
	private String boardCommentDate;
	private int boardRef;
	private int boardCommentRef;
}
