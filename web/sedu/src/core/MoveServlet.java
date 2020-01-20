package core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MoveServlet
 */
@WebServlet(name = "move", urlPatterns = { "/move" })
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String home = null;
		home = request.getParameter("action");
		String str = null;
		if (home == null)
			out.print("<h2>전달된 쿼리 문자열이 없어서 MoveServlet이 직접 응답합니당..</h2>");
		else if (home.equals("naver"))
			str = "https://www.naver.com/";
		else if (home.equals("daum"))
			str = "https://www.daum.net/";
		else if (home.equals("google"))
			str = "https://www.google.com/";

		if (str != null)
			response.sendRedirect(str);

	}

}
