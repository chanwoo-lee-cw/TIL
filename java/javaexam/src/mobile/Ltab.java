package mobile;

public class Ltab extends Mobile {

	Ltab() {
	}

	Ltab(String mobileName, int batterySize, String osType) {
		super(mobileName, batterySize, osType);
	}

	@Override
	void operate(int time) {
		// TODO Auto-generated method stub
		setBatterySize(getBatterySize() - time * 10);
	}

	@Override
	void charge(int time) {
		// TODO Auto-generated method stub
		setBatterySize(getBatterySize() + time * 10);
	}

}
