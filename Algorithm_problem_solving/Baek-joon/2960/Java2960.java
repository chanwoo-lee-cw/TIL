import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int n, k;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            Erasto erasto = new Erasto();
            System.out.println(erasto.kthDelete(n, k));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Erasto {
    public int kthDelete(int n, int k) {
        int sqrtN = (int) Math.sqrt(n);
        boolean[] primeNum = new boolean[n + 1];
        Arrays.fill(primeNum, true);
        int cnt = 0;
        for (int i = 2; i < n + 1; i++) {
            for (int j = i; j < n + 1; j += i) {
                if (primeNum[j]) {
                    cnt++;
                    primeNum[j] = false;
                    if (cnt == k)
                        return j;
                }
            }
        }
        return 0;
    }
}
