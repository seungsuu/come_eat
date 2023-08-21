package kr.or.comeeat.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.comeeat.member.model.service.MemberService;
import kr.or.comeeat.member.model.vo.Member;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
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
			model.addAttribute("loc", "/"); // 회원가입 페이지로 넘어가도록 수정  
		}else {
			session.setAttribute("m", m);
			model.addAttribute("loc", "/");
		}
		return "common/msg";
	}
	
	//로그아웃
	@GetMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	//회원가입페이지로 이동
	@GetMapping(value="/signupFrm")
	public String signupFrm() {
		return "member/signupFrm";
	}
	
	//회원가입
	@PostMapping(value="/signup") 
		public String signup(Member member, Model model) {
			int result = memberService.insertMember(member);
			if(result>0) {
				model.addAttribute("loc", "/");
			}else {
				model.addAttribute("title", "회원가입 실패");
				model.addAttribute("msg", " * 필수항목란과 이용약관을 확인해주세요.");
				model.addAttribute("icon", "error");
				model.addAttribute("loc", "/");
			}
			return "common/msg";
	}
	
	//id중복검사 체크(회원가입/중복검사 버튼 눌렀을때)
	@PostMapping(value="/checkId")
	public String checkId(String checkId, Model model) {
		Member member = memberService.selectOneMemeber(checkId);
		model.addAttribute("chekId",checkId);
		if(member == null) {
			model.addAttribute("result", 0);
		}else {
			model.addAttribute("result",1);
		}
		return "member/checkId";
	}
	
	
	
	
	
	
	
	
}
