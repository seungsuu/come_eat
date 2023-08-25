package kr.or.comeeat.review.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.comeeat.location.model.vo.Location;
import kr.or.comeeat.location.model.vo.LocationRowMapper;
import kr.or.comeeat.review.model.vo.Review;
import kr.or.comeeat.review.model.vo.ReviewRowMapper;

@Repository
public class ReviewDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private ReviewRowMapper reviewRowMapper;
	@Autowired
	private LocationRowMapper locationRowMapper;

	public Location selectOneRestaurant(int loNo) {
		String query = "select * from location where lo_no=?";
		List list = jdbc.query(query,locationRowMapper ,loNo);
		return (Location)list.get(0);
	}

	public int insertReview(Review r) {
		String query = "insert into review values(review_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?,?)";
		Object[] params = {r.getReviewWriter(),r.getReviewContent(),r.getReviewGrade(),r.getReviewFilepath(),r.getLoNo()};
		int result = jdbc.update(query,params);
		return result;
	}

}
