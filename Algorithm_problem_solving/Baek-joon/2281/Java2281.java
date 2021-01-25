import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2281
public class Main {
    /*
    dp에 저장할 것 -> 빈칸의 갯수
    일단, 작은 경우의 수부터 생각을 해보면...
    이름이 1개 있을 때
    1개 넣고 빈간 갯수
    2개 있을 때, 경우의 수 2가지
    옆에 붙히거나 밑에 붙히거나
    ... 이건 아닌거 같고...
    이름의 갯수 1천개니 dfs인가...
     */

    private static int n;  // 노트의 세로 길이이자, 이름의 갯수
    private static int m;  // 노드의 가로 길이
    private static int[] names; // 적힐 이름의 갯수
    private static int[][] dp; // 적힐 이름의 갯수, dp[length][pos]
        // dp에서 length를 저장하는 이유. 이 줄에 몇번째로 써졌냐가 아니라
        // 이름이 어디에 적혀져 있느냐를 기준으로 삼는다.
        // 행은 고려할 필요가 없는데 저장된 시점에서 어디에 적혀졌느냐가 최소값으로 저장되어 있기 때문이다.

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        names = new int[n + 1];
        dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            names[i] = Integer.parseInt(bf.readLine());
        }

        for (int i = 1; i <= n; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        st = null;
        bf.close();

        System.out.println(getBlankCnt(2, 1, names[1]));
    }

    /*
    빈칸의 갯수를 출력하는 함수.
    이름을 적을 수 있는 경우의 수
    1. 옆에 이름을 젓는다.
        1. 빈칸이 있는 경우 적는다.
        2. break
    2. brank에 남은 빈칸의 제곱을 더한 다음에아래에 이름을젓는다.

    매개변수, 현재 이름의 위치, 현재 칸의 남은 빈칸의 갯수
     */
    private static int getBlankCnt(int pos, int line, int length) {
        if (pos > n)
            return 0;
        else if (dp[pos][length] != Integer.MAX_VALUE)
            return dp[pos][length];
        // else
        if (m - length >= names[pos] + 1) {
            dp[pos][length] = Math.min(dp[pos][length], getBlankCnt(pos + 1, line, length + names[pos] + 1));
        }
        dp[pos][length] = Math.min(dp[pos][length], getBlankCnt(pos + 1, line + 1, names[pos]) + (m - length) * (m - length));
        return dp[pos][length];
    }
}