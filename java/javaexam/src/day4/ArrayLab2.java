package day4;

public class ArrayLab2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ary = new int[10];
		int sum = 0;

		for (int i = 0; i < ary.length; i++) {
			sum += ary[i] = (int) (Math.random() * 17) + 4;
		}
		System.out.print("모든 원소의 값 : ");
		for (int i = 0; i < ary.length; i++) {
			System.out.print(ary[i]);
			if (i != ary.length - 1)
				System.out.print(",");
		}
		System.out.println();
		System.out.print("모든 원소의 합 : " + sum);
	}

}
