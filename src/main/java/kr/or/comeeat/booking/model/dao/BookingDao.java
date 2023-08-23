package kr.or.comeeat.booking.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookingDao {
	@Autowired
	private JdbcTemplate jdbc;
}
