import sys

input = sys.stdin.readline


def fillPalindrome(palindrome, sentence):
    palindrome[0][0] = True
    for i in range(1, len(sentence)) :
        palindrome[i][i] = True
        if(sentence[i] == sentence[i-1]) :
            palindrome[i-1][i] = True
        for j in range(i-1):
            if(sentence[j] == sentence[i] and palindrome[j+1][i-1]):
                palindrome[i][j] = True

def minPalindromeSplit(palindrome, sentence):
    dp = [float('inf')] * len(sentence)
    dp[0] = 1
    for i in range(len(sentence)) :
        for j in range(i+1) :
            if(palindrome[j][i]):
                dp[i] = min(dp[i], dp[j-1]+1)
    return dp[len(sentence)-1]

if __name__ == "__main__":
    sentence = input()
    palindrome = [[False] * len(sentence) for _ in range(len(sentence))]
    fillPalindrome(palindrome, sentence)
    print(minPalindromeSplit(palindrome, sentence))