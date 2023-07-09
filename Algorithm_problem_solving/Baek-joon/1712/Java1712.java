import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /**
     * 손익 분기점을 구한다
     *
     * @param fixedCost    고정비용
     * @param variableCost 가변비용
     * @param salesPrice   판매비용
     * @return 손익분기점
     */
    private static long getBreakEvenPolong(long fixedCost, long variableCost, long salesPrice) {
        double breakEvenPoint = 0;
        if (variableCost >= salesPrice) {
            return -1;
        }
        breakEvenPoint = (double) fixedCost / (salesPrice - variableCost);
        // 손익 분기점은 이익이 나는 시점이기 때문에 0이 되면 1+
        return (long) (breakEvenPoint == Math.ceil(breakEvenPoint) ? breakEvenPoint + 1 : Math.ceil(breakEvenPoint));
    }

    public static void main(String[] args) {
        long fixedCost, variableCost, salesPrice;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            fixedCost = Long.parseLong(st.nextToken());
            variableCost = Long.parseLong(st.nextToken());
            salesPrice = Long.parseLong(st.nextToken());
        } catch (IOException e) {
            return;
        }

        System.out.println(getBreakEvenPolong(fixedCost, variableCost, salesPrice));
    }
}