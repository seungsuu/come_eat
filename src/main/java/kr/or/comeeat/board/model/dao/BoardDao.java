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

	//게시물 조회해오기(10개) - 카테고리별
	public List boardListType(int start, int end, int boardType) {
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT * FROM BOARD WHERE BOARD_TYPE=? ORDER BY 1 DESC)N) WHERE RNUM BETWEEN ? AND ?";
		List list = jdbc.query(query,boardRowMapper,boardType,start,end);
		return list;
	}
	//천제 게시물 수 조회 - 카테고리별
	public int selectBoardTotalType(int boardType) {
		String query = "select Count(*) from board where board_type=?";
		int totalList = jdbc.queryForObject(query, Integer.class, boardType);
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
	public Board boardView(int boardNo) {
		String query ="select * from board where board_no=?";
		List list = jdbc.query(query, boardRowMapper, boardNo);
		return (Board)list.get(0);
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
		return result;
	}
	
	public int insertComment(BoardComment bc) {
		String query = "insert into board_comment values(board_comment_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		String boardCommentRef = bc.getBoardCommentRef()==0?null:String.valueOf(bc.getBoardCommentRef());
		Object[] params = {bc.getBoardCommentWriter(),bc.getBoardCommentContent(),bc.getBoardRef(),boardCommentRef};
		int result = jdbc.update(query,params);
		return result;
	}

	public List selectCommentList(int boardNo, int memberNo) {
		String query = "select bc.*,(select count(*) from board_comment_like where board_comment_no=bc.board_comment_no and member_no=?) as is_like,(select count(*) from board_comment_like where board_comment_no=bc.board_comment_no) as like_count from (select * from board_comment where board_ref=? and board_comment_ref is null order by 1)bc";
		List list = jdbc.query(query, boardCommentRowMapper, memberNo, boardNo);
		return list;
	}

	public List selectReCommentList(int boardNo, int memberNo) {
		String query = "select bc.*,(select count(*) from board_comment_like where board_comment_no=bc.board_comment_no and member_no=?) as is_like,(select count(*) from board_comment_like where board_comment_no=bc.board_comment_no) as like_count from (select * from board_comment where board_ref=? and board_comment_ref is not null order by 1)bc";
		List list = jdbc.query(query, boardCommentRowMapper, memberNo, boardNo);
		return list;
	}

	public int insertCommentLike(int boardCommentNo, int memberNo) {
		String query = "insert into board_comment_like values(?,?)";
		Object[] params = {boardCommentNo, memberNo};
		int result = jdbc.update(query, params);
		return result;
	}

	public int likeCount(int boardCommentNo) {
		String query ="select count(*) from board_comment_like where board_comment_no=?";
		int likeCount = jdbc.queryForObject(query, Integer.class, boardCommentNo);
		return likeCount;
	}

	public int removeCommentLike(int boardCommentNo, int memberNo) {
		String query = "delete from board_comment_like where board_comment_no=? and member_no=?";
		Object[] params = {boardCommentNo, memberNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public int updateComment(BoardComment bc) {
		String query="update board_comment set board_comment_content=? where board_comment_no=?";
		Object[] params = {bc.getBoardCommentContent(),bc.getBoardCommentNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int deleteComment(int boardCommentNo) {
		String query = "delete from board_comment where board_comment_no=?";
		Object[] params = {boardCommentNo};
		int result = jdbc.update(query, params);
		return result;
	}
}