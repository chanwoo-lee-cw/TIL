package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vo.StepVO;
// POJO
@Controller
public class StepController {		
	@RequestMapping(value="/step",
			 method=RequestMethod.POST)
	// @ModelAttribute는 stepVO를 보관할 때 이름을 정하는 방법
	public String memberHandle(@ModelAttribute("kkk") StepVO vo) {
		if(vo.getAge() < 18)
			// 나이가 18세 미만이면 다시 입력 하라고 보냄.
			// 하지만 jsp가 아니므로 받는건 redirect:로 원하는 장소로 보내줌
			return "redirect:/resources/stepForm.html";
		System.out.println("[ Information for the passed Command object ]");
		System.out.println(vo.getName());
		System.out.println(vo.getPhoneNumber());
		System.out.println(vo.getAge());
		return  "stepOutput";
	}
}
