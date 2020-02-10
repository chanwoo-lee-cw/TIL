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
		// 자동적으로 memberVO라는 이름으로 리퀘스트 객체에 보관한다.
		ModelAndView mav = new ModelAndView();
		
		
		if(vo.getMname()==null || vo.getMname().equals(""))
			vo.setMname("없음");
		
		mav.addObject("member", vo);
		// 만약 memberVO라는 이름으로 꺼낼꺼면  보관하지 않아도 괜찮다.
		// 모델에 보관하면 자동으로 requsetscope에 보관
		mav.setViewName("memberView");
		return mav;
	}

}
