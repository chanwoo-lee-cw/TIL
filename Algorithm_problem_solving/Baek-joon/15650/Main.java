import java.util.ArrayList;
import java.util.Scanner;

//https://www.acmicpc.net/problem/15650

public class Main {

    static int n;
    static int m;
    static ArrayList<Integer> output;

    public static void main(String[] args) throws Exception {
        // BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        m = in.nextInt();

        in.close();

        output = new ArrayList<Integer>();

        dfs(1);
    }

    // Obtain the combination in DFS way
    private static void dfs(int pos) {
        // If the desired length is reached, print it out
        if (output.size() == m) {
            for (int i = 0; i < output.size(); i++) {
                System.out.print(output.get(i) + " ");
            }
            System.out.println();
        } else {
            // Since you don't need to get the contents you've already put in,
            // start looking for pos first
            for (int i = pos; i < n + 1; i++) {
                output.add(i);
                dfs(i + 1);
                output.remove(output.size() - 1);
            }
        }
    }
}