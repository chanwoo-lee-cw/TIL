import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


// 틀렸습니다 - 사유 메모리 초과
public class Main {
    public static void main(String[] args) {
        String A, B;
        int k;
        String answer;
        Solution solution;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            A = bf.readLine();
            B = bf.readLine();
            k = Integer.parseInt(bf.readLine());

            solution = new Solution();
            answer = solution.kth_goat(A, B, k);
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Solution {
    public String kth_goat(String A, String B, int k) {
        String answer = null;
        int a = Integer.parseInt(A, 2);
        int b = Integer.parseInt(B, 2);

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            int o1ZeroCnt = 0;
            int o2ZeroCnt = 0;
            String s1 = Integer.toBinaryString(o1);
            String s2 = Integer.toBinaryString(o2);
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == '1') o1ZeroCnt++;
            }
            for (int i = 0; i < s2.length(); i++) {
                if (s2.charAt(i) == '1') o2ZeroCnt++;
            }
            if (o1ZeroCnt > o2ZeroCnt)
                return 1;
            else if (o1ZeroCnt < o2ZeroCnt)
                return -1;
            else
                return o1 - o2;
        });

        for (int i = a; i < b; i++) {
            queue.add(i);
        }

        for (int i = 0; i < k; i++) {
            answer = Integer.toBinaryString(queue.poll());
        }
        return answer;
    }
}