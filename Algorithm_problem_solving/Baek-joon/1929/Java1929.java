import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int m, n;
        try {
            st = new StringTokenizer(bf.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            Eratos eratos = new Eratos(n);
            eratos.findPrime();
            eratos.printPrimeFromM(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Eratos {
    private int n;
    private boolean[] dp;

    public Eratos(int n) {
        this.n = n;
        this.dp = new boolean[n + 1];
        Arrays.fill(dp, true);
    }

    public void findPrime() {
        int sqrtN = (int) Math.sqrt(n);
        dp[0] = false;
        dp[1] = false;
        for (int i = 2; i <= sqrtN; i++) {
            if (dp[i]) {
                for (int j = (int) Math.pow(i, 2); j <= this.n; j += i) {
                    dp[j] = false;
                }
            }
        }
    }

    public void printPrimeFromM(int m) {
        StringBuilder sb = new StringBuilder();
        for (int i = m; i <= n; i++) {
            if (dp[i]) {
                sb.append(i);
                sb.append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}