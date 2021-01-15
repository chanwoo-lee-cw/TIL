import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/12996
public class Main {
    public static void main(String[] args) throws IOException {
        int s;  // 불러야 되는 곡의 갯수
        int[] persons;  // 각자 불어야 되는 곡의 수
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        s = Integer.parseInt(st.nextToken());
        persons = new int[3];
        for (int i = 0; i < 3; i++) {
            persons[i] = Integer.parseInt(st.nextToken());
        }

        long[][][][] dp = new long[51][51][51][51];

        // 0이 아닌 수로 초기화 시켜줘야 한다. 반환 값이 0이 주로 되므로 쓸데 없는 재귀가 너무 많아진다.
        for (int i = 0; i < 51; i++) {
            for (int j = 0; j < 51; j++) {
                for (int k = 0; k < 51; k++)
                    Arrays.fill(dp[i][j][k], -1);
            }
        }
        System.out.println(memozation(dp, s, persons[0], persons[1], persons[2]));
    }

    // 다이나믹 프로그래밍으로 각자 더한다
    public static long memozation(long[][][][] dp, int s, int first, int second, int third) {
        if (s == 0 && first == 0 && second == 0 && third == 0)
            // 모든 곡이 끝났고 모두가 필요한 만큼 노래를 불렀을
            return 1;
        else if (s < 0 || first < 0 || second < 0 || third < 0)
            // 조건이 부족할 때
            return 0;
        else if (dp[s][first][second][third] != -1)
            // 만약 이미 한번 방문한 경우
            return dp[s][first][second][third];
        else if (first + second + third < s)
            // 계산해 봐도 모든 곡을 다 부르지 못하는 경우는 계산할 필요도 없다.
            return 0;
        long thisable = 0;
        // 3명이 다 같이 부른 경우
        thisable += memozation(dp, s - 1, first - 1, second - 1, third - 1);
        // 2명이 부른 경우
        thisable += memozation(dp, s - 1, first - 1, second - 1, third);
        thisable += memozation(dp, s - 1, first - 1, second, third - 1);
        thisable += memozation(dp, s - 1, first, second - 1, third - 1);
        // 혼자서 부른 경우
        thisable += memozation(dp, s - 1, first - 1, second, third);
        thisable += memozation(dp, s - 1, first, second - 1, third);
        thisable += memozation(dp, s - 1, first, second, third - 1);

        return dp[s][first][second][third] = thisable % 1000000007;
    }
}