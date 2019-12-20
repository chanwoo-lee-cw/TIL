package day4;

public class ArrayLab1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ary = new int[10];
		int ten = 0;
		for (int i = 0; i < ary.length; i++) {
			System.out.print(ary[i] + " ");
			ary[i] = ten += 10;
		}
		System.out.println();
		for (int i = 0; i < ary.length; i++) {
			System.out.print(ary[i] + " ");
		}
		System.out.println();
		for (int i = ary.length - 1; i >= 0; i--) {
			System.out.print(ary[i] + " ");
		}
		System.out.println();
		for (int i = 1; i < ary.length; i += 2) {
			System.out.print(ary[i] + " ");
		}
	}

}