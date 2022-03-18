import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n, m;
        int answer;
        ShortestRoute shortestRoute;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            n = Integer.parseInt(bf.readLine());
            m = Integer.parseInt(bf.readLine());
            shortestRoute = new ShortestRoute(n, m);
            {
                int s, e, c;
                for (int i = 0; i < m; i++) {
                    st = new StringTokenizer(bf.readLine());
                    s = Integer.parseInt(st.nextToken());
                    e = Integer.parseInt(st.nextToken());
                    c = Integer.parseInt(st.nextToken());
                    shortestRoute.addRoudte(s, e, c);
                }
            }

            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            answer = shortestRoute.getShortestRoute(start, dest);

            System.out.println(answer);
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}

/**
 * Solution of the minimum cost
 */
class ShortestRoute {
    private int n, m;
    private HashMap<Integer, ArrayList<int[]>> route = new HashMap<>();

    /**
     * @param n number of cities
     * @param m number of bus
     */
    public ShortestRoute(int n, int m) {
        this.n = n;
        this.m = m;
        for (int i = 1; i < n + 1; i++) {
            this.route.put(i, new ArrayList<>());
        }
    }

    /**
     * add a route of cost from s to e
     *
     * @param s start
     * @param e end
     * @param c cost
     */
    public void addRoudte(int s, int e, int c) {
        route.get(s).add(new int[]{e, c});
    }

    /**
     * get the minimum cost from the starting point to the destination
     *
     * @param start starting point
     * @param dest  destination
     * @return minimum cost from starting point to destination
     */
    public int getShortestRoute(int start, int dest) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int[] visited = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            visited[i] = Integer.MAX_VALUE;
        }
        visited[start] = 0;
        priorityQueue.add(new int[]{start, 0});
        int nextCost;
        while (!priorityQueue.isEmpty()) {
            int[] curr = priorityQueue.poll();
            if (curr[0] == dest)
                return curr[1];
            for (int[] next : route.get(curr[0])) {
                nextCost = curr[1] + next[1];
                if (nextCost >= visited[next[0]])
                    continue;
                priorityQueue.add(new int[]{next[0], nextCost});
                visited[next[0]] = nextCost;
            }
        }
        return visited[dest];
    }
}
