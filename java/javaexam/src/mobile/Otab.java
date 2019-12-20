package mobile;

public class Otab extends Mobile {

	Otab() {
	}

	Otab(String mobileName, int batterySize, String osType) {
		super(mobileName, batterySize, osType);
	}

	@Override
	void operate(int time) {
		// TODO Auto-generated method stub
		setBatterySize(getBatterySize() - time * 12);
	}

	@Override
	void charge(int time) {
		// TODO Auto-generated method stub
		setBatterySize(getBatterySize() + time * 8);
	}

}
