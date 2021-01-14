import java.util.*;

// https://www.acmicpc.net/problem/13913
public class Main {

    public static HashMap<Integer, Integer> wherefrom;

    public static int bfs(int n, int k, int[] dp) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(n, -1, 0));
        wherefrom = new HashMap<>();
        {
            Position curr;
            while (!queue.isEmpty()) {
                curr = queue.poll();
                if (!wherefrom.containsKey(curr.pos))
                    wherefrom.put(curr.pos, curr.from);
                dp[curr.pos] = curr.move;
                if (curr.pos == k)
                    break;
                if (curr.pos * 2 < 100101 && dp[curr.pos * 2] == 0)
                    queue.add(new Position(curr.pos * 2, curr.pos, curr.move + 1));
                if (curr.pos + 1 < 100101 && dp[curr.pos + 1] == 0)
                    queue.add(new Position(curr.pos + 1, curr.pos, curr.move + 1));
                if (curr.pos - 1 >= 0 && dp[curr.pos - 1] == 0)
                    queue.add(new Position(curr.pos - 1, curr.pos, curr.move + 1));
            }
        }
        return dp[k];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int k = in.nextInt();

        int[] dp = new int[100101];

        int moveCnt = bfs(n, k, dp);
        System.out.println(moveCnt);

        for (int item : getRoute(k, n, moveCnt)) {
            System.out.printf("%d ", item);
        }
    }

    private static int[] getRoute(int k, int n, int moveCnt) {

        int[] route = new int[moveCnt + 1];
        while (k != n) {
            route[moveCnt] = k;
            moveCnt--;
            k = wherefrom.get(k);
        }
        route[0] = k;
        return route;
    }
}

class Position {
    int pos;
    int from;
    int move;

    Position(int pos, int from, int move) {
        this.pos = pos;
        this.from = from;
        this.move = move;
    }
}