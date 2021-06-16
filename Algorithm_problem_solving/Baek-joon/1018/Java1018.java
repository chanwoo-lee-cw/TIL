import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int n, m;
        char[][] board;
        int answer;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                n = Integer.parseInt(st.nextToken());
                m = Integer.parseInt(st.nextToken());
            }
            board = new char[n + 1][m + 1];
            {
                String inputLine;
                for (int i = 1; i < n + 1; i++) {
                    inputLine = bf.readLine();
                    for (int j = 1; j < m + 1; j++) {
                        board[i][j] = inputLine.charAt(j - 1);
                    }
                }
            }

            ChessRecolor chess = new ChessRecolor(n, m, board);
            answer = Math.min(chess.minRecolorBlockByTop('W'), chess.minRecolorBlockByTop('B'));
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ChessRecolor {
    private int n, m;
    private char[][] board;

    public ChessRecolor(int n, int m, char[][] board) {
        this.n = n;
        this.m = m;
        this.board = board;
    }

    public int minRecolorBlockByTop(char topColor) {
        int[][] dp = new int[n + 1][m + 1];
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
                if ((i + j) % 2 == 1) {
                    if (board[i][j] == topColor) {
                        dp[i][j] += 1;
                    }
                } else {
                    if (board[i][j] != topColor) {
                        dp[i][j] += 1;
                    }
                }
            }
        }
        for (int i = 8; i < n + 1; i++) {
            for (int j = 8; j < m + 1; j++) {
                answer = Math.min(answer, dp[i][j] - dp[i - 8][j] - dp[i][j - 8] + dp[i - 8][j - 8]);
            }
        }
        return answer;
    }
}
