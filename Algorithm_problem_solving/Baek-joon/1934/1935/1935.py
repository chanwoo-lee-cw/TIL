# https://www.acmicpc.net/problem/1935
import sys

alphaDic = {}

# 후위 연산자를 계산하는 코드
def eval(postfix):
    len_fix = len(postfix)
    stack = []
    # 후위 연산자가 끝날때까지 뽑는다.
    for element in postfix:
        if(element != '+' and element != '-' and element != '*' and element != '/'):
            # elemnt가 정수일때 계산, 단 [0-9]의 정수만 됨.
            # value = ord(element) - ord('0')
            # 딕셔너리 형태일때
            value = alphaDic.get(element)
            # value를 큐에 집어 넣는다.
            stack.append(value)
        else:
            # 스택 최상단의 숫자 2개를 뽑아 계산한 후에 스택에 다시 집어 넣는다.
            op2 = stack.pop()
            op1 = stack.pop()
            if(element == '+'):
                stack.append(op1+op2)
            elif(element == '-'):
                stack.append(op1-op2)
            elif(element == '*'):
                stack.append(op1*op2)
            elif(element == '/'):
                stack.append(op1/op2)
    # 마지막에 남은 값 반환
    return stack[0]


if __name__ == "__main__":
    input = sys.stdin.readline

    n = int(input().strip())
    postfix = input().strip()

    # 알파벳 순서대로 딕셔너리에 집어 넣는다.
    alpha = 'A'
    for i in range(n):
        alphaDic[alpha] = int(input().strip())
        alpha = chr(ord(alpha) + 1)
    print('{0:.2f}'.format(eval(postfix)))
