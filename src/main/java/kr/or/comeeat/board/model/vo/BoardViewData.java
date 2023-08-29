package kr.or.comeeat.board.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardViewData {
	private Board b;
	private List commentList;
	private List reCommentList;

}
