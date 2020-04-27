import java.util.Scanner;

public class Main {
	static long dp[][][];
	final static int MAX = 20;

	public static void main(String args[]) {

		dp = new long[MAX+1][MAX+1][MAX+1];
		Scanner in = new Scanner(System.in);

		while(true) {
			String[] line = in.nextLine().split(" ");
			
			int a=Integer.parseInt(line[0]);
			int b=Integer.parseInt(line[1]);
			int c=Integer.parseInt(line[2]);
			
			if(a==-1&&b==-1&&c==-1)
				break;
			
			System.out.printf("w(%d, %d, %d) =%d\n",a,b,c,w(a,b,c));
			
			
		}
	}

	static long w(int a, int b, int c) {
		long result;
		if (0 <= a && a <= MAX && 0 <= b && b <= MAX && 0 <= c && c <= MAX) {
			if (dp[a][b][c] != 0)
				return dp[a][b][c];
		}
		if (a <= 0 || b <= 0 || c <= 0) {
			return 1;
		} else if (a > 20 || b > 20 || c > 20) {
			result = w(20, 20, 20);
		} else if (a < b && b < c) {
			result = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
		} else {
			result = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
		}
		if (0 <= a && a <= MAX && 0 <= b && b <= MAX && 0 <= c && c <= MAX)
			dp[a][b][c] = result;
		return result;
	}
}