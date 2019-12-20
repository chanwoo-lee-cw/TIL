package day2;

public class ConditionOPerLab {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int ran = (int) (Math.random() * 5) + 1;
		int result;

		if (ran == 1) {
			result = 300 + 50;
		} else if (ran == 2) {
			result = 300 - 50;
		} else if (ran == 3) {
			result = 300 * 50;
		} else if (ran == 4) {
			result = 300 / 50;
		} else {
			result = 300 % 50;
		}
		System.out.println("°á°ú°ª : " + result);
	}

}
