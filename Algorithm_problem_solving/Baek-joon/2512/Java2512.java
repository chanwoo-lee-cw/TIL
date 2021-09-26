import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n;
        int[] needs;
        int budget;
        Soultion soultion;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            n = Integer.parseInt(bf.readLine());
            needs = new int[n];
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < n; i++) {
                needs[i] = Integer.parseInt(st.nextToken());
            }
            budget = Integer.parseInt(bf.readLine());
            soultion = new Soultion();
            System.out.println(soultion.getBudget(n, needs, budget));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Soultion {
    public int getBudget(int n, int[] needs, int budget) {
        int answer = 0;
        Arrays.sort(needs);

        int high = needs[needs.length - 1];
        int low = 0;
        int mid, sumMoney;
        while (low <= high) {
            mid = (high + low) / 2;
            sumMoney = 0;
            for (int i = 0; i < n; i++) {
                sumMoney += needs[i] >= mid ? mid : needs[i];
            }
            if (sumMoney <= budget) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return answer;
    }
}