package exam1;

import java.time.LocalTime;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample2.InterFoo;

public class GreetingTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext factory = new ClassPathXmlApplicationContext("exam1/beans.xml");

		LocalTime ob1 = (LocalTime) factory.getBean("time");
//		System.out.println(ob1.getHour());

		int time = ob1.getHour();
		Greeting ob2 = null;
		if (6 <= time && time < 12) {
			ob2 = (MorningGreetingImpl) factory.getBean("Morning");
		} else if (12 <= time && time < 17) {
			ob2 = (AfternoonGreetingImpl) factory.getBean("Afternoon");
		} else if (17 <= time && time < 20) {
			ob2 = (EveningGreetingImpl) factory.getBean("Evening");
		} else {
			ob2 = (NightGreetingImpl) factory.getBean("NightNight");
		}
		
		ob2.greet();
	}

}
