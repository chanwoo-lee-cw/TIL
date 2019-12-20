package work;

abstract class Plane {

	private String planeName;
	int fuelSize;

	Plane() {
	}

	Plane(String planeName, int fuelSize) {
		setPlaneName(planeName);
		setFuelSize(fuelSize);
	}

	public String getPlaneName() {
		return planeName;
	}

	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}

	public int getFuelSize() {
		return fuelSize;
	}

	public void setFuelSize(int fuelSize) {
		this.fuelSize = fuelSize;
	}

	public void refuel(int fuel) {
		this.fuelSize += fuel;
	}

	abstract void flight(int distance);

}
