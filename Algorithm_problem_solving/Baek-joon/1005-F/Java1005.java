import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1005
public class Main {
    public static void main(String[] args) {
        ACM acm;
        int t;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            t = Integer.parseInt(bf.readLine());
            for (int test = 0; test < t; test++) {
                acm = new ACM();
                System.out.println(acm.spendTime(bf));
            }

            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class ACM {
    /*
    acm에서 목표하는 건물을 지을 때까지 소모하는 시간을 리턴
     */
    public int spendTime(BufferedReader bf) throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());   // 건물의 갯수
        int k = Integer.parseInt(st.nextToken());   // 선행 건물 조건의 갯수
        int w;                                      // 목표하는 건물
        int answer = 0;                             // 리턴할 건물의 값
        Queue<Building> queue = new LinkedList<>(); // 건설 할 수 있는 건물을 집어 넣기 위한 큐

        Building[] buildings = new Building[n + 1];
        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i < n + 1; i++) {
            buildings[i] = new Building(i, Integer.parseInt(st.nextToken()));
        }

        {
            int x, y;
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(bf.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                buildings[x].addNexts(y);
                buildings[y].plusNeeds();
            }
        }

        for (int i = 1; i < n + 1; i++) {
            if (buildings[i].getNeeds() == 0)
                queue.add(buildings[i]);
        }

        w = Integer.parseInt(bf.readLine());
        answer = 0;

        Building curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.getBuildingNum() == w) {
                // 만약 목표 건물이라면 값을 리턴
                answer = curr.getResultTime();
                break;
            }
            for (int item : curr.getNexts()) {
                buildings[item].minusNeeds();
                // 만약 현재 건물이 완공 되었을 때, 이 건물을 사전 조건으로 가지는 다음 빌딩의 완공 예상 시간
                buildings[item].setResultTime(Math.max(buildings[item].getResultTime(), curr.getResultTime() + buildings[item].getSpendTime()));
                if (buildings[item].getNeeds() == 0)
                    queue.add(buildings[item]);
            }
        }

        return answer;
    }
}

class Building {
    private int buildingNum;    // 빌딩의 넘버
    private int spendTime;      // 이 건물을 짓는데 걸리는 시간
    private int resultTime;     // 이 건물이 완공 되었을 때의 예상 시간
    private ArrayList<Integer> nexts;   // 이 건물을 사전 조건으로 가지는 건물 번호들
    private int needs;              // 이 건물을 짓는데 사전 조건으로 필요한 건물의 수

    public Building(int n, int spendTime) {
        this.buildingNum = n;
        this.spendTime = spendTime;
        this.nexts = new ArrayList<>();
        this.needs = 0;
        this.resultTime = spendTime;
    }

    public void addNexts(int next) {
        this.nexts.add(next);
    }

    public ArrayList<Integer> getNexts() {
        return nexts;
    }

    public int getNeeds() {
        return needs;
    }

    public void plusNeeds() {
        this.needs++;
    }

    public void minusNeeds() {
        this.needs--;
    }

    public int getBuildingNum() {
        return buildingNum;
    }

    public int getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(int spendTime) {
        this.spendTime = spendTime;
    }

    public int getResultTime() {
        return resultTime;
    }

    public void setResultTime(int resultTime) {
        this.resultTime = resultTime;
    }
}