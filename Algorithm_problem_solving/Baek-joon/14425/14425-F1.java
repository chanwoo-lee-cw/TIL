import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        HashSet<String> nSet = new HashSet<>();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        for (int i = 0; i < n; i++) {
            nSet.add(bufferedReader.readLine().strip());
        }
        int answer = 0;
        for (int i = 0; i < m; i++) {
            String currString = bufferedReader.readLine();
            if (nSet.contains(currString)) answer++;
        }

        System.out.println(answer);
    }
}