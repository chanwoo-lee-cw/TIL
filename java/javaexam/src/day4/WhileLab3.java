package day4;

public class WhileLab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int ran = (int) (Math.random() * 31);
		char n = 'A' - 1;
		int count = 0;

		while (true) {
			if (27 <= ran && ran <= 30 || ran == 0)
				break;
			System.out.println((char) (n + ran) + "(" + ran + ")");
			count++;
			ran = (int) (Math.random() * 31);
		}
		System.out.print("수행 횟수는 " + count + "번 입니다.");
	}

}
