import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n;
        int[] arr;
        Solution solution;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            n = Integer.parseInt(bf.readLine());
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(bf.readLine());
            }
            Arrays.sort(arr);
            solution = new Solution();

            System.out.println(solution.getArrAvg(n, arr));
            System.out.println(solution.getArrMid(n, arr));
            System.out.println(solution.getArrMode(n, arr));
            System.out.println(solution.getArrRange(n, arr));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Solution {

    public int getArrAvg(int n, int[] arr) {
        int sum = 0;
        for (int item : arr) {
            sum += item;
        }
        return sum / arr.length;
    }

    public int getArrMid(int n, int[] arr) {
        return arr[n / 2];
    }

    public int getArrMode(int n, int[] arr) {
        int[] answer;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int item : arr) {
            if (map.containsKey(item))
                map.put(item, map.get(item) + 1);
            else
                map.put(item, 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0]) {
                    return 1;
                } else if (o1[0] > o2[0]) {
                    return -1;
                }
                // else
                return o1[1] - o2[1];
            }
        });
        for (int item : map.keySet()) {
            queue.add(new int[]{map.get(item), item});
        }
        answer = queue.poll();
        if (!queue.isEmpty() && answer[0] == queue.peek()[0])
            answer = queue.poll();
        return answer[1];
    }

    public int getArrRange(int n, int[] arr) {
        return arr[n - 1] - arr[0];
    }
}
