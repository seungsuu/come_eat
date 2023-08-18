package kr.or.comeeat.review.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewFileRowMapper implements RowMapper<ReviewFile>{

	@Override
	public ReviewFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReviewFile rf = new ReviewFile();
		rf.setReviewFileNo(rs.getInt("review_file_no"));
		rf.setReviewFilepath(rs.getString("review_filepath"));
		rf.setReviewNo(rs.getInt("review_no"));
		return rf;
	}

}
