import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int n, m;
        Set<Integer> broken = new HashSet<>();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(br.readLine().trim());
            m = Integer.parseInt(br.readLine().trim());

            if (m > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    broken.add(Integer.parseInt(st.nextToken()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("입력 처리 중 오류가 발생했습니다.");
        }

        RemoteController remoteController = new RemoteController(n, broken);
        System.out.println(remoteController.solution());
    }
}

class RemoteController {
    int wantChannel;
    List<Integer> ableButton;

    public RemoteController(int wantChannel, Set<Integer> brokenButton) {
        this.wantChannel = wantChannel;
        this.ableButton = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (!brokenButton.contains(i)) {
                ableButton.add(i);
            }
        }
    }

    public int solution() {
        int answer = Math.abs(wantChannel - 100);
        int len = String.valueOf(wantChannel).length();

        for (int size = 1; size <= len + 1; size++) {
            answer = Math.min(answer, dfs(new StringBuilder(), size));
        }
        return answer;
    }

    private int dfs(StringBuilder sb, int wantSize) {
        if (sb.length() >= wantSize) {
            int now = Integer.parseInt(sb.toString());
            return Math.abs(now - wantChannel) + sb.length();
        }

        int answer = Integer.MAX_VALUE;
        for (int d : ableButton) {
            sb.append(d);
            answer = Math.min(answer, dfs(sb, wantSize));
            sb.deleteCharAt(sb.length() - 1);
        }
        return answer;
    }
}