package core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberLocalServlet
 */
@WebServlet(name = "query", urlPatterns = { "/query" })
public class MemberLocalServlet2 extends HttpServlet {
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
		String p1 = request.getParameter("p1");
		int p2 = Integer.parseInt(request.getParameter("p2"));
		String p3[] = request.getParameterValues("p3");
		out.print("<ul>");
		out.print("<li> p1 : "+ p1 + "</li>");
		out.print("<li> p2 : "+ p2 + "</li>");
		for(int i=0; i< p3.length;i++)
			out.print("<li> p3 : "+ p3[i] + "</li>");
		out.print("<ul>");
		out.close();
	}

}