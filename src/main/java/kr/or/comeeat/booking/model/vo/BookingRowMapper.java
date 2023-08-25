package kr.or.comeeat.booking.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BookingRowMapper implements RowMapper<Booking>{

	@Override
	public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
		Booking b = new Booking();
		b.setBookingDate(rs.getString("booking_date"));
		b.setBookingNo(rs.getInt("booking_no"));
		b.setBookingTime(rs.getInt("booking_time"));
		b.setBookingTotalnum(rs.getInt("booking_totalnum"));
		b.setMemberNo(rs.getInt("member_no"));
		b.setMemberName(rs.getString("member_name"));
		b.setLoNo(rs.getInt("lo_no"));
		b.setLoTitle(rs.getString("lo_title"));
		b.setBookingPay(rs.getInt("booking_pay"));
		return b;
	}
}
