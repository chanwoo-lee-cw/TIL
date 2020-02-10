package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class TestModelController2 {
	// 모델 어트리부트가 부여 되어 있는걸 커멘드 매서드- 클라이언트가 호출할 때마다 실행, 컨트롤러가 실행 되기 전에
	@ModelAttribute("data1")
	public int createModel1() {	
		System.out.println("createModel() call - data1");
		return 100;
	}
	@ModelAttribute("data2")
	public int createModel2() {	
		System.out.println("createModel() call - data2");
		return 200;
	}
	@RequestMapping(value="/testmodel2")
	public String handle(@ModelAttribute("data1") int vo1, 
			      @ModelAttribute("data2") int vo2) {
		// 즉, vo1에는 data1, vo2에는 data2가 들어간다
		// 각각 100 200 이 들어가는데
		// 만약 딴 곳에서 data1과 data2가 있다면 저 위에건 무시 되고 지금 것이 들어간다
		System.out.println("handle() : "+vo1 + " - " + vo2);
		System.out.println("=============================");
		return "modelResult2";
	}	
}


