import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static boolean[][][] visited;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        in.close();

        visited = new boolean[1001][1001][1001];
        if ((a + b + c) % 3 != 0)
            System.out.println(0);
        else
            System.out.println(checkMakeSame(a, b, c) ? 1 : 0);
    }

    public static boolean checkMakeSame(int a, int b, int c) {
        // 일단 수가 전부 같다면 1 반환
        // visited로 방문한 장소 체크 현재 상태에서 방문할 수 있는 곳 방문 했으면 0 반환
        // 만약 y-x가 0보다 작다면 그쪽으로 탐색 x
        // 하나라도 1 반환했으면 그거 반
        // 가장 작은 것만 찾아서 나머지 둘과 계산하면 된다.
        if (a == b && b == c)
            return true;
        int[] arr = new int[]{a, b, c};
        Arrays.sort(arr);
        if (visited[arr[0]][arr[1]][arr[2]])
            return false;
        visited[arr[0]][arr[1]][arr[2]] = true;
        if (arr[1] - arr[0] >= 0) {
            if (checkMakeSame(arr[0] + arr[0], arr[1] - arr[0], arr[2]))
                return true;
        }
        if (arr[2] - arr[0] >= 0) {
            if (checkMakeSame(arr[0] + arr[0], arr[1], arr[2] - arr[0]))
                return true;
        }
        return false;
    }
}