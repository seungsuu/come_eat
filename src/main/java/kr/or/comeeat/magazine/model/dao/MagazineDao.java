package kr.or.comeeat.magazine.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



import kr.or.comeeat.magazine.model.vo.Magazine;
import kr.or.comeeat.magazine.model.vo.MagazineFile;
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
		String query ="insert into magazine values(magazine_seq.nextval,?,?,to_char(sysdate, 'yyyy-mm-dd'),0,?,?";
		Object[] params = {m.getMagazineTitle(),m.getMagazineContent(),m.getMemberNo(),m.getMagazineSubtitle()};
		int result = jdbc.update(query, params);
		return result;
	}


	public int updateReadCount(int magazineNo) {
		String query = "update magazine set read_count = read_count+1 where magazine_no=?";
		Object[] params = {magazineNo};
		int result = jdbc.update(query, params);
		return result;
	}


	public Magazine selectOneMagazine(int magazineNo) {
		String query = "select * from magazine where magazine_no=?";
		List list = jdbc.query(query, magazineRowMapper, magazineNo);
		return (Magazine)list.get(0);
	}


	public List selectMagazineFile(int magazineNo) {
		String query = "magazine * from magazine_file where magazine_no=?";
		List list = jdbc.query(query, magazineFileRowMapper, magazineNo);
		return list;
	}


	public int getMagazineNo() {
		String query = "select max(magazine_No) from magazine";
		int magazineNo = jdbc.queryForObject(query, Integer.class);
		return magazineNo;
	}


	public int insertMagazineFile(MagazineFile file) {
		String query = "insert into magazine_file values(magazine_file_seq.nextval,?,?)";
		Object[] params = {file.getMFilepath(),getMagazineNo()};
		int result = jdbc.update(query, params);
		return result;
	}
	
	
	
}
