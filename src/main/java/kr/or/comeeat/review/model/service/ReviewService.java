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

	//리뷰 쓸때 필요한 식당정보 가져오기
	public Location selectOneRestaurant(int loNo) {
		Location l = reviewDao.selectOneRestaurant(loNo);
		return l;
	}

	//리뷰 insert
	@Transactional
	public int insertReview(Review r) {
		int result = reviewDao.insertReview(r);
		return result;
	}

	//식당 정보와 리뷰 갯수 가져오기
	public detailReviewList selectDetailRestaurant(int loNo) {
		Location l = reviewDao.selectOneRestaurant(loNo);
		int totalCount = reviewDao.totalCount(loNo);
		detailReviewList drl = new detailReviewList(l, totalCount);
		return drl;
	}

	//수정을 위한 하나의 리뷰 가져오기
	public Review selectOneReview(int reviewNo) {
		Review r = reviewDao.selectOneReview(reviewNo);
		return r;
	}

	//리뷰 수정
	@Transactional
	public int updateReview(Review r) {
		int result = reviewDao.updateReview(r);
		return result;
	}

	//리뷰 삭제
	@Transactional
	public int deleteReview(int reviewNo) {
		int result = reviewDao.deleteReview(reviewNo);
		return result;
	}

	//리뷰 리스트 불러오기
	public List selectReviewList(int start, int end, int loNo) {
		List list = reviewDao.selectReviewList(start,end,loNo);
		return list;
	}
}
