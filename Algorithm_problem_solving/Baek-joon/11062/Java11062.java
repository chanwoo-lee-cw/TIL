import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11062
public class Java11062 {
    // cards : 카드의 수
    // strat, end : 현재 남아 있는 카드의 범위
    // turn : 현재 누구의 차례인가 - true 근우의 차례.
    // return : 근우가 얻을 수 있는 점수
    public static int[][][] dp;

    public static int playGame(int[] cards, int start, int end, boolean turn) {
        int answer = 0;
        if (dp[start][end][turn ? 1 : 0] != 0) {
            answer = dp[start][end][turn ? 1 : 0];
        } else if (start == end) {
            answer = dp[start][end][turn ? 1 : 0] = turn ? cards[start] : 0;
        } else {
            if (turn) {
                answer = dp[start][end][1] = Math.max(playGame(cards, start + 1, end, false) + cards[start],
                        playGame(cards, start, end - 1, false) + cards[end]);
            } else {
                answer = dp[start][end][0] = Math.min(playGame(cards, start + 1, end, true),
                        playGame(cards, start, end - 1, true));
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        {
            int n = 0;
            int[] cards = null;
            StringTokenizer st = null;
            for (int cases = 0; cases < t; cases++) {
                n = Integer.parseInt(bf.readLine());
                cards = new int[n];
                st = new StringTokenizer(bf.readLine());
                dp = new int[n][n][2];
                for (int i = 0; i < n; i++) {
                    cards[i] = Integer.parseInt(st.nextToken());
                }
                System.out.println(playGame(cards, 0, n - 1, true));
            }
        }
        bf.close();
    }
}