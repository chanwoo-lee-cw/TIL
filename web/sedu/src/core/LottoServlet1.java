package core;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LottoServlet1
 */
@WebServlet(name = "lotto1", urlPatterns = { "/lotto1" })
public class LottoServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int num = Integer.parseInt((request.getParameter("num")));
		int ranNum = (int) (Math.random() * 6 + 1);

		System.out.print(String.format("전달된 값 : %d, 추출된 값 : %d", num, ranNum));

		RequestDispatcher rd = null;
		if (num == ranNum) {
			rd = request.getRequestDispatcher("/jspexam/success.jsp");
		} else {
			rd = request.getRequestDispatcher("/jspexam/fail.jsp");
		}
		rd.forward(request, response);
	}
}