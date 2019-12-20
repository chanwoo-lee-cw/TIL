package day2;

public class OPerAndLap {

	public static void main(String[] args) {
		int grade = (int) (Math.random() * 6) + 1;

		if (1 == grade || 2 == grade || 3 == grade)
			System.out.print(grade + "학년은 저학년입니다.");
		else
			System.out.print(grade + "학년은 고학년입니다.");
	}
}
