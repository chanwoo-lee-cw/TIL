package sample6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {
	public static void main(String[] args) {
		ApplicationContext factory
        		= new ClassPathXmlApplicationContext("sample6/applicationContext.xml");

		System.out.println("** Container Initialization End **");
		MessageBean bean=(MessageBean)factory.getBean("messageBean");
		bean.helloCall();

		((ClassPathXmlApplicationContext) factory).close();
	}
}
