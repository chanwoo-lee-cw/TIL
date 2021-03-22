import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int t;          // 테스트 케이스의 갯수
        int n, m;       // 저장해줄 프린트 물을 저장
        MyQueue queue;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            t = Integer.parseInt(bf.readLine());
            for (int test = 0; test < t; test++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                n = Integer.parseInt(st.nextToken());
                m = Integer.parseInt(st.nextToken());
                queue = new MyQueue(n, m);
                queue.push(bf.readLine());
                System.out.println(queue.wantPull());
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MyQueue {
    private int n;      // 대기열에 있는 갯수
    private int m;      // 원하는 출력물의 번호
    private Queue<int[]> queue;     // 큐에 들어가 있는 출력물
    private PriorityQueue<Integer> prior;       // 우선 순위를 저

    public MyQueue(int n, int m) {
        this.n = n;
        this.m = m;
        this.queue = new LinkedList<>();
        this.prior = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    /*
    큐에 출력물을 넣는다.
    */
    public void push(String input) {
        StringTokenizer st = new StringTokenizer(input);
        int next;
        for (int i = 0; i < n; i++) {
            next = Integer.parseInt(st.nextToken());
            queue.add(new int[]{next, i == m ? 1 : 0});
            prior.add(next);
        }
    }

    /*
    만약 우선 순위가 더 높은게 있다면 다시 넣는다.
    우선 순위가 같을 때, 필요한 거라면 그대로 출력하고 아니면, 버린다.
    리턴값 : 출력 순서
    */
    public int wantPull() {
        int answer = 0;
        int[] curr;

        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr[0] < prior.peek()) {
                queue.add(curr);
            } else {
                answer++;
                if (curr[1] == 1)
                    return answer;
                else
                    prior.poll();
            }
        }

        return answer;
    }
}