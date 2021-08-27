import sys
import heapq

input = sys.stdin.readline

if __name__ == "__main__":
    input_str:str = input().strip()     # 입력된 문자열
    alpha_cnt:list = [0] * 26           # 숫자를 카운트한 문자열
    input_str = input_str.upper()
    for item in input_str:
        alpha_cnt[ord(item) - ord('A')] += 1
    que = []
    for i in range(26):
        heapq.heappush(que, (-alpha_cnt[i], i))

    answer = heapq.heappop(que)
    if answer[0] == que[0][0]:
        print("?")
    else:
        print(chr(ord('A') + answer[1]))
