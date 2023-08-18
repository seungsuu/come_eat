package kr.or.comeeat.magazine.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.comeeat.magazine.model.vo.MagazineFileRowMapper;
import kr.or.comeeat.magazine.model.vo.MagazineRowMapper;

@Repository
public class MagazineDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private MagazineRowMapper magazineRowMapper;
	@Autowired
	private MagazineFileRowMapper magazineFileRowMapper;
}
