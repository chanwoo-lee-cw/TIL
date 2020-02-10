package my.spring.springedu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vo.MemberVO;

/**
 * Servlet implementation class MemberServlet
 */
@Controller
public class MemberController {
	
	@RequestMapping(value = "/member", 
            method = RequestMethod.POST)
	public ModelAndView proc(MemberVO vo) {
		ModelAndView mav = new ModelAndView();
		
		
		if(vo.getMname()==null || vo.getMname().equals(""))
			vo.setMname("없음");
		
		mav.addObject("member", vo);
		// 모델에 보관하면 자동으로 requsetscope에 보관
		mav.setViewName("memberView");
		return mav;
	}

}
