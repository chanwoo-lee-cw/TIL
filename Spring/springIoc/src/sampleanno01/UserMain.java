package sampleanno01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMain {

	public static void main(String[] args) {
		ApplicationContext factory 
		    = new ClassPathXmlApplicationContext("sampleanno01/bean.xml");
		System.out.println("**** Container Initialization End ****");		
		UserShow ob=factory.getBean("myProcess0", UserShow.class);
		System.out.println(ob.toString());	
		System.out.println("-----------------");		
		UserShow ob1=factory.getBean("myProcess1", UserShow.class);
		System.out.println(ob1.toString());	
		System.out.println("-----------------");		
		UserShow ob2=factory.getBean("myProcess2", UserShow.class);
		System.out.println(ob2.toString());	
		System.out.println("-----------------");		
		UserShow ob3=factory.getBean("myProcess3", UserShow.class);
		System.out.println(ob3.toString());	
		
		((ClassPathXmlApplicationContext)factory).close();
	}
}
