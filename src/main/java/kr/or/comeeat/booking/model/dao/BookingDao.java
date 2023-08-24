package kr.or.comeeat.booking.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.comeeat.booking.model.vo.Booking;

@Repository
public class BookingDao {
	@Autowired
	private JdbcTemplate jdbc;

	public int insertBooking(Booking b) {
		String query = "insert into booking values(booking_seq.nextval,?,?,?,?,?)";
		Object[] params = {b.getBookingTime(),b.getBookingDate(),b.getBookingTotalnum(),b.getMemberNo(), b.getMemberName()};
		int result = jdbc.update(query, params);
		return result;
	}
}
