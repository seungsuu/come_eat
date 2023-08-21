package kr.or.comeeat.member.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberRowMapper implements RowMapper<Member>  {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member m = new Member();
		m.setEnrollDate(rs.getString("enroll_date"));
		m.setMemberPhone(rs.getString("member_phone"));
		m.setMemberLevel(rs.getInt("member_level"));
		m.setMemberEmail(rs.getString("member_email"));
		m.setMemberName(rs.getString("member_name"));
		m.setMemberPw(rs.getString("member_pw"));
		m.setMemberId(rs.getString("member_id"));
		m.setMemberNo(rs.getInt("member_no"));
		return m;
	}
}
