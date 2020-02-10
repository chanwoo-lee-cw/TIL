package my.spring.springedu;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vo.QueryVO;
@Controller
public class QueryStringController2 {	
	@RequestMapping("/querystring5")
	public ModelAndView proc(QueryVO vo) {
		// queryVO라는 이름으로 저장한다.
		// 다른건 없다면 null을 보관하지만 int, double 형은 값이 안온다면 0을 보관한다
		ModelAndView mav = new ModelAndView();
		String name = vo.getTestName();
		int age = vo.getTestAge();
		String addr = vo.getTestAddr();
		mav.addObject("spring", name + "-" + age + "-" + addr);
		mav.setViewName("queryView3");
		return mav;
	}	
	@RequestMapping(value = "/querystring6", 
			                      method = RequestMethod.POST)
	public ModelAndView procPost(QueryVO vo) {
		ModelAndView mav = new ModelAndView();
		String name = vo.getTestName();
		int age = vo.getTestAge();
		String addr = vo.getTestAddr();
		mav.addObject("spring", name + "@" + age + "@" + addr);
		mav.setViewName("queryView3");
		return mav;
	}
	@RequestMapping("/querystring7")
	public ModelAndView proc(HttpServletRequest request) {
		// requset를 받아서 직접 쿼리 추출을 하겠다는 의미이다.
		ModelAndView mav = new ModelAndView();
		String name =request.getParameter("testName");
		int age = Integer.parseInt(request.getParameter("testAge"));
		String addr = request.getParameter("testAddr");
		mav.addObject("spring", name + "#" + age + "#" + addr);
		mav.setViewName("queryView3");
		return mav;
	}
	@RequestMapping(value="/locale.do")
	public ModelAndView proc(Locale l) {
		// 요청을 보내온 클라이언트의 매핑명
		// 요청을 보내온 클라이언트의 재역 정보를 받는다.
		ModelAndView mav = new ModelAndView();
		mav.addObject("spring", "Processing locale ............"+
		       l.getDisplayCountry()+"_"+l.getDisplayLanguage());
		mav.setViewName("queryView3");
		return mav;
	}
}