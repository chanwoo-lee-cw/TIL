package day5;

public class TwoArrayLab1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] arr = new int[4][4];

		int a = 8;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = a += 2;
			}
		}

		System.out.println("1행 1열의 데이터 : " + arr[0][0]);
		System.out.println("3행 4열의 데이터 : " + arr[2][3]);
		System.out.println("행의 갯수 : " + arr.length);
		System.out.println("열의 갯수 : " + arr[0].length);
		System.out.print("3행의 데이터들 : ");
		for (int j = 0; j < arr[2].length; j++) {
			System.out.print(arr[2][j] + " ");
		}
		System.out.println();
		System.out.print("2열의 데이터들 : ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i][1] + " ");
		}
		System.out.println();
		System.out.print("왼쪽 대각선 데이터들 : ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i][i] + " ");
		}
		System.out.println();
		System.out.print("오른쪽 대각선 데이터들 : ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i][arr.length - i - 1] + " ");
		}
	}

}
