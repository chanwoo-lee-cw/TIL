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
                    } else if (maze[i][j] == 'B') {
                        beads.setBlueY(i);
                        beads.setBlueX(i);
                    } else if (maze[i][j] == 'O') {
                        outHall[0] = i;
                        outHall[1] = j;
                    }
                }
            }
            iputLine = null;

            getMinMove(n, m, maze, outHall, beads);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getMinMove(int n, int m, char[][] maze, int[] outHall, Beads beads) {
        /*
        일단 베이스로 구슬 하나 빼는 것만 생
         */
        Queue<BeadMove> queue = new LinkedList<>();
        HashSet<Beads> visited = new HashSet<>();
        BeadMove curr = new BeadMove(beads, 0);
        queue.add(curr);
        visited.add(beads);

        Beads nextBeads;
        boolean blueMove, redMove;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            beads = curr.getBeads();
            for (int[] next : way) {

                while (true) {
                    /*
                    경우의 수 2가지, 두 구슬이 붙어 있을때 빨강 파랑 2가지가 붙어 있는 경우.
                    처리를 어떻게 하느냐가 중요한데...만약 어느 구슬이 어디 있느냐에 따라 방향이 달라짐
                    */
                    nextBeads = new Beads(beads.getRedY() + next[0], beads.getRedX() + next[1],
                            beads.getBlueY() + next[0], beads.getBlueX() + next[1]);
                    blueMove = nextBeads.blueBeadCanMove(maze);
                    redMove = nextBeads.redBeadCanMove(maze);
                    if (blueMove && redMove) {
                        // 둘다 움직였을때
                        if(visited.contains(nextBeads))
                            continue;
                        visited.add(nextBeads);
                        queue.add(new BeadMove(nextBeads, curr.move + 1));
                    } else if (blueMove || redMove) {
                        // 둘 중 하나만 움직일때, -> 한쪽만 움직이면 다른 한쪽도 다른 돌이 걸리면 break;
                        if (blueMove) {
                            if (Beads.checkBeadOverlap(nextBeads.getBlueY(), nextBeads.getBlueX(), beads.getRedY(), beads.getRedX())) {
                                break;
                            } else {
                                if(visited.contains(nextBeads))
                                    continue;
                                visited.add(nextBeads);
                                queue.add(new BeadMove(nextBeads, curr.move + 1));
                            }
                        } else {
                            if (Beads.checkBeadOverlap(nextBeads.getRedY(), nextBeads.getRedX(), beads.getBlueY(), beads.getRedY())) {
                                break;
                            } else {
                                if(visited.contains(nextBeads))
                                    continue;
                                visited.add(nextBeads);
                                queue.add(new BeadMove(nextBeads, curr.move + 1));
                            }
                        }
                    } else {
                        // 둘 다 못 움직일때
                        break;
                    }
                }
            }
        }

        return 0;
    }
}

class Beads {
    private int redY, redX;
    private int blueY, blueX;

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

    public static boolean checkBeadOverlap(int firstY, int firstX, int secondY, int secondX) {
        if (firstY == secondY && firstX == secondX)
            return true;
        else
            return false;
    }

    public boolean redBeadCanMove(char[][] maze) {
        if (maze[redY][redX] == '#')
            return false;
        else
            return true;
    }

    public boolean blueBeadCanMove(char[][] maze) {
        if (maze[blueY][blueX] == '#')
            return false;
        else
            return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beads beads = (Beads) o;
        return redY == beads.redY && redX == beads.redX && blueY == beads.blueY && blueX == beads.blueX;
    }
}

class BeadMove {
    Beads beads;
    int move;

    public BeadMove(Beads beads, int move) {
        this.beads = new Beads();
        this.move = move;
    }

    public BeadMove(int redY, int redX, int blueY, int blueX, int move) {
        beads = new Beads(redY, redX, blueY, blueX);
        this.move = move;
    }

    public Beads getBeads() {
        return beads;
    }

    public void setBeads(Beads beads) {
        this.beads = beads;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeadMove beadMove = (BeadMove) o;
        return move == beadMove.move && beads.equals(beadMove.beads);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beads, move);
    }
}
