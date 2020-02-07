package sampleanno05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("carTire") // 기본값 : car
public class Car {
	@Autowired(required = false) // default = true
	// 없으면 null 이 설정된다. 기본값은 true라 없으면 에러나는 것
	private Tire tire;

	public Car() {
		System.out.println("Tire - Create object 0");
	}

	@Autowired(required = false)
	// 오토와이어가 어디에 붙어 있느냐에 따라 어떤 생성자가 실행되느냐가 결정된다.
	public Car(Tire tire) {
		System.out.println("Tire - Create object 1");
	}

	public void drive() {
		if (tire != null)
			System.out.println("Nice made of "+tire.getBrand()+"s car.");
		else
			System.out.println("tire --> "+tire);
	}
}
