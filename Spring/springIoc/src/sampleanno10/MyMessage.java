package sampleanno10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  
public class MyMessage {
	@Autowired
	String message;
	
	public MyMessage(){
		System.out.println("Create object 0 : "+message);		
	}
	@Autowired
	public MyMessage(String m){
		System.out.println("Create object 1 : "+m);		
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
	public String getMessage() {
		return message;
	}	
}
