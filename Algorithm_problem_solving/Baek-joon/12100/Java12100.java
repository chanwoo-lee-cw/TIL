import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {

        Board gameBoaed;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            gameBoaed = new Board(bf);

            gameBoaed.getBoardInMinValue(5);

            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Board {
//    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}};
    private int n;
    private int[][] board;

    Board(BufferedReader bf) throws IOException {
        this.n = Integer.parseInt(bf.readLine());
        board = new int[n][n];
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    /*
    dfs 방식으
     */
    public int getBoardInMinValue(int remainedMoveCnt) {
        int answer = 0;
        if (remainedMoveCnt == 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    answer = Math.max(answer, board[i][j]);
                }
            }
        } else {
            int[][] tempBoard;
            for (int[] next : way) {
                tempBoard = board;
                moveNumber(next);
                answer = Math.max(answer, getBoardInMinValue(remainedMoveCnt - 1));
                board = tempBoard;
            }
        }
        return answer;
    }

    public void moveNumber(int[] next) {
        // 이번 턴에 움직이는 방향에 따라 보드를 움직인다.

        int[][] tempBoard = new int[n][n];
        boolean flag;
        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, tempBoard[i], 0, n);
        }
        if (next[0] == -1 && next[1] == 0) {
            // -1, 0
            for (int i = 0; i < n; i++) {
                int pos = 0;
                for (int j = 0; j < n; j++) {
                    if (tempBoard[i][j] != 0) {
                        if(pos == j)
                            continue;
                        if (tempBoard[i][pos] == 0) {
                            tempBoard[i][pos] = tempBoard[i][j];
                        } else if (tempBoard[i][pos] == tempBoard[i][j]) {
                            tempBoard[i][pos] = tempBoard[i][pos] * 2;
                            pos++;
                        } else {
                            ++pos;
                            tempBoard[i][pos] = tempBoard[i][j];
                        }
                    }
                }
            }
        } else if(next[0] == 1 && next[1] == 0) {
            for (int i = 0; i < n; i++) {
                int pos = n-1;
                for (int j = n-1; j >= 0; j--) {
                    if (tempBoard[i][j] != 0) {
                        if(pos == j)
                            continue;
                        if (tempBoard[i][pos] == 0) {
                            tempBoard[i][pos] = board[i][j];
                        } else if (tempBoard[i][pos] == board[i][j]) {
                            tempBoard[i][pos] = tempBoard[i][pos] * 2;
                            pos--;
                        } else {
                            --pos;
                            tempBoard[i][pos] = board[i][j];
                        }
                        tempBoard[i][j] = 0;
                    }
                }
            }
        }
        board = tempBoard;
    }
}