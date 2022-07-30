package blog.service;

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
	
	
}
