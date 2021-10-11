import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int col, row;
        Matrix A, B, C;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());
            A = new Matrix(row, col);
            for (int i = 0; i < row; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < col; j++) {
                    A.setItem(i, j, Integer.parseInt(st.nextToken()));
                }
            }
            st = new StringTokenizer(bf.readLine());
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());
            B = new Matrix(row, col);
            for (int i = 0; i < row; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < col; j++) {
                    B.setItem(i, j, Integer.parseInt(st.nextToken()));
                }
            }
            C = A.multiple(B);
            C.printMatrix();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Matrix {
    private int row, col;
    private int[][] matrix;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        this.matrix = new int[row][col];
    }

    public Matrix(int row, int col, int[][] matrix) {
        this.row = row;
        this.col = col;
        this.matrix = matrix;
    }

    public Matrix multiple(Matrix o) {
        Matrix answer = new Matrix(this.row, o.col);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < o.col; j++) {
                for (int k = 0; k < o.row; k++) {
                    int temp1 = answer.getItem(i, j);
                    int temp2 = this.getItem(i, k);
                    int temp3 = o.getItem(k, j);
                    answer.setItem(i, j, temp1 + temp2 * temp3);
                }
            }
        }
        return answer;
    }

    public int getItem(int y, int x) {
        return matrix[y][x];
    }

    public void setItem(int y, int x, int item) {
        matrix[y][x] = item;
    }

    public void printMatrix() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sb.append(matrix[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}