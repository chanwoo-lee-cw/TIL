import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int f; // 건물 층 수
        int s; // 시작 층
        int g; // 목적 층
        int u; // 올라가는 층
        int d; // 내려가는 층

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        st = null;

        System.out.println(getMinButtonPush(f, s, g, u, d));
        bf.close();
    }

    // 눌러야 되는 버튼 수를 반환하는 함수
    private static String getMinButtonPush(int floor, int start, int goal, int upStair, int downStair) {
        StringBuilder sb = new StringBuilder(); // 결과 값을 반환하기 위한 벼누
        boolean[] visited = new boolean[floor + 1]; // 방문 여부를 표시
        Queue<Position> queue = new LinkedList<>(); // BFS방식으로 탐색

        Position curr = new Position(start, 0);
        queue.add(curr);
        visited[curr.floor] = true;
        int tempNum; // 여러번 계산하지 않기 위한 임시 변수
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.floor == goal) {
                // 목적지에 도착할 수 있다면 결과 반환
                sb.append(curr.buttonPush);
                return sb.toString();
            }
            tempNum = curr.floor + upStair;
            if (tempNum <= floor && !visited[tempNum]) {
                queue.add(new Position(tempNum, curr.buttonPush + 1));
                visited[tempNum] = true;
            }
            tempNum = curr.floor - downStair;
            if (tempNum > 0 && !visited[tempNum]) {
                queue.add(new Position(tempNum, curr.buttonPush + 1));
                visited[tempNum] = true;
            }

        }
        // 만약 끝까지 못 간다면 문장 반환
        sb.append("use the stairs");
        return sb.toString();
    }
}

class Position {
    int floor; // 현재 층수
    int buttonPush; // 현재 층수 까지 오는데 누른 버튼의 수

    Position(int floor, int buttonPush) {
        this.floor = floor;
        this.buttonPush = buttonPush;
    }
}