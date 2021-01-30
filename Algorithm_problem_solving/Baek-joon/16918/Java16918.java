import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;      // 구슬의 갯수
        ArrayList<Integer> beads;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(bf.readLine());
        beads = new ArrayList<>();

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i < n + 1; i++) {
            beads.add(Integer.parseInt(st.nextToken()));
        }

        int maxSum = 0;
        for (int i = 1; i < n - 1; i++) {
            maxSum = Math.max(maxSum, getWeight(n, beads, i));
        }
        System.out.println(maxSum);
    }

    /*
    재귀로 구슬을 하나씩 빼며 계산한다. 반환되 값과 이 구슬 하나 뺐을 때의 점수를 더하며 반화한다.
    매개변수 : 남은 구슬의 갯수, 현재 남은 구슬들의 점수, 빼야될 구슬의 위치
     */
    private static int getWeight(int n, ArrayList<Integer> beads, int i) {
        if (n == 2)
            return 0;
        int saveWeight = beads.get(i);
        int point = beads.get(i - 1) * beads.get(i + 1);
        int maxSum = point;
        beads.remove(i);
        for (int j = 1; j < beads.size() - 1; j++)
            maxSum = Math.max(maxSum, getWeight(n - 1, beads, j) + point);
        beads.add(i, saveWeight);
        return maxSum;
    }
}