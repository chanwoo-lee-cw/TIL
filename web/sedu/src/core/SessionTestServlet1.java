package core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLocalServlet
 */
@WebServlet(name = "sessiontest1", urlPatterns = { "/sessiontest1" })
public class SessionTestServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int member_v=0;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int local_v = 0;
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		// 없으면 만들고 있으면 찾아온다.
		if(session.getAttribute("cnt")==null)
			session.setAttribute("cnt", new int[1]);
		// 세션 객체에 데이터를 보관하는 방은 무조건 객체여야 한다.
		int[] session_v = (int[])session.getAttribute("cnt");
		session_v[0] += 10;
		member_v += 10;
		local_v += 10;
		out.print("<ul>");
		out.print("<li> 멤버변수 : "+ member_v + "</li>");
		out.print("<li> 지역변수 : "+ local_v + "</li>");
		out.print("<li> 세션객체에 저장된 배열원소 : "+ session_v[0] + "</li>");
		out.print("<ul>");
		out.close();
	}

}