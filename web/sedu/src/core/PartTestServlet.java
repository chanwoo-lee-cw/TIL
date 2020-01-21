package core;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@WebServlet("/part")
@MultipartConfig  
// 파트를 쪼개서 올리겠다는 말이다.
public class PartTestServlet extends HttpServlet {   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
        Collection<Part> parts = request.getParts();
        // @MultipartConfig 에노테이션을 정의하지 않으면 getParts를 사용하지 못한다.
        System.out.println("========== 요청 받음 =========");
        for(Part part : parts) {        	
            System.out.print("name : ");
            System.out.println(part.getName());            
            System.out.println("[ 헤더 정보 ] ");
            for(String headerName : part.getHeaderNames()) {
            	// 업로드 되는 파일에 대한 헤더의 이름을 추출한다.
                System.out.print(headerName + " : ");
                System.out.println(part.getHeader(headerName));
            }
            System.out.println("size : "+ part.getSize());
            String filename = part.getSubmittedFileName();
            // 실제 업로되는 파일의 이름
            if (filename != null)
            	System.out.println("file name : "+filename);
            System.out.println("------------------------------------");
        }        
    }
}


