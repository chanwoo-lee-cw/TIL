package day3;

public class WileLab1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int num = (int) (Math.random() * 6) + 5;
		System.out.println("[for 결과]");
		for (int i = 1; i <=
				num; i++) {
			System.out.println(i + "->" + i * i);
		}
		int count = 1;
		System.out.println("[while 결과]");
		while (count <= num) {
			System.out.println(count++ + "->" + count * count);
		}
	}

}
