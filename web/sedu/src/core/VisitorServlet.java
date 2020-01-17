package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisitorServlet
 */
@WebServlet(name = "visitor", urlPatterns = { "/visitor" })
public class VisitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		// html 형식으로 출력 할 수 있도록 명시한다.
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("gname");
		String text = request.getParameter("gtext");
		
		GregorianCalendar cal= new GregorianCalendar();
		out.print("<h2>"+name+"님이 "+cal.get(Calendar.YEAR)+"년 "+ (cal.get(Calendar.MONTH)+1)+"월 "+
					cal.get(Calendar.DATE)+"일에 남긴 글입니다</h2>");
		out.print("<hr>");
		out.print(text);
		
		out.print("<br><br><br><a href='/sedu/html/visitorForm.html'>입력화면으로<a>");
		out.close();
		// 외부 자원과 연결 되어 있으므로 꼭 close해야한다.
		
	}

}
