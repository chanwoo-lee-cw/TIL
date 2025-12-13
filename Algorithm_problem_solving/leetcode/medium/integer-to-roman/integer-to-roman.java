import java.util.HashMap;
import java.util.Map;

class Solution {

    private Map<Integer, String> romanNumber;

    Solution() {
        romanNumber = new HashMap<>();
        romanNumber.put(1000, "M");
        romanNumber.put(500, "D");
        romanNumber.put(100, "C");
        romanNumber.put(10, "X");
        romanNumber.put(50, "L");
        romanNumber.put(5, "V");
        romanNumber.put(1, "I");
    }

    public String intToRoman(int num) {
        // 1 : I
        // 2 : II
        // 3 : III
        // 4 : IV
        // 5 : V
        // 6 : VI
        // 7 : VII
        // 8 : VIII
        // 9 : IX
        // 10 : X

        StringBuilder answer = new StringBuilder();
        int pivot = 1;
        while (num > 0) {
            StringBuilder parts = new StringBuilder();
            int currNum = num % 10;
            if (currNum <= 3) {
                for (int i = 0; i < currNum; i++) {
                    parts.append(romanNumber.get(pivot));
                }
            } else if (currNum == 4) {
                parts.append(romanNumber.get(pivot));
                parts.append(romanNumber.get(pivot * 5));
            } else if (currNum <= 8) {
                parts.append(romanNumber.get(pivot * 5));
                for (int i = 0; i < currNum - 5; i++) {
                    parts.append(romanNumber.get(pivot));
                }
            } else {
                parts.append(romanNumber.get(pivot));
                parts.append(romanNumber.get(pivot * 10));
            }
            pivot = pivot * 10;
            num = num / 10;
            answer.insert(0, parts.toString());
        }

        return answer.toString();
    }
}