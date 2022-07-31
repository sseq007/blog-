package blog.service;

import java.util.List;

import blog.domain.board.Board;
import blog.domain.board.BoardDao;
import blog.domain.board.dto.DetailResDto;
import blog.domain.board.dto.SaveReqDto;

public class BoardService {

	private BoardDao boardDao;
	
	public BoardService() {
		boardDao = new BoardDao();
	}
	public DetailResDto 글상세보기(int id) {
		
		int result = boardDao.updateReadCount(id);
		if(result==1) {
			return boardDao.findById(id);	
		}else {
			return null;
		}
		
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
