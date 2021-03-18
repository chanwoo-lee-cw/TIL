import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1920
public class Main {
    public static void main(String[] args) {
        NetworkCon netCon;      // 문제 객체
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            netCon = new NetworkCon(bf);
            System.out.println(netCon.minSpanningTree());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class NetworkCon {
    private int n;          // vertex의 갯수
    private PriorityQueue<int[]> queue;     // 낮은 비용 순서대로 정리하기 위한 값
    private int[] parents;      // 각 vertex가 묶여 있는지 확인
    private int[] levels;       // 각 트리의 길이

    public NetworkCon(BufferedReader bf) throws IOException {
        this.n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        this.queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        StringTokenizer st;
        int a, b, c;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            queue.add(new int[]{a, b, c});
        }

        parents = new int[n + 1];
        levels = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }

    // 유니온 파인드
    private int find(int u) {
        if (parents[u] == u)
            return parents[u];
        parents[u] = find(parents[u]);
        return parents[u];
    }

    // 유니온 파인트 - 만약 merge가 된다면 true, 이미 묶여 있다면 false
    private boolean merge(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v)
            return false;
        if (levels[u] > levels[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        parents[u] = v;
        if (levels[u] == v)
            levels[v]++;
        return true;
    }

    // 크루스칼 알고리즘으로 최소 스패닝 트리를 구한다.
    public int minSpanningTree() {
        int answer = 0;
        int usedVertex = 1;

        int[] curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (merge(curr[0], curr[1])) {
                answer += curr[2];
                usedVertex++;
                if (usedVertex == n)
                    break;
            }
        }

        return answer;
    }
}