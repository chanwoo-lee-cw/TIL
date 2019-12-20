package day6;

public class MethodLab4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int arr1[] = { 10, 20, 30 };
		int arr2[] = { 100, 500, 300, 200, 400 };
		int arr3[] = { 1, 10, 3, 4, 5, 8, 7, 6, 9, 2 };

		System.out.printf("가장 큰 값은 %d 입니다.\n", MethodLab4(arr1));
		System.out.printf("가장 큰 값은 %d 입니다.\n", MethodLab4(arr2));
		System.out.printf("가장 큰 값은 %d 입니다.\n", MethodLab4(arr3));
	}

	public static int MethodLab4(int[] n) {
		int max = n[0];

		for (int data : n) {
			max = data > max ? data : max;
		}
		return max;
	}

}
