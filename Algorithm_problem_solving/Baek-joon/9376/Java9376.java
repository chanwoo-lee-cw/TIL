import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;

// https://www.acmicpc.net/problem/9376
public class Main {
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        int t;      // 테스트 케이스 갯수
        int h, w;   // 높이와 너비
        char[][] prison;
        ArrayList<int[]> prisoners;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String inputLine;       // 밑에 감옥 정보를 입력 받기 위한 임시 변수
        t = Integer.parseInt(bf.readLine());
        for (int testCase = 0; testCase < t; testCase++) {
            st = new StringTokenizer(bf.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            prison = new char[h + 2][w + 2];    // 외부에서 들어가는 경우이므로 각각 입구를 고려하는 것보다 각 외부 빈 공간을 둔다.
            prisoners = new ArrayList<>();
            for (int i = 1; i <= h; i++) {
                inputLine = bf.readLine();
                for (int j = 1; j <= w; j++) {
                    if (inputLine.charAt(j - 1) == '.') {
                        prison[i][j] = '.';
                    } else if (inputLine.charAt(j - 1) == '*') {
                        prison[i][j] = '*';
                    } else if (inputLine.charAt(j - 1) == '$') {
                        prison[i][j] = '.';
                        prisoners.add(new int[]{i, j});
                    } else {
                        prison[i][j] = '#';
                    }
                }
            }
            System.out.println(getMinDoorOpen(h, w, prison, prisoners));
        }
        st = null;
        bf.close();
    }

    private static int getMinDoorOpen(int h, int w, char[][] prison, ArrayList<int[]> prisoners) {
        int answer = Integer.MAX_VALUE;     // 반환값

        int[][] dist1 = searchDistOpenDoor(h, w, prison, new int[]{0, 0});      // 3번째 사람이 찾으러 들어가는 경우 각 장소마다 문을 열여야 되는 횟수
        int[][] dist2 = searchDistOpenDoor(h, w, prison, prisoners.get(0));     // 1번째 죄수의 경우 각 장소마다 문을 열여야 되는 횟수
        int[][] dist3 = searchDistOpenDoor(h, w, prison, prisoners.get(1));     // 2번째 죄수의 경우 각 장소마다 문을 열여야 되는 횟수
        {
            int openDoorNum;
            // 3사람이 감옥 안에서 만나지 않아도 되니 외부 범위도 포함
            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    if (prison[i][j] == '*')
                        continue;
                    openDoorNum = dist1[i][j] + dist2[i][j] + dist3[i][j];      // i,j에서 3명이 만나는 경우 열어야 되는 문의 갯수
                    if (prison[i][j] == '#')    //  그 장소가 문이라면 -2
                        openDoorNum -= 2;
                    answer = Math.min(answer, openDoorNum);
                }
            }
        }
        return answer;
    }

    // 각 사람이 각 장소를 이동하는 경우의 열어야 되는 문의 갯수를 구한다.
    private static int[][] searchDistOpenDoor(int h, int w, char[][] prison, int[] person) {
        int[][] dist = new int[h + 2][w + 2];   // 3번째 사람의 경우 외부에서 시작하기 위해 빈 공간을 둔다.
        for (int i = 0; i < h + 2; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);

        PriorityQueue<PersonPosition> priorityQueue = new PriorityQueue<>(new Comparator<PersonPosition>() {
            @Override
            public int compare(PersonPosition o1, PersonPosition o2) {
                return o1.openDoor - o2.openDoor;
            }
        });     // 문을 최소로 여는 것이니 우선순위 큐로 계산한다. - 큐로 방문한 장소라도 문연 횟수가 더 적으면 다시 계산해도 되지만, 우선 순위 큐가 더 효율적
        PersonPosition curr = new PersonPosition(person[0], person[1], 0);
        priorityQueue.add(curr);
        dist[person[0]][person[1]] = 0;
        {
            int nextY, nextX;
            while (!priorityQueue.isEmpty()) {
                curr = priorityQueue.poll();
                for (int[] next : way) {
                    nextY = curr.y + next[0];
                    nextX = curr.x + next[1];
                    if (nextY < 0 || nextX < 0 || nextY > h + 1 || nextX > w + 1
                            || prison[nextY][nextX] == '*' || dist[nextY][nextX] != Integer.MAX_VALUE)
                        continue;
                        // else
                    else if (prison[nextY][nextX] == '#') {
                        priorityQueue.add(new PersonPosition(nextY, nextX, curr.openDoor + 1));
                        dist[nextY][nextX] = curr.openDoor + 1;
                    } else {
                        priorityQueue.add(new PersonPosition(nextY, nextX, curr.openDoor));
                        dist[nextY][nextX] = curr.openDoor;
                    }
                }
            }
        }
        return dist;
    }
}

class PersonPosition {
    public int y;           // 현재 사람의 y위치
    public int x;           // 현재 사람의 x위치
    public int openDoor;    // 현재 위치까지의 문을 연 횟수

    PersonPosition(int y, int x, int openDoor) {
        this.y = y;
        this.x = x;
        this.openDoor = openDoor;
    }
}