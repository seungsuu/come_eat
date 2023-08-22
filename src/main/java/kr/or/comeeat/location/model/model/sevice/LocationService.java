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
	public List busan(ArrayList<Location> list) {
		List bList = locationDao.busanSelect();
		if(bList.isEmpty()) {			
			int result = locationDao.busanInsert(list);
			List bList2 = locationDao.busanSelect();
			return bList2;
		}
		return bList;
	}
}
