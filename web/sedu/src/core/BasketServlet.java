package core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BasketServlet
 */
@WebServlet(name = "basket", urlPatterns = { "/basket" })
public class BasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String pid = request.getParameter("pid");
		int pnum = Integer.parseInt(pid.replaceAll("[^0-9]", ""));
		
		out.print("선택한 상품 : " + pid +
				"<br>");
		out.print("<img src='/sedu/images/"+pnum+".jpg' style = 'width : 50%;'>");
		out.print("<br><br><a href='/sedu/html/productlog.html'>입력화면으로<a>");
		out.close();
		
	}

}
