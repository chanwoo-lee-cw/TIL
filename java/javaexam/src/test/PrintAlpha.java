package test;

public class PrintAlpha {
	public static void main(String[] args) {

		char alpha = 'A';

		for (int i = 1; i < 6; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(alpha++);
			}
			System.out.println();
		}

	}
}
