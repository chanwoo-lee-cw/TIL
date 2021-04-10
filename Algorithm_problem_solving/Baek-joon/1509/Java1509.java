import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    s부터 시작 되는 문장에서 dfs방식으로 최소로 나눌 수 있는 경우의 수를 리턴
    */
    public int getMinPalindrome(int s) {
        if (s > n)
            return 0;
        else if (minSplit[s] != 0)
            return minSplit[s];
        int answer = Integer.MAX_VALUE;
        for (int i = s; i <= n; i++) {
            if (dp[s][i])
                answer = Math.min(answer, getMinPalindrome(i + 1) + 1);
        }
        return answer;
    }

    /*
    dfs 방식으로 전체 문장에서 가장 적은 수로 나눌 수 있는 팰린드롬을 리턴
    */
    public int getMinPalindrome() {
        minSplit = new int[n + 1];
        return getMinPalindrome(1);
    }
}