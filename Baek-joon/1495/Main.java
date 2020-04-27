import java.util.Scanner;

public class Main {
	public static void main(String args[]) {

		int n, start, maximum;

		Scanner in = new Scanner(System.in);
		String arr[] = in.nextLine().split(" ");

		n = Integer.parseInt(arr[0]);
		start = Integer.parseInt(arr[1]);
		maximum = Integer.parseInt(arr[2]);

		int[] volum = new int[n + 1];

		arr = in.nextLine().split(" ");
		for (int i = 1; i <= n; i++) {
			volum[i] = Integer.parseInt(arr[i - 1]);
		}

		boolean[][] dp = new boolean[n + 1][maximum + 1];

		dp[0][start] = true;
		for (int i = 1; i <= n; i++) {
//			boolean count = false;
			for (int j = 0; j <= maximum; j++) {
				if (dp[i - 1][j]) {
					if (j + volum[i] <= maximum) {
						dp[i][j + volum[i]] = true;
//						count = true;
					}
					if (j - volum[i] >= 0) {
						dp[i][j - volum[i]] = true;
//						count = true;
					}
				}
			}
//			if (count == false) {
//				System.out.println("-1");
//				break;
//			}
		}
		

		int max=-1;
		for (int i = 0; i <= maximum; i++) {
			if (dp[n][i])
				max = i;
		}
		if(max>-1)	System.out.print(max);
		else	System.out.print("-1");
	}

}