package day9;

public class Multiplication {
	private int dan;
	private int number;

	Multiplication() {
	}

	Multiplication(int dan) {
		this.dan = dan;
	}

	Multiplication(int dan, int number) {
		this.dan = dan;
		this.number = number;
	}

	public void printPart() {
		//num 0일때 dan의 전체 구구단 출력
		if (number == 0) {
			for (int n = 1; n <= 9; n++)
				System.out.print("\t" + dan + "*" + n + "=" + dan * n);
			System.out.println();
		} else {
			//아니면 그냥 dan이랑 nu 출력
			System.out.println(dan * number);
		}
	}
}
