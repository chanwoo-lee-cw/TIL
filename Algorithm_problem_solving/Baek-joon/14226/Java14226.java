import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/14226
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        System.out.println(getMinActive(n));
    }

    // bfs 방식으로 가장 빠른 경로 출력
    public static int getMinActive(int n) {
        Queue<Status> que = new LinkedList<>();
        que.add(new Status(1, 0, 0));
        // 이미 한번 방문한 장소는 재방문 안하기 위한 변수
        boolean[][] dp = new boolean[1100][1100];
        Status currStatus = null;
        while (!que.isEmpty()) {
            currStatus = que.poll();
            if (currStatus.emoticon == n)
                break;
            // 행동 3개 복사,
            if (!dp[currStatus.emoticon][currStatus.emoticon]) {
                que.add(new Status(currStatus.emoticon, currStatus.emoticon, currStatus.active + 1));
                dp[currStatus.emoticon][currStatus.emoticon] = true;
            }
            // 붙혀넣기
            if ((currStatus.emoticon + currStatus.clipBoard) < n + 100 && !dp[currStatus.emoticon + currStatus.clipBoard][currStatus.clipBoard]) {
                que.add(new Status(currStatus.emoticon + currStatus.clipBoard, currStatus.clipBoard, currStatus.active + 1));
                dp[currStatus.emoticon + currStatus.clipBoard][currStatus.clipBoard] = true;
            }
            // 삭제
            if (currStatus.emoticon - 1 >= 0 && !dp[currStatus.emoticon - 1][currStatus.clipBoard]) {
                que.add(new Status(currStatus.emoticon - 1, currStatus.clipBoard, currStatus.active + 1));
                dp[currStatus.emoticon - 1][currStatus.clipBoard] = true;
            }
        }
        return currStatus.active;
    }
}

class Status {
    int emoticon;
    int clipBoard;
    int active;

    Status(int emoticon, int clipBoard, int active) {
        this.emoticon = emoticon;
        this.clipBoard = clipBoard;
        this.active = active;
    }
}