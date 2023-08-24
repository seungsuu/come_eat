package kr.or.comeeat.member.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.comeeat.member.model.vo.Member;
import kr.or.comeeat.member.model.vo.MemberRowMapper;

@Repository
public class MemberDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private MemberRowMapper memberRowMapper;
	
	public Member selectOneMember(String signId, String signPw) {
		String query = "select * from member where member_id=? and member_pw=?";
		List list = jdbc.query(query, memberRowMapper, signId, signPw);
		if(list.isEmpty()) {
			return null; 
		}
		return (Member)list.get(0);
	}

	//회원가입
	public int insertMember(Member member) {
		String query ="insert into member values(member_seq.nextval,?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
		Object[] params = {member.getMemberId(),member.getMemberPw(),member.getMemberName(),member.getMemberEmail(),2,member.getMemberPhone()};
		int result = jdbc.update(query,params);
		return result;
	}
	
	//id중복검사
	public Member selectOneMember(String checkId) {
		String query = "select * from member where member_id=?";
		List list = jdbc.query(query, memberRowMapper,checkId);
		if(list.isEmpty()) {
			return null;
		}
		return (Member)list.get(0);
	}

	public Member selectMemberId(String memberName, String memberEmail) {
		String query = "select * from member where member_name=? and member_email=?";
		List list = jdbc.query(query, memberRowMapper,memberName,memberEmail);
		if(list.isEmpty()) {
			return null;
		}
		return (Member)list.get(0);
	}
	
	//회원 삭제
	public int deleteMember(int memberNo) {
		String query = "delete from member where member_no=?";
		int result = jdbc.update(query,memberNo);
		return result;
	}

	//회원정보 수정
	public int updateMember(Member member) {
		String query = "update member set member_pw=?,member_name=?,member_email=?,member_phone=? where member_id=?";
		Object[] params = {member.getMemberPw(), member.getMemberName(), member.getMemberEmail(), member.getMemberPhone(),member.getMemberId()};
		int result = jdbc.update(query,params);
		return result;
	}

	public List selectAllMember() {
		String query = "select * from member order by 1";
		List list = jdbc.query(query, memberRowMapper);
		return list;
	}

}
