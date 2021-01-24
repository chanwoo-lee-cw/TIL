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
        /*
        관건은 어떻게 방문 표시를 하느냐... 0,0을 기준으로 삼고 하
         */
        HashSet<Coins> visited = new HashSet<>();
        Queue<CoinMove> queue = new LinkedList<>();
        Coins coin = new Coins();
        coin.setCoin1(coins.get(0));
        coin.setCoin2(coins.get(1));
        CoinMove curr = new CoinMove(coin, 0);
        visited.add(coin);
        queue.add(curr);

        int coinDrop;
        int[] coin1Next, coin2Next;
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
                coin1Next = curr.getCoin1();
                coin1Next[0] += next[0];
                coin1Next[1] += next[1];
                coin2Next = curr.getCoin2();
                coin2Next[0] += next[0];
                coin2Next[1] += next[1];
                if (coin1Next[0] >= 0 && coin1Next[0] < n && coin1Next[1] >= 0 && coin1Next[1] < m && board[coin1Next[0]][coin1Next[1]])
                    coin1Next = curr.getCoin1();
                if (coin2Next[0] >= 0 && coin2Next[0] < n && coin2Next[1] >= 0 && coin2Next[1] < m && board[coin2Next[0]][coin2Next[1]])
                    coin2Next = curr.getCoin2();
                coin = new Coins(coin1Next, coin2Next);
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
    private int[] coin1;
    private int[] coin2;

    public Coins() {
    }

    public Coins(int[] coin1, int[] coin2) {
        this.coin1 = coin1;
        this.coin2 = coin2;
    }

    public int[] getCoin1() {
        return coin1.clone();
    }

    public void setCoin1(int[] coin1) {
        this.coin1 = coin1;
    }

    public int[] getCoin2() {
        return coin2.clone();
    }

    public void setCoin2(int[] coin2) {
        this.coin2 = coin2;
    }

    public boolean coin1Drop(int n, int m) {
        if (coin1[0] < 0 || coin1[1] < 0 || coin1[0] >= n || coin1[1] >= m)
            return true;
        else
            return false;
    }

    public boolean coin2Drop(int n, int m) {
        if (coin2[0] < 0 || coin2[1] < 0 || coin2[0] >= n || coin2[1] >= m)
            return true;
        else
            return false;
    }
}

class CoinMove extends Coins {
    private int moveCnt;

    public CoinMove(int[] coin1, int[] coin2, int moveCnt) {
        super(coin1, coin2);
        this.moveCnt = moveCnt;
    }

    public CoinMove(Coins coin, int moveCnt) {
        super(coin.getCoin1(), coin.getCoin2());
        this.moveCnt = moveCnt;
    }

    public int getMoveCnt() {
        return moveCnt;
    }

    public void setMoveCnt(int moveCnt) {
        this.moveCnt = moveCnt;
    }
}