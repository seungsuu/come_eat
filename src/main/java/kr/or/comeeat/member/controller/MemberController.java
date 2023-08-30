package kr.or.comeeat.member.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.comeeat.EmailSender;
import kr.or.comeeat.booking.model.service.BookingService;
import kr.or.comeeat.location.model.vo.SavePlace;
import kr.or.comeeat.member.model.service.MemberService;
import kr.or.comeeat.member.model.vo.Member;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private EmailSender emailSender;
	
	//메일 테스트
	@GetMapping(value="/emailTest")
	public String emailTest() {
		return "member/emailTest";
	}
	

	//인증번호 메일 전송
	//비동기요청일시 @ResponseBody
	@ResponseBody
	@PostMapping(value="/auth")
	public String authMail(String email, Model model) {
		String authCode = emailSender.authMail(email);
		return authCode;
	}
	
	
	//로그인 페이지로 이동
	@GetMapping(value="login")
	public String login() {
		return "member/login";
	}
	
	//로그인
	@PostMapping(value="/signin")
	public String signIn(String signId, String signPw, Model model, HttpSession session) {
		Member m = memberService.selectOneMemeber(signId, signPw);
		if(m == null) {
			model.addAttribute("title","로그인 실패."); //title : 제목
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인하세요.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/member/login");
			
		}else {
			session.setAttribute("m", m);
			return "redirect:/";
		}
		return "common/msg";
	}
	
	//로그아웃
	@GetMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	//회원가입 페이지로 이동
	@GetMapping(value="/joinFrm")
	public String join() {
		return "member/joinFrm_copy";
	}
	
	//회원가입
	@PostMapping(value="/signup") 
		public String signup(Member member, Model model) {
			int result = memberService.insertMember(member);
			if(result>0)  {
				model.addAttribute("title", "회원가입 성공");
				model.addAttribute("msg", "신규 회원 가입을 축하합니다.");
				model.addAttribute("icon", "success");
				model.addAttribute("loc", "/");	
			}else {
				model.addAttribute("title", "회원가입 실패");
				model.addAttribute("msg", "필수 정보 입력란을 확인해주세요");
				model.addAttribute("icon", "error");
				model.addAttribute("loc", "/member/joinFrm_copy");
			}
			
			return "common/msg";
	}
	
	@ResponseBody
	@GetMapping(value="/ajaxCheckId")
	public String ajaxCheckId(String memberId) {
	Member m = memberService.selectOneMember(memberId);
	if(m == null) {
		return "0";
	}else {
		return "1";
	}
  }
	@GetMapping(value="/searchIdFrm")
	public String searchIdFrm() {
		return "member/searchIdFrm";
	}
	
	//아이디 찾기(이름,이메일주소)
	@PostMapping(value="/searchId")
	public String searchId(String memberName, String memberEmail, Model model) {
		Member member = memberService.selectMemberId(memberName, memberEmail);
		model.addAttribute("member", member);
		if(member == null) {
			model.addAttribute("msg","조회되는 아이디가 없습니다.");
			model.addAttribute("icon","error");
			model.addAttribute("loc", "/member/login");
			
		}else {
			model.addAttribute("msg","회원님의 아이디는 "+member.getMemberId()+" 입니다.");
			model.addAttribute("icon","success");
			model.addAttribute("loc", "/member/login");
		}
		return "common/msg";
		//System.out.println(member.getMemberId());
		//return "member/searchId";
		
	}
	//아이디 찾기2(이름,핸드폰번호)
		@PostMapping(value="/searchId2")
		public String searchId2(String memberName, String memberPhone, Model model) {
			Member member = memberService.selectMemberId2(memberName, memberPhone);
			model.addAttribute("member", member);
			if(member == null) {
				model.addAttribute("msg","조회되는 아이디가 없습니다.");
				model.addAttribute("icon","error");
				model.addAttribute("loc", "/member/login");
				System.out.println(member);
				
			}else {
				model.addAttribute("msg","회원님의 아이디는 "+member.getMemberId()+" 입니다.");
				model.addAttribute("icon","success");
				model.addAttribute("loc", "/member/login");
			}
			return "common/msg";
			
		}
	
	//마이페이지로 이동
	@GetMapping(value="/mypage")
	public String mypage() {
		return "member/mypage";
	}
	//마이페이지로 이동(관리자)
	@GetMapping(value="/adminMypage")
	public String adminMypage(Model model) {
		//model.addAttribute("select", 0);
		return "member/adminMypage";
		
	}
	
	
	//회원 수정
	@PostMapping(value="/update")
	public String update(Member member, Model model, @SessionAttribute(required = false)Member m) {
		int result = memberService.updateMember(member);
		if(result>0) {
			
			m.setMemberPw(member.getMemberPw());
			m.setMemberName(member.getMemberName());
			m.setMemberEmail(member.getMemberEmail());
			m.setMemberPhone(member.getMemberPhone());
			
			model.addAttribute("msg","회원정보가 수정 되었습니다.");
			model.addAttribute("icon","success");
			model.addAttribute("loc","/member/mypage");
		}
		else {
			model.addAttribute("msg","다시 시도해주세요.");
			model.addAttribute("icon","success");
			model.addAttribute("loc","/member/mypage");
		}
		return "common/msg";
	}
	//메일인증: 비밀번호 수정
	@PostMapping(value="/updatePw")
	public String updatePw(String memberEmail, String newPwRe, Model model) {
		int result = memberService.updateMember(memberEmail,newPwRe);
		if(result>0) {
			model.addAttribute("msg", "비밀번호가 변경되었습니다 다시 로그인해주세요.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/member/login");
		}else {
			model.addAttribute("msg", "실패.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/member/login");
			
		}
		return "common/msg";
	}
	
	
	//회원 탈퇴
	@GetMapping(value="/delete")
	public String delete(Model model, @SessionAttribute(required = false)Member m) {
		int result = memberService.deleteMember(m.getMemberNo());
		if(result>0) {
			model.addAttribute("msg","자동 로그아웃 됩니다.");
			model.addAttribute("icon","success");
			model.addAttribute("loc","/");
			
		}else {
			model.addAttribute("msg","다시 시도해주세요.");
			model.addAttribute("icon","error");
			model.addAttribute("loc","/member/mypage");
		}
		return "common/msg";
	}
	
	//전체회원 조회 : admin
	@GetMapping(value="/adminMember")
		public String admin(Model model) {
		 List list = memberService.selectAllMember();
		 model.addAttribute("list", list);
		 //model.addAttribute("select", 1);
		 return "member/adminMember";
		 
	}
	@GetMapping(value="/adminBook")
	public String adminBook(Model model) {
		List booking = bookingService.delSelectAllBooking();
		
		 model.addAttribute("b", booking);
		 
		 return "member/adminBook";
	}
	
	
	
	@GetMapping(value="/changeLevel")
	public String changeLevel(int memberNo,int memberLevel, Model model) {
		int result = memberService.changeLevel(memberNo,memberLevel);
		if(result>0) {
			return "redirect:/member/adminMember";
		}else {
			model.addAttribute("msg", "등급 변경에 실패했습니다.");
			model.addAttribute("icon","error");
			model.addAttribute("loc","member/adminMember");
			return "common/msg";
		}
	}
	
	@GetMapping(value="/checkedChangeLevel")
	public String checkedLevel(String no, String level, Model model) {
		boolean result = memberService.checkedChangeLevel(no,level);
		if(result) {
			return "redirect:/member/adminMember";	
		}else {
			model.addAttribute("msg", "등급변경에 실패했습니다.");
			model.addAttribute("icon","error");
			model.addAttribute("loc","member/adminMember");
			return "common/msg";
		}
	}
	
	@GetMapping(value="/checkedPayNo")
	public String checkedPayNo(String no, String level, Model model) {
		boolean result = bookingService.checkedPayNo(no,level);
		if(result) {
			return "redirect:/member/adminBook";	
		}else {
			model.addAttribute("msg", "결제상태 변경에 실패했습니다.");
			model.addAttribute("icon","error");
			model.addAttribute("loc","member/adminBook");
			return "common/msg";
		}
	}
	
	@GetMapping(value="/changePayNo")
	public String changePayNo(int delBookingNo ,int bookingPay, Model model) {
		int result = bookingService.changeDelLevel(delBookingNo,bookingPay);
		if(result>0) {
			return "redirect:/member/adminBook";
		}else {
			model.addAttribute("msg", "결제상태 변경에 실패했습니다.");
			model.addAttribute("icon","error");
			model.addAttribute("loc","member/adminBook");
			return "common/msg";
		}
	}
	
}
	

