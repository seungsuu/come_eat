package kr.or.comeeat.booking.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.comeeat.booking.model.dao.BookingDao;
import kr.or.comeeat.booking.model.vo.Booking;

@Service
public class BookingService {
	@Autowired
	private BookingDao bookingDao;

	public int insertBooking(Booking b) {
		int result = bookingDao.insertBooking(b);
		return result;
	}
}
