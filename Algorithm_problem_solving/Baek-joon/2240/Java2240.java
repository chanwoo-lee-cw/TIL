import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int T, W;
        int[] apple;
        Solution solution;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            T = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            apple = new int[T + 1];
            for (int i = 1; i <= T; i++) {
                apple[i] = Integer.parseInt(bf.readLine());
            }
            solution = new Solution(T, W, apple);
            System.out.println(solution.maxApple());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Solution {
    int t, w;
    int[] arr;

    public Solution(int t, int w, int[] arr) {
        this.t = t;
        this.w = w;
        this.arr = arr;
    }

    public int maxApple() {
        int[][] dp = new int[w + 1][t + 1];
        int answer;
        for (int i = 1; i <= t; i++) {
            dp[0][i] = arr[i] == 1 ? dp[0][i - 1] + 1 : dp[0][i - 1];
        }

        int curr;   // 현재 서있는 위치
        for (int i = 1; i <= w; i++) {
            curr = i % 2 == 1 ? 2 : 1;
            for (int j = 1; j <= t; j++) {
                dp[i][j] = dp[i - 1][j - 1] > dp[i][j - 1] ? dp[i - 1][j - 1] : dp[i][j - 1];
                if (arr[j] == curr) {
                    dp[i][j] = dp[i][j] + 1;
                }
            }
        }
        answer = 0;
        for (int i = 0; i <= w; i++) {
            answer = Math.max(answer, dp[i][t]);
        }
        return answer;
    }
}
