import sys

input = sys.stdin.readline

# 다른 사람의 풀이
if __name__ == "__main__":
    input_str:str = input().strip()     # 입력된 문자열
    alpha_cnt:list = [0] * 26           # 숫자를 카운트한 문자열
    input_str = input_str.upper()
    for item in input_str:
        alpha_cnt[ord(item) - ord('A')] += 1
    answer = '?' if alpha_cnt.count(max(alpha_cnt)) > 1 else chr(ord('A') + alpha_cnt.index(max(alpha_cnt)))
    print(answer)
