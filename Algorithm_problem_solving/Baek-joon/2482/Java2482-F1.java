import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2482
public class Main {
    public static void main(String[] args) {
        Coloring coloring;

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            coloring = new Coloring(bf);
            System.out.println(coloring.selectColorCaseNum(0, Integer.parseInt(bf.readLine())));
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Coloring {
    private int n;
    private boolean[] visited;

    public Coloring(BufferedReader bf) throws IOException {
        this.n = Integer.parseInt(bf.readLine());
        this.visited = new boolean[n + 3];
    }

    /*
    만약 남은 색의 수가 남은 픽업 수보다 작다면 0 리턴
    k가 0이 된다면 1리턴
     */
    public int selectColorCaseNum(int pos, int k) {
        if (k == 0) {
            return 1;
        } else if ((n - pos) / 2 < k) {
            return 0;
        }
        // else
        int answer = 0;
        for (int i = pos + 1; i <= n; i++) {
            if (visited[i - 1])
                continue;
            if (i == n && visited[1])
                continue;
            visited[i] = true;
            answer += selectColorCaseNum(i, k - 1);
            visited[i] = false;
        }
        return answer;
    }

}