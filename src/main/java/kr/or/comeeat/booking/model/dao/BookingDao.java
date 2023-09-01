package kr.or.comeeat.booking.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.comeeat.booking.model.vo.Booking;
import kr.or.comeeat.booking.model.vo.BookingRowMapper;
import kr.or.comeeat.booking.model.vo.DelBookingRowMapper;

@Repository
public class BookingDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private BookingRowMapper bookingRowMapper;
	@Autowired
	private DelBookingRowMapper delbookingRowMapper;

	public int insertBooking(Booking b) {
		String query = "insert into booking values(booking_seq.nextval,?,?,?,?,?,?,?,1)";
		Object[] params = {b.getBookingTime(),b.getBookingDate(),b.getBookingTotalnum(),b.getMemberNo(), b.getMemberName(), b.getLoNo(), b.getLoTitle()};
		int result = jdbc.update(query, params);
		return result;
	}


	public List allBookingTime(String bookingDate, String loTitle) {
		String query = "select * from booking where booking_date=? and lo_title=?";
		List list =jdbc.query(query, bookingRowMapper, bookingDate, loTitle);
		return list;
	}


	public List selectAllBooking() {
		String query = "select * from booking";
		List Booking =jdbc.query(query, bookingRowMapper);
		return Booking;
	}


	public List delSelectAllBooking() {
		String query = "select * from del_booking";
		List Booking =jdbc.query(query, delbookingRowMapper);
		return Booking;
	}


	public int ChangeDelPay(int delBookingNo, int bookingPay) {
		String query = "update del_booking set booking_pay = ? where del_booking_no = ?";
		Object[] params = {bookingPay,delBookingNo};
		int result = jdbc.update(query,params);
		return result;
	}


	public int changeDelLevel(int delBookingNo, int bookingPay) {
		String query = "update del_booking set booking_pay = ? where del_booking_no = ?";
		Object[] params = {bookingPay,delBookingNo};
		int result = jdbc.update(query,params);
		return result;
	}


	public List myBookInfo(int memberNo) {
		String query ="select * from booking where member_no=?";	
		List list = jdbc.query(query, bookingRowMapper, memberNo);
		return list;
	}


	public int deleteBooking(int bookingNo) {
		String query = "delete from booking where booking_no=?";
		Object[] params= {bookingNo};
		int result =jdbc.update(query,params);
		return result;
	}


	public int insertDelBooking(Booking b) {
		String query = "insert into del_booking values(del_booking_seq.nextval,?,?,?,?,1,?";
		Object[] params= {b.getBookingTime(),b.getBookingDate(),b.getBookingTotalnum(),b.getLoTitle(),b.getMemberName()};		
		int result =jdbc.update(query,params);
		return result;
	}


	public int insertDelBooking(String loTitle, int bookingTime, String bookingDate, int bookingTotalnum,String memberName) {
		String query = "insert into del_booking values(del_booking_seq.nextval,?,?,?,?,1,?)";
		Object[] params= {bookingTime, bookingDate, bookingTotalnum,loTitle,memberName};		
		int result =jdbc.update(query,params);
		return result;
		}


	public List bookingTime(int loNo, int memberNo) {
		String query = "select * from booking where member_no=? and lo_No=? order by 1;";
		List list = jdbc.query(query,bookingRowMapper, memberNo, loNo);
		return list;
	}
}
