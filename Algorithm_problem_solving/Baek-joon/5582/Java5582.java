import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        String str1, str2;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        str1 = bf.readLine();
        str2 = bf.readLine();

        bf.close();

        int result = getLcsLength(str1, str2);
        System.out.println(result);
    }

    // LCS를 반환하는 함수
    private static int getLcsLength(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        int answer = 0;     // 가장 긴 LCS를 저장하는 변수
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    // 값이 다른 경우는 저장하지 않고 0으로 저장해도 된다.
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    answer = Math.max(dp[i][j], answer);
                }
            }
        }
        return answer;
    }
}