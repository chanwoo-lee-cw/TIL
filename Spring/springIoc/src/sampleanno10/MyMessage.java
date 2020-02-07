package sampleanno10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  
public class MyMessage {
	// autowiring tnstj : 생성자, 멤버변수 초기화(setter), 그 다음이 일반 매서드
	@Autowired
	String message;
	
	public MyMessage(){
		System.out.println("Create object 0 : "+message);		
	}
	@Autowired
	public MyMessage(String msg){
		System.out.println("Create object 1 : "+msg);		
	}
	@Autowired
	public void setMessage(String message) {
		System.out.println("SETTER : "+message);
		this.message = message;
	}
	@Autowired
	public void ccc(String message) {
		System.out.println("ccc : "+message);
		this.message = message;
	}
	@Autowired
	public void aaa(String message) {
		System.out.println("aaa : "+message);
		this.message = message;
	}
	@Autowired
	public void bbb(String message) {
		System.out.println("bbb : "+message);
		this.message = message;
	}
	// 객체 생성은 알파벳 순, 하지만 a를 만드는데 b가 필요하면 당연 b부터 한다.
	public String getMessage() {
		return message;
	}	
}
