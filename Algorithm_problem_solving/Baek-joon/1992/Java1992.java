import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        QuadTree quadTree;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            quadTree = new QuadTree(bf);
            System.out.println(quadTree.getQuadTree());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class QuadTree {
    private int n;          // 쿼드 트리의 크기
    private int[][] img;    // 쿼드 트리의 구성

    public QuadTree(BufferedReader bf) throws IOException {
        this.n = Integer.parseInt(bf.readLine());
        this.img = new int[n][n];
        String input;
        for (int i = 0; i < n; i++) {
            input = bf.readLine();
            for (int j = 0; j < n; j++) {
                img[i][j] = input.charAt(j) - '0';
            }
        }
    }

    public String getQuadTree() {
        return makeQuadTree(0, 0, n);
    }

    /*
    쿼드 트리트리를 4분면으로 나눠서 만약 전부 같은거라면 그대로 반환, 다른 거라면 나열해서 ()로 싸서 반환한다.
     */
    public String makeQuadTree(int xs, int ys, int size) {
        if (size == 1)
            return String.format("%d", img[ys][xs]);

        int half = size / 2;

        ArrayList<String> arr = new ArrayList<>();
        arr.add(makeQuadTree(xs, ys, half));
        arr.add(makeQuadTree(xs + half, ys, half));
        arr.add(makeQuadTree(xs, ys + half, half));
        arr.add(makeQuadTree(xs + half, ys + half, half));

        boolean sameChk = true;
        String first = arr.get(0);
        for (String item : arr) {
            if (item.length() > 1) {
                sameChk = false;
                break;
            } else if (!first.equals(item)) {
                sameChk = false;
                break;
            }
        }
        if (sameChk)
            return first;
        else {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            for (String item : arr) {
                sb.append(item);
            }
            sb.append(")");
            return sb.toString();
        }

    }
}