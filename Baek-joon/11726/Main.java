import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		int n;
		Scanner in = new Scanner(System.in);

		n = in.nextInt();

		int[] dp = new int[1001];

		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] % 10007;
		}

		System.out.print(dp[n]);
	}
}