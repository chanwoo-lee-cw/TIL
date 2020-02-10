package my.spring.springedu;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import vo.CountVO;


@Controller 
// count1과 count2를 세션 스코프로 지정한다.
// 세션 어트리 부트는 무조건 클래스 위에 선언해야 한다.
@SessionAttributes({"count1", "count2"})
public class CountController {
	// 한번 호출 됬으면 그 다음부터 호출되지 않는다.
	@ModelAttribute("count1")
	public CountVO countMethod1() {	
		System.out.println("countMethod1 호출 - count1");
		return new CountVO();
	}
	@ModelAttribute("count2")
	public CountVO countMethod2() {
		System.out.println("countMethod2 호출 - count2");		
		return new CountVO();
	}	
	//return이 void 면 같은 이름의 리턴 값을 찾게 된다.
	@RequestMapping(value="/count")
	public void handle(@ModelAttribute("count1")CountVO vo1, 
			      @ModelAttribute("count2")CountVO vo2, int num1, int num2) {
		vo1.setCountNum(num1);
		vo2.setCountNum(num2);
		System.out.println("handle() : "+ 
			      vo1.getCountNum() + " : " + vo2.getCountNum());
		System.out.println("=============================");
	}
	@RequestMapping(value="/countdel")
	// 매개변수가 sessionStatus
	public void handle(SessionStatus s) {
		// 세션 객체는 남겨두지만, 세션 내부 내용을 전부 삭제 하겠다는 의미이다.
		// 즉, 선별해서 내용을 지울 수 없다.
		// 만약 일부만 지우고 싶다면 httpSession으로 직접 지워야 한다.
		s.setComplete();
		System.out.println("Both count1 and count2 deleted!");	
		System.out.println("=============================");
	}
}



