package controller;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MeetingMybatisDAO;
import model.vo.MeetingVO;

/**
 * Servlet implementation class MeetingServlet
 */
@WebServlet(name = "meeting", description = "meeting", urlPatterns = { "/meeting" })
public class MeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String keyword = request.getParameter("keyword");
		String delid =request.getParameter("delid");
		
		if(delid!=null) {
			MeetingMybatisDAO dao = new MeetingMybatisDAO();
			boolean result = dao.delete(Integer.parseInt(delid));
			//request.getRequestDispatcher("/meeting").forward(request, response);
		}
		if(keyword == null) {
			MeetingMybatisDAO dao = new MeetingMybatisDAO();
			List<MeetingVO> list = dao.listAll();
			
			request.setAttribute("list", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jspexam/meetingView_jstl.jsp");
			for (MeetingVO vo : list)
				System.out.println(vo);
			rd.forward(request, response);
		}
		else {
			MeetingMybatisDAO dao = new MeetingMybatisDAO();
			List<MeetingVO> list = dao.search(keyword);
			
			request.setAttribute("list", list);
			
			RequestDispatcher impossible = request.getRequestDispatcher("/jspexam/meetingView_jstl.jsp");
			for (MeetingVO vo : list)
				System.out.println(vo);
			impossible.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String meetingDate = request.getParameter("meetingDate");
		
		MeetingVO vo = new MeetingVO();
		
		vo.setName(name);
		vo.setTitle(title);
		vo.setMeetingDate(meetingDate);
		
		MeetingMybatisDAO dao = new MeetingMybatisDAO();
		
		boolean result = dao.insert(vo);
		if(result)
			System.out.println("삽입 성공");
		else
			fail("삽입 실패");
		doGet(request, response);
	}

}
