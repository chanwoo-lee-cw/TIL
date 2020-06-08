import java.util.ArrayList;
import java.util.Scanner;

// https://www.acmicpc.net/problem/15651

public class Main {

    static int n;
    static int m;
    static ArrayList<Integer> output;
    // StringBuilder for shortening output time
    static StringBuilder sb;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        m = in.nextInt();
        sb = new StringBuilder();

        in.close();

        output = new ArrayList<Integer>();

        dfs();
        System.out.println(sb);
    }

    public static void dfs() {
        // It is a function that is used repeatedly, so it is saved as a variable
        int outLen = output.size();

        // When the list is full, save it to sb.
        if (outLen == m) {
            for (int i = 0; i < outLen; i++) {
                sb.append(output.get(i) + " ");
            }
            sb.append("\n");
        } else {
            // There is no need to check the duplicate value, so just put it
            for (int i = 1; i < n + 1; i++) {
                if (outLen < m) {
                    output.add(i);
                    dfs();
                    output.remove(output.size() - 1);
                }
            }
        }
    }
}