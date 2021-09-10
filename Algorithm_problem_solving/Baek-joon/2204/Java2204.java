import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n;
        Solution solution;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                n = Integer.parseInt(bf.readLine());
                if (n == 0) {
                    break;
                }
                solution = new Solution();
                for (int i = 0; i < n; i++) {
                    solution.AddDic(bf.readLine());
                }

                System.out.println(solution.getDic());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Solution {
    PriorityQueue<String> queue;

    public Solution() {
        this.queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
    }

    public void AddDic(String sentence) {
        queue.add(sentence);
    }

    public String getDic() {
        return queue.poll();
    }
}
