import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;    // 입력될 문자의 길이
        String[] words;   // 단어의 갯수
        HashMap<Character, Integer> alphaNum;   // 단어를 저장할 해시 맵

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        alphaNum = new HashMap<>();
        n = Integer.parseInt(bf.readLine());
        words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = bf.readLine();
            int sqrt = 1;
            for (int w = words[i].length() - 1; w >= 0; w--) {
                if (alphaNum.containsKey(words[i].charAt(w))) {
                    alphaNum.put(words[i].charAt(w), alphaNum.get(words[i].charAt(w)) + sqrt);
                } else {
                    alphaNum.put(words[i].charAt(w), sqrt);
                }
                sqrt *= 10;
            }
        }
        System.out.println(getMaxSum(alphaNum));
    }

    /*
    해시 맵에 저장되어 있는 값을 배열로 바꾼 다음에 가장 큰 수부터 9 부터 곱한다.
     */
    private static int getMaxSum(HashMap<Character, Integer> alphaNum) {
        int maxSum = 0;   // 최대 합의 갯수

        List<Integer> alhaList = new ArrayList<>(alphaNum.values());
        Collections.sort(alhaList, Collections.reverseOrder());
        {
            int multi = 9;
            for (int item : alhaList) {
                maxSum += (multi--) * item;
            }
        }
        return maxSum;
    }
}