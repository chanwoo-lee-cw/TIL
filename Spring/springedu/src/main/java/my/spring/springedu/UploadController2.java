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
public class UploadController2 {
	@Autowired
	ServletContext context; 
	@RequestMapping("/uploadForm2")
	public void formFile() {
	}

	@RequestMapping("/upload2")
	public ModelAndView saveFile(MultipartRequest mreq) {
		//MultipartRequest를 사용하면 전달되는 파일이 1개가 아니라 여러개라도 된다.
		// 매개변수를 multifile로 해야한다.
		// 만약 Request로 한다면 파일을 직접 꺼내줘야 한다.
		// 그게 바로 밑에 getFiles이다.
		ModelAndView mav = new ModelAndView();
		List<MultipartFile> list = mreq.getFiles("mfile");
		// 리스트즉, 업로드 되는 파일 갯수만큼 파일이 들어간다.
		String resultStr = "";
		String path = "c:/uploadtest/multi";
		File isDir = new File(path);
		if (!isDir.isDirectory()) {
			isDir.mkdirs();
		}
		mav.setViewName("uploadForm2");
		for (MultipartFile mfile : list) {
			// 파일의 갯수만큼 반복문
			String fileName = mfile.getOriginalFilename();
			try {
				File f = new File("c:/uploadtest/multi/" + fileName);
				//String fileInfo = context.getRealPath("/") + "resources/images/"+fileName;
				//File f = new File(fileInfo);
				if (f.exists()) {
					resultStr += fileName + " : 파일이 이미 존재해요!!<br>";
				} else {
					mfile.transferTo(f);
					// 파일 아웃 풋을 사용하지 않고, 멀티파트의 매서드를 사용해서 올리는 형태
					// 받아와서 추가적인 액션을 전혀 하지 않고 바로 내보내는 형태이다.
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
