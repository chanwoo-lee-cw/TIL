import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/14889
public class Main {
    public static int n;    // 총 인원 수
    public static int[][] ablity;   // 각 팀원당 팀을 맺을 경우의 능력치
    public static ArrayList<Integer> oneTeam;   // 팀 하나만 저장한다, 나머지 팀은, 여기에 속해 있지 않은 나머지
    public static HashSet<ArrayList<Integer>> visited;  // 방문 여부

    public static void main(String[] args) {
        oneTeam = new ArrayList<>();
        visited = new HashSet<>();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = null;
            n = Integer.parseInt(bf.readLine());
            ablity = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 1; j <= n; j++) {
                    ablity[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            st = null;
            bf.close();

            minIntervalAblity(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    dfs로 가능한 팀의 조합의 경우의 수를 모두 찾는다.
     */
    private static void minIntervalAblity(int index) {
        if (oneTeam.size() == n / 2) {
            if(visited.contains(oneTeam))
                return;
            visited.add(oneTeam);
            ArrayList<Integer> anotherTeam = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if(!oneTeam.contains(i)) {
                    anotherTeam.add(i);
                }
            }
            visited.add(anotherTeam);
            calIntervalAblity();
        } else {
            for (int i = index; i <= n; i++) {
                oneTeam.add(i);
                minIntervalAblity(i + 1);
                oneTeam.remove(oneTeam.size() - 1);
            }
        }
    }

    /*
    두 팀의 점수 차이를 반환하는 함수
    */
    private static void calIntervalAblity() {
        System.out.println(Arrays.toString(oneTeam.toArray()));
    }
}