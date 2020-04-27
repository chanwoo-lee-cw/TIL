import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		long [][] dp = new long[101][21];
		int[] line = new int[101];

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		for (int i = 0; i < n; i++) {
			line[i] = in.nextInt();
		}
		
		dp[0][line[0]]++;
		for (int i = 1; i < n - 1; i++) {
			for (int j = 0; j <= 20; j++) {
				if (dp[i - 1][j] != 0) {
					if (j + line[i] <= 20)
						dp[i][j + line[i]] += dp[i - 1][j];
					if (j - line[i] >= 0)
						dp[i][j - line[i]] += dp[i - 1][j];
				}
			}
		}
//		for (int j = 0; j < n - 1; j++) {
//			System.out.println(line[j]);
//			for (int i = 0; i <= 20; i++) {
//				System.out.printf("%-3d", i);
//			}
//			System.out.println();
//			for (int i = 0; i <= 20; i++) {
//				System.out.printf("%-3d", dp[j][i]);
//			}
//			System.out.println();
//			System.out.println();
//		}
		System.out.println(dp[n - 2][line[n - 1]]);
	}

}