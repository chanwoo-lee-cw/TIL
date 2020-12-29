import java.util.*;

public class Main {

    public static int bfs(int n, int k, int[] dp) {
        boolean[] visited = new boolean[100101];
        // 순간이동으로 이동하는 것은 동작 0이므로 우선순위 큐로 동작의 수가 작은 것이 먼저 나온다.
        PriorityQueue<Position> queue = new PriorityQueue<>(new Comparator<Position>() {
            @Override
            public int compare(Position o1, Position o2) {
                return o1.move - o2.move;
            }
        });
        queue.add(new Position(n, 0));
        visited[n] = true;
        {
            Position curr;
            while (!queue.isEmpty()) {
                curr = queue.poll();
                dp[curr.pos] = curr.move;
                // 방문했다는 마킹은 도착한 이후에 한다.
                // 왜냐하면 14의 경우에는 7에서 처리하는게 2*x해 오는게 가장 빠르지만,
                // 15에 7보다 먼저 도착하기 때문에 먼저 15에서 먼저 마킹이 되기 때문.
                visited[curr.pos] = true;
                if (curr.pos == k)
                    break;
                if (curr.pos * 2 < 100101 && dp[curr.pos * 2] == 0 && !visited[curr.pos * 2]) {
                    queue.add(new Position(curr.pos * 2, curr.move));
                    visited[curr.pos * 2] = true;
                }
                if (curr.pos + 1 < 100101 && dp[curr.pos + 1] == 0 && !visited[curr.pos + 1]) {
                    queue.add(new Position(curr.pos + 1, curr.move + 1));
                }
                if (curr.pos - 1 >= 0 && dp[curr.pos - 1] == 0 && !visited[curr.pos - 1]) {
                    queue.add(new Position(curr.pos - 1, curr.move + 1));
                }
            }
        }
        return dp[k];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int k = in.nextInt();

        int[] dp = new int[100101];

        System.out.println(bfs(n, k, dp));
    }
}

class Position {
    int pos;
    int move;

    Position(int pos, int move) {
        this.pos = pos;
        this.move = move;
    }
}