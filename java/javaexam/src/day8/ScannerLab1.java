package day8;

import java.util.Scanner;

public class ScannerLab1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		int num1 = in.nextInt();
		int num2 = in.nextInt();
		String cal = in.next();
		in.close();
		int result;
		//여기서 if, else문 써서 탈출 하는 방법도 있음.
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
			System.out.print("+,-,/,* 를 입력하숑");
			return;
		}
		System.out.printf("%d 의 %d %s의 연산결과는 %d입니다", num1, num2, cal, result);
	}
}
