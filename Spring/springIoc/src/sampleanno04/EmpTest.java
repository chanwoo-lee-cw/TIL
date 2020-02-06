package sampleanno04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmpTest {
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("sampleanno04/bean.xml");
		
		Engineer my = factory.getBean("engineer", Engineer.class);
		System.out.println(my.toString());
		
		((ClassPathXmlApplicationContext) factory).close();
	}
}










