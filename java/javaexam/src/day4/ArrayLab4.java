package day4;

public class ArrayLab4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ary = new int[10];
		char[] cry = new char[10];
		char temp = 'A'-1;
		for (int i = 0; i < ary.length; i++) {
			ary[i] = (int) (Math.random() * 26) + 1;
			cry[i] = (char) (temp +ary[i]);
			System.out.print(ary[i]);
			if (i != ary.length - 1)
				System.out.print(",");
			else System.out.println();
		}
		
		for (int i = 0; i < ary.length; i++) {
			System.out.print(cry[i]);
			if (i != ary.length - 1)
				System.out.print(",");
		}
		
	}

}
