import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2252
public class Main {
    // 위상 정렬 문제

    // 매개변수
    // inCnt를 통해 해당 노드가 전위가 몇개나 남았는지 확인
    // smaller를 통해 다음에 들어갈 수 있는 노드를 탐색
    public static String getSort(int n, int[] inCnt, ArrayList<Integer>[] smaller) {
        StringBuilder sb = new StringBuilder();
        // 큐를 통해 들어가는 순서를 정한다
        Queue<Integer> que = new LinkedList<>();
        // 전위가 없는 노드를 전부 큐에 집어 넣는다.
        for (int i = 1; i < n + 1; i++) {
            if (inCnt[i] == 0) {
                que.add(i);
            }
        }
        {
            int curr = 0;
            // 큐를 통해 다음 노드들이 전위 카운트가 0이 될때마다 큐에 집어 넣는다.
            while (!que.isEmpty()) {
                curr = que.poll();
                sb.append(curr);
                sb.append(" ");
                for (int next : smaller[curr]) {
                    inCnt[next]--;
                    if (inCnt[next] == 0) {
                        que.add(next);
                    }
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] smaller = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            smaller[i] = new ArrayList<>();
        }
        int[] inCnt = new int[n + 1];
        {
            int a = 0;
            int b = 0;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(bf.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                smaller[a].add(b);
                inCnt[b]++;
            }
        }
        System.out.printf(getSort(n, inCnt, smaller));
        bf.close();
    }
}