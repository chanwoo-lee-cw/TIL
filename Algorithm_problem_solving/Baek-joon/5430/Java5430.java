import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        try {
            int t = Integer.parseInt(bf.readLine());
            for (int i = 0; i < t; i++) {
                String p = bf.readLine();
                int n = Integer.parseInt(bf.readLine());
                Deque<Integer> arr = new ArrayDeque<>();
                String temp = bf.readLine();
                st = new StringTokenizer(temp.substring(1, temp.length() - 1));
                while (st.hasMoreTokens()) {
                    arr.add(Integer.parseInt(st.nextToken(",")));
                }
                FucntionAC fucntionAC = new FucntionAC(p, arr);
                System.out.println(fucntionAC.run());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class FucntionAC {
    private String p;
    private Deque<Integer> arr;
    private boolean frontIs0;

    public FucntionAC(String p, Deque<Integer> arr) {
        this.p = p;
        this.arr = arr;
        this.frontIs0 = true;
    }

    private boolean excuteR() {
        this.frontIs0 = !this.frontIs0;
        return true;
    }

    private boolean excuteD() {
        if (arr.size() == 0)
            return false;
        // else
        if (this.frontIs0)
            arr.pollFirst();
        else
            arr.pollLast();
        return true;
    }

    private String makeSring() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (!this.arr.isEmpty()) {
            sb.append(this.frontIs0 ? arr.pollFirst() : arr.pollLast());
            sb.append(",");
        }
        if (sb.length() > 1) sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    public String run() {
        char command;
        for (int i = 0; i < this.p.length(); i++) {
            command = this.p.charAt(i);
            if (command == 'R') {
                excuteR();
            } else if (command == 'D') {
                if (!excuteD())
                    return "error";
            }
        }
        return makeSring();
    }
}