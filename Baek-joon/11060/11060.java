import java.util.Scanner;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine());

		int[] ai = new int[n];
		int[] dp = new int[n];

		Arrays.fill(dp, -1);

		String[] aList = in.nextLine().split(" ");

		for (int i = 0; i < n; i++) {
			ai[i] = Integer.parseInt(aList[i]);
		}

		dp[0] = 0;

		for (int i = 0; i < n - 1; i++) {
			if (dp[i] == -1)
				continue;
			for (int j = 1; j <= ai[i]; j++) {
				if (i+j >= n) break;
				if (dp[i + j] == -1)
					dp[i + j] = dp[i] + 1;
				else {
					dp[i+j] = Math.min(dp[i+j], dp[i]+1);
				}

			}
		}
		System.out.print(dp[n-1]);
//		for (int i=0;i<n;i++) {
//			System.out.print(ai[i] + " ");
//		}
	}

}