import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1197
// 최소 스패닝 트리
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        Prim prim = new Prim(v, e);
        prim.setEgelist(bf);

        System.out.println(prim.prim());
    }
}

// 프림 알고리즘 객체
class Prim {
    private ArrayList<Node>[] edgelist;
    private int v, e;
    // 각 정점과 간선의 갯수와, 이어진 간선의 갯수를 저장할 List초기화
    Prim(int v, int e) {
        this.v = v;
        this.e = e;
        edgelist = new ArrayList[v+1];
        for(int i = 1; i<v+1; i++) {
            edgelist[i] = new ArrayList<>();
        }
    }

    // 각 정정과 이어진 간선의 갯수를 초기화
    public void setEgelist(BufferedReader bf) throws IOException{
        int start = 0;
        int end = 0;
        int weight = 0;
        StringTokenizer st = null;
        for(int i = 0; i<e; i++) {
            st = new StringTokenizer(bf.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            edgelist[start].add(new Node(end, weight));
            edgelist[end].add(new Node(start, weight));
        }
    }

    // 프림 알고리즘
    public int prim() {
        // 가장 작은 간선을 뽑기 위한 우선 순위 큐
        PriorityQueue<Node> pque = new PriorityQueue<>();
        int answer = 0;
        boolean[] visited = new boolean[v+1];
        // 첫번째 정점을 초기화
        pque.add(new Node(1, 0));
        Node outed = null;
        Node node_to_pque = null;
        while (!pque.isEmpty()) {
            outed = pque.poll();
            // 이어지는 edge가 한번 방문한 곳이라면 continue
            if(visited[outed.end]) {
                continue;
            }
            visited[outed.end] = true;
            answer += outed.weight;
            // 우선 순위 큐에서 간선을 출력한 다음에 방문하지 않은 곳이라면 que에 넣는다.
            for(int i = 0; i < edgelist[outed.end].size(); i++) {
                node_to_pque = edgelist[outed.end].get(i);
                if(visited[node_to_pque.end])
                    continue;
                pque.add(node_to_pque);
            }
        }
        return answer;
    }
}

class Node implements Comparable<Node>{
    public int end;
    public int weight;

    Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}