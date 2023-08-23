package kr.or.comeeat.booking.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.comeeat.booking.model.dao.BookingDao;

@Service
public class BookingService {
	@Autowired
	private BookingDao bookingDao;
}
