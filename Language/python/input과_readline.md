# 입력

백준 [1717번 - 집합의 표현](https://www.acmicpc.net/problem/1717)을 푸는데 문제가 생겼었다.

아무리 코드를 수정해 보아도 시간 초과가 나는 문제가 생겼었기 때문이다.



아무리 풀어 봐도 답이 나오지 않길래 인터넷에 다른 언어로 짜여진 코드를 봤지만, 풀었던 문제와 비슷한 과정으로 진행 되는 코드였고 인터넷을 찾는 도중에 한 코드를 보았다.

```python
import sys
input = sys.stdin.readline
print = sys.stdout.write

def get_parent(x):
    while x != parent[x]:
        x = parent[x]
    return parent[x]

def union(x, y):
    x = get_parent(x)
    y = get_parent(y)
    if x == y: return
    if rank[x] < rank[y]: parent[x] = y
    else: parent[y] = x
    if rank[x] == rank[y]: rank[x] += 1

def is_same(x, y):
    return "YES\n" if get_parent(x) == get_parent(y) else "NO\n"

n, m = map(int, input().split())
parent = [i for i in range(n+1)]
rank = [0]*(n+1)
i = 0
while i < m:
    o, a, b = map(int, input().split())
    if o: print(is_same(a, b))
    else: union(a, b)
    i += 1
    
# 출처 : https://home-body.tistory.com/599
```



이런 코드였는데 거의 비슷한 과정이였지만, 눈에 띄는 부분이 하나 있었다.

```python
import sys

input = sys.stdin.readline
print = sys.stdout.write
```

즉, 입출력을 오버라이딩 하고 있었고, 혹시 라는 생각으로 나도 똑같이 입출력을 오버라이딩 하니 338MS로 여유롭게 정답이 나왔다.



그래서 어째서 이런 문제가 생겼는지 인터넷을 찾으며 정답을 찾아봤는데



python은 동적 변수를이 사용하기 때문에 input()은 입력 받은 값을 재가공하기 때문이라는 답을 얻었다.

예를 들면 

```python
x = input(1+2)
print("출력 : " + x)
# 출력 : 3
# 이런 식으로 파이썬은 입력을 받으면 입력 받은 것의 의미를 평가 다음에 자료형을 결정한다.
x = sys.stdin.readline(1+2)
print("출력 : " + x)
# 출력 : 1+2
# 하지만 readline 함수는 이런 과정을 모두 없애고 단순히 받은 문자열을 그대로 저장
```

input()은 raw_input()을 evaluate한 결과를 반환한다.
즉, prompt를 pass했는지 안했는지 evaluate를 해야는 오버레드가 계속해서 발생하기 때문에 느리다.



## input()과 readline()의 차이점

1. input()은 선택적으로 실행되는 interpreter가 있다면 보여주는 prompt parameter를 가지고 있다. 이것은 prompt가 비어 있는 경우에도 overhead를 초래한다.

2. input()은 개행 문자를 제거해준다. readline()은 strip()을 추가해줘야 한다.

3. input()은 더이상 입력을 하지 않을 때 EOFError를 증가시킨다.
   하지만 readline()은 EOF에서 빈 문자열을 반환한다.



즉, input은 입력은 받은 다음에  interpreter가 있는지 확인한다. 이것이 overhead를 유발하고, 개행 문자를 확인하고 있다면 제거한다. 하지만, readline 은 개행문자를 확인하려면 사용자가 직접 사용하는 수 밖에 없다.

그리고 혹시 모를 예외처리까지 한다. readline 은 예외 처리를 사용자가 직접 해줘야 한다.





### 참고



https://m.blog.naver.com/PostView.nhn?blogId=rndrnjs2003&logNo=221335512131&categoryNo=11&proxyReferer=https%3A%2F%2Fwww.google.com%2F



https://www.acmicpc.net/board/view/855







