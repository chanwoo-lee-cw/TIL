// https://www.acmicpc.net/problem/1422
// 실패 사유 : 메모리 부족

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        MakeBiggestNumber makeBiggestNumber;
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer stringTokenizer = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[] numberList = new int[n];

            for (int i = 0; i < n; i++) {
                numberList[i] = Integer.parseInt(bf.readLine());
            }
            makeBiggestNumber = new MakeBiggestNumber(n, m, numberList);
            makeBiggestNumber.printMaxNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class MakeBiggestNumber {
    private int numberSize;
    private int wantUsed;
    private int[] numberList;
    private int[] usedList;
    private String result;

    public MakeBiggestNumber(int numberSize, int wantUsed, int[] numberList) {
        this.numberSize = numberSize;
        this.wantUsed = wantUsed;
        this.numberList = Arrays.stream(numberList).sorted().toArray();
        this.usedList = new int[numberSize];
        this.result = "";
        Arrays.fill(usedList, 0);
    }

    public void printMaxNumber() {
        StringBuilder sb = new StringBuilder();
        getMakealeMaxNumber(sb, 0, 0);
        System.out.println(result);
    }

    private String getMakealeMaxNumber(StringBuilder sb, int usedCount, int idx) {
        if (wantUsed == usedCount) {
            if (unlessUsedNumber()) {
                return null;
            }
            return sb.toString();
        } else {
            for (int i = idx; i < numberSize; i++) {
                usedList[i]++;
                sb.append(numberList[i]);
                String answer = getMakealeMaxNumber(sb, usedCount + 1, 0);
                compareTwoStringRetunLarge(answer);
                int sbLenght = sb.length();
                sb.delete(sbLenght - Integer.toString(numberList[i]).length(), sbLenght);
                usedList[i]--;
            }
        }
        return result;
    }

    /**
     * @return 사용하지 않은 number가 있으면 True, 전부 사용 False
     */
    private boolean unlessUsedNumber() {
        for (int i = 0; i < numberSize; i++) {
            if (usedList[i] == 0) return true;
        }
        return false;
    }

    /**
     * 두 문자열 중에 가장 큰 문자열을 내려다준다.
     *
     * @param answer 비교할 문자열
     */
    private void compareTwoStringRetunLarge(String answer) {
        if (answer == null) {
            // do nothing
        } else if (answer.length() > result.length()) {
            result = answer;
        } else if (answer.length() == result.length()) {
            for (int j = 0; j < answer.length(); j++) {
                int answerItem = answer.charAt(j);
                int resultItem = result.charAt(j);
                if (answerItem > resultItem) {
                    result = answer;
                } else if (answerItem < resultItem) {
                    return;
                }
            }
        }
    }


}