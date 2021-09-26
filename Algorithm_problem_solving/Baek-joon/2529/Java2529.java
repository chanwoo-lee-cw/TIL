import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/2529
public class Main {
    public static int k;    // 수의 배열 k
    public static char[] signs; // 부등호의 갯수
    public static String minValue; // 반환 되어야 하는 최소 수
    public static String maxValue; // 반환 해야하는 최대

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(bf.readLine());
        signs = new char[k + 1];

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i < k + 1; i++) {
            signs[i] = st.nextToken().charAt(0);
        }

        st = null;
        bf.close();

        StringBuilder numList = new StringBuilder();
        boolean[] visited = new boolean[10];
        minValue = String.valueOf(Long.MAX_VALUE);
        maxValue = String.valueOf(Long.MIN_VALUE);

        makeNumList(numList, 0, visited);

        System.out.println(maxValue);
        System.out.println(minValue);
    }

    /*
    dfs로 수열은 만들어서 비교해서 최대와 최소를 만드는 함수
    
    매개변수 : 수열 저장할 문자열, 현재 위치, 방문 여부
    */
    private static void makeNumList(StringBuilder numList, int pos, boolean[] visited) {
        if (pos > k) {
            // 끝에 도달한다면 값 비교 및 출력
            long currNum = Long.parseLong(numList.toString());
            if (Long.parseLong(minValue) > currNum)
                minValue = numList.toString();
            if (Long.parseLong(maxValue) < currNum)
                maxValue = numList.toString();
        } else {
            for (int i = 0; i < 10; i++) {
                if (visited[i])
                    // 이미 사용한 수라면 continue
                    continue;
                if (pos > 0) {
                    int preNum = numList.charAt(pos - 1) - '0';
                    if (signs[pos] == '<' && preNum > i)
                        continue;
                    if (signs[pos] == '>' && preNum < i)
                        continue;
                }
                numList.append(i);
                visited[i] = true;
                makeNumList(numList, pos + 1, visited);
                visited[i] = false;
                numList.deleteCharAt(pos);
            }
        }
    }
}