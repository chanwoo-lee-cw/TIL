import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n;
        int[] coins;
        String answer;
        DrawStar drawStar;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(bf.readLine());
            drawStar = new DrawStar();
            answer = drawStar.draw(n);
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DrawStar {

    public String draw(int n) {
        char[][] paper = new char[n][n];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            Arrays.fill(paper[i], ' ');
        }
        fillStar(paper, 0, n, 0, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(paper[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void fillStar(char[][] paper, int ys, int ye, int xs, int xe) {
        if (xe - xs == 1) {
            paper[ys][xs] = '*';
            return;
        }

        int interval = (ye - ys) / 3;
        // y: 3부분중 첫번째
        fillStar(paper, ys, ys + interval, xs, xs + interval);
        fillStar(paper, ys, ys + interval, xs + interval, xs + 2 * interval);
        fillStar(paper, ys, ys + interval, xs + 2 * interval, xe);
        // y : 3부분중 두번째
        fillStar(paper, ys + interval, ys + 2 * interval, xs, xs + interval);
        fillStar(paper, ys + interval, ys + 2 * interval, xs + 2 * interval, xe);
        // y: 마지막
        fillStar(paper, ys + 2 * interval, ye, xs, xs + interval);
        fillStar(paper, ys + 2 * interval, ye, xs + interval, xs + 2 * interval);
        fillStar(paper, ys + 2 * interval, ye, xs + 2 * interval, xe);
    }

}
