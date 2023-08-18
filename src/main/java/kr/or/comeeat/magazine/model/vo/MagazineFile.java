package kr.or.comeeat.magazine.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MagazineFile {
	private int mFileNo;
	private String mFilepath;
	private int magazineNo;
}
