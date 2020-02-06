package sample1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class HelloSpringApp {
	public static void main(String[] args) {
		ApplicationContext factory
		    	= new ClassPathXmlApplicationContext("sample1/beans.xml");
		// ApplicationContext 객체를 beans.xml를 등록하면서 생성하고 있다.
		// 무조건 src를 기준으로 찾기 때문에 패키지 명도 같이 언급해줘야한다.
		// 미리 Beans.xml 기반으로 객체를 생성해 놓고 호출 되는 순간 등록하는 것이다.
		System.out.println("**** Container Initialization End ****");
		
		MessageBean bean=(MessageBean)factory.getBean("messageBean");
		bean.sayHello();                 
		bean.sayHello("banana", 1500);   
		System.out.println(bean);
		System.out.println(factory.getBean("messageBean"));
		System.out.println(factory.getBean("messageBean"));
		// 스프링 컨테이너가 자바 객체를 관리하는 것은 객체를 하나만 관리한다. 즉, 참조값이 모두 같다.
		((ClassPathXmlApplicationContext)factory).close();
		
	}
}





