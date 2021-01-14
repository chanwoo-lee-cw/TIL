import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2234
public class Main {

    public static void main(String[] args) throws IOException {
        int n, m;            // 성의 가로,세로
        CattleRoom currCattle;  // 성 객체

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        currCattle = new CattleRoom(n, m);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                currCattle.cattle[i][j] = String.format("%04d", Integer.parseInt(Integer.toBinaryString(Integer.parseInt(st.nextToken()))));
            }
        }
        System.out.println(currCattle.getRoomSumary());
    }
}

class CattleRoom {
    public static int[][] way = {{1, 0, 0}, {0, 1, 1}, {-1, 0, 2}, {0, -1, 3}};     // 이동할 방향 y,x,벽의 여부

    int n;  // 성의 세로
    int m;  // 성의 가로
    String[][] cattle;  // 성의 벽이 세워진 방식
    int[][] roomVisited;    // 몇번째 방인지 체크
    ArrayList<Integer> roomSizeList;    // 방의 사이즈들을 저장하는 변수
    ArrayList<boolean[]> adjacentRoom;  // 인접한 방들의 리스

    CattleRoom(int n, int m) {
        this.n = n;
        this.m = m;
        this.cattle = new String[n][m];
        this.roomVisited = new int[n][m];
        this.adjacentRoom = new ArrayList<>();
        this.roomSizeList = new ArrayList<>();
        // 계산하기 쉽기 위해 방의 번호를 1부터 시작하기 위해 넣는다.
        this.adjacentRoom.add(new boolean[0]);
        this.roomSizeList.add(0);
    }

    // 결과 값을 만들어서 출력
    public String getRoomSumary() {
        StringBuilder sb = new StringBuilder(); // 결과를 반환할 문자

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (roomVisited[i][j] == 0)
                    roomSizeList.add(searchRoom(roomSizeList.size(), i, j));
            }
        }
        sb.append(roomSizeList.size() - 1);
        sb.append("\n");
        sb.append(Collections.max(roomSizeList));
        sb.append("\n");
        sb.append(getMaxMergeRoom());
        return sb.toString();
    }

    // 병합했을때 최대 방의 크기 리턴
    public int getMaxMergeRoom() {
        int maxMergeRoom = 0;
        for (int i = 1; i < roomSizeList.size(); i++) {
            for (int j = 1; j < i; j++) {
                if (adjacentRoom.get(i)[j])
                    maxMergeRoom = Math.max(maxMergeRoom, roomSizeList.get(i) + roomSizeList.get(j));
            }
        }
        return maxMergeRoom;
    }

    // 인접한 방이 몇개인지 확인 및 방의 크기 리턴
    public int searchRoom(int roomNum, int y, int x) {
        int currRoomsize = 0;       // 현재 방의 크기
        boolean[] currAdjacentRoom = new boolean[roomNum + 1];  // 인접한 방 조사
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        roomVisited[y][x] = roomNum;

        int[] curr;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            currRoomsize++;
            for (int[] next : way) {
                nextY = next[0] + curr[0];
                nextX = next[1] + curr[1];
                if (nextY < 0 || nextY >= n || nextX < 0 || nextX >= m)
                    continue;
                else if (roomVisited[nextY][nextX] != 0) {
                    // 방문한 장소인지 확인 및 옆방이라면 인접했다고 체크
                    if (!currAdjacentRoom[roomVisited[nextY][nextX]])
                        currAdjacentRoom[roomVisited[nextY][nextX]] = true;
                    continue;
                } else if (cattle[curr[0]][curr[1]].charAt(next[2]) == '1')
                    // 벽이 세워져 있는지 체크
                    continue;
                // else
                queue.add(new int[]{nextY, nextX});
                roomVisited[nextY][nextX] = roomNum;
            }
        }
        // 인접한 방들의 리스트를 집어 넣는다.
        adjacentRoom.add(currAdjacentRoom);
        return currRoomsize;
    }
}