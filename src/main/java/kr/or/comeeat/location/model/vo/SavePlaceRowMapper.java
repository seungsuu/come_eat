package kr.or.comeeat.location.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class SavePlaceRowMapper implements RowMapper<SavePlace>{

	@Override
	public SavePlace mapRow(ResultSet rs, int rowNum) throws SQLException {
		SavePlace sp = new SavePlace();
		sp.setLoNo(rs.getInt("lo_no"));
		sp.setMemberNo(rs.getInt("member_no"));
		sp.setSavePlaceNo(rs.getInt("save_place_no"));
		return sp;
	}
	
}
