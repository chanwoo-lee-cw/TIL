package my.spring.springedu;
	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.LoginService;
import vo.LoginVO;
import vo.ResultVO;

@Controller
public class LoginController {
	@Autowired
	LoginService ms;
	@RequestMapping(value = "/login", produces="application/json; charset=utf-8", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO login(LoginVO p) { 
		ResultVO vo = new ResultVO();
		boolean result = ms.login(p.getId(), p.getPasswd());
		if (result == true) {
			vo.setResult("ok");
		}
		else {
			vo.setResult("fail");
		}
		return vo;
	}

}
