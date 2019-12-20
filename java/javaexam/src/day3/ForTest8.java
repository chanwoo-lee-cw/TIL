package day3;

public class ForTest8 {

	public static void main(String args[]) {
		System.out.print("{");
		for (int i=0; i<8;i++) {
			System.out.print((int)(Math.random()*100)+1 + ", ");
		}
		System.out.print("}");
	}
}
