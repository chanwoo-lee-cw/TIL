import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1707
public class Main {
    public static void main(String[] args) {
        int t;      // 테스트 케이스의 갯수
        BisectorGraph bg;   // 바이너리 그래프 예제
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            t = Integer.parseInt(bf.readLine());
            for (int testCase = 0; testCase < t; testCase++) {
                bg = new BisectorGraph(bf);
                if (bg.isBisectorGraph())
                    System.out.println("YES");
                else
                    System.out.println("NO");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class BisectorGraph {
    private int v;  // 정점의 갯수
    private int e;  // 간선의 갯수
    private ArrayList<Integer> link[];  // 각 정점과 연결되어 있는 간선

    public BisectorGraph(BufferedReader bf) throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        this.v = Integer.parseInt(st.nextToken());
        this.e = Integer.parseInt(st.nextToken());
        this.link = new ArrayList[this.v + 1];
        for (int i = 1; i <= this.v; i++) {
            this.link[i] = new ArrayList<>();
        }
        int a, b;
        for (int i = 0; i < this.e; i++) {
            st = new StringTokenizer(bf.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            this.link[a].add(b);
            this.link[b].add(a);
        }
    }


    /*
    이분 그래프인지 아닌지를 반환하는 매서드
    */
    public boolean isBisectorGraph() {
        int[] coloring = new int[v + 1];
        Queue<int[]> queue = new LinkedList<>();
        if (link[1].size() == 0)
            // 정점이 다른 정점과 연결되어 있지 않으면 2 그룹으로 묶일 수 없다는 의미이므로 false 리턴
            return false;
        queue.add(new int[]{1, 1});
        coloring[1] = 1;

        int[] curr;     // 현재 조사하고 있는 것을 탐색하기 위한 정점
        int color;      // 칠해야 되는 색을 저장
        while (!queue.isEmpty()) {
            curr = queue.poll();
            for (int item : link[curr[0]]) {
                if (coloring[item] != 0) {
                    // 만약 이미 방문했지만, 이미 다른 색으로 칠해져 있으면 false, 같은 색이면 계속 탐색
                    if (coloring[item] == curr[1])
                        return false;
                    else
                        continue;
                }
                color = curr[1] == 1 ? 2 : 1;
                queue.add(new int[]{item, color});
                coloring[item] = color;
            }
        }
        for(int i=1;i<=v;i++) {
            // 만약 끝까지 방문한 적이 없다면 false리턴
            if(coloring[i] == 0)
                return false;
        }
        return true;
    }
}