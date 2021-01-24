import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16197
public class Main {
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int n, m;    // 판의 크기
    public static boolean[][] board;

    public static void main(String[] args) throws IOException {
        ArrayList<int[]> coins = new ArrayList<>();
        {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            board = new boolean[n][m];
            String tempLine;
            char tempChar;
            for (int i = 0; i < n; i++) {
                tempLine = bf.readLine();
                for (int j = 0; j < m; j++) {
                    tempChar = tempLine.charAt(j);
                    if (tempChar == '#')
                        board[i][j] = true;
                    else if (tempChar == 'o')
                        coins.add(new int[]{i, j});
                }
            }
            st = null;
            bf.close();
        }
        System.out.println(getButtonPush(coins));
    }

    // 최소 이동 횟수를 구하는 함수.
    private static int getButtonPush(ArrayList<int[]> coins) {
        HashSet<Coins> visited = new HashSet<>();   // 방문 여부를 체크
        Queue<CoinMove> queue = new LinkedList<>(); // bfs로 탐색
        Coins coin = new Coins(coins.get(0)[0], coins.get(0)[1], coins.get(1)[0], coins.get(1)[1]);
        CoinMove curr = new CoinMove(coin, 0);
        visited.add(coin);
        queue.add(curr);

        int coinDrop;
        int coin1NextY, coin1NextX, coin2NextY, coin2NextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.getMoveCnt() > 10)
                return -1;
            coinDrop = 0;
            if (curr.coin1Drop(n, m))
                coinDrop++;
            if (curr.coin2Drop(n, m))
                coinDrop++;
            if (coinDrop == 1)
                return curr.getMoveCnt();
            else if (coinDrop == 2)
                continue;
            for (int[] next : way) {
                coin1NextY = curr.getCoin1Y() + next[0];
                coin1NextX = curr.getCoin1X() + next[1];
                coin2NextY = curr.getCoin2Y() + next[0];
                coin2NextX = curr.getCoin2X() + next[1];
                if (coin1NextY >= 0 && coin1NextY < n && coin1NextX >= 0 && coin1NextX < m && board[coin1NextY][coin1NextX]) {
                    coin1NextY = curr.getCoin1Y();
                    coin1NextX = curr.getCoin1X();
                }
                if (coin2NextY >= 0 && coin2NextY < n && coin2NextX >= 0 && coin2NextX < m && board[coin2NextY][coin2NextX]) {
                    coin2NextY = curr.getCoin2Y();
                    coin2NextX = curr.getCoin2X();
                }
                coin = new Coins(coin1NextY, coin1NextX, coin2NextY, coin2NextX);
                if (visited.contains(coin))
                    continue;
                visited.add(coin);
                queue.add(new CoinMove(coin, curr.getMoveCnt() + 1));
            }
        }
        return -1;
    }
}

class Coins {
    // coin 1의 좌표
    private int coin1X;
    private int coin1Y;
    // coin 2의 좌
    private int coin2Y;
    private int coin2X;

    public Coins() {
    }

    public Coins(int coin1Y, int coin1X, int coin2Y, int coin2X) {
        this.coin1Y = coin1Y;
        this.coin1X = coin1X;
        this.coin2Y = coin2Y;
        this.coin2X = coin2X;
    }

    public int getCoin1Y() {
        return coin1Y;
    }

    public void setCoin1Y(int coin1Y) {
        this.coin1Y = coin1Y;
    }

    public int getCoin1X() {
        return coin1X;
    }

    public void setCoin1X(int coin1X) {
        this.coin1X = coin1X;
    }

    public int getCoin2Y() {
        return coin2Y;
    }

    public void setCoin2Y(int coin2Y) {
        this.coin2Y = coin2Y;
    }

    public int getCoin2X() {
        return coin2X;
    }

    public void setCoin2X(int coin2X) {
        this.coin2X = coin2X;
    }

    public boolean coin1Drop(int n, int m) {
        if (coin1Y < 0 || coin1X < 0 || coin1Y >= n || coin1X >= m)
            return true;
        else
            return false;
    }

    public boolean coin2Drop(int n, int m) {
        if (coin2Y < 0 || coin2X < 0 || coin2Y >= n || coin2X >= m)
            return true;
        else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coins coins = (Coins) o;
        return coin1Y == coins.coin1Y && coin1X == coins.coin1X && coin2Y == coins.coin2Y && coin2X == coins.coin2X;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coin1Y, coin1X, coin2Y, coin2X);
    }
}

class CoinMove extends Coins {
    // 최소 이동 횟수
    private int moveCnt;

    public CoinMove(int coin1Y, int coin1X, int coin2Y, int coin2X, int moveCnt) {
        super(coin1Y, coin1X, coin2Y, coin2X);
        this.moveCnt = moveCnt;
    }

    public CoinMove(Coins coin, int moveCnt) {
        super(coin.getCoin1Y(), coin.getCoin1X(), coin.getCoin2Y(), coin.getCoin2X());
        this.moveCnt = moveCnt;
    }

    public int getMoveCnt() {
        return moveCnt;
    }

    public void setMoveCnt(int moveCnt) {
        this.moveCnt = moveCnt;
    }
}