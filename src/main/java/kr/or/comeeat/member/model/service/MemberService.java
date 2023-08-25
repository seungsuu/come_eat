package kr.or.comeeat.member.model.service;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.comeeat.location.model.vo.SavePlace;
import kr.or.comeeat.member.model.dao.MemberDao;
import kr.or.comeeat.member.model.vo.Member;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;

	//로그인
	public Member selectOneMemeber(String signId, String signPw) {
		Member m = memberDao.selectOneMember(signId, signPw);
		return m;
	}

	//회원가입
	@Transactional
	public int insertMember(Member member) {
		int result = memberDao.insertMember(member);
		return result;
	}
	
	//id중복검사
	public Member selectOneMember(String checkId) {
		Member m = memberDao.selectOneMember(checkId);
		return m;
	}
	//아이디찾기
	public Member selectMemberId(String memberName, String memberEmail) {
			Member member = memberDao.selectMemberId(memberName, memberEmail);
			return member;
	}
	//아이디찾기2
	public Member selectMemberId2(String memberName, String memberPhone) {
		Member member = memberDao.selectMemberId2(memberName, memberPhone);
		return member;
}

	@Transactional
	public int deleteMember(int memberNo) {
		int result  = memberDao.deleteMember(memberNo);
		return result;
	}
	
	@Transactional
	//회원정보 수정
	public int updateMember(Member member) {
		int result = memberDao.updateMember(member);
		return result;
	}

	public List selectAllMember() {
		List list = memberDao.selectAllMember();
		return list;
	}

	@Transactional
	public int changeLevel(int memberNo, int memberLevel) {
		int result = memberDao.changeLevel(memberNo,memberLevel);
		return result;
	}
	@Transactional
	public boolean checkedChangeLevel(String no, String level) {
		StringTokenizer sT1 = new StringTokenizer(no,"/");
		StringTokenizer sT2 = new StringTokenizer(level,"/");
		boolean result = true;
		while(sT1.hasMoreTokens()) {
			int memberNo = Integer.parseInt(sT1.nextToken());
			int memberLevel = Integer.parseInt(sT2.nextToken());
			int changeResult = memberDao.changeLevel(memberNo, memberLevel);
			if(changeResult == 0) {
				result = false;
				break;//한번이라도 실패가 일어나면 while문 종료
			}
		}
		return result;
	}

}
	

