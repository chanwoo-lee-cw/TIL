package sampleanno05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("carTire") // 기본값 : car
public class Car {
	@Autowired(required = false) // default = true
	private Tire tire;

	public Car() {
		System.out.println("Tire - Create object 0");
	}

	@Autowired(required = false)
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
