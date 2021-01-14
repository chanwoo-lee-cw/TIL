import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    public static HashSet<ArrayList<Character>> map = new HashSet<>();  // 한번 입력된 내용인지 확인하기 위한 Set
    /*
    백트래킹으로 풀기
    A,B,C의 갯수를 각각 세서
    매개 변수로 갯수를 넣는다. 마지막 A의 위치와 B위치를 기억한다.
     */
    public static void main(String[] args) throws IOException {
        int cntA = 0;   // A의 갯수
        int cntB = 0;   // B의 갯수
        int cntC = 0;   // C의 갯수
        String inputLine = null;    // 입력된 줄
        ArrayList<Character> workLog = new ArrayList<>();


        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        inputLine = bf.readLine();
        for (int i = 0; i < inputLine.length(); i++) {
            if (inputLine.charAt(i) == 'A')
                cntA++;
            else if (inputLine.charAt(i) == 'B')
                cntB++;
            else
                cntC++;
        }

        bf.close();

        if (!makeWorkLog(workLog, inputLine.length(), cntA, cntB, cntC, -10, -10))
            System.out.println(-1);
    }

    // 백트래킹으로 방문 여부 생산
    private static boolean makeWorkLog(ArrayList<Character> workLog, int length, int cntA, int cntB, int cntC, int beforeB, int beforeC) {
        if(map.contains(workLog)){
            // 이미 방문 한 곳인지 확인
            return false;
        }
        map.add(workLog);
        if (workLog.size() == length) {
            // 출결 사항이 완성 된다면 출력
            StringBuilder sb = new StringBuilder();
            for (char item : workLog)
                sb.append(item);
            System.out.println(sb.toString());
            return true;
        } else {
            if (beforeC < -2 && cntC > 0) {
                workLog.add('C');
                if (makeWorkLog(workLog, length, cntA, cntB, cntC - 1, beforeB - 1, -1))
                    return true;
                workLog.remove(workLog.size() - 1);
            }
            if (beforeB < -1 && cntB > 0) {
                workLog.add('B');
                if (makeWorkLog(workLog, length, cntA, cntB - 1, cntC, -1, beforeC - 1))
                    return true;
                workLog.remove(workLog.size() - 1);
            }
            if (cntA > 0) {
                workLog.add('A');
                if (makeWorkLog(workLog, length, cntA - 1, cntB, cntC, beforeB - 1, beforeC - 1))
                    return true;
                workLog.remove(workLog.size() - 1);
            }
        }
        return false;
    }
}