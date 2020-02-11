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
	@RequestMapping("/uploadForm3")
	public String formFile() {
		return "uploadForm2";
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
				String fileInfo = context.getRealPath("/") + "resources/images/"+fileName;
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
