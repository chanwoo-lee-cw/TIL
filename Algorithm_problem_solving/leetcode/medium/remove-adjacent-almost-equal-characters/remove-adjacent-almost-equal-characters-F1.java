class Solution {

    public int removeAlmostEqualCharacters(String word) {
        if(word.length() <= 1) {
            return 0;
        }
        int answer = 0;
        if( word.length() == 2) {
            char beforeTwo = word.charAt(0);
            char beforeOne = word.charAt(1);
            if(Math.abs(beforeTwo - beforeOne) <= 1) {
                answer++;
            }
        }
        int i = 2;
        while (i < word.length()) {
            char beforeTwo = word.charAt(i - 2);
            char beforeOne = word.charAt(i - 1);
            char curr = word.charAt(i);
            if (
                    Math.abs(beforeTwo - beforeOne) <= 1 || Math.abs(beforeOne - curr) <= 1
            ) {
                answer++;
                i += 2;
            } else {
                i++;
            }
        }
        return answer;
    }
}