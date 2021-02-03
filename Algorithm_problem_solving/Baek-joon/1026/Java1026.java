import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int n;    // 단어의 갯수
    public static int k;    // 선택할 수 있는 알파벳의 갯수
    public static HashSet<Character> allAlphaSet;   // 사용된 모든 알파벳 set
    public static HashSet<Character>[] wordsSet;    // 각 단어마다 알파벳 set

    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            wordsSet = new HashSet[n];

            {
                String tempWord;
                HashSet<Character> alphaSet;
                char tempAlpha;
                for (int i = 0; i < n; i++) {
                    alphaSet = new HashSet<>();
                    tempWord = bf.readLine();
                    for (int w = 4; w < tempWord.length() - 4; w++) {
                        tempAlpha = tempWord.charAt(w);
                        switch (tempAlpha) {
                            // a,n,t,i,c는 제외
                            case 'a':
                            case 'n':
                            case 't':
                            case 'i':
                            case 'c':
                                break;
                            default:
                                alphaSet.add(tempAlpha);
                                break;
                        }
                    }
                    wordsSet[i] = alphaSet;
                }
            }

            st = null;
            bf.close();

            allAlphaSet = new HashSet<>();
            for (int i = 0; i < n; i++) {
                allAlphaSet.addAll(wordsSet[i]);
            }

            if (k < 5) {
                // actic 5개중 하나라도 못 가리키면 0 반환
                System.out.println(0);
                return;
            }

            ArrayList<Character> allAlphaList = new ArrayList<>(allAlphaSet);   //get을 쉽게 하기 위해 리스트로 변경
            HashSet<Character> leardAlpha = new HashSet<>();    // 가르칠 수 있는 알파벳 셋

            System.out.println(getMaxRead(k - 5, allAlphaList, leardAlpha, 0)); // a,n,t,i,c는 필수 이므로 5를 빼고 계상
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    백 트래킹, ancic를 제외한 알파벳의 갯수를 센다.
    매개변수 - 가르칠 수 있는 알파벳 갯수, 전체 알파벳 셋, 현재 가르친 알파벳 셋, 조합을 위한 인덱스
     */
    private static int getMaxRead(int k, ArrayList<Character> allAlphaList, HashSet<Character> leardAlpha, int index) {
        int maxRead = 0;
        if (k >= allAlphaSet.size())
            // 만약 전부 배울 수 있으면 계산 안하고 리턴
            maxRead = n;
        else if (k == leardAlpha.size()) {
            boolean flag;
            for (int w = 0; w < n; w++) {
                flag = true;
                for (char item : wordsSet[w]) {
                    if (!leardAlpha.contains(item)) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    maxRead++;
            }
        } else {
            for (int i = index; i < allAlphaList.size(); i++) {
                leardAlpha.add(allAlphaList.get(i));
                maxRead = Math.max(maxRead, getMaxRead(k, allAlphaList, leardAlpha, i + 1));
                leardAlpha.remove(allAlphaList.get(i));
            }
        }

        return maxRead;
    }
}