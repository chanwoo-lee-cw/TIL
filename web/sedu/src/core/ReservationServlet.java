package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet(name = "reservation", urlPatterns = { "/reservation" })
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("rervname");
//		String password = request.getParameter("rervpword");

		String room = request.getParameter("rervroom");

		String option[] = request.getParameterValues("resvoption");
		String[] day = request.getParameter("rervday").split("-");
		// 다른 방법1
		// LocalDate ndate = LocalDate.now();
		// DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		// String date = ndate.format(dateF);
		
		// 다른 방법 2
		// String rdate = request.getParameter("rdate");
		// LocalDate ndate = LocalDate.parse(rdate)

		out.print("<h1>" + name + "님의 예약내용</h1>" + 
					"<hr>" + 
					"<ul>" + 
					"<li> 룸 : " + room + 
					"<li> 추가요청사항 : ");
		if (option != null) {
			for (int i = 0; i < option.length; i++) {
				out.print(option[i]);
				if (i < option.length - 1)
					out.print(", ");
			}
		} else {
			out.print("없음");
		}
		out.print("<li> 예약날짜 : " + day[0] +"년 "+day[1] +"월 "+day[2] +"일 "
				+ "</ul>");
		out.print("<br><br><a href='/sedu/html/reservation.html'>예약입력화면으로<a>");
		out.close();

	}

}
