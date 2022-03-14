import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        String plainText;
        String key;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            plainText = bf.readLine();
            key = bf.readLine();

            System.out.println(chiperText(plainText, key));
        } catch (IOException error) {
            error.printStackTrace();
        }
    }


    /**
     * This method is that plaintext is encrypted with a key.
     *
     * @param plainText : text for encrypt
     * @param key       : key for encrypt
     * @return encrypted text
     */
    public static String chiperText(String plainText, String key) {
        StringBuilder answer = new StringBuilder();

        int crypt;
        for (int i = 0; i < plainText.length(); i++) {
            char chr = plainText.charAt(i);
            if ('a' > chr || 'z' < chr) {
                // not alphabet
                answer.append(chr);
            } else {
                crypt = chr - key.charAt(i % key.length());
                if (crypt <= 0) {
                    // included in alphabet ascii range
                    answer.append((char) ('z' + crypt));
                } else {
                    // out of alphabet ascii range
                    answer.append((char) ('a' + crypt - 1));
                }
            }
        }
        return answer.toString();
    }
}