package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EduServlet
 */
@WebServlet(name = "grade", description = "grade", urlPatterns = { "/grade" })
public class EduServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

//		String name = request.getParameter("gname");

		int grade = Integer.parseInt(request.getParameter("ggrade"));
		RequestDispatcher rd = null;
		switch (grade / 10) {
		case 9:
			rd = request.getRequestDispatcher("/jspexam/gradeA.jsp");
			break;
		case 8:
			rd = request.getRequestDispatcher("/jspexam/gradeB.jsp");
			break;
		case 7:
			rd = request.getRequestDispatcher("/jspexam/gradeC.jsp");
			break;
		default:
			rd = request.getRequestDispatcher("/jspexam/gradeD.jsp");
			break;
		}
		rd.forward(request, response);
	}

}
