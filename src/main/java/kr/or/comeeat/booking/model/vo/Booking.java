package kr.or.comeeat.booking.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Booking {
	private int bookingNo;
	private int bookingTime;
	private String bookingDate;
	private int bookingTotalnum;
	private int memberNo;
}
