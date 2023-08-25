package kr.or.comeeat.review.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.comeeat.location.model.vo.Location;
import kr.or.comeeat.review.model.dao.ReviewDao;
import kr.or.comeeat.review.model.vo.Review;
import kr.or.comeeat.review.model.vo.detailReviewList;

@Service
public class ReviewService {

	@Autowired
	private ReviewDao reviewDao;

	public Location selectOneRestaurant(int loNo) {
		Location l = reviewDao.selectOneRestaurant(loNo);
		return l;
	}

	@Transactional
	public int insertReview(Review r) {
		int result = reviewDao.insertReview(r);
		return result;
	}

	public detailReviewList selectDetailRestaurant(int loNo) {
		Location l = reviewDao.selectOneRestaurant(loNo);
		List reviewList = reviewDao.selectReviewList(loNo);
		detailReviewList drl = new detailReviewList(l, reviewList);
		return drl;
	}
}
