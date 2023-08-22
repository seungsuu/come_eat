package kr.or.comeeat.location.model.model.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.comeeat.location.model.model.dao.LocationDao;

@Service
public class LocationService {
	@Autowired
	private LocationDao locationDao;
}
