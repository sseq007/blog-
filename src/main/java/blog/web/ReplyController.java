package blog.web;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import blog.domain.board.dto.CommonRespDto;
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
			BufferedReader br = request.getReader();
			String reqData = br.readLine();
			Gson gson = new Gson();
			SaveReqDto dto = gson.fromJson(reqData, SaveReqDto.class);
			//System.out.println("dto: "+dto);
		
			int result = replyService.댓글쓰기(dto);
			
			CommonRespDto commonRespDto = new CommonRespDto<>();
			commonRespDto.setStatusCode(result);
			//commonRespDto.setData("댓글입력성공");
			
			String responseData = gson.toJson(commonRespDto);
			System.out.println("responseData : "+ responseData);
			Script.responseData(response, responseData);
		
		}
	}


}
