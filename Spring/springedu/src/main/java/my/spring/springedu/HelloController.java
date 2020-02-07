package my.spring.springedu;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class HelloController {	
//	@Autowired
//	ServletContext context; 
	public HelloController() {
		System.out.println("HelloController Create object");
		// 생성자 매서드에서 나오는 메세지.
		// 즉 서버가 기동할때 출력된다.
		// @Controller라는 빈객체라는 것을 명시해 줬기 때문애, 시동할때 기동한다.
		// 이런것을 POJO라고 한다. Plain Old Java Object
	}
	@RequestMapping("/hello")
	// 클라이언트로 부터 요청된 URL이 HELLO라고 끝나면 얘를 호출하라고 명시한다.
	// 이런것을 컨트롤러 메서드 or 핸들러 메서드라고 한다.
	// public ModelAndView xxx(HttpServletRequest req){	
	// 매서드 이름의 제약과, 매개 변수 제역도 간단하다.
	// 리턴도 자유롭지만 ModelAndView가 보통이다.
	public ModelAndView xxx(){
		//ModelAndView에 응답할 view의 이름과 객체를 전달한다라는 것을 알려준다.
		ModelAndView mav = new ModelAndView();
		mav.setViewName("helloView");
		//views/helloView.jsp를 찾아가라는 명시
		mav.addObject("msg", getMessage());
		// 리퀘스트에다 msg라는 객체라는 이름의 getMessage()의 결과를 매핑해 준다.
		return mav;
		// 이 리턴값을 dispathcerservlet이 받는다
	}
	private String getMessage(){
		int hour = Calendar.getInstance()
				.get(Calendar.HOUR_OF_DAY);		
		if(hour >= 6 && hour <= 10){
			return "Good Morning!!";
		}else if(hour >= 12 && hour <= 15){
			return "Good Afternoon";
		}else if(hour >= 18 && hour <= 22){
			return "Good Evening!!";
		}else{ 
			return "Hello!!";
		}
	}
}




