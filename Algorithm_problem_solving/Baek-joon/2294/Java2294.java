import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2294
public class Java2294 {
    public static int getNeedCoin(int n, int k, int[] coin) {
        int[][] needCoinCase = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(needCoinCase[i], Integer.MAX_VALUE - 1);
        }
//        일단 최소 단위의 동전인 경우
//        coin[0]의 값까지 전부 -1 그 이후엔 coin[0]의 배수마다 1씩 증가
//        그 다음단위 동전인 경우 case[i][now-coin[i]]+1 or case[i-1][now] 둘 중 더 작은 것
        for (int j = 0; j <= k; j += coin[0]) {
            if (j % coin[0] != 0) {
                continue;
            }
            needCoinCase[0][j] = j / coin[0];
        }
        for (int i = 0; i < n; i++) {
            needCoinCase[i][0] = 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j < coin[i])
                    needCoinCase[i][j] = needCoinCase[i - 1][j];
                else
                    needCoinCase[i][j] = Math.min(needCoinCase[i][j - coin[i]] + 1, needCoinCase[i - 1][j]);
            }
        }

        return needCoinCase[n - 1][k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = null;
        int[] coin = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(bf.readLine());
        }
        bf.close();
        Arrays.sort(coin);
        int result = getNeedCoin(n, k, coin);
        if (result >= Integer.MAX_VALUE - 1)
            System.out.println(-1);
        else
            System.out.println(result);
    }
}