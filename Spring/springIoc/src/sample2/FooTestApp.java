package sample2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FooTestApp {
	public static void main(String[] args) {
		ApplicationContext factory
    		= new ClassPathXmlApplicationContext("sample2/applicationContext.xml");
		System.out.println("**** Container Initialization End ****");
		
		System.out.println("\n\nScope(singleton/prototype)");
		InterFoo ob1=(InterFoo)factory.getBean("foo0");
		System.out.println(ob1);
		InterFoo ob2=(InterFoo)factory.getBean("foo0");
		System.out.println(ob2);
		InterFoo ob3=(InterFoo)factory.getBean("foo0");
		System.out.println(ob3);
		// 프로토 타입으로 지정하면 시작할때는 생성 안하고 있다가. getBean 으로 요청할때마다 생성한다.
		
		((ClassPathXmlApplicationContext)factory).close();
	}
}
