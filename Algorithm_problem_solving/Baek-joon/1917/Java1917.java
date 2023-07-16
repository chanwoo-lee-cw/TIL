import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static final int TESTCASE = 3;

    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            for (int i = 0; i < TESTCASE; i++) {
                TestCase testCase = new TestCase();
                testCase.run(bf);
            }
        } catch (IOException e) {
            return;
        }
    }


}

class TestCase {

    private static final int[][] WAY = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int[][] board;
    private Dice dice;

    public TestCase() {
        board = new int[6][6];
        dice = new Dice();
    }

    public void run(BufferedReader bf) throws IOException {
        String[] line = null;
        for (int i = 0; i < 6; i++) {
            line = bf.readLine().split(" ");
            for (int j = 0; j < 6; j++) {
                board[i][j] = Integer.parseInt(line[j]);
            }
        }

        findDice:
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (board[i][j] == 1) {
                    dfs(i, j);
                    break findDice;
                }
            }
        }

        // 결과 찾기
        if (dice.isCompleteDice()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    public void dfs(int y, int x) {
        int[] thisWay;
        int nextY, nextX;

        dice.checkDice();
        board[y][x] = 0;
        for (int i = 0; i < WAY.length; i++) {
            thisWay = WAY[i];
            nextY = thisWay[0] + y;
            nextX = thisWay[1] + x;
            if (nextX < 0 || nextY < 0 || nextX >= 6 || nextY >= 6) {
                continue;
            } else if (board[nextY][nextX] == 0) {
                continue;
            }
            if (i == 0) {
                dice.moveUp();
                dfs(nextY, nextX);
                dice.moveDown();
            } else if (i == 1) {
                dice.moveDown();
                dfs(nextY, nextX);
                dice.moveUp();
            } else if (i == 2) {
                dice.moveLeft();
                dfs(nextY, nextX);
                dice.moveRight();
            } else {
                dice.moveRight();
                dfs(nextY, nextX);
                dice.moveLeft();
            }
        }

    }

}


class Dice {
    boolean[] dice;

    public Dice() {
        this.dice = new boolean[6];
    }

    public void checkDice() {
        dice[0] = true;
    }

    public void moveDown() {
        /**
         * 주사위를 아래로 굴린다.
         */
        boolean temp = dice[0];
        dice[0] = dice[1];
        dice[1] = dice[2];
        dice[2] = dice[3];
        dice[3] = temp;
    }

    public void moveUp() {
        /**
         * 주사위를 위로 굴린다.
         */
        boolean temp = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[2];
        dice[2] = dice[1];
        dice[1] = temp;
    }

    public void moveLeft() {
        /**
         * 주사위를 좌로 굴린다.
         */
        boolean temp = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[2];
        dice[2] = dice[5];
        dice[5] = temp;
    }

    public void moveRight() {
        /**
         * 주사위를 우로 굴린다.
         */
        boolean temp = dice[0];
        dice[0] = dice[5];
        dice[5] = dice[4];
        dice[4] = dice[2];
        dice[2] = temp;
    }

    public boolean isCompleteDice() {
        for (boolean side : dice) {
            if (!side) return false;
        }
        return true;
    }
}