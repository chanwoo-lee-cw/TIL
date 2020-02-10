package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class QueryStringController1 {
	@RequestMapping("/querystring1")
	public ModelAndView proc(String name) {
		ModelAndView mav = new ModelAndView();
		System.out.println("["+name+"]");
		mav.addObject("spring", name);
		// ModelAndView에 오브젝트를 저장하면 기본적으로 request에 저장된다.
		mav.setViewName("queryView1");
		return mav;
	}	
	@RequestMapping("/querystring2")
	public ModelAndView proc(int number) {
		// int나 double가 매개변수이면 자동적으로 형변환을 해주는 처리를 해주기 때문에 qeury를 주지 않으면 에러가 난다.
		ModelAndView mav = new ModelAndView();
		mav.addObject("spring", number);
		mav.setViewName("queryView1");
		return mav;
	}	
	@RequestMapping("/querystring3")
	public ModelAndView proc(String name, 
			                   @RequestParam("num")int number) {
		// num 이라는 이름으로 전달받은 쿼리 문자열을 number에 세팅한다.
		ModelAndView mav = new ModelAndView();
		mav.addObject("spring", name+":"+number);
		mav.setViewName("queryView1");
		return mav;
	}
	@RequestMapping("/querystring4")
	public ModelAndView proc(
	@RequestParam("myname1") String name1,		
		//myname1이라는 이름으로 전달 받은 쿼리 문자열을 name1에 저장하겠다.
	@RequestParam(value="myname2", required=false) String name2,	
		//@RequestParam을 지정하면 자동적으로 required true가 되지만, 이건 false로 지정해서 필수가 아니라는 것을 명시했다. 만약 안온다면 null로 세팅
	@RequestParam(defaultValue="10") int number1, 
		//만약 number1가 없다면 10을 세팅해라
	@RequestParam(value="NUM2", defaultValue="100")int number2){
		//NUM2라는 이름으로 전달된 쿼리가 없다면 100을 세팅해라
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("spring", name1+":"+(number1 + number2) 
				+":"+name2);
		mav.setViewName("queryView1");
		return mav;
	}
}






