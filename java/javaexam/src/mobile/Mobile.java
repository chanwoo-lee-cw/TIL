package mobile;

abstract class Mobile {
	private String mobileName;
	private int batterySize;
	private String osType;

	Mobile() {
	}

	Mobile(String mobileName, int batterySize, String osType) {

		setMobileName(mobileName);
		setBatterySize(batterySize);
		setOsType(osType);
	}

	public String getMobileName() {
		return mobileName;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

	public int getBatterySize() {
		return batterySize;
	}

	public void setBatterySize(int batterySize) {
		this.batterySize = batterySize;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	abstract void operate(int time);

	abstract void charge(int time);

	public String printMobile() {
		return String.format("%-10s%10d%15s", mobileName, batterySize, osType);
	}
}
