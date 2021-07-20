import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        int n, m;
        boolean[][] cheese;
        MeltingCheese meltingCheese;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            cheese = new boolean[n + 2][m + 2];
            for (int i = 1; i < n + 1; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 1; j < m + 1; j++) {
                    cheese[i][j] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
                }
            }
            meltingCheese = new MeltingCheese(n, m, cheese);
            meltingCheese.melting();
        } catch (IOException error) {
            error.printStackTrace();
        }

    }
}

class MeltingCheese {
    private final static int[][] way = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    int n, m;
    boolean[][] cheese;

    public MeltingCheese(int n, int m, boolean[][] cheese) {
        this.n = n;
        this.m = m;
        this.cheese = cheese;
    }

    public void melting() {
        int[][] meltTime = new int[n + 2][m + 2];
        int spendTime = 0;
        int thisTimeMelt = 0;
        PriorityQueue<Time> priorityQueue = new PriorityQueue<>(new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                return o1.time - o2.time;
            }
        });

        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(meltTime[i], -1);
        }

        priorityQueue.add(new Time(0, 0, 0));
        meltTime[0][0] = 0;

        Time curr;
        int nextY, nextX;
        while (!priorityQueue.isEmpty()) {
            curr = priorityQueue.poll();
            if (cheese[curr.y][curr.x]) {
                if (spendTime < curr.time) {
                    spendTime = curr.time;
                    thisTimeMelt = 0;
                }
                thisTimeMelt++;
            }

            for (int[] next : way) {
                nextY = curr.y + next[0];
                nextX = curr.x + next[1];
                if (nextY < 0 || nextY > n + 1 || nextX < 0 || nextX > m + 1)
                    continue;
                else if (meltTime[nextY][nextX] != -1)
                    continue;
                // else
                if (cheese[nextY][nextX]) {
                    priorityQueue.add(new Time(nextY, nextX, curr.time + 1));
                    meltTime[nextY][nextX] = curr.time + 1;
                } else {
                    priorityQueue.add(new Time(nextY, nextX, curr.time));
                    meltTime[nextY][nextX] = curr.time;
                }
            }
        }
        System.out.println(spendTime);
        System.out.println(thisTimeMelt);
    }
}

class Time {
    int y, x;
    int time;

    public Time(int y, int x, int time) {
        this.y = y;
        this.x = x;
        this.time = time;
    }
}