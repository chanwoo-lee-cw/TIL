package core;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstSevlet
 */
@WebServlet("/redirect")
public class RedirectSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.sendRedirect("/sedu/first.html");
		//리다이렉트는 제한이 없다. 네이버같은 데로도 갈 수 있음.
		// 단 forward는 같은 컨텍스트 패스 같은 웹 어프리 케이션안에 있는 것만 접근 가능
	}

}
