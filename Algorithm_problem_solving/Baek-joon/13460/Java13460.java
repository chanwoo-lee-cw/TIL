import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//https://www.acmicpc.net/problem/13460
public class Main {
    public static int[][] way = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};
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
        Queue<BeadMove> queue = new LinkedList<>();
        HashSet<Beads> visited = new HashSet<>();
        BeadMove curr = new BeadMove(beads.getRedX(), beads.getRedX(), beads.getBlueY(), beads.getBlueX(), 0);
        queue.add(curr);
        visited.add(curr);

        int redY, redX;
        int blueY, blueX;
        boolean dontMove;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            for (int[] next : way) {
                dontMove = true;
                
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beads beads = (Beads) o;
        return redY == beads.redY && redX == beads.redX && blueY == beads.blueY && blueX == beads.blueX;
    }
}

class BeadMove extends Beads {
    int move;

    public BeadMove(int redY, int redX, int blueY, int blueX, int move) {
        super(redY, redX, blueY, blueX);
        this.move = move;
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
        return super.equals(o);
    }
}