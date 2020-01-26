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

/**
 * Servlet implementation class BasketServlet
 */
@WebServlet(name = "BasketServlet1", urlPatterns = { "/BasketServlet1" })
public class BasketServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/json; charset=utf-8");
		String pid = String.format("%03d",Integer.parseInt(request.getParameter("pid")));

        String path = "C:/logtest";
        File isDir = new File(path);
        if (!isDir.exists()) {
        	isDir.mkdirs();
        }
        
        try (FileWriter writer = new FileWriter(path+"/mylog.txt",true);){
        	
        	LocalDateTime currentDate = LocalDateTime.now();
        	writer.write(currentDate.getYear()+String.format("%02d",currentDate.getMonthValue())+currentDate.getDayOfMonth()+currentDate.getHour()+currentDate.getMinute() +" p"+pid+"\n");
        } catch (IOException ioe) {
            System.out.println("파일로 출력할 수 없습니다.");
        }
        String str = "{\"pid\" : \"p"+pid+"\"}";
        System.out.print(str);
        PrintWriter out = response.getWriter();
        out.println(str);
        
	}

}
