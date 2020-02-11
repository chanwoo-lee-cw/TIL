package vo;
public class LottoVO {
	private int lottoNum;
	private String result;
	public LottoVO()  {
		System.out.println("LottoVO Create object");
	}
	public int getLottoNum() {
		return lottoNum;
	}
	public void setLottoNum(int lottoNum) {
		this.lottoNum = lottoNum;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}	
}
