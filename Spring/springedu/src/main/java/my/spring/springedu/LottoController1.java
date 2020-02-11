package my.spring.springedu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.LottoService;
import vo.LottoVO;
@Controller
public class LottoController1 {
	// 로토 서비스에 의존적이다. 로토 컨트롤러가 호출될때 생성된다.
	@Autowired
	private LottoService lottoService;	
	@RequestMapping("/lotto1")
	public String lottoProcess(LottoVO vo) {		
		if (lottoService.getLottoProcess(vo.getLottoNum())) {
			vo.setResult("추카추카!!");
		} else {
			vo.setResult("아쉽네요 .. 다음 기회를!!");
		}
		return "lottoView1";
	}
}



