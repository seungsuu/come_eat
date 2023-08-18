package kr.or.comeeat.review.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewFile {
	private int reviewFileNo;
	private int reviewNo;
	private String reviewFilepath;
}
