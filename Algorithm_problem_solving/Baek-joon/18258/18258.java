import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st;
            Queue<Integer> queue = new Queue<>();

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                QueueCommand command = QueueCommand.valueOf(st.nextToken());

                switch (command) {
                    case push:
                        queue.push(Integer.parseInt(st.nextToken()));
                        break;
                    case pop:
                        builder.append(Optional.ofNullable(queue.pop()).orElse(-1)).append("\n");
                        break;
                    case size:
                        builder.append(queue.size()).append("\n");
                        break;
                    case empty:
                        builder.append(queue.isEmpty() ? 1 : 0).append("\n");
                        break;
                    case front:
                        builder.append(Optional.ofNullable(queue.front()).orElse(-1)).append("\n");
                        break;
                    case back:
                        builder.append(Optional.ofNullable(queue.back()).orElse(-1)).append("\n");
                        break;
                }
            }
            System.out.println(builder.toString());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

enum QueueCommand {
    push,
    pop,
    size,
    empty,
    front,
    back;
}


class Queue<T> {
    List<T> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(T item) {
        list.add(item);
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return list.remove(0);
    }

    public T front() {
        if (isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public T back() {
        if (isEmpty()) {
            return null;
        }
        return list.get(size() - 1);
    }
}