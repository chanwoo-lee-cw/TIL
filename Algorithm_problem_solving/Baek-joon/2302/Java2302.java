import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Theater theater;    // 좌석에 앉는 경우의 수를 구하는 객체
        int n, m;   // 좌석의 수와 vip석의 갯수
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(bf.readLine());
            m = Integer.parseInt(bf.readLine());
            theater = new Theater(n, m);
            theater.fillVIP(bf);
            System.out.println(theater.peopleSeat());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Theater {
    private int n;      // 좌석의 갯수
    private int m;      // 고정석의 갯수
    private boolean[] vipList;      // 해당 자리가 vip전용이라면 true
    private long[] dp;       // 앉을 수 있는 경우의

    public Theater(int n, int m) {
        this.n = n;
        this.m = m;
        this.vipList = new boolean[n + 2];
    }

    /*
    예약된 vip좌석을 채우는 코드
     */
    public void fillVIP(BufferedReader bf) throws IOException {
        int vip;
        for (int i = 0; i < m; i++) {
            vip = Integer.parseInt(bf.readLine());
            vipList[vip] = true;
        }
    }

    /*
    n번 자리까지 앉을 수 있는 경우의 수
     */
    public long peopleSeat() {
        dp = new long[n + 2];

        // basecase
        dp[1] = 1;
        dp[2] = (!vipList[1] && !vipList[2]) ? 2 : 1;
        
        /*
        n번 자리에 사람이 앉는 경우의 수는 그 자리에 예약한 사람이 앉는 경우 dp[n-1]과 같고
        n-1과 n번 자리의 사람이 자리를 바꿔 앉는 경우와 같다.
        점화식:
        인접한 vip석이 한자리도 없는 경우 : dp[i] = dp[i - 1] + dp[i - 2];
        한 자리 전이 vip석인 경우 : dp[i] = dp[i - 2];
        두 자리 전이 vip 석인 경우 : dp[i - 1] * 2;
        */
        for (int i = 3; i <= n; i++) {
            if (!vipList[i - 1] && !vipList[i - 2])
                dp[i] = dp[i - 1] + dp[i - 2];
            else if (vipList[i - 1])
                dp[i] = dp[i - 2];
            else if (vipList[i - 2])
                dp[i] = dp[i - 1] * 2;
        }
        return dp[n];
    }
}

