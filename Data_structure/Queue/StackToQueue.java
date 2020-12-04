import java.util.Stack;

public class StackToQueue {
    public static void main(String[] args) {
        // 스택 2개로 큐 구현
        Que<Integer> que = new Que<>();
        for (int i = 0; i < 10; i++) {
            que.push(i);
            if (i % 3 == 0) {
                System.out.println(que.pop());
            }
        }
        while (!que.isEmpty()) {
            System.out.println(que.pop());
        }
    }
}

class Que<E> {
    private Stack<E> stk1;
    private Stack<E> stk2;

    Que() {
        stk1 = new Stack<>();
        stk2 = new Stack<>();
    }

    public void push(E input) {
        stk1.push(input);
    }

    // 만약 stk2가 비어 있다면 stk1에 있는 모든 값을 stk2로 옮긴다.
    // stk2가 비어 있지 않다면 출력
    public E pop() {
        if (stk2.isEmpty()) {
            while (!stk1.isEmpty()) {
                stk2.push(stk1.pop());
            }
        }
        return stk2.pop();
    }

    public boolean isEmpty() {
        if (stk1.isEmpty() && stk2.isEmpty())
            return true;
        else
            return false;
    }
}