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

class JailBreak {
    int n;
    boolean[] jail;
    int[] jailBreak;

    public JailBreak(int n) {
        this.n = n;
        this.jail = new boolean[n + 1];
        this.jailBreak = new int[n + 1];
        fill_jail();
    }

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

    public int getJailBreaker(int n) {
        return jailBreak[n];
    }
}