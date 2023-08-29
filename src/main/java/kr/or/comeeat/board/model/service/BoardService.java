package kr.or.comeeat.board.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.comeeat.board.model.dao.BoardDao;
import kr.or.comeeat.board.model.vo.Board;
import kr.or.comeeat.board.model.vo.BoardComment;
import kr.or.comeeat.board.model.vo.BoardData;
import kr.or.comeeat.board.model.vo.BoardFile;
import kr.or.comeeat.board.model.vo.BoardViewData;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	public BoardData boardList(int pageNum) {
		//페이지당 게시물 수
		int page = 10;
		//마지막게시물번호
		int end = pageNum * page;
		//시작 게시물번호
		int start = end - page + 1;
		//게시물 조회해오기(해당 페이지 게시물 10개)
		List list = boardDao.boardList(start,end);
		
		
		//네비게이션
		//총 게시물 수 조회
		int totalList = boardDao.selectBoardTotal();
		//총 페이지 수 계산(10개씩 잘랐을때 나머지 있으면 1페이지 추가)
		int totalPage = totalList%page == 0 ? totalList/page : totalList/page+1;
		
		//한페이지에 보여줄 네비게이션 개수 지정
		int pageNaviSize = 5;
		int pageNo = ((pageNum-1)/pageNaviSize)*pageNaviSize + 1;
		
		//페이지 네비게이션 제작 시작
		String pageNavi = "<ul>";
		//이전버튼제작
		if(pageNo != 1){
			pageNavi += "<li>";
			pageNavi += "<a href='/board/list?pageNum="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>arrow_back_ios</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		//페이지 숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == pageNum) {
				//현재페이지와 요청페이지가 같은 경우(현재보고있는 페이지버튼에만 class로 백그라운드주기)
				pageNavi += "<li>";
				pageNavi += "<a class='active-page' href='/board/list?pageNum="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}else {
				//현재페이지와 요청페이지가 같지 않은 경우
				pageNavi += "<li>";
				pageNavi += "<a href='/board/list?pageNum="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				//총 페이지 수 이상의 페이지 버튼은 만들어지지 않게 하기
				break;
			}
		}
		//다음버튼제작
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a href='/board/list?pageNum="+(pageNo)+"'>";//이미 for문에서 pageNo++; 했기 때문에 +1안함
			pageNavi += "<span class='material-icons'>arrow_forward_ios</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>";
		
		//pageNavi와 게시물List 두가지를 넘겨줘야 하므로 Vo생성
		BoardData boardData = new BoardData(list,pageNavi);
		
		return boardData;
	}

	//글쓰기
	@Transactional
	public int insertBoard(Board b) {
		//baord insert
		int result = boardDao.insertBoard(b);
		return result;
	}

	//상세보기
	public BoardViewData boardView(int boardNo, int memberNo) {
		int result = boardDao.boardCountUp(boardNo);
		if(result>0) {
			Board b = boardDao.boardView(boardNo);
			List commentList = boardDao.selectCommentList(boardNo,memberNo);
			List reCommentList = boardDao.selectReCommentList(boardNo, memberNo);
			BoardViewData bvd = new BoardViewData(b, commentList, reCommentList);
			return bvd;
			}else {
				return null;
			}
	}
	

	//게시글삭제
	@Transactional
	public int deleteBoard(int boardNo) {
		int result = boardDao.deleteNotice(boardNo);
		return result;
	}

	//게시글 수정
	@Transactional
	public int boardUpdate(Board b) {
		int result = boardDao.boardUpdate(b);
		return result;
	}


	//조회수추가
	@Transactional
	public int boardCountUp(int boardNo) {
		int result = boardDao.boardCountUp(boardNo);
		return result;
	}

	@Transactional
	public int insertCommet(BoardComment bc) {
		int result = boardDao.insertComment(bc);
		return result;
	}

	public Board boardView2(int boardNo) {
		Board b = boardDao.boardView(boardNo);
		return b;
	}

	public int insertCommentLike(int boardCommentNo, int memberNo) {
		int result = boardDao.insertCommentLike(boardCommentNo, memberNo);
		int likeCount = boardDao.likeCount(boardCommentNo);
		return likeCount;
	}

	public int removeCommentLike(int boardCommentNo, int memberNo) {
		int result = boardDao.removeCommentLike(boardCommentNo, memberNo);
		int likeCount = boardDao.likeCount(boardCommentNo);
		return likeCount;
	}

	public int updateComment(BoardComment bc) {
		int result = boardDao.updateComment(bc);
		return result;
	}

	public int deleteComment(int boardCommentNo) {
		int result = boardDao.deleteComment(boardCommentNo);
		return result;
	}
}