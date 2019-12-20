package day3;

public class ForLab8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char alpha = 'A';

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(alpha++);
			}
			System.out.println();
		}
	}

}
