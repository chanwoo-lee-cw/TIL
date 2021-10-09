import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        ApartmentComplex complex;

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            {
                int n = Integer.parseInt(bf.readLine());
                boolean[][] apartment = new boolean[n][n];
                String temp;
                for (int i = 0; i < n; i++) {
                    temp = bf.readLine();
                    for (int j = 0; j < n; j++) {
                        apartment[i][j] = temp.charAt(j) == '1';
                    }
                }
                complex = new ApartmentComplex(n, apartment);
                System.out.println(complex.getComplexCnt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ApartmentComplex {
    private int n;
    private boolean[][] complex;
    private final int[][] way = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public ApartmentComplex(int n, boolean[][] complex) {
        this.n = n;
        this.complex = complex;
    }

    public int dfs(boolean[][] visited, int y, int x) {
        int complexSize = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        visited[y][x] = true;

        int[] curr;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            complexSize++;
            for (int[] next : way) {
                nextY = next[0] + curr[0];
                nextX = next[1] + curr[1];
                if (nextY < 0 || nextY >= n || nextX < 0 || nextX >= n) {
                    continue;
                } else if (visited[nextY][nextX] || !complex[nextY][nextX]) {
                    continue;
                }
                //else
                queue.add(new int[]{nextY, nextX});
                visited[nextY][nextX] = true;
            }
        }

        return complexSize;
    }

    public String getComplexCnt() {
        int complexCnt = 0;
        ArrayList<Integer> complexSize = new ArrayList<>();
        StringBuilder answer = new StringBuilder();
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (complex[i][j] && !visited[i][j]) {
                    complexCnt++;
                    complexSize.add(dfs(visited, i, j));
                }
            }
        }
        Collections.sort(complexSize);
        answer.append(complexCnt);
        answer.append("\n");
        for (int item : complexSize) {
            answer.append(item);
            answer.append("\n");
        }
        return answer.toString();
    }
}