import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n, k;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            HashSet<Integer> set = new HashSet<>();
            for (int i = 1; i <= n / 2; i++) {
                if (n % i == 0) {
                    set.add(i);
                    set.add(n / i);
                }
            }
            List<Integer> list = new ArrayList<>(set);
            Collections.sort(list);
            if (list.size() >= k)
                System.out.println(list.get(k - 1));
            else
                System.out.println(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}