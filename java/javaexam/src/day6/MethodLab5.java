package day6;

public class MethodLab5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int r1[] = powerArray(2);
		int r2[] = powerArray(3);
		int r3[] = powerArray(4);

		for (int i = 0; i < 10; i++) {
			System.out.print(r1[i] + (i < 9 ? "," : "\n"));
		}
		for (int i = 0; i < 10; i++) {
			System.out.print(r2[i] + (i < 9 ? "," : "\n"));
		}
		for (int i = 0; i < 10; i++) {
			System.out.print(r3[i] + (i < 9 ? "," : "\n"));
		}
	}

	public static int[] powerArray(int n) {
		int[] arr = new int[10];
		int num = 0;
		for (int i = 0; i < 10; i++) {
			arr[i] = num += n;
		}
		return arr;
	}

}
