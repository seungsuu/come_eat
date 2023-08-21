package kr.or.comeeat.magazine.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.comeeat.magazine.model.dao.MagazineDao;
import kr.or.comeeat.magazine.model.vo.Magazine;

@Service
public class MagazineService {
	@Autowired
	private MagazineDao magazineDao;

	public int totalCount() {
		int totalCount = magazineDao.totalCount();
		return totalCount;
	}
	
	@Transactional
	public int insertMagazine(Magazine m) {
		int result = magazineDao.insertMagazein(m);
		return result;
	}

	








}

