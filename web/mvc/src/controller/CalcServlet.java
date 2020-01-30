package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcServlet
 */
@WebServlet(name = "calc", description = "clac", urlPatterns = { "/calc" })
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));

		int result = 0;
		switch (request.getParameter("cal")) {
		case "+":
			result = num1 + num2;
			request.setAttribute("result", result);
			break;
		case "-":
			result = num1 - num2;
			request.setAttribute("result", result);
			break;
		case "*":
			result = num1 * num2;
			request.setAttribute("result", result);
			break;
		case "/":
			if (num2 != 0) {
				result = num1 / num2;
				request.setAttribute("result", result);
			}
			else {
				request.setAttribute("errmsg", "나눗셈 연산시 두 번째 숫자는 0일 수 없습니다!!");
				request.getRequestDispatcher("/jspexam/errorResult.jsp").forward(request, response);
				return;
			}
			break;
		}
		
		request.getRequestDispatcher("/jspexam/calcResult.jsp").forward(request, response);

	}

}
