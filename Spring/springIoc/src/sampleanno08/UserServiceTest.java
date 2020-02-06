package sampleanno08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {

	public static void main(String[] args) {
		ApplicationContext factory 
		   = new ClassPathXmlApplicationContext("sampleanno08/bean.xml");

		UserService uu = factory.getBean("userService", UserService.class);
		uu.addUser();

		((ClassPathXmlApplicationContext) factory).close();

	}

}
