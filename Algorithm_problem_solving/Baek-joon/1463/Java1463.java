import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Java1463 {

    public static int getMinCalculation(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 0, n + 1, Integer.MAX_VALUE);
        Queue<NumwithCnt> que = new LinkedList<>();
        que.add(new NumwithCnt(1, 0));
        while (!que.isEmpty()) {
            NumwithCnt curr = que.poll();
            if (dp[curr.num] != Integer.MAX_VALUE)
                continue;
            dp[curr.num] = curr.cnt;
            if (curr.num == n)
                break;
            if (curr.num * 2 <= n && dp[curr.num * 2] == Integer.MAX_VALUE)
                que.add(new NumwithCnt(curr.num * 2, curr.cnt + 1));
            if (curr.num * 3 <= n && dp[curr.num * 3] == Integer.MAX_VALUE)
                que.add(new NumwithCnt(curr.num * 3, curr.cnt + 1));
            if (curr.num + 1 <= n && dp[curr.num + 1] == Integer.MAX_VALUE)
                que.add(new NumwithCnt(curr.num + 1, curr.cnt + 1));
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(getMinCalculation(n));
    }
}

class NumwithCnt {
    int num;
    int cnt;

    NumwithCnt(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }
}