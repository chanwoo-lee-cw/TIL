package day3;

public class WileLab2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int num1;
		int num2;
		do {
			num1 = (int) (Math.random() * 6) + 1;
			num2 = (int) (Math.random() * 6) + 1;
			if (num1>num2) System.out.println(+num1+"이" + num2+"보다 크다.");
			else if (num1<num2) System.out.println(+num1+"이" + num2+"보다 작다.");
		} while(num1!=num2);
		System.out.println("게임 끝");
	}

}
