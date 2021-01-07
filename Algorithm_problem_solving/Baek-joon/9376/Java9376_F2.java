import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 메모리 초과
// https://www.acmicpc.net/problem/9376
public class Main {
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        int t;  // 테스트 케이스 갯수
        int h, w;    // 높이와 너비
        char[][] prison;
        ArrayList<int[]> outsideList;
        ArrayList<int[]> prizoners;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String inputLine;       // 밑에 감옥 정보를 입력 받기 위한 임시 변수
        t = Integer.parseInt(bf.readLine());
        for (int testCase = 0; testCase < t; testCase++) {
            st = new StringTokenizer(bf.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            prison = new char[h][w];
            outsideList = new ArrayList<>();
            prizoners = new ArrayList<>();

            for (int i = 0; i < h; i++) {
                inputLine = bf.readLine();
                for (int j = 0; j < w; j++) {
                    if (inputLine.charAt(j) == '.') {
                        prison[i][j] = '.';
                    } else if (inputLine.charAt(j) == '*') {
                        prison[i][j] = '*';
                    } else if (inputLine.charAt(j) == '$') {
                        prison[i][j] = '$';
                        prizoners.add(new int[]{i, j});
                    } else {
                        prison[i][j] = '#';
                    }
                    if ((i == 0 || j == 0 || i == h - 1 || j == w - 1) && prison[i][j] != '*') {
                        outsideList.add(new int[]{i, j});
                    }
                }
            }
            System.out.println(getMinDoorOpen(h, w, prison, outsideList, prizoners));

        }
        st = null;
        bf.close();
    }

    // 죄수가 각 방의 위치까지 움직이는데 열어야 되는 방문의 수를 센다.
    private static int getMinDoorOpen(int h, int w, char[][] prison, ArrayList<int[]> outsideList, ArrayList<int[]> prizoners) {
        int minDoorOpenCnt = Integer.MAX_VALUE;
        int[][] thirdPesonVisited = getThirdDoorOpen(h, w, prison, outsideList);    // 3번째 사람이 각 방을 돌아다닐때 열어야 되는 문의 갯수
        int[][] prizonerVisited = getPrizonerDoorOpen(h, w, prison, prizoners.get(0));  // 첫번째 죄수이 각 방을 돌아다닐때 열어야 되는 문의 갯수
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                thirdPesonVisited[i][j] += prizonerVisited[i][j];
            }
        }
        prizonerVisited = null;
        prizonerVisited = getPrizonerDoorOpen(h, w, prison, prizoners.get(1));  // 두번째 죄수가 각방을 돌아다닐때 열어야 되는 문의 갯수
        int getNum;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                getNum = thirdPesonVisited[i][j] + prizonerVisited[i][j];
                if (prison[i][j] == '#')
                    getNum -= 2;
                minDoorOpenCnt = Math.min(getNum, minDoorOpenCnt);
            }
        }

        return minDoorOpenCnt;
    }

    // 세번째 사람의 각 위치까지 가는데 방문의 수를 반환
    private static int[][] getThirdDoorOpen(int h, int w, char[][] prison, ArrayList<int[]> outsideList) {
        int[][] visited = new int[h][w];
        Queue<PersonPosition> queue = new LinkedList<>();
        PersonPosition curr;

        for (int i = 0; i < h; i++)
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        // 세번째 사람은 모든 밖으로 열려 있는 곳에서 들어올 수 있으므로 문이나 열려 있는 모든 곳을 큐에 추가
        for (int[] outside : outsideList) {
            curr = new PersonPosition(outside[0], outside[1], 0);
            if (prison[curr.y][curr.x] == '#')
                curr.openDoorCnt++;
            queue.add(curr);
            visited[curr.y][curr.x] = curr.openDoorCnt;
        }

        return searchOpenDoor(h, w, queue, prison, visited);
    }

    private static int[][] getPrizonerDoorOpen(int h, int w, char[][] prison, int[] prizoner) {
        int[][] visited = new int[h][w];
        Queue<PersonPosition> queue = new LinkedList<>();
        PersonPosition curr;

        for (int i = 0; i < h; i++)
            Arrays.fill(visited[i], Integer.MAX_VALUE);

        curr = new PersonPosition(prizoner[0], prizoner[1], 0);
        queue.add(curr);
        visited[curr.y][curr.x] = curr.openDoorCnt;

        return searchOpenDoor(h, w, queue, prison, visited);
    }

    // bfs각 위치까지 움직이는데 열어야 되는 방문의 수를 센다
    private static int[][] searchOpenDoor(int h, int w, Queue<PersonPosition> queue, char[][] prison, int[][] visited) {
        PersonPosition curr;
        int nextX, nextY;
        PersonPosition nextPos;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            for (int[] next : way) {
                nextY = next[0] + curr.y;
                nextX = next[1] + curr.x;
                if (nextY < 0 || nextX < 0 || nextY >= h || nextX >= w || prison[nextY][nextX] == '*' || visited[nextY][nextX] <= curr.openDoorCnt)
                    continue;
                if (prison[nextY][nextX] == '#')
                    nextPos = new PersonPosition(nextY, nextX, curr.openDoorCnt + 1);
                else
                    nextPos = new PersonPosition(nextY, nextX, curr.openDoorCnt);
                visited[nextY][nextX] = nextPos.openDoorCnt;
                queue.add(nextPos);
            }
        }
        return visited;
    }
}

class PersonPosition {
    int y;      // 현재 사람의 y위치
    int x;      // 현재 사람의 x위치
    int openDoorCnt;    // 현재 위치까지 오는데 열어야 되는 문의 수

    PersonPosition(int y, int x, int openDoorCnt) {
        this.y = y;
        this.x = x;
        this.openDoorCnt = openDoorCnt;
    }
}