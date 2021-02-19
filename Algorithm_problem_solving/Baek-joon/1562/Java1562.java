import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        StiarNumToN stair;

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            stair = new StiarNumToN(Integer.parseInt(bf.readLine()));

            System.out.println(stair.getStiarNumToN());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

class StiarNumToN {
    int n;
    boolean[] numUsed;
    long[][][] dp;

    public StiarNumToN(int n) {
        this.n = n;
        numUsed = new boolean[10];
        dp = new long[n + 1][10][1 << 10];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < 10; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
    }

    public long getStiarNumToN() {
        long answer = 0;
        for (int i = 1; i < 10; i++) {
            answer = (answer + makeStairNum(1, i)) % 1000000000;
        }
        return answer;
    }

    public long makeStairNum(int digits, int lastNum) {
        long answer = 0;
        int currBinary = getbinary();
        if (dp[digits][lastNum][currBinary] != -1) {
            return dp[digits][lastNum][getbinary()];
        }
        numUsed[lastNum] = true;
        if (n == digits) {
            for (int i = 0; i < 10; i++) {
                if (!numUsed[i]) {
                    numUsed[lastNum] = false;
                    return 0;
                }
            }
            answer = 1;
        } else {
            if (lastNum == 0) {
                answer = (answer + makeStairNum(digits + 1, 1)) % 1000000000;
            } else if (lastNum == 9) {
                answer = (answer + makeStairNum(digits + 1, 8)) % 1000000000;
            } else {
                answer = (answer + makeStairNum(digits + 1, lastNum - 1) + makeStairNum(digits + 1, lastNum + 1)) % 1000000000;
            }
        }
        numUsed[lastNum] = false;
        dp[digits][lastNum][getbinary()] = answer;
        return answer;
    }

    public int getbinary() {
        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer = answer << 1;
            if (numUsed[i])
                answer++;
        }
        return answer;
    }
}
