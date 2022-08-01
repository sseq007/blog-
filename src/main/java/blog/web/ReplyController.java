package blog.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.domain.reply.dto.SaveReqDto;
import blog.service.ReplyService;
import blog.util.Script;

@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ReplyController() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		ReplyService replyService = new ReplyService();
		HttpSession session = request.getSession();
		if (cmd.equals("save")) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			int boardId = Integer.parseInt(request.getParameter("boardId"));
			String content = request.getParameter("content");
		
			SaveReqDto dto = new SaveReqDto();
			dto.setUserId(userId);
			dto.setBoardId(boardId);
			dto.setContent(content);
			
			int result = replyService.댓글쓰기(dto);
			
			if(result == 1) {
				response.sendRedirect("/blog/board?cmd=detail&id="+boardId);
			}else {
				Script.back(response,"댓글쓰기 실패");
			}
		}
	}


}
