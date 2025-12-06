class Solution {
    public String longestPalindrome(String s) {
        int sLength = s.length();
        String maxPalindrome = "";

        for (int i = 0; i < sLength; i++) {
            String answer;
            answer = getMaxPalindrome(s, i, i + 1);
            if (answer.length() > maxPalindrome.length()) {
                maxPalindrome = answer;
            }
            answer = getMaxPalindrome(s, i, i + 2);
            if (answer.length() > maxPalindrome.length()) {
                maxPalindrome = answer;
            }
        }
        return maxPalindrome;
    }

    private String getMaxPalindrome(
            String s,
            int left,
            int right
    ) {
        String maxPalindrome = "";
        while (left >= 0 && right <= s.length()) {
            StringBuilder sb = new StringBuilder(s.substring(left, right));
            String checkString = sb.toString();
            String reversedString = sb.reverse().toString();
            if (!checkString.equals(reversedString)) break;
            left--;
            right++;
            maxPalindrome = checkString;
        }
        return maxPalindrome;
    }
}