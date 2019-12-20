package day6;

public class MethodLab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for (int i = 0; i < 5; i++) {
			System.out.print(getRandom(10));
			if (i < 4)
				System.out.print(",");
			else
				System.out.println();
		}
		for (int i = 0; i < 10; i++) {
			System.out.print(getRandom(10, 20));
			if (i < 9)
				System.out.print(",");
			else
				System.out.println();
		}
	}

	public static int getRandom(int n) {
		return (int) (Math.random() * n) + 1;
	}

	public static int getRandom(int n1, int n2) {
		return (int) (Math.random() * (n2 - n1 + 1)) + n1;
	}

}
