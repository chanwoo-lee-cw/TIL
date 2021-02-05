import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//https://www.acmicpc.net/problem/13460
public class Main {
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        int n;  // 미로의 세로길이
        int m;  // 미로의 가로길이
        char[][] maze;  // 미로의 모습
        Beads beads = new Beads();

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            maze = new char[n][m];
            st = null;

            String iputLine;
            for (int i = 0; i < n; i++) {
                iputLine = bf.readLine();
                for (int j = 0; j < m; j++) {
                    maze[i][j] = iputLine.charAt(j);
                    if (maze[i][j] == 'R') {
                        beads.setRedY(i);
                        beads.setRedX(j);
                    } else if (maze[i][j] == 'B') {
                        beads.setBlueY(i);
                        beads.setBlueX(j);
                    }
                }
            }
            iputLine = null;

            System.out.println(getMinMove(n, m, maze, beads));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getMinMove(int n, int m, char[][] maze, Beads beads) {
        BeadMove curr;      // 현재 구슬의 위치와 채의 현황
        Queue<BeadMove> queue = new LinkedList<>();     // bfs탐색
        HashSet<Beads> visited = new HashSet<>();       // 방문 여부 탐색
        curr = new BeadMove(beads, 1);
        visited.add(beads);
        queue.add(curr);

        Beads nextPos;          // 움직이고 있는 구슬의 위치
        int nextX, nextY;       // 빨간 구슬이 다음으로 움직일 위치
        int bNextX, bNextY;     // 파랑 구슬이 다음으로 움직일 위치
        boolean redMove, blueMove;  // 구슬이 벽에 부딪혔는지 여부
        boolean chekRedOut, checkBlueOut;   // 구슬이 밖으로 나갔나의 여부
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.getMove() > 10)
                return -1;
            for (int[] next : way) {
                nextPos = curr.getBeads().clone();
                chekRedOut = false;
                checkBlueOut = false;
                redMove = true;
                blueMove = true;

                while (true) {
                    // 벽을 만날 때까지 구슬을 움직인다.
                    if (maze[nextPos.getRedY()][nextPos.getRedX()] == 'O')
                        chekRedOut = true;
                    if (maze[nextPos.getBlueY()][nextPos.getBlueX()] == 'O') {
                        checkBlueOut = true;
                        break;
                    }
                    if (next[0] == 0) {
                        nextX = nextPos.getRedX() + next[1];
                        bNextX = nextPos.getBlueX() + next[1];
                        if (maze[nextPos.getRedY()][nextX] == '#') {
                            redMove = false;
                        } else {
                            nextPos.setRedX(nextX);
                        }
                        if (maze[nextPos.getBlueY()][bNextX] == '#') {
                            blueMove = false;
                        } else {
                            nextPos.setBlueX(bNextX);
                        }
                    } else {
                        nextY = nextPos.getRedY() + next[0];
                        bNextY = nextPos.getBlueY() + next[0];
                        if (maze[nextY][nextPos.getRedX()] == '#') {
                            redMove = false;
                        } else {
                            nextPos.setRedY(nextY);
                        }
                        if (maze[bNextY][nextPos.getBlueX()] == '#') {
                            blueMove = false;
                        } else {
                            nextPos.setBlueY(bNextY);
                        }
                    }
//                    if(visited.contains(nextPos)){
//                        break;
//                    } else {
//                        if(!nextPos.checkBeadsOverlap())
//                            visited.add(nextPos);
//                    }
//                    maze[nextPos.getRedY()][nextPos.getRedX()] = 'R';
//                    maze[nextPos.getBlueY()][nextPos.getBlueX()] = 'B';
//                    maze[nextPos.getRedY()][nextPos.getRedX()] = '.';
//                    maze[nextPos.getBlueY()][nextPos.getBlueX()] = '.';
                    if (!blueMove && !redMove) {
                        break;
                    }
                }
                if (checkBlueOut)
                    continue;
                if (chekRedOut)
                    return curr.getMove();
                if (nextPos.checkBeadsOverlap()) {
                    // 구슬의 위치가 겹치면, 처음 파랑과 빨강의 위치 차이를 계산해 1만큼 벌려준다.
                    // 서로의 위치 계산
                    int nearY = curr.getBeads().getRedY() - curr.getBeads().getBlueY();   // 음수일때 빨간 구슬이 위
                    int nearX = curr.getBeads().getRedX() - curr.getBeads().getBlueX();   // 음수일때 빨간 구슬이 왼쪽
                    if (nearY < 0) {
                        if (next[0] == -1)
                            nextPos.setBlueY(nextPos.getBlueY() + 1);
                        else
                            nextPos.setRedY(nextPos.getRedY() - 1);
                    } else if (nearY > 0) {
                        if (next[0] == -1)
                            nextPos.setRedY(nextPos.getRedY() + 1);
                        else
                            nextPos.setBlueY(nextPos.getBlueY() - 1);
                    } else if (nearX < 0) {
                        if (next[1] == -1)
                            nextPos.setBlueX(nextPos.getBlueX() + 1);
                        else
                            nextPos.setRedX(nextPos.getRedX() - 1);
                    } else {
                        if (next[1] == -1)
                            nextPos.setRedX(nextPos.getRedX() + 1);
                        else
                            nextPos.setBlueX(nextPos.getBlueX() - 1);
                    }
                }
                if (visited.contains(nextPos))
                    continue;
                visited.add(nextPos);
                queue.add(new BeadMove(nextPos, curr.getMove() + 1));
                if (chekRedOut) {
                    return curr.getMove();
                }
            }
        }
        return -1;
    }
}

class Beads {
    private int redY, redX;     //빨간 구슬의 위치
    private int blueY, blueX;   // 파란 구슬의 위치

    public Beads() {
    }

    public Beads(int redY, int redX, int blueY, int blueX) {
        this.redY = redY;
        this.redX = redX;
        this.blueY = blueY;
        this.blueX = blueX;
    }

    public int getRedY() {
        return redY;
    }

    public void setRedY(int redY) {
        this.redY = redY;
    }

    public int getRedX() {
        return redX;
    }

    public void setRedX(int redX) {
        this.redX = redX;
    }

    public int getBlueY() {
        return blueY;
    }

    public void setBlueY(int blueY) {
        this.blueY = blueY;
    }

    public int getBlueX() {
        return blueX;
    }

    public void setBlueX(int blueX) {
        this.blueX = blueX;
    }

    public boolean checkBeadsOverlap() {
        if (redY == blueY && redX == blueX)
            return true;
        else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beads beads = (Beads) o;
        return redY == beads.redY && redX == beads.redX && blueY == beads.blueY && blueX == beads.blueX;
    }

    @Override
    public int hashCode() {
        return Objects.hash(redY, redX, blueY, blueX);
    }

    @Override
    public Beads clone() {
        return new Beads(getRedY(), getRedX(), getBlueY(), getBlueX());
    }
}

class BeadMove {
    private Beads beads;    // 구슬의 위치
    private int move;       // 채를 움직인 횟수

    public BeadMove(Beads beads, int move) {
        this.beads = beads;
        this.move = move;
    }

    public Beads getBeads() {
        return beads;
    }

    public void setBeads(Beads beads) {
        this.beads = beads;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeadMove beadMove = (BeadMove) o;
        return move == beadMove.move && Objects.equals(beads, beadMove.beads);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beads, move);
    }
}