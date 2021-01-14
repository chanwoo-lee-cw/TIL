import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // 이동할 수 있는 위치를 저장하는 변
    public static int[][] way = new int[][]{{-2, -1}, {-2, 1}, {0, -2}, {0, 2}, {2, -1}, {2, 1}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ChessPosition curr = new ChessPosition(in.nextInt(), in.nextInt(), 0);
        int[] direction = new int[]{in.nextInt(), in.nextInt()};
        in.close();

        System.out.println(getMoveCnt(n, curr, direction));
    }

    // BFS로 해당 위치까지 가는 횟수를 찾는다.
    public static int getMoveCnt(int n, ChessPosition curr, int[] direction) {
        boolean[][] visited = new boolean[n + 1][n + 1];
        Queue<ChessPosition> queue = new LinkedList<>();
        queue.add(curr);
        visited[curr.y][curr.x] = true;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.y == direction[0] && curr.x == direction[1])
                return curr.action;
            for (int i = 0; i < way.length; i++) {
                nextY = curr.y + way[i][0];
                nextX = curr.x + way[i][1];
                if (nextX > n || 0 > nextX || nextY > n || 0 > nextY || visited[nextY][nextX])
                    continue;
                queue.add(new ChessPosition(nextY, nextX, curr.action + 1));
                visited[nextY][nextX] = true;
            }
        }
        return -1;
    }
}

// 현재 위치와 이동 횟수를 저장하기 위한 클래스
class ChessPosition {
    public int y;
    public int x;
    public int action;

    ChessPosition(int y, int x, int action) {
        this.y = y;
        this.x = x;
        this.action = action;
    }
}