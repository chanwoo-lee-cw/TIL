package day3;

public class ForLab6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final char AND = '&';

		int ran = (int) (Math.random() * 6) + 5;

		for (int i = 0; i < ran; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(AND);
			}
			System.out.println();
		}

	}

}
