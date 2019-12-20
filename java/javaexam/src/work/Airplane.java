package work;

public class Airplane extends Plane {

	Airplane() {

	}

	Airplane(String planeName, int fuelSize) {
		super(planeName, fuelSize);
	}

	@Override
	void flight(int distance) {
		// TODO Auto-generated method stub

		setFuelSize(getFuelSize() - distance * 3);
	}

}
