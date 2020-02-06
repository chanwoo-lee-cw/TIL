package sample8;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmpMain {
	public static void main(String[] args) {
		ApplicationContext factory =
				   new ClassPathXmlApplicationContext("sample8/bean1.xml");
		
		Developer b1 = (Developer)factory.getBean("developer");
		System.out.println(b1.toString());
		
		Engineer b2 = (Engineer)factory.getBean("engineer");
		System.out.println(b2.toString());
		
		((ClassPathXmlApplicationContext)factory).close();
	}
}
