package kr.or.comeeat.magazine.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.comeeat.magazine.model.vo.Magazine;
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
	
	
	public int totalCount() {
		String query = "select count(*) from magazine";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}


	public int insertMagazein(Magazine m) {
		String query ="insert into magazine values(magazine_seq.nextval,?,?,to_char(sysdate, 'yyyy-mm-dd'),?,0,?,?";
		Object[] params = {m.getMagazineTitle(),m.getMagazineContent(),m.getFilepath(),m.getMemberNo(),m.getMagazineSubtitle()};
		int result = jdbc.update(query, params);
		return result;
	}
}
