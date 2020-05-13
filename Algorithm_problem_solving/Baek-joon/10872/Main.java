import java.io.*;

// https://www.acmicpc.net/problem/10872

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bf.readLine());

		System.out.print(factorial(n));
	}

// 	재귀의 형식으로 계산한다.
	public static int factorial(int n) {
//		System.out.println(n);
//		0!은 1이다.
		if (n <= 1)
			return 1;
		return n * factorial(n - 1);
	}

}