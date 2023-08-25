package kr.or.comeeat.review.model.vo;

import java.util.List;

import kr.or.comeeat.location.model.vo.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class detailReviewList {
	private Location l;
	private List reviewList;
	
}
