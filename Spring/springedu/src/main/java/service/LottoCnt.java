package service;

import org.springframework.stereotype.Repository;

//@Repository
public class LottoCnt {

	private int cnt;
	
	public LottoCnt() {
		this.cnt=0;
		System.out.print("이러면 생성 되야 되지 않나.");
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int num) {
		this.cnt += num;
	}
}
