import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /**
     * 에라토스 테네스의 체로 소수들을 찾는다.
     *
     * @param n n의 범위 까지의 소수
     * @return 소수인 수는 true로 체크되어있는 n까지의 배열
     */
    private static boolean[] eratos(int n) {
        boolean[] seive = new boolean[n + 1];
        Arrays.fill(seive, true);
        seive[0] = seive[1] = false;
        for (int i = 2; i < Math.sqrt(n) + 1; i++) {
            if (seive[i]) {
                for (int j = i * i; j < n + 1; j = j + i) {
                    seive[j] = false;
                }
            }
        }

        return seive;
    }

    private static int dfs(boolean[] seive, int start, int n, int sum) {
        if (sum > n) {
            return 0;
        } else if (sum == n) {
            return 1;
        } else {
            int answer = 0;
            int temp = 0;
            for (int i = start; i < n + 1; i++) {
                if (seive[i]) {
                    temp = dfs(seive, start + 1, n, sum + i);
                    if (temp == 0) {
                        break;
                    }
                }
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        boolean[] seive = eratos(n);

        System.out.println(dfs(seive, 2, n, 0));
    }
}