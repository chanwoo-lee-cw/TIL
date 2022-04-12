import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 5;
        int[][] board = new int[5][5];

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Bingo bingo = new Bingo(n, board);
            int cnt = 0;
            loop:
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < n; j++) {
                    cnt++;
                    if (bingo.checkNumber(Integer.parseInt(st.nextToken())))
                        break loop;
                }
            }
            System.out.println(cnt);

        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}

class Bingo {
    private int n;
    private int[][] board;
    private boolean[][] check;
    private int bingoCnt;


    public Bingo(int n, int[][] board) {
        this.n = n;
        this.board = board;
        this.check = new boolean[n][n];
        this.bingoCnt = 0;
    }

    public boolean checkNumber(int num) {
        loop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == num) {
                    check[i][j] = true;
                    countBongo(i, j);
                    break loop;
                }
            }
        }

        return bingoCnt >= 3;
    }

    private void countBongo(int y, int x) {
        boolean flag;

        flag = true;
        for (int i = 0; i < n; i++) {
            if (!check[i][x]) {
                flag = false;
                break;
            }
        }
        if (flag) this.bingoCnt++;

        flag = true;
        for (int i = 0; i < n; i++) {
            if (!check[y][i]) {
                flag = false;
                break;
            }
        }
        if (flag) this.bingoCnt++;

        if (y == x) {
            flag = true;
            for (int i = 0; i < n; i++) {
                if (!check[i][i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) this.bingoCnt++;
        }

        if (y + x == n - 1) {
            flag = true;
            for (int i = 0; i < n; i++) {
                if (!check[i][n - 1 - i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) this.bingoCnt++;
        }
    }
}
