package exam3;

import java.time.LocalTime;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GreetingTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext factory = new ClassPathXmlApplicationContext("exam3/beans_anno.xml");

		LocalTime ob1 = (LocalTime) factory.getBean("time");
//		System.out.println(ob1.getHour());

		int time = ob1.getHour();
		Greeting ob2 = null;
		if (6 <= time && time < 12) {
			ob2 = factory.getBean("morning", MorningGreetingImpl.class);
		} else if (12 <= time && time < 17) {
			ob2 = factory.getBean("afternoon", AfternoonGreetingImpl.class);
		} else if (17 <= time && time < 20) {
			ob2 = factory.getBean("evening", EveningGreetingImpl.class);
		} else {
			ob2 = factory.getBean("night",NightGreetingImpl.class);
		}

		ob2.greet();
	}

}
