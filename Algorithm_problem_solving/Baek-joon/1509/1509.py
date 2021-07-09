import sys

input = sys.stdin.readline

"""
palindrome[i][j] : 주어진 문장의 i~j까지가 팰린드롬이라면 True를 리턴
"""
def fillPalindrome(palindrome, sentence):
    palindrome[0][0] = True
    for i in range(1, len(sentence)):
        palindrome[i][i] = True
        if(sentence[i] == sentence[i-1]):
            palindrome[i-1][i] = True
        for j in range(i-1):
            if(sentence[j] == sentence[i] and palindrome[j+1][i-1]):
                palindrome[i][j] = True

"""
문자열을 i~j까지 잘랐을때 가장 적은 수의 팰린드롬으로 자르는 경우의 수 리턴
"""
def minPalindromeSplit(palindrome, sentence):
    dp = [float('inf')] * len(sentence)
    dp[0] = 1
    for i in range(len(sentence)):
        for j in range(i+1):
            if(palindrome[j][i]):
                dp[i] = min(dp[i], dp[j-1]+1)
    return dp[len(sentence)-1]


if __name__ == "__main__":
    sentence = input()  # 원하는 문장
    palindrome = [[False] * len(sentence) for _ in range(len(sentence))]    # palindrome[i][j] : 문장의 i~j가 팰린드롬이라면 True
    fillPalindrome(palindrome, sentence)
    print(minPalindromeSplit(palindrome, sentence))
