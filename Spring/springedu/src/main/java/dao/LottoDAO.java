package dao;
import java.util.Random;
import org.springframework.stereotype.Repository;

// 이게 붙어 있기 때문에 실행 될때 불러진다.
// @ 붙은건 거의 대부분 component의 자손이다.
// @ 를 찾으려면 compoent에 선언되어 있어야 한다
// @가 붙어 있으므로 컴포넌트가 객체 생성 해준다.
@Repository
public class LottoDAO {
	public LottoDAO()  {
		System.out.println("LottoDAO 객체생성");
	}
	public int getLottoNumber() {
		Random rand = new Random();
		return rand.nextInt(6)+1;
	}
}
