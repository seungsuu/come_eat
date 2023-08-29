package kr.or.comeeat.board.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kr.or.comeeat.board.model.vo.Board;
import kr.or.comeeat.board.model.vo.BoardComment;
import kr.or.comeeat.board.model.vo.BoardCommentRowMapper;
import kr.or.comeeat.board.model.vo.BoardFile;
import kr.or.comeeat.board.model.vo.BoardRowMapper;

@Component
public class BoardDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private BoardRowMapper boardRowMapper;
	@Autowired
	private BoardCommentRowMapper boardCommentRowMapper;
	
	
	//게시물 조회해오기(10개)
	public List boardList(int start, int end) {
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT * FROM BOARD ORDER BY 1 DESC)N) WHERE RNUM BETWEEN ? AND ?";
		List list = jdbc.query(query,boardRowMapper,start,end);
		return list;
	}

	//천제 게시물 수 조회
	public int selectBoardTotal() {
		String query = "select Count(*) from board";
		int totalList = jdbc.queryForObject(query, Integer.class);
		return totalList;
	}

	//글쓰기 - board insert
	public int insertBoard(Board b) {
		String query = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL,?,?,?,?,TO_CHAR(SYSDATE,'YYYY-MM-DD'),DEFAULT)";
		Object[] params = {b.getBoardType(),b.getBoardWriter(),b.getBoardTitle(),b.getBoardContent()};
		int result = jdbc.update(query,params);
		return result;
	}

	//상세보기
	public List boardView(int boardNo) {
		String query ="select * from board where board_no=?";
		List list = jdbc.query(query, boardRowMapper, boardNo);
		return list;
	}

	//게시글삭제
	public int deleteNotice(int boardNo) {
		String query = "delete from board where board_no=?";
		int result = jdbc.update(query,boardNo);
		return result;
	}

	//게시글수정
	public int boardUpdate(Board b) {
		String query = "update board set board_title=?, board_content=? where board_no=?";
		Object[] params = {b.getBoardTitle(),b.getBoardContent(),b.getBoardNo()};
		int result = jdbc.update(query,params);
		return result;
	}


	//조회수추가
	public int boardCountUp(int boardNo) {
		String query = "update board set board_count=board_count+1 where board_no=?";
		int result = jdbc.update(query,boardNo);

	}
	public int insertComment(BoardComment bc) {
		String query = "insert into board_comment values(board_comment_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		String boardCommentRef = bc.getBoardCommentRef()==0?null:String.valueOf(bc.getBoardCommentRef());
		Object[] params = {bc.getBoardCommentWriter(),bc.getBoardCommentContent(),bc.getBoardRef(),boardCommentRef};
		int result = jdbc.update(query,params);
		return result;
	}
