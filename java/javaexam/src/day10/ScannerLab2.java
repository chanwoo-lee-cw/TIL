package day10;

import java.util.Scanner;

public class ScannerLab2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);

		int result;
		int num1;
		int num2;
		String cal;

		for (int i = 0; i < 3; i++) {

			result = 0;
			System.out.print("첫 번째 숫자를 입력하세요 : ");
			num1 = in.nextInt();
			System.out.print("두 번째 숫자를 입력하세요 : ");
			num2 = in.nextInt();
			System.out.print("연산자(+, -, *, /)를 입력하세요 : ");
			cal = in.next();

			// 여기서 if, else문 써서 탈출 하는 방법도 있음.
			switch (cal) {
			case "+":
				result = num1 + num2;
				break;
			case "-":
				result = num1 - num2;
				break;
			case "/":
				result = num1 / num2;
				break;
			case "*":
				result = num1 * num2;
				break;
			default:
				System.out.print("+,-,/,* 를 입력하숑\n\n");
				continue;
			}

			System.out.printf("%d 의 %d %s의 연산결과는 %d입니다\n\n", num1, num2, cal, result);
		}
		in.close();
	}
}
