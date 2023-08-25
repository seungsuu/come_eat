package kr.or.comeeat.booking.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.comeeat.booking.model.vo.Booking;
import kr.or.comeeat.booking.model.vo.BookingRowMapper;

@Repository
public class BookingDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private BookingRowMapper bookingRowMapper;

	public int insertBooking(Booking b) {
		String query = "insert into booking values(booking_seq.nextval,?,?,?,?,?)";
		Object[] params = {b.getBookingTime(),b.getBookingDate(),b.getBookingTotalnum(),b.getMemberNo(), b.getMemberName()};
		int result = jdbc.update(query, params);
		return result;
	}


	public List allBookingTime(String bookingDate) {
		String query = "select * from booking where booking_date=?";
		System.out.println("dao통과1");
		List list =jdbc.query(query, bookingRowMapper, bookingDate);
		System.out.println("dao통과2");
		System.out.println(bookingDate);
		return list;
	}
}
