package kr.or.comeeat.location.model.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.comeeat.location.model.model.service.LocationDao;

@Service
public class LocationService {
	@Autowired
	private LocationDao locationDao;
}
