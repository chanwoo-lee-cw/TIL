package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class CalcServlet {
	
	@RequestMapping("/calc")
	public ModelAndView proc(
			@RequestParam(defaultValue="0") int num1, 
			@RequestParam(defaultValue="0") int num2,
			@RequestParam(defaultValue="+") String cal) {
		ModelAndView mav = new ModelAndView();
		int result = 0;
		switch (cal) {
		case "+":
			result = num1 + num2;
			mav.addObject("result", result);
			break;
		case "-":
			result = num1 - num2;
			mav.addObject("result", result);
			break;
		case "*":
			result = num1 * num2;
			mav.addObject("result", result);
			break;
		case "/":
			if (num2 != 0) {
				result = num1 / num2;
				mav.addObject("result", result);
			}
			else {
				mav.addObject("errmsg", "나눗셈 연산시 두 번째 숫자는 0일 수 없습니다!!");
				mav.setViewName("errorResult");
				return mav;
			}
			break;
		}
		mav.setViewName("calcResult");
		return mav;

	}

}
