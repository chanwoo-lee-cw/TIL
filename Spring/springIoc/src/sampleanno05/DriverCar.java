package sampleanno05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DriverCar {
	public static void main(String[] args) {
		ApplicationContext factory=new ClassPathXmlApplicationContext("sampleanno05/bean.xml");
		System.out.println("**** Container Initialization End ****");
		Car tire2=factory.getBean("carTire", Car.class);
		tire2.drive();
		/*
		 * Tire tire=factory.getBean("vestTire", Tire.class);
		 * System.out.println(tire.getBrand());
		 */
		((ClassPathXmlApplicationContext)factory).close();	
	}
}








