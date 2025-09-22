import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int t = Integer.parseInt(bufferedReader.readLine());


        for (int i = 0; i < t; i++) {
            String checkVps = bufferedReader.readLine();
            answer.append(isVps(checkVps)).append("\n");
        }

        System.out.println(answer);
    }


    private static String isVps(String strings) {
        String NO = "NO";
        String YES = "YES";
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < strings.length(); i++) {
            char currChar = strings.charAt(i);
            if (currChar == '(') {
                stack.push(currChar);
            } else if (currChar == ')') {
                if (stack.isEmpty()) {
                    return NO;
                } else {
                    stack.removeLast();
                }
            }
        }

        if (!stack.isEmpty()) {
            return NO;
        } else {
            return YES;
        }
    }
}
