package service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dao.LottoDAO;
@Service
public class LottoService {
	public LottoService()  {
		System.out.println("LottoService 객체생성");
	}
	//lottoDAO를 오토 와이어드 해준다.
	@Autowired
	LottoDAO lottoDAO = null;
	public boolean getLottoProcess(int lottoNum) {
		boolean result = false;		
		int randomNumber = lottoDAO.getLottoNumber();
		System.out.println("--- 난수: " + randomNumber);
		System.out.println("--- 입력한 수: " + lottoNum);
		if(randomNumber == lottoNum) 
			result = true;
		return result;
	}
} 
