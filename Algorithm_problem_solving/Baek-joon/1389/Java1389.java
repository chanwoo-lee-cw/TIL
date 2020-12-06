import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static int n;
    public static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] relations = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            relations[i] = new ArrayList<>();
        }
        {
            int linkA, linkB;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(bf.readLine());
                linkA = Integer.parseInt(st.nextToken());
                linkB = Integer.parseInt(st.nextToken());
                relations[linkA].add(linkB);
                relations[linkB].add(linkA);
            }
        }
        PriorityQueue<Kebinbaker> priorityQueue = new PriorityQueue<>();
        for (int i = 1; i < n + 1; i++) {
            priorityQueue.add(new Kebinbaker(i, bfs(i, relations)));
        }
        System.out.println(priorityQueue.poll().personNum);
    }

    public static int bfs(int person, ArrayList<Integer>[] relations) {
        int answer = 0;
        Queue<int[]> que = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        que.add(new int[]{person, 0});
        visited[person] = true;
        int[] curr = null;
        while (!que.isEmpty()) {
            curr = que.poll();
            for (int next : relations[curr[0]]) {
                if (visited[next]) {
                    continue;
                }
                que.add(new int[]{next, curr[1] + 1});
                answer += curr[1] + 1;
                visited[next] = true;
            }
        }
        return answer;
    }
}

class Kebinbaker implements Comparable<Kebinbaker> {
    public int personNum;
    public int kebinbaker;

    Kebinbaker(int personNum, int kebinbaker) {
        this.personNum = personNum;
        this.kebinbaker = kebinbaker;
    }

    @Override
    public int compareTo(Kebinbaker o) {
        return this.kebinbaker - o.kebinbaker;
    }
}