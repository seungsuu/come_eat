package kr.or.comeeat.review.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.comeeat.location.model.vo.Location;
import kr.or.comeeat.review.model.dao.ReviewDao;

@Service
public class ReviewService {

	@Autowired
	private ReviewDao reviewDao;

	public Location selectOneRestaurant(int loNo) {
		Location l = reviewDao.selectOneRestaurant(loNo);
		return l;
	}
}
