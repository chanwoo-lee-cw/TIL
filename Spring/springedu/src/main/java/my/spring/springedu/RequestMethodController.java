package my.spring.springedu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RequestMethodController {

	public RequestMethodController() {
		System.out.println("RequestMethodController 객체생성");
	}
	
	// 요청된 리퀘스트의 명은 동일하지만, 매서드의 방식으로 구분한다.
	// 만약 이렇게 구분 안하면 에러 뜸
	// 2가지 이상일때는 각각 네임 밸류 쌍으로 반드시 구분해 줘야 한다.
	@RequestMapping(value = "/requestmethod", method = RequestMethod.GET)
	public String myGet1() {
		System.out.println("GET ............");
		return "getResult";
	}

	@RequestMapping(value = "/requestmethod", method = RequestMethod.POST)
	public String myPost() {
		System.out.println("POST ............");
		return "postResult";
	}
}






