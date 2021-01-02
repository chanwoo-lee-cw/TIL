import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    // bfs로 n에서 출발하여 k까지의 위치를 탐색한다.
    public static int bfs(int n, int k, int[] dp) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(n, 0));
        {
            Position curr;
            while (!queue.isEmpty()) {
                curr = queue.poll();
                dp[curr.pos] = curr.move;
                if (curr.pos == k)
                    break;
                if (curr.pos * 2 < 100101 && dp[curr.pos * 2] == 0)
                    queue.add(new Position(curr.pos * 2, curr.move + 1));
                if (curr.pos + 1 < 100101 && dp[curr.pos + 1] == 0)
                    queue.add(new Position(curr.pos + 1, curr.move + 1));
                if (curr.pos - 1 >= 0 && dp[curr.pos - 1] == 0)
                    queue.add(new Position(curr.pos - 1, curr.move + 1));
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