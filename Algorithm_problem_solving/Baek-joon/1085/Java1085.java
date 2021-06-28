import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int x, y;   // 현재 위치
        int w, h;   // 직사각형의 크기
        int answer; // 출력될 정답
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            answer = Math.min(Math.min(x, y), Math.min(w - x, h - y));  // 직선 방향으로 가장 가까운 모서리 찾기
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
