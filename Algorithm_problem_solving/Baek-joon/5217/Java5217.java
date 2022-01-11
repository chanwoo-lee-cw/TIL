import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        try {
            int t = Integer.parseInt(bf.readLine());
            ArrayList<int[]>[] numList = new ArrayList[13 * 2 + 1];
            for (int i = 0; i < 13 * 2 + 1; i++) {
                numList[i] = new ArrayList();
            }
            for (int i = 1; i <= 13; i++) {
                for (int j = i + 1; j <= 13; j++) {
                    numList[i + j].add(new int[]{i, j});
                }
            }
            int n;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < t; i++) {
                n = Integer.parseInt(bf.readLine());
                sb.append(String.format("Pairs for %d:", n));
                for (int j = 0; j < numList[n].size(); j++) {
                    int[] item = numList[n].get(j);
                    sb.append(String.format(" %d %d", item[0], item[1]));
                    sb.append(",");
                }
                if (numList[n].size() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                sb.append("\n");
            }
            System.out.print(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
