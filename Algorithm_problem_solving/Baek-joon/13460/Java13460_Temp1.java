import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//https://www.acmicpc.net/problem/13460
// 일단 빨간 구슬만 있을 경우 뽑아내는 방법
public class Main {
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        int n;  // 미로의 세로길이
        int m;  // 미로의 가로길이
        char[][] maze;  // 미로의 모습
        int[] outHall = new int[2];
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
//                    } else if (maze[i][j] == 'B') {
//                        beads.setBlueY(i);
//                        beads.setBlueX(i);
                    } else if (maze[i][j] == 'O') {
                        outHall[0] = i;
                        outHall[1] = j;
                    }
                }
            }
            iputLine = null;

            System.out.println(getMinMove(n, m, maze, outHall, beads));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getMinMove(int n, int m, char[][] maze, int[] outHall, Beads beads) {
        /*
        일딴 빨간 구슬만 이동하는걸 생각하자.
         */
        BeadMove curr;
        Queue<BeadMove> queue = new LinkedList<>();
        HashSet<Beads> visited = new HashSet<>();
        curr = new BeadMove(beads, 0);
        visited.add(beads);
        queue.add(curr);

        Beads nextPos;
        int nextX, nextY;
        boolean chekRedOut;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            for (int[] next : way) {
                nextPos = curr.getBeads().clone();
                chekRedOut = false;
                while (true) {
                    if (maze[nextPos.getRedY()][nextPos.getRedX()] == 'O')
                        chekRedOut = true;
                    if (next[0] == 0) {
                        nextX = nextPos.getRedX() + next[1];
                        if (maze[nextPos.getRedY()][nextX] == '#') {
                            break;
                        }
                        nextPos.setRedX(nextX);
                    } else {
                        nextY = nextPos.getRedY() + next[0];
                        if (maze[nextY][nextPos.getRedX()] == '#') {
                            break;
                        }
                        nextPos.setRedY(nextY);
                    }
                }
                if (visited.contains(nextPos))
                    continue;
                visited.add(nextPos);
                queue.add(new BeadMove(nextPos, curr.getMove() + 1));
                if (chekRedOut) {
                    return curr.getMove() + 1;
                }
            }
        }
        return curr.getMove();
    }
}

class Beads {
    private int redY, redX;

    public Beads() {
    }

    public Beads(int redY, int redX) {
        this.redY = redY;
        this.redX = redX;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beads beads = (Beads) o;
        return redY == beads.redY && redX == beads.redX;
    }

    @Override
    public int hashCode() {
        return Objects.hash(redY, redX);
    }

    @Override
    public Beads clone() {
        return new Beads(getRedY(), getRedX());
    }
}

class BeadMove {
    private Beads beads;
    private int move;

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