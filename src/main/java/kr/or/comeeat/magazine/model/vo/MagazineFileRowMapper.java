package kr.or.comeeat.magazine.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MagazineFileRowMapper implements RowMapper<MagazineFile>{

	@Override
	public MagazineFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		MagazineFile file = new MagazineFile();
		file.setMagazineNo(rs.getInt("magazine_no"));
		file.setMFileNo(rs.getInt("m_file_no"));
		file.setMFilepath(rs.getString("m_filepath"));
		return file;
	}

}
