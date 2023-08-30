package kr.or.comeeat.booking.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DelBooking {
	private int delBookingNo;
	private int bookingTime;
	private String bookingDate;
	private int bookingTotalnum;
	private String loTitle;
	private int bookingPay;
	private String memberName;
}
