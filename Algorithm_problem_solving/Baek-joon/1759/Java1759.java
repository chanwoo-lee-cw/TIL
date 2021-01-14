import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1759
public class Main {

    public static int l;
    public static int c;

    // 현재 문자열에 들어가 있는 자음과 모음의 갯수
    public static int consonant;
    public static int vowel;

    // 자음이 포함 되 있는지 확인하기 위한 해시 셋
    public static HashSet<Character> vowelList = new HashSet<>();

    // dfs 방식으로 문자열 탐색,
    public static void dfs(ArrayList<Character> output, char[] words, int pos) {
        // 탈출
        if (output.size() == l) {
            if (consonant >= 2 && vowel >= 1) {
                StringBuilder st = new StringBuilder();
                for (char item : output) {
                    st.append(item);
                }
                System.out.println(st.toString());
            }
        } else {
            char currword;
            for (int i = pos; i < c; i++) {
                currword = words[i];
                output.add(currword);
                if (vowelList.contains(currword))
                    vowel++;
                else
                    consonant++;
                dfs(output, words, i + 1);
                if (vowelList.contains(currword))
                    vowel--;
                else
                    consonant--;
                output.remove(output.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (char item : new char[]{'a', 'e', 'i', 'o', 'u'})
            vowelList.add(item);

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        consonant = 0;
        vowel = 0;
        char[] words = new char[c];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < c; i++) {
            words[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(words);
        bf.close();
        
        dfs(new ArrayList<>(), words, 0);
    }

}