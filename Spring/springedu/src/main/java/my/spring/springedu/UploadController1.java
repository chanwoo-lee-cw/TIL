package my.spring.springedu;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vo.FileVO;
@Controller
public class UploadController1 {
	@RequestMapping("/uploadForm1")
	///uploadForm1.jsp로 날아감.
	public void formFile() {	   
	}
	@RequestMapping("/upload")
	public ModelAndView saveFile(FileVO vo) {	    
	     String fileName =  vo.getUploadFile().getOriginalFilename();
	     // 실제 파일 네임 리턴해준다.
	     byte[] content = null;
	     ModelAndView mav = new ModelAndView();
	     mav.setViewName("uploadForm1");
	     try {
	    	 content =  vo.getUploadFile().getBytes();
	    	 // getbytes로 바이트형 배열 리턴 된다.
	    	 // 만약 추가적인 작업을 하고 싶으면 request말고 getByte로 추가적인 작업을 해줘야 한다.
	    	 File f = new File("c:/uploadtest/"+fileName);
	    	 if ( f.exists() ) {
	    		 mav.addObject("msg", fileName + " : 파일이 이미 존재해요!!");
	    	 } else {
	    		 FileOutputStream fos = new FileOutputStream(f);
	    		 fos.write(content);
	    		 //이미지 같은 바이트형 파일은 문제가 안되지만, 텍스트는 문제가 생김.
	    		 // 그냥 그대로 읽어서 그래도 옮기는 이런건 문제가 안되지만, 텍스트를 읽어서 수정하거나 프로그램에 출력하는건 문제가 생긴다.
	    		 fos.close();
	    		 mav.addObject("msg", fileName + ": 파일이 저장되었어요!!");
	    	 }
	     } catch (IOException e) {
	    	 e.printStackTrace();
	    	 mav.addObject("msg", "오류가 발생했어요!!");
	     }	    
	     //msg로 상황 메세지를 전달해 준다.
	    return mav;
	}
}

