from sys import stdin

# 연산자 우선 순위를 반환
def prec(operator):
    if operator == '(' or operator == ')':
        return 0
    elif operator == '+' or operator == '-':
        return 1
    elif operator == '*' or operator == '/':
        return 2
    return -1

# 중위표기식을 후위 표기식으로 바꿔주는 함수
def infix_to_postfix(infix):
    postfix = []
    stack = []
    
    for element in infix:
        # 연산자인 경우
        if element == '+' or element == '-' or element == '*' or element == '/':
            # 스택에서 현재 들고 있는 연산자보다 우선순위가 큰 것을 모두 뽑아내서 후위표기식에 붙힌다.
            while(stack and prec(stack[len(stack)-1]) >= prec(element)):
                postfix.append(stack.pop())
            # 현재 들고 있는 것을 스택에 집어 넣는다.
            stack.append(element)
        # 우선순위가 가장 낮으므로 스택에 집어 넣는다.
        elif element == '(':
            stack.append(element)
        # 괄호가 닫힐때까지 스택에 들어가 있는 연산자를 모두 꺼낸다.
        elif element == ')':
            stkTop = stack.pop()
            while (stkTop != '('):
                postfix.append(stkTop)
                stkTop = stack.pop()
        # 피연산자인 경우
        else:
            postfix.append(element)
    # 중이 표기식이 끝난 경우 스택에 들어가 있는 모든 연산자를 꺼낸다.
    while stack:
        postfix.append(stack.pop())
    # 만들어진 리스트를 스트링으로 변환하여 출력한다.
    return ''.join(postfix)


if __name__ == "__main__":
    input = stdin.readline
    infix = input().strip()

    print(infix_to_postfix(infix))
