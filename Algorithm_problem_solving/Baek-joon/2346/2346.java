import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(bf.readLine());
            StringTokenizer st = new StringTokenizer(bf.readLine());
            ArrayList<Pair> balloons = new ArrayList<>();
            BalloonPop BalloonPop;
            for (int i = 0; i < n; i++) {
                balloons.add(new Pair(Integer.parseInt(st.nextToken()), i));
            }
            BalloonPop = new BalloonPop(balloons);
            BalloonPop.runGame();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class BalloonPop {
    ArrayList<Pair> balloons;
    ArrayList<Integer> popedBalloon = new ArrayList<>();

    public BalloonPop(ArrayList<Pair> balloons) {
        this.balloons = balloons;
    }

    public void runGame() {
        int pos = 0;
        int next = 0;
        while (pos >= 0) {
            if (pos >= balloons.size()) {
                break;
            } else {
                Pair curr = balloons.get(pos);
                popedBalloon.add(curr.index);
                next = pos + curr.number;
                balloons.remove(pos);
                pos = next;
            }
        }
        printPop();
    }

    public void printPop() {
        StringBuilder sb = new StringBuilder();
        for (int item : popedBalloon) {
            sb.append(item);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}

class Pair {
    public int number;
    public int index;

    public Pair(int number, int index) {
        this.number = number;
        this.index = index;
    }
}