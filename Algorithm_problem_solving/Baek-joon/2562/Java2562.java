import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int t = 9;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            int num;
            for (int i = 1; i <= 9; i++) {
                num = Integer.parseInt(bf.readLine());
                priorityQueue.add(new int[]{num, i});
            }
            int[] answer = priorityQueue.poll();
            System.out.println(answer[0]);
            System.out.println(answer[1]);
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}

