package kr.or.comeeat.review.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Review {
	private int reviewNo;
	private String reviewWriter;
	private String reviewContent;
	private String reviewDate;
	private int reviewGrade;
	private String reviewFilepath;
	private int loNo;
}
