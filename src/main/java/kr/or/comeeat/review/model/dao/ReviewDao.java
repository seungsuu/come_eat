package kr.or.comeeat.review.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.comeeat.location.model.vo.Location;
import kr.or.comeeat.location.model.vo.LocationRowMapper;
import kr.or.comeeat.review.model.vo.ReviewRowMapper;

@Repository
public class ReviewDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private ReviewRowMapper reviewRowMapper;
	@Autowired
	private LocationRowMapper locationRowMapper;

	public Location selectOneRestaurant(String loTitle) {
		String query = "select * from location where lo_title=?";
		List list = jdbc.query(query,locationRowMapper ,loTitle);
		return (Location)list.get(0);
	}
}
