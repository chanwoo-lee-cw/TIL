import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;  //보도 블록의 길이
        String street;     // 보도 블록의 모양
        int[] dp;   // 각 칸 펼로 필요한 에너지의 총량

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        street = bf.readLine();

        dp = getMinJump(n, street);
        System.out.println(dp[n]);
    }

    /*
    각 위치마다 최소 에너지 소모값을 배열 반환해 주는 함수
    매개변수 : 문자열 길이, 문자열
    반환 값 : 각 위치마다 BOJ의 최소 값
     */
    private static int[] getMinJump(int n, String street) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // basecase
        dp[0] = -1;
        dp[1] = 0;
        // 점화식
        // dp[i] = Math.min(dp[i], dp[j] + (i - j) * (i - j));
        char currChr, findChr;
        for (int i = 2; i <= n; i++) {
            currChr = street.charAt(i - 1);
            if (currChr == 'B')
                findChr = 'J';
            else if (currChr == 'O')
                findChr = 'B';
            else
                findChr = 'O';
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] != -1 && street.charAt(j - 1) == findChr) {
                    dp[i] = Math.min(dp[i], dp[j] + (i - j) * (i - j));
                }
            }
            if (dp[i] == Integer.MAX_VALUE)
                // 만약 BOJ가 완성된게 없을 경우
                dp[i] = -1;
        }
        return dp;
    }
}