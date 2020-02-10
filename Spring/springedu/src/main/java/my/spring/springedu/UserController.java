package my.spring.springedu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vo.UserVO;

@Controller
public class UserController {

	@ModelAttribute("popularFruit")
	public String[] refPopularFruit() {
		return new String[] { "apple", "grape", "watermelon", "plum" };
	}

	@RequestMapping(value = "/userForm", method = RequestMethod.GET)
	public String userForm() {
		System.out.println("----- UserController.userForm() : GET -----");
		return "userForm";
	}

	@RequestMapping(value = "/userSave", method = RequestMethod.POST)
	public ModelAndView userSave(UserVO userVo, Model model) {
		System.out.println("----- UserController.userSave() : POST -----");
		System.out.println("userInfo : " + userVo.toString());

		model.addAttribute("msg", "REQUEST SUCCESS");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userInfo");
		return modelAndView;
	}
}
