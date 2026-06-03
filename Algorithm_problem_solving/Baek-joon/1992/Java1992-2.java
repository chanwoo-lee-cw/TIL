import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;
        int[][] matrix;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String inputLine;

            n = Integer.parseInt(br.readLine());
            matrix = new int[n + 1][n + 1];
            for (int i = 0; i < n; i++) {
                inputLine = br.readLine();
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = inputLine.charAt(j) - '0';
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("입력 도중에 에러가 발생했습니다.");
            throw e;
        }

        QuadTree quadTree = new QuadTree(n, matrix);
        System.out.println(quadTree.getQuadTree());
    }
}

class QuadTree {
    private final int n;
    private final int[][] matrix;

    QuadTree(int n, int[][] matrix) {
        this.n = n;
        this.matrix = matrix;
    }

    public String getQuadTree() {
        return compression(0, 0, n);
    }

    private String compression(
            int y,
            int x,
            int size
    ) {
        if (size == 0) {
            return String.valueOf(matrix[y][x]);
        }
        int halfSize = size / 2;

        String leftUp = compression(
                y,
                x,
                halfSize
        );
        String rightUp = compression(
                y + halfSize,
                x,
                halfSize
        );
        String leftDown = compression(
                y,
                x + halfSize,
                halfSize
        );
        String rightDown = compression(
                y + halfSize,
                x + halfSize,
                halfSize
        );

        if (isEqualColor(leftUp, rightUp, leftDown, rightDown)) {
            return leftUp;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(leftUp);
            sb.append(leftDown);
            sb.append(rightUp);
            sb.append(rightDown);
            sb.append(")");
            return sb.toString();
        }
    }

    private boolean isEqualColor(
            String leftUp,
            String rightUp,
            String leftDown,
            String rightDown
    ) {
        return leftUp.length() == 1
                && leftUp.equals(rightUp)
                && leftDown.equals(rightDown)
                && leftUp.equals(leftDown);
    }
}