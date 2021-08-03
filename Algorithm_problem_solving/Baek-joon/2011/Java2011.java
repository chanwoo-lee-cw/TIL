import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2011
public class Main {
    public static void main(String[] args) {
        String cryperText;
        Decrypt decrypt = new Decrypt();
        int answer = 0;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            cryperText = bf.readLine();
            answer = decrypt.decrypt(cryperText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(answer);
    }
}

class Decrypt {
    public int decrypt(String text) {
        int textLen = text.length();
        int[] dp = new int[textLen + 1];

        dp[0] = 1;
        dp[1] = text.charAt(textLen - 1) != '0' ? 1 : 0;
        char curr;
        for (int i = 2; i <= textLen; i++) {
            curr = text.charAt(textLen - i);
            dp[i] = dp[i - 1];
            if (curr == '1') {
                dp[i] += dp[i - 2];
            } else if (curr == '2' && text.charAt(textLen - i + 1) - '0' <= 6) {
                dp[i] += dp[i - 2];
            } else if (curr == '0') {
                dp[i] = 0;
                if (text.charAt(textLen - i + 1) == 0) {
                    return 0;
                }
            }
            dp[i] %= 1000000;
        }
        return dp[textLen];
    }
}