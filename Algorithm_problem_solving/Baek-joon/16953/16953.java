import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;


class NumberWithConvertCnt {
    private final long number;
    private final int convertCnt;

    public NumberWithConvertCnt(long number, int convertCnt) {
        this.number = number;
        this.convertCnt = convertCnt;
    }

    public long getNumber() {
        return number;
    }

    public int getConvertCnt() {
        return convertCnt;
    }

}


class Solution {
    /**
     * startNumber로 destNumber 다음과 같은 규칙을 통해 만들 수 있는 최소의 횟수를 리턴한다.
     *
     * <ul>
     *     <li>2를 곱한다.</li>
     *     <li>1을 수의 가장 오른쪽에 추가한다.</li>
     * </ul>
     *
     * @param startNumber 시작할 수
     * @param destNumber 목표로 하는 수
     * @return 변경할 수 있다면 : 최소 횟수, 없다면 -1
     */
    public static int getMinAtoBConvert(
            long startNumber,
            long destNumber
    ) {
        ArrayDeque<NumberWithConvertCnt> queue = new ArrayDeque<>();
        HashSet<Long> visited = new HashSet<>();

        queue.add(new NumberWithConvertCnt(startNumber, 1));
        visited.add(startNumber);
        while (!queue.isEmpty()) {
            NumberWithConvertCnt curr = queue.poll();
            if (curr.getNumber() == destNumber) {
                return curr.getConvertCnt();
            }

            long nextDouble = curr.getNumber() * 2;
            if (!isVisited(nextDouble, visited) && !isOverTarget(nextDouble, destNumber)) {
                visited.add(nextDouble);
                queue.add(new NumberWithConvertCnt(nextDouble, curr.getConvertCnt() + 1));
            }
            long nextPlusOne = Long.parseLong(Long.toString(curr.getNumber()) + "1");
            if (!isVisited(nextPlusOne, visited) && !isOverTarget(nextPlusOne, destNumber)) {
                visited.add(nextPlusOne);
                queue.add(new NumberWithConvertCnt(nextPlusOne, curr.getConvertCnt() + 1));
            }
        }
        return -1;
    }

    /**
     * @return next가 target 보다 크다면 true
     */
    private static boolean isOverTarget(long next, long target) {
        return next > target;
    }

    /**
     * @return next가 visited에 방문한적 있다면 true
     */
    private static boolean isVisited(long next, HashSet<Long> visited) {
        return visited.contains(next);
    }

}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(bf.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());


        System.out.println(
                Solution.getMinAtoBConvert(a, b)
        );
    }
}
