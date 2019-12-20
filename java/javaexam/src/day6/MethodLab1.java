package day6;

public class MethodLab1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		charFor('@', 3);
		charFor('%', 4);
		charFor('A', 5);
		charFor('°¡', 3);
	}

	static void charFor(char alpha, int num) {
		for (int i = 0; i < num; i++) {
			System.out.print(alpha);
		}
		System.out.println();
	}

}
