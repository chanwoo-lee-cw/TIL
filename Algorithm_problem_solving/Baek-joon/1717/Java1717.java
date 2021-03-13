import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1717
public class Main {
    public static void main(String[] args) {
        int n, m;   // n 노드의 수, m 연산의 갯수
        GroupSet set;   // 테스트 객체

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            set = new GroupSet(n);

            boolean isMerge;    // 탐색인지 병합인지를 계싼
            int u, v;           // 계산할 두 노
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(bf.readLine());
                isMerge = Integer.parseInt(st.nextToken()) == 0 ? true : false;
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                if (isMerge) {
                    set.merge(u, v);
                } else {
                    if (set.find(u) == set.find(v))
                        System.out.println("YES");
                    else
                        System.out.println("NO");
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class GroupSet {
    private int n;      // 노드의 갯수
    private int[] parents;      // 최상위 부모가 누구인지
    private int[] levels;       // 트리의 높이를 리턴

    public GroupSet(int n) {
        this.n = n;
        this.parents = new int[n + 1];
        this.levels = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parents[i] = i;
            levels[i] = i;
        }
    }

    /*
    해당 노드의 최상위 부모가 누구인지를 리턴
     */
    public int find(int u) {
        if (u == parents[u])
            return u;
        parents[u] = find(parents[u]);
        return parents[u];
    }

    /*
    두 노드를 하나의 그룹으로 묶는다.
     */
    public void merge(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v)
            return;
        if (levels[u] > levels[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        parents[u] = v;
        if (levels[u] == levels[v])
            levels[u]++;
    }
}
