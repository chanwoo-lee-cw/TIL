import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Solution solution;      // 문제의 답을 출력할 객체
        int n;                  // 큐 안에 들어갈 수의 갯수
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            solution = new Solution();
            n = Integer.parseInt(bf.readLine());
            System.out.println(solution.run(n));
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Solution {

    /*
    1~n까지의 수를 집어 넣고, 1 discard, 2 rein, 3 discard, 4 rein,....
    식으로 했을 때 마지막에 남는 수를 반환
    */
    public int run(int n) {
        MyQueue<Integer> queue = new MyQueue<>(n);
        for (int i = 1; i <= n; i++) {
            queue.push(i);
        }
        int rein;
        while (queue.size() > 1) {
            queue.pop();
            rein = queue.pop();
            queue.push(rein);
        }
        return queue.pop();
    }

}

class MyQueue<T> {
    private T[] queue;      // 큐의 수를 저장할 배열
    private int maxSize;    // 큐의 최대 크기
    private int front, rear;    // 큐의 첫번째와 마지막의 위치

    public MyQueue(int maxSize) {
        this.maxSize = maxSize + 1;
        this.queue = (T[]) new Object[this.maxSize];
        this.front = 0;
        this.rear = 0;
    }

    public boolean isEmpty() {
        if (front == rear)
            return true;
        else
            return false;
    }

    public boolean isFull() {
        if (((rear + 1) % maxSize) == front)
            return true;
        else
            return false;
    }

    public void push(T item) {
        if (isFull())
            return;
        rear = (rear + 1) % maxSize;
        queue[rear] = item;
    }

    public T pop() {
        if (isEmpty())
            return null;
        front = (front + 1) % maxSize;
        return queue[front];
    }

    public int size() {
        int answer = rear - front;
        return answer >= 0 ? answer : maxSize + answer;
    }
}