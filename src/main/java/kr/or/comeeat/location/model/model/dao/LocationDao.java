package kr.or.comeeat.location.model.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kr.or.comeeat.location.model.vo.Location;
import kr.or.comeeat.location.model.vo.LocationRowMapper;
import kr.or.comeeat.location.model.vo.SavePlace;
import kr.or.comeeat.location.model.vo.SavePlaceRowMapper;

@Repository
public class LocationDao {
	private static final String Location = null;
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private LocationRowMapper locationRowMapper;
	@Autowired SavePlaceRowMapper savePlaceRowMapper;
	
	
	//맛집저장조회
	public int selectSavePlace(int loNo, int memberNo) {
		int result = 0;
		String query = "SELECT * FROM SAVEPLACE WHERE LO_NO = ? AND MEMBER_NO = ?";
		List list = jdbc.query(query,savePlaceRowMapper, loNo, memberNo);
		if(!list.isEmpty()) {
			result = 1;
		}
		return result;
	}
	
	//맛집저장하기
	public int insertSavePlace(int loNo, int memberNo) {
		String query = "INSERT INTO SAVEPLACE VALUES(SAVEPLACE_SEQ.NEXTVAL,?,?)";
		int result = jdbc.update(query, memberNo, loNo);
		return result;
	}

	//맛집저장취소하기
	public int deleteSavePlace(int loNo, int memberNo) {
		String query = "DELETE FROM SAVEPLACE WHERE LO_NO = ? AND MEMBER_NO = ?";
		int result = jdbc.update(query, loNo, memberNo);
		return result;
	}
		
	//검색조회
	public List searchList(String search, int end, int start) {
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, L.* FROM (SELECT * FROM LOCATION WHERE (LO_ADDR LIKE ?) OR (LO_TITLE LIKE ?) OR  (LO_INFO LIKE ?) OR (LO_MENU LIKE ?) ) L) WHERE RNUM BETWEEN ? AND ?";
		Object[] params = {"%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%",start,end};
		List bList = jdbc.query(query, locationRowMapper,params);
		return bList;
	}

	//네비게이션제작 - 전체게시물 수 조회
	public int searchTotal(String search) {
		String query = "SELECT COUNT(*) FROM (SELECT * FROM LOCATION WHERE (LO_ADDR LIKE ?) OR (LO_TITLE LIKE ?) OR  (LO_INFO LIKE ?) OR (LO_MENU LIKE ?))";
		Object[] params = {"%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%"};
		int result = jdbc.queryForObject(query,Integer.class,params);
		return result;
	}

	//맛집 저장하기
	public int locationInsert(ArrayList<Location> list) {
		String query = "INSERT INTO LOCATION VALUES(LOCATION_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
		int result = 0;
		for(Location location : list) {
			Object[] params = {location.getLoCode(),location.getLoTitle(),location.getLoInfo(),location.getLoLat(),location.getLoLng(),location.getLoAddr(),location.getLoTel(),location.getLoTime(),location.getLoMenu(),location.getLoThumb()};
			result = jdbc.update(query,params);
		}
		return result;
	}

	//맛집 가져오기
	public List locationSelect(String loCode, int end, int start) {
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, L.* FROM (SELECT * FROM LOCATION WHERE LO_CODE=?) L) WHERE RNUM BETWEEN ? AND ? ";
		Object[] params = {loCode,start,end};
		List bList = jdbc.query(query, locationRowMapper,params);
		return bList;
	}
	
	//네비게이션제작 - 전체게시물 수 조회
	public int selectTotal(String loCode) {
		String query = "SELECT COUNT(*) FROM LOCATION WHERE LO_CODE=?";
		int result = jdbc.queryForObject(query,Integer.class,loCode);
		return result;
	}

	//지도위치 전체출력
	public List locationMap(String loCode) {
		String query = "SELECT * FROM LOCATION WHERE LO_CODE=?";
		List list = jdbc.query(query, locationRowMapper,loCode);
		return list;
	}


	public List searchAroundPlace(String searchPlace) {
		String query = "select * from location where lo_addr like '%' || ? || '%'";
		List list = jdbc.query(query, locationRowMapper,searchPlace);
		return list;
	}


	
}
