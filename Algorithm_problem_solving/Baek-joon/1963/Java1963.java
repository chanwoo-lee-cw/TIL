import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int testCase;   // 입력 받을 테스트 케이스의 갯수
        int startNum, destinNum;    // 시작 수와, 목표된 숫자.
        boolean[] eratosthenes = makeEratosthenesSheive(10000);   // 소수 구별을 위한 에라토스테네스의 체

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(bf.readLine());

        StringTokenizer st;     // 시작 수를 입력 받는다.
        StringBuilder sb = new StringBuilder();       // 출력을 빠르게 하기 위한 스트링 빌더

        for (int t = 0; t < testCase; t++) {
            st = new StringTokenizer(bf.readLine());
            startNum = Integer.parseInt(st.nextToken());
            destinNum = Integer.parseInt(st.nextToken());

            sb.append(gerMinModify(startNum, destinNum, eratosthenes));
            sb.append("\n");
        }

        st = null;
        bf.close();
        System.out.println(sb.toString());
    }

    // 변환에 필요한 최소 횟수를 반환한다.
    private static int gerMinModify(int startNum, int destinNum, boolean[] eratosthenes) {
        int[] curr = new int[]{startNum, 0};    //현재 숫자와, 변환 횟수를 저장한다.
        boolean[] visited = new boolean[10000]; // 방문 여부를 저장한다.
        Queue<int[]> queue = new LinkedList<>();
        queue.add(curr);
        visited[curr[0]] = true;

        String currNumStr;      // 변환화기 위한 임시 스트링 저장 변수
        StringBuilder insertNum;    // 자릿수를 바꾸기 위한 임시 저장 변수
        int currNum;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr[0] == destinNum)
                return curr[1];
            currNumStr = Integer.toString(curr[0]);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    insertNum = new StringBuilder(currNumStr);
                    insertNum.setCharAt(i, (char) ('0' + j));
                    currNum = Integer.parseInt(insertNum.toString());
                    if (1000 <= currNum && currNum < 10000 && eratosthenes[currNum] && !visited[currNum]) {
                        queue.add(new int[]{currNum, curr[1] + 1});
                        visited[currNum] = true;
                    }
                }
            }
        }
        return curr[1];
    }

    // n까지의 에라토스테네스의 체를 만든다.
    private static boolean[] makeEratosthenesSheive(int n) {
        boolean[] eratosthenes = new boolean[n];
        Arrays.fill(eratosthenes, 2, n, true);
        for (int i = 2; i < (int) Math.sqrt(n); i++) {
            if (eratosthenes[i]) {
                for (int j = (int) Math.pow(i, 2); j < n; j += i) {
                    eratosthenes[j] = false;
                }
            }
        }
        return eratosthenes;
    }
}