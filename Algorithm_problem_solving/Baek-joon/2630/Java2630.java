import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int n;
        int[][] paper;
        PaperCut paperCut;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            n = Integer.parseInt(bf.readLine());
            paper = new int[n + 1][n + 1];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < n; j++) {
                    paper[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            paperCut = new PaperCut(n, paper);
            System.out.println(paperCut.cutPaper());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class PaperCut {
    private int n;
    private int[][] paper;
    private int blueCnt;
    private int whiteCnt;

    public PaperCut(int n, int[][] paper) {
        this.n = n;
        this.paper = paper;
        this.blueCnt = 0;
        this.whiteCnt = 0;
    }

    public int divide(int ys, int ye, int xs, int xe) {
        if (ye - ys <= 1) {
            return paper[ys][xs];
        }
        int ym = (ys + ye) / 2;
        int xm = (xs + xe) / 2;
        int[] answer = new int[4];

        answer[0] = divide(ys, ym, xs, xm);
        answer[1] = divide(ym, ye, xs, xm);
        answer[2] = divide(ys, ym, xm, xe);
        answer[3] = divide(ym, ye, xm, xe);

        int blueCnt = 0;
        int whiteCnt = 0;

        for (int item : answer) {
            if (item == 1)
                blueCnt++;
            else if (item == 0)
                whiteCnt++;
        }
        if (blueCnt == 4 || whiteCnt == 4) {
            return answer[0];
        } else {
            this.blueCnt += blueCnt;
            this.whiteCnt += whiteCnt;
            return -1;
        }
    }

    public String cutPaper() {
        int result = divide(0, n, 0, n);
        if (result == 1)
            this.blueCnt++;
        else if (result == 0)
            this.whiteCnt++;
        String answer = String.format("%d\n%d", this.whiteCnt, this.blueCnt);
        return answer;
    }
}