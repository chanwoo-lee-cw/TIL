import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int n;
        boolean[] primeNum;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                n = Integer.parseInt(bf.readLine());
                if (n == 0) {
                    break;
                }
                arr.add(n);
            }
            primeNum = eratos(2 * Collections.max(arr));
            for (int item : arr) {
                int cnt = 0;
                int doubleItem = 2 * item;
                for (int i = item + 1; i <= doubleItem; i++) {
                    if (primeNum[i])
                        cnt++;
                }
                sb.append(cnt);
                sb.append("\n");
            }
            System.out.print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean[] eratos(int n) {
        boolean[] primeNum = new boolean[n + 1];
        int sqrtN = (int) Math.sqrt(n);
        Arrays.fill(primeNum, true);
        primeNum[0] = false;
        primeNum[1] = false;

        for (int i = 2; i < sqrtN + 1; i++) {
            if (!primeNum[i])
                continue;
            for (int j = i * i; j < n + 1; j += i) {
                primeNum[j] = false;
            }
        }
        return primeNum;
    }
}