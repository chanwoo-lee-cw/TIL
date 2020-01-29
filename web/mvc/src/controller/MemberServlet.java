package controller;

import model.vo.MemberVO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet(name = "member", urlPatterns = { "/member" })
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		MemberVO vo = new MemberVO();
		String mname = request.getParameter("mname");
		if(mname.equals(""))
			mname = "없음";
		String mphone = request.getParameter("mphone");
		if(mphone.equals(""))
			mphone = "없음";
		String maccount = request.getParameter("maccount");
		if(maccount.equals(""))
			maccount = "없음";
		String mpassword = request.getParameter("mpassword");
		if(mpassword.equals(""))
			mpassword = "없음";
		vo.setMname(mname);
		vo.setMphone(mphone);
		vo.setMaccount(maccount);
		vo.setMpassword(mpassword);
		
		request.setAttribute("member", vo);
		request.getRequestDispatcher("/jspexam/memberView.jsp").forward(request, response);
	}

}
