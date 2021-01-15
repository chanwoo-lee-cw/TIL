import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// A,B,C로 한게 실수였음. 배열로 반복문 처리하는게 훨씬 나았을듯.
public class Main {
    public static void main(String[] args) throws IOException {
        Hanoi hanoi = new Hanoi();  // 하노이의 탑 상태를 저장할 배

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int length;
        String temp;
        {
            st = new StringTokenizer(bf.readLine());
            length = Integer.parseInt(st.nextToken());
            if (length > 0) {
                temp = st.nextToken();
                for (int j = 0; j < length; j++) {
                    hanoi.stkA.add(temp.charAt(j));
                    if (hanoi.stkA.peek() != 'A')
                        hanoi.exceptA++;
                }
            }
            st = new StringTokenizer(bf.readLine());
            length = Integer.parseInt(st.nextToken());
            if (length > 0) {
                temp = st.nextToken();
                for (int j = 0; j < length; j++) {
                    hanoi.stkB.add(temp.charAt(j));
                    if (hanoi.stkB.peek() != 'B')
                        hanoi.exceptB++;
                }
            }
            st = new StringTokenizer(bf.readLine());
            length = Integer.parseInt(st.nextToken());
            if (length > 0) {
                temp = st.nextToken();
                for (int j = 0; j < length; j++) {
                    hanoi.stkC.add(temp.charAt(j));
                    if (hanoi.stkC.peek() != 'C')
                        hanoi.exceptC++;
                }
            }
        }
        st = null;
        bf.close();

        System.out.println(getMinMoveCnt(hanoi));
    }

    private static int getMinMoveCnt(Hanoi hanoi) {
        HashSet<String> visited = new HashSet<>();  // set으로 방문 여부 체크
        Queue<Hanoi> queue = new LinkedList<>();

        queue.add(hanoi);
        visited.add(hanoi.toString());
        Character temp;
        while (!queue.isEmpty()) {
            hanoi = queue.poll();
            if (hanoi.exceptA == 0 && hanoi.exceptB == 0 && hanoi.exceptC == 0)
                return hanoi.moveCnt;
            // A에서 이동
            if (!hanoi.stkA.isEmpty()) {
                temp = hanoi.stkA.pop();
                if (temp != 'A')
                    hanoi.exceptA--;
                // A->B
                hanoi.stkB.add(temp);
                if (temp != 'B')
                    hanoi.exceptB++;
                if (!visited.contains(hanoi.toString())) {
                    queue.add(Hanoi.clone(hanoi, hanoi.moveCnt + 1));
                    visited.add(hanoi.toString());
                }
                hanoi.stkB.pop();
                if (temp != 'B')
                    hanoi.exceptB--;
                // A->C
                hanoi.stkC.add(temp);
                if (temp != 'C')
                    hanoi.exceptC++;
                if (!visited.contains(hanoi.toString())) {
                    queue.add(Hanoi.clone(hanoi, hanoi.moveCnt + 1));
                    visited.add(hanoi.toString());
                }
                hanoi.stkC.pop();
                if (temp != 'C')
                    hanoi.exceptC--;
                hanoi.stkA.add(temp);
                if (temp != 'A')
                    hanoi.exceptA++;
            }

            // B에서 이동
            if (!hanoi.stkB.isEmpty()) {
                temp = hanoi.stkB.pop();
                if (temp != 'B')
                    hanoi.exceptB--;
                // B->A
                hanoi.stkA.add(temp);
                if (temp != 'A')
                    hanoi.exceptA++;
                if (!visited.contains(hanoi.toString())) {
                    queue.add(Hanoi.clone(hanoi, hanoi.moveCnt + 1));
                    visited.add(hanoi.toString());
                }
                hanoi.stkA.pop();
                if (temp != 'A')
                    hanoi.exceptA--;
                // B->C
                hanoi.stkC.add(temp);
                if (temp != 'C')
                    hanoi.exceptC++;
                if (!visited.contains(hanoi.toString())) {
                    queue.add(Hanoi.clone(hanoi, hanoi.moveCnt + 1));
                    visited.add(hanoi.toString());
                }
                hanoi.stkC.pop();
                if (temp != 'C')
                    hanoi.exceptC--;
                hanoi.stkB.add(temp);
                if (temp != 'B')
                    hanoi.exceptB++;

                // C에서 이동
                if (!hanoi.stkC.isEmpty()) {
                    temp = hanoi.stkC.pop();
                    if (temp != 'C')
                        hanoi.exceptC--;
                    // C->B
                    hanoi.stkB.add(temp);
                    if (temp != 'B')
                        hanoi.exceptB++;
                    if (!visited.contains(hanoi.toString()))
                        queue.add(Hanoi.clone(hanoi, hanoi.moveCnt + 1));
                    hanoi.stkB.pop();
                    if (temp != 'B')
                        hanoi.exceptB--;
                    // C->A
                    hanoi.stkA.add(temp);
                    if (temp != 'A')
                        hanoi.exceptA++;
                    if (!visited.contains(hanoi.toString()))
                        queue.add(Hanoi.clone(hanoi, hanoi.moveCnt + 1));
                    hanoi.stkA.pop();
                    if (temp != 'A')
                        hanoi.exceptA--;
                    hanoi.stkC.add(temp);
                    if (temp != 'C')
                        hanoi.exceptC++;
                }
            }
        }

        return 0;
    }
}

class Hanoi {
    Stack<Character> stkA;  // A 탑의 상태
    int exceptA;            // A 탑에 A가 아닌 다른 원판의 갯수
    Stack<Character> stkB;  // B 탑의 상태
    int exceptB;            // A 탑dp B가 아닌 다른 원판의 갯수
    Stack<Character> stkC;  // C 탑의 상태
    int exceptC;            // C 탑에 C가 아닌 다른 원판의 갯수
    int moveCnt;            // 움직인 횟수

    Hanoi() {
        stkA = new Stack<>();
        int exceptA = 0;
        stkB = new Stack<>();
        int exceptB = 0;
        stkC = new Stack<>();
        int exceptC = 0;
        this.moveCnt = 0;
    }

    public Hanoi(Stack<Character> stkA, int exceptA, Stack<Character> stkB, int exceptB, Stack<Character> stkC, int exceptC, int moveCnt) {
        this.stkA = (Stack<Character>) stkA.clone();
        this.exceptA = exceptA;
        this.stkB = (Stack<Character>) stkB.clone();
        this.exceptB = exceptB;
        this.stkC = (Stack<Character>) stkC.clone();
        this.exceptC = exceptC;
        this.moveCnt = moveCnt;
    }

    public static Hanoi clone(Hanoi old, int moveCnt) {
        return new Hanoi(old.stkA, old.exceptA, old.stkB, old.exceptB, old.stkC, old.exceptC, moveCnt);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hanoi{");
        sb.append("stkA=");
        sb.append(stkA.toString());
        sb.append(", stkB=");
        sb.append(stkB.toString());
        sb.append(", stkC=");
        sb.append(stkC.toString());
        sb.append('}');
        return sb.toString();
    }
}