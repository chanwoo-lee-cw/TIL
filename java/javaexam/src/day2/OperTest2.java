package day2;

public class OperTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int su1 = 10;
		int su2 = 10;
		System.out.println(su1);
		System.out.println(++su1);
		System.out.println(++su1);
		System.out.println(--su1);

		System.out.println(su2);
		System.out.println(su2++); // 10
		System.out.println(su2++); // 11
		System.out.println(su2--); // 11

		int su3 = 10;
		System.out.println(su3); // 10
		System.out.println(su3++); // 10
		System.out.println(++su3); // 12
		System.out.println(su3++); // 12
		System.out.println(--su3); // 12
		System.out.println(--su3); // 11
	}

}
