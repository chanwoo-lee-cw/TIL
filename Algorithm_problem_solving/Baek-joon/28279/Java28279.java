import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();
        int n;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        try {
            n = Integer.parseInt(bf.readLine());
            for (int i = 0; i < n; i++) {
                deck.command(bf.readLine());
            }
            deck.printResult();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}


class Deck {
    ArrayList<Integer> list;
    StringBuilder sb;

    public Deck() {
        this.list = new ArrayList<>();
        this.sb = new StringBuilder();
    }

    public void command(String command) {
        StringTokenizer st = new StringTokenizer(command);
        int comm = Integer.parseInt(st.nextToken());
        switch (comm) {
            case 1:
                insertFront(Integer.parseInt(st.nextToken()));
                break;
            case 2:
                insertBack(Integer.parseInt(st.nextToken()));
                break;
            case 3:
                sb.append(popFront());
                sb.append("\n");
                break;
            case 4:
                sb.append(popBack());
                sb.append("\n");
                break;
            case 5:
                sb.append(getSize());
                sb.append("\n");
                break;
            case 6:
                sb.append(isEmpty());
                sb.append("\n");
                break;
            case 7:
                sb.append(front());
                sb.append("\n");
                break;
            case 8:
                sb.append(back());
                sb.append("\n");
                break;
            default:
                break;
        }
    }

    public void printResult() {
        System.out.println(sb.toString());
    }

    public void insertFront(int num) {
        list.add(0, num);
    }

    public void insertBack(int num) {
        list.add(num);
    }

    public int popFront() {
        if (isEmpty() == 1) {
            return -1;
        }
        int answer = list.get(0);
        list.remove(0);
        return answer;
    }

    public int popBack() {
        if (isEmpty() == 1) {
            return -1;
        }
        int answer = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return answer;
    }

    public int getSize() {
        return list.size();
    }

    public int isEmpty() {
        return list.size() == 0 ? 1 : 0;
    }

    public int front() {
        if (isEmpty() == 1) {
            return -1;
        }
        return list.get(0);
    }

    public int back() {
        if (isEmpty() == 1) {
            return -1;
        }
        return list.get(list.size() - 1);
    }

}