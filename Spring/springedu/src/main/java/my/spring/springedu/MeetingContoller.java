package my.spring.springedu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.MeetingDAO;
import vo.MeetingVO;

@Controller
public class MeetingContoller {
	
	@Autowired
	MeetingDAO dao=null;
	
	@RequestMapping(value="/meetingForm")
	public void meetingForm() {
	}
	
	@RequestMapping("/listAll")
	public ModelAndView listAll() {
		ModelAndView mav = new ModelAndView();
		List<MeetingVO> list = dao.listAll();
		mav.addObject("list",list);
		mav.setViewName("meetingView_jstl");
		return mav;
	}
	
	@RequestMapping("/insertMeeting")
	public ModelAndView insertMeeting(MeetingVO vo) {
		ModelAndView mav = new ModelAndView();
		boolean result = dao.insert(vo);
		if(result) {
			List<MeetingVO> list = dao.listAll();
			mav.addObject("list",list);
			mav.setViewName("meetingView_jstl");
		}
		return mav;
	}
	
	@RequestMapping("/searchMeeting")
	public ModelAndView searchMeeting(String keyword) {
		ModelAndView mav = new ModelAndView();
		List<MeetingVO> list = dao.search(keyword);
		mav.addObject("list",list);
		mav.setViewName("meetingView_jstl");
		return mav;
	}
	
	@RequestMapping("/deleteMeeting")
	public ModelAndView deleteMeeting(int delid) {
		ModelAndView mav = new ModelAndView();
		boolean result = dao.delete(delid);
		
		if(result) {
			List<MeetingVO> list = dao.listAll();
			mav.addObject("list",list);
			mav.setViewName("meetingView_jstl");
		}
		return mav;
	}
	
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//		String keyword = request.getParameter("keyword");
//		String delid =request.getParameter("delid");
//		
//		if(delid!=null) {
//			MeetingMybatisDAO dao = new MeetingMybatisDAO();
//			boolean result = dao.delete(Integer.parseInt(delid));
//			//request.getRequestDispatcher("/meeting").forward(request, response);
//		}
//		if(keyword == null) {
//			MeetingMybatisDAO dao = new MeetingMybatisDAO();
//			List<MeetingVO> list = dao.listAll();
//			
//			request.setAttribute("list", list);
//			
//			RequestDispatcher rd = request.getRequestDispatcher("/jspexam/meetingView_jstl.jsp");
//			for (MeetingVO vo : list)
//				System.out.println(vo);
//			rd.forward(request, response);
//		}
//		else {
//			MeetingMybatisDAO dao = new MeetingMybatisDAO();
//			List<MeetingVO> list = dao.search(keyword);
//			
//			request.setAttribute("list", list);
//			
//			RequestDispatcher impossible = request.getRequestDispatcher("/jspexam/meetingView_jstl.jsp");
//			for (MeetingVO vo : list)
//				System.out.println(vo);
//			impossible.forward(request, response);
//		}
//		
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		request.setCharacterEncoding("UTF-8");
//		String name = request.getParameter("name");
//		String title = request.getParameter("title");
//		String meetingDate = request.getParameter("meetingDate");
//		
//		MeetingVO vo = new MeetingVO();
//		
//		vo.setName(name);
//		vo.setTitle(title);
//		vo.setMeetingDate(meetingDate);
//		
//		MeetingMybatisDAO dao = new MeetingMybatisDAO();
//		
//		boolean result = dao.insert(vo);
//		if(result)
//			System.out.println("삽입 성공");
//		else
//		doGet(request, response);
//	}

}
