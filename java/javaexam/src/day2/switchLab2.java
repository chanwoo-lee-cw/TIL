package day2;

public class switchLab2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int ran = (int) (Math.random() * 5) + 1;
		int result;

		switch (ran) {
		case 1:
			result = 300 + 50;
			break;
		case 2:
			result = 300 - 50;
			break;
		case 3:
			result = 300 * 50;
			break;
		case 4:
			result = 300 / 50;
			break;
		default:
			result = 300 % 50;
			break;
		}
		System.out.println("°á°ú°ª : " + result);

	}

}
