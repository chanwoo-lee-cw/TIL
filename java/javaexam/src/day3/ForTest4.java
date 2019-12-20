package day3;

public class ForTest4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int sum = 0;

//		for(int n=1;n<=10;n++) sum += n;
//		
//		System.out.print("sum :" + sum);

		for (int n = 1; n <= 10; n++) {
			System.out.println("n :" + n * n);
		}
		System.out.println("----------------");
		for (int n = 10; n <= 20; n += 3) {
			System.out.println("n :" + n * n);
		}
		System.out.println("----------------");
		int alpha = 'A';
		for (int n = 0; n < 26; n++, alpha++) {
			System.out.print((char) alpha + " ");
		}
		System.out.println();
		System.out.println("----------------");
		for (char n = 'A'; n <= 'Z'; n++) {
			System.out.print(n + " ");
		}
	}

}
