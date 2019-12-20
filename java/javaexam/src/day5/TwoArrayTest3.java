package day5;

public class TwoArrayTest3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] twoA = { 
				{ 1, 2, 3 },
				{ 4, 5, 6 },
				{ 7, 8, 9 } };
		System.out.print(twoA);
		System.out.print(twoA[0]);
		System.out.print(twoA[0][0]);
		System.out.println();

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				System.out.print(twoA[row][col] + "\t");
			}
			System.out.println();
		}
	}

}
