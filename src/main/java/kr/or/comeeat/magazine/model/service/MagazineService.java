package kr.or.comeeat.magazine.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.comeeat.magazine.model.dao.MagazineDao;
import kr.or.comeeat.magazine.model.vo.Magazine;
import kr.or.comeeat.magazine.model.vo.MagazineFile;

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
		int result = magazineDao.insertMagazine(m);
		/*
		 * if(fileList != null) { int magazineNo = magazineDao.getMagazineNo();
		 * for(MagazineFile file : fileList) { file.setMagazineNo(magazineNo); result +=
		 * magazineDao.insertMagazineFile(file); } }
		 */
		return result;
	}

	@Transactional
	public Magazine selectOneMagazine(int magazineNo) {
		int result = magazineDao.updateReadCount(magazineNo);
		Magazine m = new Magazine();
		if (result > 0) {
			m = magazineDao.selectOneMagazine(magazineNo);
		}
		return m;

	}

	public List selectMagazineList(int start, int end) {
		List magazineList = magazineDao.selectMagazineList(start, end);
		return magazineList;
	}

	@Transactional
	public List deleteMagazine(int magazineNo) {
		List list = magazineDao.selectMagazineFile(magazineNo);
		int result = magazineDao.deleteMagazine(magazineNo);
		if (result == 0) {
			return null;
		}
		return list;

	}

	public Magazine getMagazine(int magazineNo) {
		Magazine m = magazineDao.selectOneMagazine(magazineNo);
		List fileList = magazineDao.selectMagazineFile(magazineNo);
		m.setFileList(fileList);
		return m;
	}

	@Transactional
	public int updateMagazine(Magazine m) {
		int result = magazineDao.updateMagazine(m);
		System.out.println(result);
		return result;
	}
}
