package work;

public class Cargoplane extends Plane {

	Cargoplane() {

	}

	Cargoplane(String planeName, int fuelSize) {
		super(planeName, fuelSize);
	}

	@Override
	void flight(int distance) {
		// TODO Auto-generated method stub

		setFuelSize(getFuelSize() - distance * 5);
	}
}
