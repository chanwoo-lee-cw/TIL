package day5;

public class TwoArrayLab2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char[][] arr = { { 'B', 'C', 'A', 'A' }, { 'C', 'C', 'B', 'B' }, { 'D', 'A', 'A', 'D' } };

		int[] count = new int[4];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				switch (arr[i][j]) {
				case 'A':
					count[0]++;
					break;
				case 'B':
					count[1]++;
					break;
				case 'C':
					count[2]++;
					break;
				case 'D':
					count[3]++;
					break;
				}
			}
		}

		System.out.println("A는" + count[0] + "개 입니다.");
		System.out.println("B는" + count[1] + "개 입니다.");
		System.out.println("C는" + count[2] + "개 입니다.");
		System.out.println("D는" + count[3] + "개 입니다.");

	}

}
