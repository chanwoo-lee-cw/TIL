import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str1 = sc.nextLine();
		String str2 = sc.nextLine();

		sc.close();

		int[][] dp = new int[str1.length() + 1][str2.length() + 1];

		for (int i = 1; i <= str1.length(); i++) {
			char c1 = str1.charAt(i - 1);
			for (int j = 1; j <= str2.length(); j++) {
				char c2 = str2.charAt(j - 1);
				if (c1 == c2) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
//		System.out.println(dp[str1.length()][str2.length()]);
		Stack<Character> stack = new Stack<>();

		int i = str1.length();
		int j = str2.length();

		while (true) {
			if (dp[i][j] == 0)
				break;
			if (dp[i][j] > dp[i][j - 1] && dp[i][j] > dp[i - 1][j]) {
				stack.push(str2.charAt(j - 1));
				i--;
				j--;
			} else if (dp[i][j] > dp[i][j - 1])
				i--;
			else if (dp[i][j] > dp[i - 1][j])
				j--;
			else
				i--;
		}
		
		int alpha = stack.size();
		
		System.out.println(alpha);

		for (int k = 0; k < alpha; k++) {
			System.out.print(stack.pop());
		}
	}
}