package sampleanno06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TvUser {
	public static void main(String[] args) {
		ApplicationContext factory
		        = new ClassPathXmlApplicationContext("sampleanno06/bean.xml");
	
		Tv tv=(Tv)factory.getBean(args[0]);   //samsung or lg
		tv.powerOn();
		tv.powerOff();
		tv.volumeUp();
		tv.volumeDown();
		
		((ClassPathXmlApplicationContext)factory).close();
	}
}
