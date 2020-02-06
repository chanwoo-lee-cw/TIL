package sample6;

import java.io.IOException;

public class MessageBeanImpl implements MessageBean{
	private String name;
	private String phone;
	private Outputter outputter;
	
	//생성자로 name을 받음
	public MessageBeanImpl(String name) {
		super();
		this.name = name;
		System.out.println("1. Bean Constructor Call");
	}
	
	//setter을 통해서 phone와 outputter입력받음
	public void setPhone(String phone) {
		this.phone = phone;
		System.out.println("4. phone's info set");
	}
	
	public void setOutputter(Outputter outputter) {
		this.outputter = outputter;
		System.out.println("3. outputter's info set");
	}

	@Override
	public void helloCall() {
		String message=name+" : " +phone;
		System.out.println("helloCall() : "+message);
		
		try {
			outputter.output(message);
			System.out.println("6. Finish");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}







