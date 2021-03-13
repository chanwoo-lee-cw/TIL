import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1780
public class Main {
    public static void main(String[] args) {
        Paper paper;

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            paper = new Paper(bf);
            paper.printPaperCnt();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Paper {
    private int n;              // 종이의 크기
    private int[][] color;      // 각 칸의 종이의 색
    private int[] colorCnt;     // 각 색의 갯

    public Paper(BufferedReader bf) throws IOException {
        this.n = Integer.parseInt(bf.readLine());
        this.color = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        colorCnt = new int[3];
    }

    /*
    지정된 구간에서 1개라도 다른 색이 있으면 2를 리턴
    전부 같다면 그 색을 리턴한다.
     */
    private int cutPaper(int xs, int xe, int ys, int ye) {
        if (xe - xs <= 1) {
            // 분할 정복의 끝에 도달한 경우
            return color[ys][xs];
        }
        int[] nextX = new int[4];       // 분할할 x구간
        int[] nextY = new int[4];       // 분할할 y구간
        ArrayList<Integer> particalPaper = new ArrayList<>();   // 9구간에서 반환될 수를 저장하는 배열

        for (int i = 0; i < 4; i++) {
            nextX[i] = xs + (xe - xs) / 3 * i;
        }
        for (int i = 0; i < 4; i++) {
            nextY[i] = ys + (ye - ys) / 3 * i;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                particalPaper.add(cutPaper(nextX[i], nextX[i + 1], nextY[j], nextY[j + 1]));
            }
        }

        int[] colorCnt = new int[3];        // 현재 구간에서 반환된 종이의 각 갯수
        boolean sameColor = true;           // 현재 구간의 종이가 전부 같은 색이라면 true
        for (int item : particalPaper) {
            if (item == 2 || item != particalPaper.get(0)) {
                sameColor = false;
            }
            if (item == -1)
                colorCnt[0]++;
            else if (item == 0)
                colorCnt[1]++;
            else if (item == 1)
                colorCnt[2]++;
        }

        if (sameColor)
            return particalPaper.get(0);
        else {
            for (int i = 0; i < 3; i++) {
                this.colorCnt[i] += colorCnt[i];
            }
            return 2;
        }
    }

    public void printPaperCnt() {
        int answer = cutPaper(0, n, 0, n);  // 만약 끝까지 종이가 전부 같은 색일 경우 체크
        if (answer != 2) {
            if (answer == -1)
                colorCnt[0]++;
            else if (answer == 0)
                colorCnt[1]++;
            else if (answer == 1)
                colorCnt[2]++;
        }
        for (int item : colorCnt) {
            System.out.println(item);
        }
    }
}
