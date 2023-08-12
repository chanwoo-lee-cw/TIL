import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        int n, m;
        NAndM nAndM;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        try {
            StringTokenizer tokenizer = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());

            nAndM = new NAndM(n, m);

            nAndM.dfs(1);

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}


class NAndM {
    private int n, m;
    private ArrayList<Integer> output;

    public NAndM(int n, int m) {
        this.n = n;
        this.m = m;
        this.output = new ArrayList<>();
    }

    /**
     * 깊이 우선 순회로 가능한 중복조합의 수를 구한다.
     * @param pos 한번 출력한 값은 더 출력 하지 않기 위한 반복문 시작값
     */
    public void dfs(int pos) {
        if (output.size() == m) {
            // output의 길이가 목표 길이에 도달하면 출력
            printOutput();
        } else {
            for (int i = pos; i <= n; i++) {
                output.add(i);
                dfs(i);
                output.remove(output.size() - 1);
            }
        }
    }

    /**
     * dfs을 통해 얻은 값을 출력한다.
     */
    private void printOutput() {
        StringBuilder sb = new StringBuilder();
        for (int item : output) {
            sb.append(item);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}