import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t, n;
        int maxCase = 0;
        ArrayList<Integer> testCase = new ArrayList<>();
        JailBreak jailBreak;
        try {
            t = Integer.parseInt(bf.readLine());
            for (int i = 0; i < t; i++) {
                n = Integer.parseInt(bf.readLine());
                testCase.add(n);
                maxCase = Math.max(n, maxCase);
            }
            jailBreak = new JailBreak(maxCase);

            for (int item : testCase) {
                System.out.println(jailBreak.getJailBreaker(item));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


/**
 * 게임을 최대 n회 진행하는 경우 탈출하는 사람의 수를 구한다.
 * n번방에 있는 사람은 n+1 회의 게임을 진행하는 경우에 영향을 받지 않기 때문에 최대 게임의 경우만 계산하면 된다.
 */
class JailBreak {
    int n;              // 상민이가 게임을 진행하는 횟수
    boolean[] jail;     // n의 게임을 진행했을 때 탈출 가능한 사람
    int[] jailBreak;    // n의 값일때 탈출한 사람의 수

    public JailBreak(int n) {
        this.n = n;
        this.jail = new boolean[n + 1];
        this.jailBreak = new int[n + 1];
        fill_jail();
    }

    /**
     * n의 게임을 진행했을 때 탈출 가능한 사람은 true로 진행한다.
     */
    public void fill_jail() {
        for (int i = 1; i < n + 1; i++) {
            for (int j = i; j < n + 1; j += i) {
                jail[j] = !jail[j];
            }
        }

        for (int i = 1; i < n + 1; i++) {
            int canJailBreak = 0;
            for (int j = 1; j <= i; j++) {
                if (jail[j]) {
                    canJailBreak++;
                }
            }
            jailBreak[i] = canJailBreak;
        }
    }

    /**
     * @param n 게임을 진행하는 횟수
     * @return 게임을 n회 진행했을 때 탈충하는 사람의 수
     */
    public int getJailBreaker(int n) {
        return jailBreak[n];
    }
}