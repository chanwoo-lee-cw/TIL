package my.spring.springedu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class EduServlet {
	
	
	@RequestMapping("/grade")
	public String GradeController1(
			@RequestParam(value="ggrade", defaultValue="0")int grade) {
		switch (grade / 10) {
		case 9:
			return "gradeA";
		case 8:
			return "gradeB";
		case 7:
			return "gradeC";
		default:
			return "gradeD";
		}
	}
	
	// 만약 return 이 void 일 때
	// 리턴 되는 view가 없으므로 
	// @RequestMapping("/xxx")의  /xxx.jsp라는 이름으로 선언된 것으로 자동으로 리턴 된다.

}
