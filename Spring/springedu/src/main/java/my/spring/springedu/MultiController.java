package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class MultiController {	
	//select과 seacrch는 매개 변수가 없다. 전달 받고 싶은 값이 없다. 우리 맘대로 지정가능
	// return 값도 우리 맘대로 지정가능
	@RequestMapping(value="/select")
	public String select() {
		System.out.println("select ............");
		return  "viewTest";
	}
	@RequestMapping(value="/search")
	public String search() {
		System.out.println("search ............");
		return "viewTest";
	}
	@RequestMapping(value="/insert")
	public String insert(int pageno) {
		// pageno라는 쿼리 문자열로 전달 받는 값을 int형으로 저장한다
		System.out.println("insert ............"+pageno);
		return  "viewTest";
	}
}