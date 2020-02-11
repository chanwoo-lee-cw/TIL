package my.spring.springedu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import service.LottoCnt;
import service.LottoService;
import vo.LottoVO;

@Controller
@SessionAttributes({"lottoCnt"})
public class LottoController2 {
	
//	@Autowired
//	private LottoCnt lottoCnt;
	@ModelAttribute("lottoCnt")
	public LottoCnt create() {
		return new LottoCnt();
	}
	
	// 로토 서비스에 의존적이다. 로토 컨트롤러가 호출될때 생성된다.
	@Autowired
	private LottoService lottoService;
	
	@RequestMapping("/lotto2")
	public String lottoProcess(LottoVO vo,
			@ModelAttribute("lottoCnt")LottoCnt lottoCnt
	) {
		if(lottoCnt.getCnt()>=4) {
			vo.setResult("기회는 3번까지만!!");
		}
		else if (lottoService.getLottoProcess(vo.getLottoNum())) {
			vo.setResult("추카추카!!");
			lottoCnt.setCnt(10);
		} else {
			vo.setResult("아쉽네요 .. 다음 기회를!!");
			lottoCnt.setCnt(1);
		}
		System.out.println(lottoCnt.getCnt());
		return "lottoView1";
	}
}



