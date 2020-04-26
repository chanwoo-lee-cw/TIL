import java.util.Scanner;
import java.util.Arrays;

public class Main {
	public static int MAX(int a, int b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}

	public static void main(String args[]) {

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		int[] glass = new int[10001];
		int[] dp = new int[10001];

		for (int i = 1; i <= n; i++) {
			glass[i] = in.nextInt();
		}
		dp[0] = 0;
		dp[1] = glass[1];
		if (n >= 2) {
			dp[2] = MAX(dp[1] + glass[2], dp[0] + glass[2]);
			for (int i = 3; i <= n; i++) {
				dp[i] = MAX(dp[i - 1], MAX(dp[i - 3] + glass[i] + glass[i - 1], dp[i - 2] + glass[i]));
			}
		}

		System.out.println(dp[n]);

	}

}