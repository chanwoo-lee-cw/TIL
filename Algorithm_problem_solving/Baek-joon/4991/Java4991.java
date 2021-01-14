import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4991
public class Main {
    public static void main(String[] args) throws IOException {
        RobotRoute robot = null;    // 로봇과 경로 대한 객체

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        String inputLine;
        while (true) {
            robot = new RobotRoute();
            st = new StringTokenizer(bf.readLine());
            robot.w = Integer.parseInt(st.nextToken());
            robot.h = Integer.parseInt(st.nextToken());
            if (robot.w == 0 && robot.h == 0)
                // 더 이상 입력아 없을 경우 break;
                break;
            robot.room = new char[robot.h][robot.w];
            robot.trashs = new ArrayList<>();
            for (int i = 0; i < robot.h; i++) {
                inputLine = bf.readLine();
                for (int j = 0; j < robot.w; j++) {
                    robot.room[i][j] = inputLine.charAt(j);
                    if (robot.room[i][j] == 'o')
                        robot.robotCleaner = new Location(i, j, 0);
                    else if (robot.room[i][j] == '*')
                        robot.trashs.add(new Location(i, j, 0));
                }
            }
            inputLine = null;
            st = null;

            int reuslt = robot.getShortestDistance();
            System.out.println(reuslt);
        }
        bf.close();
    }
}

class RobotRoute {
    public static int[][] way = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    int w, h;   // 방의 가로와 세로
    char[][] room;   // 방의 모습
    Location robotCleaner = null;      // 로봇 청소기의 위치
    ArrayList<Location> trashs = null;  // 쓰래기들의 위치
    int minDistance = Integer.MAX_VALUE;    // 출력한 최소 경로의 길
    ArrayList<Integer> route;   // 로봇이 이동할 경로

    public int getShortestDistance() {
        int[] roboTrashDistance = new int[trashs.size()];   // 로봇과 각 쓰래기 사이의 거리
        int[][] distanceBetweenTrashs = new int[trashs.size()][trashs.size()];  // 쓰래들 사이의 거리

        for (int i = 0; i < trashs.size(); i++) {
            roboTrashDistance[i] = getAtoBdistance(robotCleaner, trashs.get(i));
            if (roboTrashDistance[i] == -1)
                // 만약 -1이 나온다면 더이상 볼것도 없이 -1 리턴
                return -1;
        }
        for (int i = 0; i < trashs.size(); i++) {
            for (int j = i + 1; j < trashs.size(); j++) {
                distanceBetweenTrashs[i][j] = getAtoBdistance(trashs.get(i), trashs.get(j));
                distanceBetweenTrashs[j][i] = distanceBetweenTrashs[i][j];
                if (distanceBetweenTrashs[i][j] == -1)
                    return -1;
            }
        }
        route = new ArrayList<>();
        searchRobotRoute(new boolean[trashs.size()], roboTrashDistance, distanceBetweenTrashs);
        return minDistance;
    }

    // Start에서 dest까지의 거리 반환
    public int getAtoBdistance(Location start, Location dest) {
        boolean[][] visited = new boolean[h][w];    // 방문 여부 체크
        Queue<Location> queue = new LinkedList<>();

        Location curr = start;
        queue.add(curr);
        visited[curr.y][curr.x] = true;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.y == dest.y && curr.x == dest.x)
                return curr.distance;
            for (int[] next : way) {
                nextY = next[0] + curr.y;
                nextX = next[1] + curr.x;
                if (nextX < 0 || nextX >= w || nextY < 0 || nextY >= h)
                    continue;
                else if (visited[nextY][nextX] || room[nextY][nextX] == 'x')
                    continue;
                // else
                queue.add(new Location(nextY, nextX, curr.distance + 1));
                visited[nextY][nextX] = true;
            }
        }
        return -1;
    }

    // 로봇이 이동할 경로를 dfs방식으로 모두 찾는다.
    public void searchRobotRoute(boolean[] dfsvisited, int[] roboTrashDistance, int[][] distanceBetweenTrashs) {
        if (route.size() == trashs.size()) {
            // 만약 경로가 완성 되면 이동거리 계산
            int currRouteDistance = roboTrashDistance[route.get(0)];
            for (int i = 1; i < route.size(); i++)
                currRouteDistance += distanceBetweenTrashs[route.get(i - 1)][route.get(i)];
            minDistance = Math.min(currRouteDistance, minDistance);
        } else {
            for (int i = 0; i < trashs.size(); i++) {
                if (dfsvisited[i])
                    continue;
                route.add(i);
                dfsvisited[i] = true;
                searchRobotRoute(dfsvisited, roboTrashDistance, distanceBetweenTrashs);
                dfsvisited[i] = false;
                route.remove(route.size() - 1);
            }
        }
    }
}

class Location {
    int y, x;   // 현재의 위치
    int distance;   // 이동거리

    public Location(int y, int x, int distance) {
        this.y = y;
        this.x = x;
        this.distance = distance;
    }
}