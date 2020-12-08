import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1890
public class Java1890 {
    public static long search(int n, int[][] matrix) {
        long[][] visited = new long[n][n];
        visited[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j] == 0)
                    continue;
                else if(i==n-1 && j == n-1)
                    break;
                if (i+matrix[i][j]<n)
                    visited[i+matrix[i][j]][j] += visited[i][j];
                if (j+matrix[i][j]<n)
                    visited[i][j+matrix[i][j]] += visited[i][j];
            }
        }
        return visited[n-1][n-1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] matrix = new int[n][n];
        {
            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        bf.close();
        System.out.println(search(n, matrix));
    }
}