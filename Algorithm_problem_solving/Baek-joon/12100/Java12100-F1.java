import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {

        Board gameBoaed;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            gameBoaed = new Board(bf);

            System.out.println(gameBoaed.getBoardInMinValue(5));

            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Board {
    public static int[][] way = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};  // 갈 수 있는 방향의 수
    private int n;      // 보드의 크기
    private int[][] board;  // 보드의 현재 모양

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
    dfs 방식으로 만들 수 있는 수의 경우의 수를 모두 구한다.
    매개변수 : 남은 이동 횟
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

    /*
    모드를 움직히는 함수
    매개변수 : 이동 방향
     */
    public void moveNumber(int[] next) {
        // 이번 턴에 움직이는 방향에 따라 보드를 움직인다.
        int[][] tempBoard = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, tempBoard[i], 0, n);
        }
        if (next[0] == -1 && next[1] == 0) {
            for (int i = 0; i < n; i++) {
                int pos = 0;
                for (int j = 0; j < n; j++) {
                    if (tempBoard[i][j] != 0) {
                        if (pos == j)
                            continue;
                        tempBoard[i][j] = 0;
                        if (tempBoard[i][pos] == 0) {
                            tempBoard[i][pos] = board[i][j];
                        } else if (tempBoard[i][pos] == board[i][j]) {
                            tempBoard[i][pos] = board[i][pos] * 2;
                            pos++;
                        } else {
                            ++pos;
                            tempBoard[i][pos] = board[i][j];
                        }
                    }
                }
            }
        } else if (next[0] == 1 && next[1] == 0) {
            for (int i = 0; i < n; i++) {
                int pos = n - 1;
                for (int j = n - 1; j >= 0; j--) {
                    if (tempBoard[i][j] != 0) {
                        if (pos == j)
                            continue;
                        tempBoard[i][j] = 0;
                        if (tempBoard[i][pos] == 0) {
                            tempBoard[i][pos] = board[i][j];
                        } else if (tempBoard[i][pos] == board[i][j]) {
                            tempBoard[i][pos] = board[i][pos] * 2;
                            pos--;
                        } else {
                            --pos;
                            tempBoard[i][pos] = board[i][j];
                        }
                    }
                }
            }
        } else if (next[0] == 0 && next[1] == -1) {
            for (int j = 0; j < n; j++) {
                int pos = 0;
                for (int i = 0; i < n; i++) {
                    if (tempBoard[i][j] != 0) {
                        if (pos == i)
                            continue;
                        tempBoard[i][j] = 0;
                        if (tempBoard[pos][j] == 0) {
                            tempBoard[pos][j] = board[i][j];
                        } else if (tempBoard[pos][j] == board[i][j]) {
                            tempBoard[pos][j] = board[pos][j] * 2;
                            pos++;
                        } else {
                            ++pos;
                            tempBoard[pos][j] = board[i][j];
                        }
                    }
                }
            }
        } else {
            for (int j = 0; j < n; j++) {
                int pos = n - 1;
                for (int i = n - 1; i >= 0; i--) {
                    if (tempBoard[i][j] != 0) {
                        if (pos == i)
                            continue;
                        tempBoard[i][j] = 0;
                        if (tempBoard[pos][j] == 0) {
                            tempBoard[pos][j] = board[i][j];
                        } else if (tempBoard[pos][j] == board[i][j]) {
                            tempBoard[pos][j] = board[pos][j] * 2;
                            pos--;
                        } else {
                            --pos;
                            tempBoard[pos][j] = board[i][j];
                        }
                    }
                }
            }
        }
        board = tempBoard;
    }
}