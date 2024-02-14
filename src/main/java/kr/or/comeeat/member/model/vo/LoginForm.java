package kr.or.comeeat.member.model.vo;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginForm {
	@NotBlank
	private String memberId;
	@NotBlank
	private String memberPw;
}
