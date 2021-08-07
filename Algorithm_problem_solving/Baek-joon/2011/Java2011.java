import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2011
public class Main {
    public static void main(String[] args) {
        String cryperText;      // 암호화된 텍스트
        Decrypt decrypt = new Decrypt();    // 암호화된 파일을 풀어주는 객체
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
    /*
    암호화된 숫자 텍스트를 넣으면 그것을 변환 가능한 경우의 수 반환
    */
    public int decrypt(String text) {
        int textLen = text.length();    // 텍스트의 길이
        int[] dp = new int[textLen + 1];    // 텍스트를 역순으로 뒤집어서 n개의 길이 일때 변환 가능한 경우의 수

        dp[0] = 1;
        dp[1] = text.charAt(textLen - 1) != '0' ? 1 : 0;    // 0이 아니년 경우를 제외하고 1로 세팅
        char curr;
        for (int i = 2; i <= textLen; i++) {
            curr = text.charAt(textLen - i);
            dp[i] = dp[i - 1];
            if (curr == '1') {
                // 현재 수가 1인 경우 뒤의 수에 따라 10의 배수 가능
                dp[i] += dp[i - 2];
            } else if (curr == '2' && text.charAt(textLen - i + 1) - '0' <= 6) {
                // 현재 수가 2인 경우 뒤의 수에 따라 20~26까지 가능
                dp[i] += dp[i - 2];
            } else if (curr == '0') {
                // 2 초과된 수이면 경우의 수 그대로 추가
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