package controller;

import model.vo.ProductVO;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(name = "product", description = "product", urlPatterns = { "/product" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json; charset=utf-8");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("prodList") == null) {
			session.setAttribute("prodList", new ProductVO());
		}
		ProductVO vo =(ProductVO)session.getAttribute("prodList");
		switch (request.getParameter("pid")) {
		case "p000" :
			session.invalidate();
			String str = "{\"msg\" : \"장바구니가 비어졌어요!\"}";
//	        System.out.print(str);
	        PrintWriter out = response.getWriter();
	        out.println(str);
			return;
		case "p001" :
			vo.setApplenum(1);
			break;
		case "p002" :
			vo.setBananannum(1);
			break;
		case "p003" :
			vo.sethallabongnum(1);
			break;
		}

		RequestDispatcher impossible = request.getRequestDispatcher("/jspexam/productView.jsp");
		impossible.forward(request, response);
	}

}
