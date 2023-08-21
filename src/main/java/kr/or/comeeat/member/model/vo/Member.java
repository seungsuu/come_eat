package kr.or.comeeat.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Member {
	public int memberNo;
	public String memberId;
	public String memberPw;
	public String memberName;
	public String memberEmail;
	public int memberLevel;
	public String memberPhone;
	public String enrollDate;
}
