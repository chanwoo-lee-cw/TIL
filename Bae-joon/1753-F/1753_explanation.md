## 백준 1753번 풀이

https://www.acmicpc.net/problem/1753

### 문제

*초기에 {0}, {1}, {2}, ... {n} 이 각각 n+1개의 집합을 이루고 있다. 여기에 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행하려고 한다.*

*집합을 표현하는 프로그램을 작성하시오.*



### 입력

*첫째 줄에 n(1≤n≤1,000,000), m(1≤m≤100,000)이 주어진다. m은 입력으로 주어지는 연산의 개수이다. 다음 m개의 줄에는 각각의 연산이 주어진다. 합집합은 0 a b의 형태로 입력이 주어진다. 이는 a가 포함되어 있는 집합과, b가 포함되어 있는 집합을 합친다는 의미이다. 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산은 1 a b의 형태로 입력이 주어진다. 이는 a와 b가 같은 집합에 포함되어 있는지를 확인하는 연산이다. a와 b는 n 이하의 자연수또는 0이며 같을 수도 있다.*



### 출력

*1로 시작하는 입력에 대해서 한 줄에 하나씩 YES/NO로 결과를 출력한다. (yes/no 를 출력해도 된다)*

***



### 풀이

하나의 집합으로 묶여있는지 확인하는 문제,

하나의 집합으로 묶고, 묶여 있는지 확인 하는 방법으로는 유니온 파인드 기법을 사용했다.

```python
parent = [i for i in range(n + 1)]

for i in range(m) :
	line = input().split()
	for j in range(3) :
		line[j]= int(line[j])
	if(line[0]==0) :
		merge(line[1],line[2],parent)
	elif (line[0]==1):
		u = find(line[1],parent)
		v = find(line[2],parent)
		if(u==v) :
			print("yes\n")
		else :
			print("no\n")

def find(u,parent) :
	if (u==parent[u]) :
		return u;
	parent[u] = find(parent[u],parent);
	return parent[u]

def merge(u, v,parent) :
	u = find(u,parent)
	v = find(v,parent)
	if (u == v) :
		return
	parent[u] = v
```

0이 입력된다면 merge로 들어가서 같은 집합으로 묶고, 1이 입력된다면 입력된 것을 find로 들어가서 만약 루트노드가 같은 값이 반환 된 부모 노드의 값이 같다면 같은 노드로 묶여 있다고 판단했다.



하지만, 문제는 무난하게 풀었지만, 전혀 예상하지 못한 부분에서 막혔는데, 정답은 맞지만 너무 오래 시간이 걸린다는게 문제였다.



처음에는 당연히 시간 복잡도가 너무 크기 때문에 오래 걸리는 줄 알았다.

유니온 파인드 부분을 트리 트리 level을 전혀 고려하지 하지 않고 짰기 때문에 트리 높이가 너무 길어지기 때문인줄 알았다.



그래서 트리 레벨을 고려해서 트리 레벨이 더 짧은 쪽으로 추가하는 방향으로 코드를 추가했다.

```python
parent = [i for i in range(n + 1)]
level = [1 for i in range(n + 1)]

for i in range(m) :
	line = input().split()
	for j in range(3) :
		line[j]= int(line[j])
	if(line[0]==0) :
		merge(line[1],line[2],parent)
	elif (line[0]==1):
		u = find(line[1],parent)
		v = find(line[2],parent)
		if(u==v) :
			print("yes\n")
		else :
			print("no\n")

def find(u,parent) :
	if (u==parent[u]) :
		return u;
	parent[u] = find(parent[u],parent);
	return parent[u]

def merge(u, v,parent) :
	u = find(u,parent)
	v = find(v,parent)
	if (u == v) :
		return
    #높이를 파악해서 더 짧은 쪽으로 붙힌다
	if (level[u] > level[v]) :
		temp =u
		u=v
		v=temp
	parent[u] = v
	if (level[u] == level[v]) :
	# 트리 레벨을 하나 늘린다.
		level[v]+=1
```

하지만 여전히 시간 초과가 났다.

내가 알고 있는 방법으론 더 이상 유니온 파인드를 최적화 시킬 수 있는 방법이 없었고, 인터넷으로 다른 언어로 짜여진 코드를 보니 코드 차이가 별로 없었고,



이제는 파이선이 연산 시간이 느려서 그렇다길래 그런가 싶어서 채점 현황에서 파이썬으로 맞춘 사람이 없나 찾아봤는데 있었다.



파이썬이 느리다곤 해도 그렇게 느릴리도 없었고, 이제 다른 방식으로 찾아봐야 했었는데,



이제 다른 코드에서 답을 찾았는데,

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

이런 코드였다.

전체적인 방향성은 전부 비슷했지만, 유달리 달라 보이는 한 점을 찾았는데

```python
import sys
input = sys.stdin.readline
print = sys.stdout.write
```

입출력 부분을 readline으로 오버라이딩을 한 부분이였다.



그래서 혹시 몰라서 나도 한번 이것만 추가해서 채점해보니 시간 388MS로 무난히 통과했다.



그럼 이제 왜 그런지 찾아 보았는데

파이썬은 여타 언어와는 다르게 변수를 동적 변수로 사용하다 보니,

input으로 들어온 값을 evaluate해야 하는 과정이 있다.

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

즉, 입력 받은 내용을 판단하는 과정을 거치는데 입력이 엄청 늘어나다 보면 이 과정이 시간을 생각보다 굉장히 잡아 먹는다. 즉, 오버헤드가 발생하는 것이 문제이다.



하지만, readline은 다른데, 무조건 스트링형으로 받고 input에서 기본적으로 제공하는 EOF예외나 개행 문제 제거도 모두 사용자가 직접해야 되는 대신 빠르다.



참고로 위에 readline메소드로 오버라이딩 하고 유니온파인드 과정을 돌려본 결과 456MS로 무난히 통과했다.

즉, input으로 발생하는 오버헤드가 트리를 최적화 시키는 것보다 오래 걸린다는 의미였다.



### 전체 코드

```python
import sys

input = sys.stdin.readline
print = sys.stdout.write

def main() :
	n,m = input().split()

	n = int(n)
	m = int(m)

	parent = [i for i in range(n + 1)]
    level = [1 for i in range(n + 1)]

	for i in range(m) :
		line = input().split()
		for j in range(3) :
			line[j]= int(line[j])
		if(line[0]==0) :
			merge(line[1],line[2],level,parent)
		elif (line[0]==1):
			u = find(line[1],parent)
			v = find(line[2],parent)
			if(u==v) :
				print("yes\n")
			else :
				print("no\n")

def find(u,parent) :
	if (u==parent[u]) :
		return u;
	parent[u] = find(parent[u],parent);
	return parent[u];

def merge(u, v,level,parent) :
	u = find(u,parent)
	v = find(v,parent)
	if (u == v) :
		return
	if (level[u] > level[v]) :
		temp =u
		u=v
		v=temp
	parent[u] = v
	if (level[u] == level[v]) :
		level[v]+=1

main()
```



### 시사점

- input은 오버헤드가 심하다. 적은 데이터일 경우에는 여러가지 기능이 붙어있는 input이 훨씬 좋지만, 데이터가 많아질 수록 오버헤드가 심해진다.

