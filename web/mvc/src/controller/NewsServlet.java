package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.NewsDAO;
import model.vo.NewsVO;

/**
 * Servlet implementation class NewsServlet
 */
@WebServlet(name = "news", description = "news", urlPatterns = { "/news" })
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String newsid = request.getParameter("newsid");

		NewsDAO newsdao = new NewsDAO();
//		System.out.println(request.getParameter("action"));
		if (action != null) {
			if (action.equals("read")) {
//			System.out.println(newsid);
				NewsVO column = newsdao.listOne(Integer.parseInt(newsid));
//			System.out.println(column);
				request.setAttribute("column", column);
			} else if (action.equals("delete")) {
				boolean result = newsdao.delete(Integer.parseInt(newsid));
				if (result)
					System.out.println("삭제 성공");
				else
					System.out.println("삭제 실패");
			}
		}

//		if(action == null && newsid == null) {
		List<NewsVO> list = newsdao.listAll();
		request.setAttribute("list", list);
//		}

		request.getRequestDispatcher("/jspexam/news.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		NewsDAO dao = new NewsDAO();
		System.out.println(request.getParameter("format"));
		if (request.getParameter("format").equals("insert")) {
			NewsVO vo = new NewsVO();
			vo.setWriter(request.getParameter("title"));
			vo.setTitle(request.getParameter("writer"));
			vo.setContent(request.getParameter("contents"));
			boolean result = dao.insert(vo);
			if (result)
				System.out.println("삽입 성공");
			else
				System.out.println("삽입 실패");
		}
		else if (request.getParameter("format").equals("update")) {
			NewsVO vo = new NewsVO();
			vo.setId(Integer.parseInt(request.getParameter("newsid")));
			vo.setWriter(request.getParameter("writer"));
			vo.setTitle(request.getParameter("title"));
			vo.setContent(request.getParameter("contents"));
			boolean result = dao.update(vo);
			if(result)
				System.out.println("업데이트 성공");
			else
				System.out.println("업데이트 실패");
		}
		doGet(request, response);
	}

}
