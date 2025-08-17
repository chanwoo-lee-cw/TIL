// https://www.acmicpc.net/problem/14923

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;


class Position {
    private int y;
    private int x;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}


class CurrentPosition extends Position {
    private int moveCount;
    private int breakWall;

    public CurrentPosition(int y, int x, int moveCount, int breakWall) {
        super(y, x);
        this.moveCount = moveCount;
        this.breakWall = breakWall;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public int getBreakWall() {
        return breakWall;
    }
}

class Solution {
    private int n, m;
    private int[][] matrix;
    private Position start;
    private Position end;


    private final int maxBreakAble = 1;
    private static final int[][] WAY = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public Solution(int n, int m, int[][] matrix, Position start, Position end) {
        this.n = n;
        this.m = m;
        this.matrix = matrix;
        this.start = start;
        this.end = end;
    }

    public int findShortestDistance() {
        ArrayDeque<CurrentPosition> queue = new ArrayDeque<>();
        int[][] visited = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        queue.add(
                new CurrentPosition(start.getY(), start.getX(), 0, 0)
        );
        visited[start.getY()][start.getX()] = 0;
        while (!queue.isEmpty()) {
            CurrentPosition curr = queue.poll();
            for (int[] next : WAY) {
                int y = curr.getY() + next[0];
                int x = curr.getX() + next[1];
                int breakWall = curr.getBreakWall();
                if (y <= 0 || y > n || x <= 0 || x > m) {
                    // 범위 밖으로 넘어가면 무시
                    continue;
                } else if (visited[y][x] <= breakWall) {
                    continue;
                }
                // else
                int moveCount = curr.getMoveCount() + 1;
                if (matrix[y][x] == 1) {
                    if (curr.getBreakWall() >= maxBreakAble) continue;
                    breakWall += 1;
                }
                if (y == end.getY() && x == end.getX()) {
                    return moveCount;
                }
                queue.add(
                        new CurrentPosition(y, x, moveCount, breakWall)
                );
                visited[y][x] = breakWall;
            }
        }
        return -1;
    }
}


public class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        try {
            st = new StringTokenizer(bf.readLine());
            Integer n = Integer.parseInt(st.nextToken());
            Integer m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(bf.readLine());

            Position start = new Position(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );

            st = new StringTokenizer(bf.readLine());

            Position dest = new Position(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );

            int[][] matrix = new int[n + 1][m + 1];
            for (int i = 1; i < n + 1; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 1; j < m + 1; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Solution solution = new Solution(n, m, matrix, start, dest);
            System.out.println(
                    solution.findShortestDistance()
            );


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}