import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        ArrayList<Character> alphaList = new ArrayList<>();

        n = in.nextInt();
        for (int i = 0; i < n; i++) {
            alphaList.add(in.next().charAt(0));
        }
        SentenceList alphaListList = new SentenceList();
        System.out.println(alphaListList.getSentence(n, alphaList));
    }
}

class SentenceList {

    /**
     * Returns the fastest string of string T's made of the two rules below in alphabetical order
     *
     * 1. Add the first character of the string S to the end of the string T.
     * 2. Add the last character of the string S to the end of the string T.
     *
     * @param n         Size of string
     * @param alphaList input string
     * @return the fastest words in alphabetical order
     */
    public String getSentence(int n, ArrayList<Character> alphaList) {
        StringBuilder answer = new StringBuilder();
        StringBuilder sentence = new StringBuilder();
        while (!alphaList.isEmpty()) {
            if (alphaList.get(0).compareTo(alphaList.get(alphaList.size() - 1)) < 0) {
                sentence.append(alphaList.get(0));
                alphaList.remove(0);
            } else if (alphaList.get(0).compareTo(alphaList.get(alphaList.size() - 1)) > 0) {
                sentence.append(alphaList.get(alphaList.size() - 1));
                alphaList.remove(alphaList.size() - 1);
            } else {
                int start = 0;
                int end = alphaList.size() - 1;
                boolean check = true;
                while (start < end) {
                    end -= 1;
                    start += start < alphaList.size() - 2 ? 1 : 0;
                    if (alphaList.get(start).compareTo(alphaList.get(end)) < 0) {
                        check = true;
                        break;
                    } else if (alphaList.get(start).compareTo(alphaList.get(end)) > 0) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    sentence.append(alphaList.get(0));
                    alphaList.remove(0);
                } else {
                    sentence.append(alphaList.get(alphaList.size() - 1));
                    alphaList.remove(alphaList.size() - 1);
                }
            }

            if (sentence.length() == 80) {
                answer.append(sentence);
                answer.append("\n");
                sentence = new StringBuilder();
            }
        }
        answer.append(sentence);
        return answer.toString();
    }
}