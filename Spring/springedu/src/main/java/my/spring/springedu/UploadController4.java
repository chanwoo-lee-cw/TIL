package my.spring.springedu;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController4 {
	@Autowired
	ServletContext context; 
	// 서블릿 컨텍스트 객체를 오토 와이어 , 밑에 getRealPath를 실행하기 위해서
	// 서블릿 컨텍스트는 프로젝트의 갯수만큼 생성된다.
	
	@RequestMapping("/uploadForm3")
	public String formFile() {
		return "uploadForm2";
		// 리턴형을 String으로 전의 예제와 jsp를 공유하려고
	}

	@RequestMapping("/upload3")
	public ModelAndView saveFile(MultipartRequest mreq) {
		ModelAndView mav = new ModelAndView();
		List<MultipartFile> list = mreq.getFiles("mfile");
		String resultStr = "";
		mav.setViewName("uploadForm2");
		for (MultipartFile mfile : list) {
			String fileName = mfile.getOriginalFilename();
			try {
				System.out.println(context.getRealPath("/"));
				String fileInfo = context.getRealPath("/") + "resources/images/"+fileName;
				// context.getRealPath 컨드롤러, 즉 이 프로젝트의 최상히 폴더를 추출하는 문장
				// 특정 위치에 올리면 클라이언트가 접근을 하지 못한, 그러니까 클라이언트가 접근 가능한 폴더인 resources에 올리기 위해서
				File f = new File(fileInfo);
				if (f.exists()) {
					resultStr += fileName + " : 파일이 이미 존재해요!!<br>";
				} else {
					mfile.transferTo(new File(fileInfo));
					resultStr += fileName + " : 파일이 저장되었어요!!<br>";
				}
			} catch (IOException e) {
				e.printStackTrace();
				resultStr += fileName + " : 오류가 발생했어요!!";				
			}
		}
		mav.addObject("msg", resultStr);	
		return mav;
	}
}
