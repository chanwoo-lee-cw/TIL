import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int n, m;
        int[] cards;
        BlackJack blackJack;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            cards = new int[n];
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < n; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }
            blackJack = new BlackJack(n, m, cards);
            blackJack.printNearM();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class BlackJack {
    private int n, m;
    private int[] cards;
    private int numNearM;

    public BlackJack(int n, int m, int[] cards) {
        this.n = n;
        this.m = m;
        this.cards = cards;
        this.numNearM = 300001;
    }

    public void dfs(ArrayList<Integer> selected, int pos) {
        if (selected.size() == 3) {
            int sum = 0;
            for (int item : selected) {
                sum += item;
            }
            if (sum <= m && Math.abs(m - sum) < Math.abs(m - numNearM)) {
                numNearM = sum;
            }

        } else {
            for (int i = pos; i < n; i++) {
                selected.add(cards[i]);
                dfs(selected, i + 1);
                selected.remove(selected.size() - 1);
            }
        }
    }

    public void printNearM() {
        dfs(new ArrayList<>(), 0);
        System.out.println(numNearM);
    }
}