package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
@Controller
// data1이라는 이름으로 생성되는 객체는 세션 객체에 보관한다.
// 세션 쓰는 방법 3가지 컨트롤러 의 매개변수를 HttpSession으로 받던가.
// HttpservletReqest를 통해 직접 세션 객체를 만들어 사용하던가.
// @SessionAttributes를 사용해서 알아서 세션 객체를 만들어 사용하게 하던가.
@SessionAttributes("data1")
public class TestModelController3 {
	@ModelAttribute("data1")
	public StringBuffer createModel1() {	
		System.out.println("createModel1() 호출 - data1(session)");
		return new StringBuffer();
	}
	@ModelAttribute("data2")
	public StringBuffer createModel2() {	
		System.out.println("createModel2() 호출 - data2(request)");
		return new StringBuffer();
	}
	@RequestMapping(value="/testmodel3")
	public String handle(@ModelAttribute("data1") StringBuffer vo1, 
			@ModelAttribute("data2") StringBuffer vo2, String str) {
		vo1.append(str+":");
		vo2.append(str+"@");
		System.out.println("handle 에서 출력 : "+vo1 + " - " + vo2);
		System.out.println("=============================");
		return "modelResult2";
	}	
}
