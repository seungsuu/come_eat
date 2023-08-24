package kr.or.comeeat.location.model.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kr.or.comeeat.location.model.vo.Location;
import kr.or.comeeat.location.model.vo.LocationRowMapper;

@Repository
public class LocationDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private LocationRowMapper locationRowMapper;
	
	//검색조회
	public List searchList(String search, int end, int start) {
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, L.* FROM (SELECT * FROM LOCATION WHERE (LO_ADDR LIKE '%'|| ? ||'%') OR (LO_TITLE LIKE '%'|| ? ||'%') OR  (LO_INFO '%'|| ? ||'%') OR (LO_MENU LIKE '%'|| ? ||'%') ) L) WHERE RNUM BETWEEN ? AND ?";
		Object[] params = {search,search,search,search,start,end};
		List bList = jdbc.query(query, locationRowMapper,params);
		return bList;
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
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, L.* FROM (SELECT * FROM LOCATION WHERE LO_CODE=?) L) WHERE RNUM BETWEEN ? AND ?";
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
