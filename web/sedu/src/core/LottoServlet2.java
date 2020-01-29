package core;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LottoServlet1
 */
@WebServlet(name = "lotto2", urlPatterns = { "/lotto2" })
public class LottoServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		if (session.getAttribute("cnt") == null)
			session.setAttribute("cnt", new Integer(0));
		
		int num = Integer.parseInt((request.getParameter("num")));
		int ranNum = (int) (Math.random() * 6 + 1);
		
		Integer cnt_s = (Integer)session.getAttribute("cnt");
		
		System.out.println(cnt_s++);
		System.out.println(String.format("전달된 값 : %d, 추출된 값 : %d", num, ranNum));

		RequestDispatcher rd = null;
		if(cnt_s>3) {
			rd = request.getRequestDispatcher("/jspexam/impossible.jsp");
		}
		else if (num == ranNum) {
			rd = request.getRequestDispatcher("/jspexam/success.jsp");
			cnt_s=4;
		} else {
			rd = request.getRequestDispatcher("/jspexam/fail.jsp");
		}
		rd.forward(request, response);
		session.setAttribute("cnt", new Integer(cnt_s++));
		request.getHeader("referrer");
	}
}