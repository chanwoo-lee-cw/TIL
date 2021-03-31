import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            Lader lader = new Lader(bf);
            System.out.println(lader.getPointMaxMin());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Lader {
    private int n;      // 사다리의 길이
    private int[][] lador;      // 사다리 각 항목들의 값

    Lader(BufferedReader bf) throws IOException {
        this.n = Integer.parseInt(bf.readLine());
        this.lador = new int[n][3];
        StringTokenizer st;
        for (int i = 0; i < this.n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 3; j++) {
                lador[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    /*
    맨 아래까지 갔을 때의, 최대 최소 값을 반환한다.
    */
    public String getPointMaxMin() {
        int[][] dpMax = new int[2][3];      // 각 위치들의 최대값을 저장하기 위한 변수
        int[][] dpMin = new int[2][3];      // 각 위치들의 최소값을 저장하기 위한 변수
        for (int j = 0; j < 3; j++) {
            dpMax[0][j] = lador[0][j];
            dpMin[0][j] = lador[0][j];
        }

        for (int i = 1; i < n; i++) {

            dpMax[1][0] = Math.max(dpMax[0][0], dpMax[0][1]) + lador[i][0];
            dpMax[1][1] = Math.max(dpMax[0][0], dpMax[0][1]);
            dpMax[1][1] = Math.max(dpMax[1][1], dpMax[0][2]) + lador[i][1];
            dpMax[1][2] = Math.max(dpMax[0][1], dpMax[0][2]) + lador[i][2];

            System.arraycopy(dpMax[1], 0, dpMax[0], 0, 3);

            dpMin[1][0] = Math.min(dpMin[0][0], dpMin[0][1]) + lador[i][0];
            dpMin[1][1] = Math.min(dpMin[0][0], dpMin[0][1]);
            dpMin[1][1] = Math.min(dpMin[1][1], dpMin[0][2]) + lador[i][1];
            dpMin[1][2] = Math.min(dpMin[0][1], dpMin[0][2]) + lador[i][2];

            System.arraycopy(dpMin[1], 0, dpMin[0], 0, 3);
        }

        StringBuilder sb = new StringBuilder();     // 최대 최소값을 스트링형으로 반환하기 위한 변수
        int maxPoint = 0, minPoint = Integer.MAX_VALUE;
        for (int j = 0; j < 3; j++) {
            maxPoint = Math.max(maxPoint, dpMax[0][j]);
            minPoint = Math.min(minPoint, dpMin[0][j]);
        }
        sb.append(maxPoint);
        sb.append(" ");
        sb.append(minPoint);
        return sb.toString();
    }
}