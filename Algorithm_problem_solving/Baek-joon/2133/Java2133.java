import java.util.Scanner;

// https://www.acmicpc.net/problem/2133
public class Java2133 {
    public static int[] dp;

    public static int memozation(int n) {
        if (dp[n] != 0) {
            return dp[n];
        } else if (n == 0) {
            dp[n] = 1;
        } else if (n == 2) {
            dp[n] = 3;
        } else if (n % 2 != 0) {
            return 0;
        } else {
            dp[n] = memozation(n - 2) * 3;
            for (int i = 4; i <= n; i += 2) {
                dp[n] += memozation(n - i) * 2;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

//        n == 1 : 0
//        n == 2 : 3
//        n == 3 : 0
//        n == 4 : 11

//        일단 n이 2의 배수가 아닐깨는 0
//        n이 2의 배수 마다 새로운 모양이 생긴다.
        dp = new int[n + 1];
        System.out.println(memozation(n));
    }
}