package kr.or.comeeat.booking.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class DelBookingRowMapper implements RowMapper<DelBooking>{

	@Override
	public DelBooking mapRow(ResultSet rs, int rowNum) throws SQLException {
		DelBooking db = new DelBooking();
		db.setBookingDate(rs.getString("booking_date"));
		db.setBookingPay(rs.getInt("booking_pay"));
		db.setBookingTime(rs.getInt("booking_time"));
		db.setBookingTotalnum(rs.getInt("booking_totalnum"));
		db.setDelBookingNo(rs.getInt("del_booking_no"));
		db.setLoTitle(rs.getString("lo_title"));
		db.setMemberName(rs.getString("member_name"));
		return db;
	}
	
}
