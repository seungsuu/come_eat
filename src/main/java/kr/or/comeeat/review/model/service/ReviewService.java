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
		int totalCount = reviewDao.totalCount(loNo);
		detailReviewList drl = new detailReviewList(l, totalCount);
		return drl;
	}

	public Review selectOneReview(int reviewNo) {
		Review r = reviewDao.selectOneReview(reviewNo);
		return r;
	}

	@Transactional
	public int updateReview(Review r) {
		int result = reviewDao.updateReview(r);
		return result;
	}

	
	@Transactional
	public int deleteReview(int reviewNo) {
		int result = reviewDao.deleteReview(reviewNo);
		return result;
	}

	public List selectReviewList(int start, int end, int loNo) {
		List list = reviewDao.selectReviewList(start,end,loNo);
		return list;
	}
}
