import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<Integer, Integer> ladder = new HashMap<>();
        HashMap<Integer, Integer> snake = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            ladder.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            snake.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        st = null;
        bf.close();

        int reuslt = getMinmoveCnt(ladder, snake);
        System.out.println(reuslt);
    }

    // 최소 이동 횟수를 bfs방식으로 구한다.
    private static int getMinmoveCnt(HashMap<Integer, Integer> ladder, HashMap<Integer, Integer> snake) {
        // 방문 여부를 나타내는 변수
        boolean[] visited = new boolean[101];       
        // 사다리는 도착 자리에서 계산하기 위해서 우선 순위 큐로 계산
        PriorityQueue<Position> queue = new PriorityQueue<>(new Comparator<Position>() {
            @Override
            public int compare(Position o1, Position o2) {
                return o1.moveCnt - o2.moveCnt;
            }
        });
        queue.add(new Position(1, 0));

        Position curr = null;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.position == 100)
                break;
            visited[curr.position] = true;
            if (ladder.containsKey(curr.position) || snake.containsKey(curr.position)) {
                if (ladder.containsKey(curr.position) && !visited[ladder.get(curr.position)])
                    queue.add(new Position(ladder.get(curr.position), curr.moveCnt));
                if (snake.containsKey(curr.position) && !visited[snake.get(curr.position)])
                    queue.add(new Position(snake.get(curr.position), curr.moveCnt));
            } else {
                for (int i = 1; i <= 6 && curr.position + i <= 100; i++) {
                    if (visited[curr.position + i])
                        continue;
                    queue.add(new Position(curr.position + i, curr.moveCnt + 1));
                }
            }
        }
        return curr.moveCnt;
    }
}

class Position {
    public int position;    // 현재의 위치
    public int moveCnt;     // 움직인 횟수

    Position(int position, int moveCnt) {
        this.position = position;
        this.moveCnt = moveCnt;
    }
}