package kr.or.comeeat.magazine.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MagazineRowMapper implements RowMapper<Magazine>{

	@Override
	public Magazine mapRow(ResultSet rs, int rowNum) throws SQLException {
		Magazine m = new Magazine();
		m.setFilepath(rs.getString("filepath"));
		m.setMagazineContent(rs.getString("magazine_content"));
		m.setMagazineDate(rs.getString("magazine_date"));
		m.setMagazineNo(rs.getInt("magazine_no"));
		m.setMagazineTitle(rs.getString("magazine_title"));
		m.setMemberNo(rs.getInt("member_no"));
		m.setReadCount(rs.getInt("read_count"));
		m.setMagazineSubtitle(rs.getString("magazine_subtitle"));
		return m;
	}
	
}
