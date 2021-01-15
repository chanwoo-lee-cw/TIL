import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 이 방법은 BFS에 방문 표시를 어떻게 해줘야 할지 감이 잘 안 잡혀서 취소.
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        System.out.println(getMaxPrintNum(n));
    }

    // bfs 탐색으로 찾는다.
    public static int getMaxPrintNum(int n) {
        Queue<KriiBoard> queue = new LinkedList<>();    // BFS를 위한 큐
        int maxPrint = Integer.MIN_VALUE;               // 가장 많이 출력된 수를 저장한다.
        queue.add(new KriiBoard(1, 0, 1, false));
        KriiBoard curr = null;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            maxPrint = Math.max(maxPrint, curr.printNum);
            if (curr.nPress == n)
                continue;
            queue.add(new KriiBoard(curr.printNum + 1, curr.bufferNum, curr.nPress + 1, false));
            if (!curr.selected)
                queue.add(new KriiBoard(curr.printNum, curr.bufferNum, curr.nPress + 1, true));
            if (curr.selected)
                queue.add(new KriiBoard(curr.printNum, curr.printNum, curr.nPress + 1, false));
            if (curr.bufferNum > 0)
                queue.add(new KriiBoard(curr.printNum + curr.bufferNum, curr.bufferNum, curr.nPress + 1, false));
        }
        return maxPrint;
    }
}

class KriiBoard {
    int printNum;       // 출력된 A의 수
    int bufferNum;      // 버퍼에 저장된 수
    int nPress;         // 버튼을 누른 횟수.
    boolean selected;

    KriiBoard(int printNum, int bufferNum, int nPress, boolean selected) {
        this.printNum = printNum;
        this.bufferNum = bufferNum;
        this.nPress = nPress;
        this.selected = selected;
    }
}