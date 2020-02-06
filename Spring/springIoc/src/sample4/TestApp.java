package sample4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApp {
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("sample4/app.xml");
		
		AbstractTest bean = (AbstractTest) factory.getBean("test");
		System.out.println("Today : " + bean.dayInfo() + ".");
		
		((ClassPathXmlApplicationContext)factory).close();
	}
}
