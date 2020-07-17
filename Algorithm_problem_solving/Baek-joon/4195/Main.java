import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;
    static int[] level;

    static int find(int u) {
        if (u == parent[u])
            return u;
        parent[u] = find(parent[u]);
        return parent[u];
    }

    static void merge(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v)
            return;
        if (level[u] > level[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        parent[u] = v;
        if(level[u] == level[v])
            level[v] = level[v] + 1;
    }

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        for(int test = 0; test < T; test++) {
            int F = Integer.parseInt(bf.readLine());
            int people_cnt = 0;
            parent = new int[F*2];
            level = new int[F*2];

            HashMap<String , Integer> names = new HashMap<String , Integer>();

            for(int i = 0; i < 2*F; i++) {
                parent[i] = i;
                level[i] = 1;
            }

            for(int i = 0; i < F; i++) {
                String s = bf.readLine();
                StringTokenizer st = new StringTokenizer(s);
                String firstname = st.nextToken();
                String secondname = st.nextToken();
                st = null;

                if(!names.containsKey(firstname)) {
                    names.put(firstname, people_cnt);
                    people_cnt++;
                }
                if(!names.containsKey(secondname)) {
                    names.put(secondname, people_cnt);
                    people_cnt++;
                }
                int first = names.get(firstname);
                int second = names.get(secondname);

                merge(first, second);
                int root = find(first);
                int cnt = 0;
                for(int j=0; j < people_cnt; j++) {
                    if(root == find(j))
                        cnt++;
                }
                System.out.println(cnt);
            }
            parent = null;
            level = null;
            names= null;
        }
    }
}
