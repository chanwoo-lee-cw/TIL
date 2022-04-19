import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        ArrayList<Character> sentence = new ArrayList<>();

        n = in.nextInt();
        for (int i = 0; i < n; i++) {
            sentence.add(in.next().charAt(0));
        }
        SentenceList sentenceList = new SentenceList();
        System.out.println(sentenceList.getSentence(n, sentence));
    }
}

class SentenceList {

    public String getSentence(int n, ArrayList<Character> sentence) {
        StringBuilder answer = new StringBuilder();
        while (!sentence.isEmpty()) {
            if (sentence.get(0).compareTo(sentence.get(sentence.size() - 1)) < 0) {
                answer.append(sentence.get(0));
                sentence.remove(0);
            } else if (sentence.get(0).compareTo(sentence.get(sentence.size() - 1)) > 0) {
                answer.append(sentence.get(sentence.size() - 1));
                sentence.remove(sentence.size() - 1);
            } else {
                int start = 0;
                int end = sentence.size() - 1;
                boolean check = true;
                while (start < end) {
                    end -= 1;
                    start += start < sentence.size() - 2 ? 1 : 0;
                    if (sentence.get(start).compareTo(sentence.get(end)) < 0) {
                        check = true;
                        break;
                    } else if (sentence.get(start).compareTo(sentence.get(end)) > 0) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    answer.append(sentence.get(0));
                    sentence.remove(0);
                } else {
                    answer.append(sentence.get(sentence.size() - 1));
                    sentence.remove(sentence.size() - 1);
                }
            }
        }
        return answer.toString();
    }
}