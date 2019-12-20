package day5;

public class TwoArrayTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a1 = new int[10];
		char a2[] = { '°¡', '³ª', '´Ù' };
		double a3[] = new double[5];

		System.out.println(a1.length + ":" + a2.length + ":" + a3.length);
		System.out.println(a1 + ":" + a1[0]);
		System.out.println(a2 + ":" + a2[0]);
		System.out.println(a3 + ":" + a3[0]);

		int[][] a4 = new int[5][12];
		System.out.println(a4 + ":" + a4[0]);
		System.out.println(a4 + ":" + a4.length);
		System.out.println(a4[0] + ":" + a4[0].length);
		System.out.println(a4[1] + ":" + a4[1].length);
		System.out.println(a4[2] + ":" + a4[2].length);
		System.out.println(a4[3] + ":" + a4[3].length);
		System.out.println(a4[4] + ":" + a4[4].length);
	}

}
