import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/1509
public class Main {
    public static void main(String[] args) {
        Palindrome palindrome;
        String input;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            input = bf.readLine();

            palindrome = new Palindrome(input);
            palindrome.makeDP();
            System.out.println(palindrome.getMinPalindrome());

            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

// 가장 긴 팰린드롬 분할을 위한 객체
class Palindrome {
    private char[] sentence;    // 입력된 문장
    private int n;      // 팰린드롬 문장의 길이
    private boolean[][] dp;     // dp[i][j] - setence i~j의 팰린드롬 여부
    private int[] minSplit;     // minSplit[i] - i~n까지의 문장의 가장 적은 수로 분할 할 수 있는 팰린드롬

    public Palindrome(String sentence) {
        this.sentence = new char[sentence.length() + 1];
        this.n = sentence.length();
        for (int i = 0; i < this.n; i++) {
            this.sentence[i + 1] = sentence.charAt(i);
        }
    }

    /*
    dp배열을 채운다. dp[i][j] - setence i~j의 팰린드롬 여부
     */
    public void makeDP() {
        this.dp = new boolean[n + 1][n + 1];
        dp[1][1] = true;
        for (int j = 2; j < n + 1; j++) {
            dp[j][j] = true;
            if (sentence[j - 1] == sentence[j])
                dp[j - 1][j] = true;
            for (int i = 1; i < j; i++) {
                if (sentence[i] == sentence[j] && dp[i + 1][j - 1])
                    dp[i][j] = true;
            }
        }
    }

    /*
    sentence를 분할된 문장이 전부 팰린드롬인 최소 갯수를 리턴한다.
    */
    public int getMinPalindrome() {
        /*
        만약 j까지에서 나눌 수 있는 최대 팰린드롬의 갯수를 구하고 싶으면
        즉, dp[0][i], dp[i][j]로 나눈다.
        dp[i][j]가 팰린드롬이라면 즉, dp[0][j]까지의 최소 팰린드롬의 갯수는 dp[0][i]까지의 최소 팰린드롬 갯수의+1 한것과 같다
         */
        minSplit = new int[n + 1];
        Arrays.fill(minSplit, n + 1);
        // basecase
        minSplit[0] = 0;
        // 점화식 - minSplit[j] = min(minSplit[j], minSplit[i - 1] + 1)
        for (int j = 1; j < n + 1; j++) {
            for (int i = 1; i <= j; i++) {
                if (dp[i][j]) {
                    minSplit[j] = Math.min(minSplit[j], minSplit[i - 1] + 1);
                }
            }
        }
        return minSplit[n];
    }
}