import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        int n;
        int[] arr;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(bf.readLine());
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(bf.readLine());
            }
            long answer = findMaxTip(n, arr);
            System.out.println(answer);
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     * Calculate and return the maximum tips that get from customers.
     *
     * @param n   The number of customers.
     * @param arr Tips from customers.
     * @return The biggest tip from customers.
     */
    public static long findMaxTip(int n, int[] arr) {
        long answer = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int item : arr) {
            priorityQueue.add(item);
        }
        int currTip;
        for (int i = 1; !priorityQueue.isEmpty(); i++) {
            currTip = priorityQueue.poll() - (i - 1);
            if (currTip > 0) {
                answer += currTip;
            } else break;
        }
        return answer;
    }
}