package blog.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import blog.domain.board.Board;
import blog.domain.board.dto.CommonRespDto;
import blog.domain.board.dto.DetailResDto;
import blog.domain.board.dto.SaveReqDto;
import blog.domain.board.dto.UpdateReqDto;
import blog.domain.user.User;
import blog.service.BoardService;
import blog.util.Script;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BoardController() {
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
		BoardService boardService = new BoardService();
		HttpSession session = request.getSession();
		if(cmd.equals("saveForm")) {
			User principal = (User) session.getAttribute("principal");
			if(principal != null) {
				RequestDispatcher dis =
						request.getRequestDispatcher("board/saveForm.jsp");
					dis.forward(request,response);
		}
			else {
				RequestDispatcher dis =
						request.getRequestDispatcher("user/loginForm.jsp");
					dis.forward(request,response);
			}
		}else if (cmd.equals("save")) { 
			int userId = Integer.parseInt(request.getParameter("userId")); // 10진수 integer형으로 변한
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			SaveReqDto dto = new SaveReqDto();
			dto.setUserId(userId);
			dto.setTitle(title);
			dto.setContent(content);
			int result = boardService.글쓰기(dto);
			if (result == 1) {
				response.sendRedirect("index.jsp");
			} else {
				Script.back(response, "글쓰기 실패");
			}
		} else if (cmd.equals("list")) {
			int page = Integer.parseInt(request.getParameter("page"));
			List<Board> boards = boardService.글목록보기(page);
			request.setAttribute("boards", boards);
			int boardCount = boardService.글개수();
			int lastPage = (boardCount - 1)/4;
			double currentPosition = (double)page/(lastPage)*100;
	
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("currentPosition", currentPosition);
			RequestDispatcher dis =
					request.getRequestDispatcher("board/list.jsp");
				dis.forward(request,response);
		}else if(cmd.equals("detail")) {
			int id = Integer.parseInt(request.getParameter("id"));
			DetailResDto dto = boardService.글상세보기(id); //board테이블 + user테이블 = 조인된 데이터 필요
			if(dto == null) {
				Script.back(response, "상세보기에 실패하였습니다.");
			}else {
				request.setAttribute("dto", dto);
				//System.out.println("DetailResDto : "+dto);
				RequestDispatcher dis =
						request.getRequestDispatcher("board/detail.jsp");
					dis.forward(request,response);
			}
		}else if(cmd.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Gson gson = new Gson();
			//System.out.println(data);
			CommonRespDto<String> commonRespDto = new CommonRespDto<>();
			int result = boardService.글삭제(id);
			commonRespDto.setStatusCode(result);
			commonRespDto.setData("성공");
			String respData = gson.toJson(commonRespDto);
			
			//System.out.println(respDto);
			PrintWriter out = response.getWriter();
			out.print(respData);
			out.flush();
		}else if (cmd.equals("updateForm")) {
			int id = Integer.parseInt(request.getParameter("id"));
			DetailResDto dto = boardService.글상세보기(id);
			request.setAttribute("dto", dto);
			RequestDispatcher dis =
					request.getRequestDispatcher("board/updateForm.jsp");
				dis.forward(request,response);
		}else if (cmd.equals("update")) {
			int id = Integer.parseInt(request.getParameter("id")); // 10진수 integer형으로 변한
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			UpdateReqDto dto = new UpdateReqDto();
			dto.setId(id);
			dto.setTitle(title);
			dto.setContent(content);
			
			int result = boardService.글수정(dto);
			System.out.println(result);
			if(result == 1) {
				response.sendRedirect("/blog/board?cmd=detail&id="+id);
			}else {
				Script.back(response, "글 수정에 실패하였습니다");
			}
		}
	}

}
