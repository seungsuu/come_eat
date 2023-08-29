package kr.or.comeeat.location.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class LocationRowMapper implements RowMapper<Location> {

	@Override
	public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
		Location l = new Location();
		l.setLoAddr(rs.getString("lo_addr"));
		l.setLoCode(rs.getString("lo_code"));
		l.setLoInfo(rs.getString("lo_info"));
		l.setLoLat(rs.getString("lo_lat"));
		l.setLoLng(rs.getString("lo_lng"));
		l.setLoMenu(rs.getString("lo_menu"));
		l.setLoNo(rs.getInt("lo_no"));
		l.setLoTel(rs.getString("lo_tel"));
		l.setLoThumb(rs.getString("lo_thumb"));
		l.setLoTime(rs.getString("lo_time"));
		l.setLoTitle(rs.getString("lo_title"));
		l.setStarRate(rs.getDouble("star_rate"));
		return l;
	}

}
