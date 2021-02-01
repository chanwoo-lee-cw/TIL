import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/14889
public class Main {
    public static int n;    // 총 인원 수
    public static int[][] ablity;   // 각 팀원당 팀을 맺을 경우의 능력치
    public static ArrayList<Integer> oneTeam;   // 팀 하나만 저장한다, 나머지 팀은, 여기에 속해 있지 않은 나머지
    public static HashSet<ArrayList<Integer>> visited;  // 방문 여부
    public static int minInterval;  // 최소 점수차

    public static void main(String[] args) {
        oneTeam = new ArrayList<>();
        visited = new HashSet<>();
        minInterval = Integer.MAX_VALUE;
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
            System.out.println(minInterval);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    dfs로 가능한 팀의 조합의 경우의 수를 모두 찾는다.
    매개변수 - dfs조합을 위한 인덱스
     */
    private static void minIntervalAblity(int index) {
        if (oneTeam.size() == n / 2) {
            if (visited.contains(oneTeam))
                return;
            visited.add(oneTeam);
            ArrayList<Integer> anotherTeam = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (!oneTeam.contains(i)) {
                    anotherTeam.add(i);
                }
            }
            visited.add(anotherTeam);
            ArrayList<Integer> memberSet = new ArrayList<>();
            minInterval = Math.min(minInterval, Math.abs(calIntervalAblity(oneTeam, memberSet, 0) - calIntervalAblity(anotherTeam, memberSet, 0)));
        } else {
            for (int i = index; i <= n; i++) {
                oneTeam.add(i);
                minIntervalAblity(i + 1);
                oneTeam.remove(oneTeam.size() - 1);
            }
        }
    }

    /*
    팀 별로, 총 능력 합계를 반환하는 함수
    두 명 씩 짝을 지어 둘의 점수를 합계를 리턴
    매개변수 - 능력치의 합계를 구할 팀의 모습, 둘 씩 짝지어주는 변수, dfs 조합을 위한 위치
    */
    private static int calIntervalAblity(ArrayList<Integer> team, ArrayList<Integer> memberSet, int index) {
        int sum = 0;
        if (memberSet.size() == 2) {
            sum = ablity[memberSet.get(0)][memberSet.get(1)];
            sum += ablity[memberSet.get(1)][memberSet.get(0)];
        } else {
            for (int i = index; i < n / 2; i++) {
                memberSet.add(team.get(i));
                sum += calIntervalAblity(team, memberSet, i + 1);
                memberSet.remove(memberSet.size() - 1);
            }
        }
        return sum;
    }
}