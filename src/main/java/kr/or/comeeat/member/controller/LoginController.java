package kr.or.comeeat.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.comeeat.SessionConst;
import kr.or.comeeat.member.model.service.MemberService;
import kr.or.comeeat.member.model.vo.LoginForm;
import kr.or.comeeat.member.model.vo.Member;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final MemberService memberService;
	
	@GetMapping("/login")
	public String loginForm(@ModelAttribute LoginForm loginForm) {
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult, HttpSession session) {
		if(bindingResult.hasErrors()) {
			return "login/loginForm";
		}
		Member loginMember = memberService.selectOneMemeber(form.getMemberId(), form.getMemberPw()); 
		if(loginMember == null) {
			bindingResult.reject("loginFail","아이디와 비밀번호가 일치하지 않습니다.");
			return "login/loginForm";
		}
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember.getMemberId());
		return "redirect:/";
	} 
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
