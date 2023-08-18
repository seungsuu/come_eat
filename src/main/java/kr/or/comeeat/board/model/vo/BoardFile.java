package kr.or.comeeat.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardFile {
	private int boardFileNo;
	private int boardNo;
	private String boardFilepath;
}
