class Solution {

    public int removeAlmostEqualCharacters(String word) {
        if (word.length() <= 1) {
            return 0;
        }
        int answer = 0;
        int i = 2;
        while (i <= word.length()) {
            char beforeTwo = word.charAt(i - 2);
            char beforeOne = word.charAt(i - 1);
            if (Math.abs(beforeTwo - beforeOne) <= 1) {
                answer++;
                i += 2;
            } else {
                i++;
            }
        }
        return answer;
    }
}
