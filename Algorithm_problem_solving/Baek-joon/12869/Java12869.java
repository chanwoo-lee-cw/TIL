import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12869
public class Main {
    // 뮤달이 scv를 공격하는 경우의 수
    public static int[][] mutal = {{9, 3, 1}, {9, 1, 3}, {3, 1, 9}, {1, 3, 9}, {1, 9, 3}, {3, 9, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] scv = new int[3];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 3; i++) {
            if (i < n)
                scv[i] = Integer.parseInt(st.nextToken());
            else
                scv[i] = 0;
        }
        bf.close();
        int[][][] dp = new int[61][61][61];
        System.out.println(mutalAttack(scv, dp));
    }

    // 재귀로 mutal이 공격하는 경우의 수를 구한다.
    public static int mutalAttack(int[] scv, int[][][] dp) {
        // 최소 공격 횟수를 반환해 주기 위한 변수
        int minAttack = Integer.MAX_VALUE;
        if (scv[0] <= 0 && scv[1] <= 0 && scv[2] <= 0)
            return 0;
        for (int i = 0; i < 3; i++) {
            if (scv[i] < 0)
                scv[i] = 0;
        }
        Arrays.sort(scv);
        if (dp[scv[0]][scv[1]][scv[2]] != 0)
            return dp[scv[0]][scv[1]][scv[2]];
        for (int[] attack : mutal) {
            minAttack = Math.min(minAttack,
                    mutalAttack(new int[]{scv[0] - attack[0], scv[1] - attack[1], scv[2] - attack[2]}, dp) + 1);
        }
        dp[scv[0]][scv[1]][scv[2]] = minAttack;
        return minAttack;
    }
}