package blog.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {

	public static void responseData(HttpServletResponse response , String jsonData) {
		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(jsonData);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void back(HttpServletResponse response , String msg) {
		PrintWriter out;
		
		try {
			out = response.getWriter();
			out.println("<script>");
			out.println("alert('"+ msg +"');");
			out.println("history.back();");
			out.println("</script>");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
