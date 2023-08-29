package kr.or.comeeat.review.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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

	public List selectReviewList(int loNo) {
		String query = "select * from review where lo_no=? order by 1 desc";
		List list = jdbc.query(query, reviewRowMapper,loNo);
		return list;
	}

	public Review selectOneReview(int reviewNo) {
		String query = "select * from review where review_no=?";
		List list = jdbc.query(query, reviewRowMapper,reviewNo);
		return (Review)list.get(0);
	}

	public int updateReview(Review r) {
		String query = "update review set review_content=?, review_grade=?, review_filepath=? where review_no=?";
		Object[] params = {r.getReviewContent(),r.getReviewGrade(),r.getReviewFilepath(),r.getReviewNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int deleteReview(int reviewNo) {
		String query = "delete from review where review_no=?";
		Object[] params = {reviewNo};
		int result = jdbc.update(query,params);
		return result;
	}

}
