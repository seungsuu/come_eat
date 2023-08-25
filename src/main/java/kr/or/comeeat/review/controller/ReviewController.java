package kr.or.comeeat.review.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.comeeat.FileUtil;
import kr.or.comeeat.location.model.vo.Location;
import kr.or.comeeat.review.model.service.ReviewService;
import kr.or.comeeat.review.model.vo.Review;
import kr.or.comeeat.review.model.vo.detailReviewList;

@Controller
@RequestMapping(value="/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private FileUtil fileUtil;
	@Value("${file.root}")
	public String root;
	

	
	@GetMapping(value="/detailRestaurant")
	public String detailRestaurant(int loNo,Model model) {
		detailReviewList drl = reviewService.selectDetailRestaurant(loNo);
		model.addAttribute("list", drl.getL());
		model.addAttribute("reviewList", drl.getReviewList());
		return "review/detailRestaurant";
	}
	
	@GetMapping(value="/reviewWriteFrm")
	public String reviewWriteFrm(int loNo,Model model) {
		Location l = reviewService.selectOneRestaurant(loNo);
		model.addAttribute("list", l);
		return "review/reviewWrite";
	}
	
	@PostMapping(value="/reviewWrite")
	public String reviewWrite(Review r, MultipartFile reviewFile, Model model) {
		// 작성자,내용,평점,식당번호 -> Review
		// 첨부파일 -> MultipartFile 한개이므로 배열로 받지 않고 변수명은 html에서 선언했던 파일name으로 가져와야함
		String savepath = root + "review/"; // ->업로드 될 파일 경로
		String filepath = fileUtil.getFilepath(savepath, reviewFile.getOriginalFilename());// ->원본파일 중복체크
		r.setReviewFilepath(filepath);// Review객체에 setting
		File upFile = new File(savepath + filepath);// 실제 파일 업로드
		try {
			reviewFile.transferTo(upFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int result = reviewService.insertReview(r);
		if (result > 0) {
			model.addAttribute("tilte", "작성완료");
			model.addAttribute("msg", "리뷰가 작성되었습니다.");
			model.addAttribute("icon", "success");
		} else {
			model.addAttribute("tilte", "작성실패");
			model.addAttribute("msg", "리뷰가 작성 중 문제가 발생했습니다.");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc", "/review/detailRestaurant?loNo=" + r.getLoNo());
		return "common/msg";
	}
}
