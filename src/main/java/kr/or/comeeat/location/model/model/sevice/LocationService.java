package kr.or.comeeat.location.model.model.sevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.comeeat.location.model.model.dao.LocationDao;
import kr.or.comeeat.location.model.vo.Location;

@Service
public class LocationService {
	@Autowired
	private LocationDao locationDao;

	//부산맛집 DB insert
	@Transactional
	public List busan(ArrayList<Location> list, String pageNo) {
		String loCode = list.get(0).getLoCode();
		int num = 6; //출력개수
		int end = num * Integer.parseInt(pageNo);//끝번호
		int start = end - num + 1;//시작번호
		List bList = locationDao.busanSelect(loCode,end,start);
		if(bList.isEmpty()) {			
			int result = locationDao.busanInsert(list);
			List bList2 = locationDao.busanSelect(loCode,end,start);
			return bList2;
		}
		return bList;
	}

	public List searchAroundPlace(String searchPlace) {
		List list = locationDao.searchAroundPlace(searchPlace);
		return list;
	}
}
