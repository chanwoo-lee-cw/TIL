import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        try {
            while (true) {
                str = bf.readLine().strip();
                if (str.equals(".")) {
                    break;
                }
                System.out.println(getSquare(str));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the length of the smallest subString of the str
     *
     * @param str string for check
     * @return Length of the smallest subString
     */
    public static int getSquare(String str) {
        if (str.length() == 0) {
            return 0;
        }

        String subStr;
        for (int i = 1; i < str.length() + 1; i++) {
            if (str.length() % i != 0) {
                continue;
            }
            subStr = str.substring(0, i);
            if (checkSubStr(str, subStr)) {
                return str.length() / i;
            }
        }
        return 1;
    }

    /**
     * true if subStr is subString of str else false
     *
     * @param str    original str
     * @param subStr substring of str
     * @return true if subStr is subString of str else false
     */
    private static boolean checkSubStr(String str, String subStr) {
        int i = subStr.length();
        for (int j = i; j < str.length(); j += i) {
            if (!subStr.equals(str.substring(j, j + i))) {
                return false;
            }
        }
        return true;
    }
}