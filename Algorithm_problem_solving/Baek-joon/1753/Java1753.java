import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int v, e;
        int k;
        ArrayList<int[]>[] edge;
        Soultion soultion;
        int[] answer;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(bf.readLine());
            edge = new ArrayList[v + 1];
            for (int i = 1; i < v + 1; i++) {
                edge[i] = new ArrayList<>();
            }
            int[] temp = new int[3];
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(bf.readLine());
                temp[0] = Integer.parseInt(st.nextToken());
                temp[1] = Integer.parseInt(st.nextToken());
                temp[2] = Integer.parseInt(st.nextToken());
                edge[temp[0]].add(new int[]{temp[1], temp[2]});
            }
            soultion = new Soultion(v, e, k, edge);
            answer = soultion.soulution();
            for (int i = 1; i < v + 1; i++) {
                if (answer[i] == Integer.MAX_VALUE)
                    System.out.println("INF");
                else
                    System.out.println(answer[i]);
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}

/*
다익스트라 알고리즘을 통한 최단경로.
 */
class Soultion {
    int v, e;
    int k;
    ArrayList<int[]>[] edge;

    public Soultion(int v, int e, int k, ArrayList<int[]>[] edge) {
        this.v = v;
        this.e = e;
        this.k = k;
        this.edge = edge;
    }

    public int[] soulution() {
        int[] answer = new int[v + 1];
        Arrays.fill(answer, Integer.MAX_VALUE);

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        priorityQueue.add(new int[]{0, k});
        int[] curr;
        answer[k] = 0;
        while (!priorityQueue.isEmpty()) {
            curr = priorityQueue.poll();
            if (answer[curr[1]] < curr[0])
                continue;
            for (int[] item : edge[curr[1]]) {
                if (curr[0] + item[1] > answer[item[0]])
                    continue;
                answer[item[0]] = curr[0] + item[1];
                priorityQueue.add(new int[]{curr[0] + item[1], item[0]});
            }
        }
        return answer;
    }
}
