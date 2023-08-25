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
		String query = "insert into booking values(booking_seq.nextval,?,?,?,?,?,?,?,1)";
		Object[] params = {b.getBookingTime(),b.getBookingDate(),b.getBookingTotalnum(),b.getMemberNo(), b.getMemberName(), b.getLoNo(), b.getLoTitle()};
		int result = jdbc.update(query, params);
		return result;
	}


	public List allBookingTime(String bookingDate) {
		String query = "select * from booking where booking_date=?";
		
		List list =jdbc.query(query, bookingRowMapper, bookingDate);
		
		System.out.println(bookingDate);
		return list;
	}


	public List selectAllBooking() {
		String query = "select * from booking";
		List Booking =jdbc.query(query, bookingRowMapper);
		return Booking;
	}



}
