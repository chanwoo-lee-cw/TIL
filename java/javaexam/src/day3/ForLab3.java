package day3;

public class ForLab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int num1 = (int) (Math.random() * 10) + 1;
		int num2 = (int) (Math.random() * 11) + 30;
		int sum = 0;
		for (int i = num1; i <= num2; i += 2) {
			sum += i;
		}
		System.out.print(" X 부터 Y 까지의 짝수의 합 :" + sum);
	}

}
