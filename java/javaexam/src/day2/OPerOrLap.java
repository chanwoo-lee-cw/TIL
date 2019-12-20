package day2;

public class OPerOrLap {

	public static void main(String[] args) {
		int grade = (int) (Math.random() * 6) + 1;

		if (1 <= grade && grade <= 3)
			System.out.print(grade + "학년은 저학년입니다.");
		else if (4 <= grade && grade <= 6)
			System.out.print(grade + "학년은 고학년입니다.");
	}
}
