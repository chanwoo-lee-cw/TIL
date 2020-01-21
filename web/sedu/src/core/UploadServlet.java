package core;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@WebServlet("/upload")
@MultipartConfig (location = "c:/uploadtest")
public class UploadServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out= response.getWriter();
		request.setCharacterEncoding("utf-8");
		String path = "c:/uploadtest";
		File isDir = new File(path);
		if (!isDir.isDirectory()) {
			isDir.mkdirs();
		}
		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			if (part.getContentType() != null) {
				// 해당 파트만의 컨텐츠 타입이 있으냐? -> 해당 파트가 파일인지 검사한다. 만약 파일이라면 content-type가 붙는다.
				String fileName = part.getSubmittedFileName();
				if (fileName != null) {
					part.write(fileName.substring(0, fileName.lastIndexOf(".")) + 
							"_"	+ System.currentTimeMillis() 
							+ fileName.substring(fileName.lastIndexOf(".")));
					// part.write가 제공하는 API를 이용해서 파일을 저장한다. 단, 저장할 폴더는 이미 생성되어 있어야 한다.
					out.print("<br>업로드한 파일 이름: " + fileName);
					out.print("<br>크기: " + part.getSize());				
				}
			} else {
				String partName = part.getName();
				String fieldValue = request.getParameter(partName);
				out.print("<br>" + partName + ": " + fieldValue);			
			}
		}
		out.close();		
	}
}




