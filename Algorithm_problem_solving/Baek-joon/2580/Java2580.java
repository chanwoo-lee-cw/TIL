import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int[][] matrix;       // 스토쿠 맵을 저장하는 변수
    public static boolean[][][] check;    // 수가 들어 있는지 체크하기 위한 함수
    public static ArrayList<int[]> blankPostion;    // 빈칸의 위치
    

    public static void main(String[] args) throws IOException {
        matrix = new int[9][9];
        check = new boolean[3][9][10];
        blankPostion = new ArrayList<>();
        StringBuilder output;       // 출력을 위한 변수

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int temp;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 9; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] == 0) {
                    blankPostion.add(new int[]{i, j});
                } else {
                    check[1][i][matrix[i][j]] = true;   // 가로줄을 사용한 수를 true로 체크
                    check[2][j][matrix[i][j]] = true;   // 세로줄을 사용한 수를 true로 체크
                    temp = j / 3 + i / 3 * 3;           // 각 구역의 번호를 구한다.
                    check[0][temp][matrix[i][j]] = true;    // 각 구역마다 사용한 숫자를 true로 체크
                }
            }
        }
        st = null;
        bf.close();

        fillBlank(0);

        output = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                output.append(matrix[i][j]);
                output.append(" ");
            }
            output.append("\n");
        }
        System.out.println(output.toString());
        output = null;
    }

    /*
    스도쿠를 dfs 탐색으로 찾아 채우는 함수
     */
    private static boolean fillBlank(int index) {
        if (index == blankPostion.size()) {
            return true;
            // 만약 배열이 스토쿠 완성 되면 탐색 종료
        } else {
            int[] curr = blankPostion.get(index);
            for (int i = 1; i <= 9; i++) {
                if (check[0][curr[1] / 3 + curr[0] / 3 * 3][i] || check[1][curr[0]][i] || check[2][curr[1]][i])
                    continue;
                matrix[curr[0]][curr[1]] = i;
                check[0][curr[1] / 3 + curr[0] / 3 * 3][i] = true;
                check[1][curr[0]][i] = true;
                check[2][curr[1]][i] = true;
                if (fillBlank(index + 1))
                    return true;
                check[0][curr[1] / 3 + curr[0] / 3 * 3][i] = false;
                check[1][curr[0]][i] = false;
                check[2][curr[1]][i] = false;
            }
            return false;
        }
    }
}