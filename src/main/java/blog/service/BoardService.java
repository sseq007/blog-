package blog.service;

import java.util.List;

import blog.domain.board.Board;
import blog.domain.board.BoardDao;
import blog.domain.board.dto.SaveReqDto;

public class BoardService {

	private BoardDao boardDao;
	
	public BoardService() {
		boardDao = new BoardDao();
	}
	
	public int 글쓰기(SaveReqDto dto) {
		
		int result =boardDao.save(dto);
		
		return  result;
	}
	
	public List<Board> 글목록보기(int page){
		return boardDao.findAll(page);
	}
	
	
	public int 글개수() {
		return boardDao.count();
	}
	
}
