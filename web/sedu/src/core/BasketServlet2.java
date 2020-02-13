package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BasketServlet
 */
@WebServlet(name = "BasketServlet2", urlPatterns = { "/BasketServlet2" })
public class BasketServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/json; charset=utf-8");

		HttpSession session = request.getSession();
		if (session.getAttribute("productcnt") == null)
			session.setAttribute("productcnt", new int[11]);

		int id = Integer.parseInt(request.getParameter("pid"));

		if (id == 0) {
			session.invalidate();
			String str = "{\"msg\": \"상품이 모두 삭제 되었습니다.\"}";
			System.out.print(str);
			PrintWriter out = response.getWriter();
			out.println(str);
		}
		else {
			String pid = String.format("%03d", id);

			int[] session_v = (int[]) session.getAttribute("productcnt");
			session_v[id] += 1;

			String path = "C:/logtest";
			File isDir = new File(path);
			if (!isDir.exists()) {
				isDir.mkdirs();
			}
			for (int i = 1; i < 11; i++)
				System.out.println(session_v[i]);

			try (FileWriter writer = new FileWriter(path + "/mylog.txt", true);) {
				LocalDateTime currentDate = LocalDateTime.now();
				writer.write(currentDate.getYear() + String.format("%02d", currentDate.getMonthValue())
						+ currentDate.getDayOfMonth() + currentDate.getHour() + currentDate.getMinute() + " p" + pid
						+ "\n");
			} catch (IOException ioe) {
				System.out.println("파일로 출력할 수 없습니다.");
			}
			// String str = "{\"pid\" : \"p"+pid+"\"}";
			String str = "{";
			for (int i = 1; i < 11; i++) {				
				if(session_v[i]==0) continue;
				str += "\"p" + String.format("%03d", i) + "\" : " + session_v[i];
				str += ",";
			}
			str = str.substring(0, str.length()-1);
			str += "}";
			System.out.print(str);
			PrintWriter out = response.getWriter();
			out.println(str);
		}

	}

}
