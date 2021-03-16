import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// https://www.acmicpc.net/problem/1874
public class Main {
    public static void main(String[] args) {
        int n;
        Soulution soulution;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            soulution = new Soulution(bf);
            soulution.run();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Soulution {
    private Stack<Integer> stk;
    private int n;
    private int nextInsertNum;
    private int[] arr;
    private ArrayList<Character> output;

    public Soulution(BufferedReader bf) throws IOException {
        this.n = Integer.parseInt(bf.readLine());
        this.stk = new Stack<>(n);

        this.nextInsertNum = 1;
        this.arr = new int[this.n];
        output = new ArrayList<>();
        for (int i = 0; i < n; i++)
            this.arr[i] = Integer.parseInt(bf.readLine());
    }

    public void run() {
        for (int i = 0; i < n; i++) {
            // 현재 필요한 수가 나올 때까지 집어 넣는다.
            while (nextInsertNum <= arr[i]) {
                stk.push(nextInsertNum++);
                output.add('+');
            }
            if (stk.peek() < arr[i]) {
                System.out.println("NO");
                return;
            }
            stk.pop();
            output.add('-');
        }
        print();
    }

    private void print() {
        for (char item : output) {
            System.out.println(item);
        }
    }
}


class Stack<T> {
    private int maxSize;
    private int top;
    private T[] arr;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.top = -1;
        this.arr = (T[]) new Object[maxSize + 1];
    }

    public boolean isFull() {
        if (top == maxSize - 1)
            return true;
        else
            return false;
    }

    public boolean isEmpty() {
        if (top == -1)
            return true;
        else
            return false;
    }

    public void push(T item) {
        if (isFull())
            return;
        arr[++top] = item;
    }

    public T pop() {
        T answer = arr[top--];
        return answer;
    }

    public T peek() {
        return arr[top];
    }
}
