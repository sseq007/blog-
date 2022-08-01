package blog.service;

import blog.domain.reply.ReplyDao;
import blog.domain.reply.dto.SaveReqDto;

public class ReplyService {

private ReplyDao replyDao;
	
	public ReplyService() {
		replyDao = new ReplyDao();
	}
	
	public int 댓글쓰기(SaveReqDto dto) {
		
		int result =replyDao.save(dto);
		
		return  result;
	}
}
