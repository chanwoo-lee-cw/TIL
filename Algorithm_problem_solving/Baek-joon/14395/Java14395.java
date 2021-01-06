import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        long s;  // 시작 갑
        long t;  // 목 표 값

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        st = null;
        bf.close();

        String result = MinCalculation(s, t);
        System.out.println(result);
    }

    // s에서 t값이 될수 있나 찾는 것을 bfs 방식으로 찾는다.
    private static String MinCalculation(long s, long t) {
        StringBuilder sb = new StringBuilder();     // 공식을 출력하기 위한
        if (s == t)     // 만약 수가 같다면, true 반환
            return sb.append("0").toString();

        Queue<NumFromTo> queue = new LinkedList<>();
        HashMap<Long, NumFromTo> whereFrom = new HashMap<>();   // 현재의 값이 어디에서 왔는지 찾기 위한 해쉬맵
        NumFromTo curr = new NumFromTo(s, s, 'c');
        queue.add(curr);
        long tempNum;
        NumFromTo saveClass;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.to == t)
                break;
            // -는 계산 안 했는데 s-s는 0무조건 0이고 0에서 갈 수 있는 값도 다시 s밖에 없기 때
            tempNum = curr.to * curr.to;
            if (!whereFrom.containsKey(tempNum) && (tempNum <= t || tempNum <= s)) {
                saveClass = new NumFromTo(tempNum, curr.to, '*');
                queue.add(saveClass);
                whereFrom.put(tempNum, saveClass);
            }
            tempNum = curr.to + curr.to;
            if (!whereFrom.containsKey(tempNum) && (tempNum <= t || tempNum <= s)) {
                saveClass = new NumFromTo(tempNum, curr.to, '+');
                queue.add(saveClass);
                whereFrom.put(tempNum, saveClass);
            }
            tempNum = curr.to / curr.to;
            if (!whereFrom.containsKey(tempNum) && (tempNum <= t || tempNum <= s)) {
                saveClass = new NumFromTo(tempNum, curr.to, '/');
                queue.add(saveClass);
                whereFrom.put(tempNum, saveClass);
            }
        }
        if (!whereFrom.containsKey(t))          // 만약 끝까지 t값에 도착하지 못했으면 false 반환
            return sb.append("-1").toString();
        while (t != s) {        // 해쉬맵에 저장된 것을 바탕으로 역순으로 찾아간다.
            curr = whereFrom.get(t);
            sb.append(curr.operator);
            t = curr.from;
        }
        return sb.reverse().toString();
    }
}

class NumFromTo {
    long to;        // 현재 값
    long from;      // 현재 값이 어디에서 왔는지 찾기
    char operator;  // 연산자를 저장

    NumFromTo(long to, long from, char operator) {
        this.to = to;
        this.from = from;
        this.operator = operator;
    }
}