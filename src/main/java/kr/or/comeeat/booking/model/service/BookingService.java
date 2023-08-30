package kr.or.comeeat.booking.model.service;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.comeeat.booking.model.dao.BookingDao;
import kr.or.comeeat.booking.model.vo.Booking;

@Service
public class BookingService {
	@Autowired
	private BookingDao bookingDao;
	
	@Transactional
	public int insertBooking(Booking b) {
		int result = bookingDao.insertBooking(b);
		return result;
	}

	

	public List allBookingTime(String bookingDate, String loTitle) {
		List list = bookingDao.allBookingTime(bookingDate, loTitle);
		
		return list;
	}



	public List selectAllBooking() {
		List Booking = bookingDao.selectAllBooking();
		return Booking;
	}



	public List delSelectAllBooking() {
		List Booking = bookingDao.delSelectAllBooking();
		return Booking;
	}


	@Transactional
	public boolean checkedPayNo(String no, String level) {
		StringTokenizer sT1 = new StringTokenizer(no,"/");
		StringTokenizer sT2 = new StringTokenizer(level,"/");
		boolean result = true;
		while(sT1.hasMoreTokens()) {
			int delBookingNo = Integer.parseInt(sT1.nextToken());
			int bookingPay = Integer.parseInt(sT2.nextToken());
			int changeResult = bookingDao.ChangeDelPay(delBookingNo, bookingPay);
			if(changeResult == 0) {
				result = false;
				break;//한번이라도 실패가 일어나면 while문 종료
			}
		}
		return result;
	}


	@Transactional
	public int changeDelLevel(int delBookingNo, int bookingPay) {
		int result = bookingDao.changeDelLevel(delBookingNo,bookingPay);
		return result;
	}
}
