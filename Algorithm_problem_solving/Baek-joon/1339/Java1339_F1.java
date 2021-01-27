import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static int n;    // 입력될 문자의 길이
    public static String[] words;   // 단어의 갯수
    public static int maxSum = 0;   // 최대 합의 갯수
    public static HashSet<Character> alphaSet;  // 중복을 제거 하기 위한 set

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        alphaSet = new HashSet<>();
        n = Integer.parseInt(bf.readLine());
        words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = bf.readLine();
            for (int w = 0; w < words[i].length(); w++) {
                alphaSet.add(words[i].charAt(w));
            }
        }

        boolean[] visited = new boolean[10];    // 0~9까지의 수 중에 중복을 제거 하기 위한 방문 체크
        HashMap<Character, Integer> map = new HashMap<>();  // 알바벳을 빠르게 뽑기 위한 셋
        List<Character> alphaList = new ArrayList<>(alphaSet);  // 셋에 저장된 것을 번호를 매기기 위한 리스트

        makeMaxSum(visited, map, alphaList);

        System.out.println(maxSum);
    }


    /*
    dfs로 각각 알파벳에 수를 할당
     */
    private static void makeMaxSum(boolean[] visited, HashMap<Character, Integer> map, List<Character> alphaList) {
        if (map.size() == alphaSet.size()) {
            // 각각 알파벳 마다 수가 더 정해지면 입력
            getSumNumber(map);
        } else {
            char inputChar = alphaList.get(map.size());
            for (int i = 0; i < 10; i++) {
                if (visited[i])
                    continue;
                map.put(inputChar, i);
                visited[i] = true;
                makeMaxSum(visited, map, alphaList);
                visited[i] = false;
                map.remove(inputChar);
            }
        }
    }

    // 만들어진 수로 수로 변환
    private static void getSumNumber(HashMap<Character, Integer> map) {
        StringBuilder sb;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sb = new StringBuilder();
            for (int w = 0; w < words[i].length(); w++) {
                sb.append(map.get(words[i].charAt(w)));
            }
            sum += Integer.parseInt(sb.toString());
        }
        maxSum = Math.max(maxSum, sum);
    }
}