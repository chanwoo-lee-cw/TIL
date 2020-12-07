# PriorityQueue

## 우선 순위 큐란

데이터를 선입 선출로 추가 및 제거하는 일반적인 큐의 자료 구조와는 다르게 데이터의 추가는 어느 순서로 하던 상관이 없지만, 제거 할때는 가장 작은 값부터 제거하는 자료구조

이는 파이선에서는 heapq라는 모듈에 구현되어 있다.





### 1. 클래스 임포트

우선 `PriorityQueue` 클래스는 `queue` 내장 모듈에서 제공되기 때문에 파이썬만 설치되어 있으면 별다른 추가 설치없이 임포트할 수 있다.

```python
# 방법 1
from queue import PriorityQueue

#방법 2
import heapq
```



### 2. 우선순위 큐 생성

`PriorityQueue()` 생성자를 이용해서 비어있는 우선순위 큐를 초기화하거나 그냥 리스트를 선언한 다음에 우선 순위 큐 처럼 사용할 수 있다.

```python
# 방법1
que1 = PriorityQueue()				# 크기의 제한이 없는 우선순위 큐
que2 = PriorityQueue(maxsize=MAX)	# MAX의 값에 따라 우선 순위 큐의 크기의 제한이 정해진다.

# 방법2
que3 = []

#초기화 - 자동으로 힙으로 만들어준다.
que4 = [7,6,2,5,2]
heapq.heapfy(queq4)

```



### 3. 우선순위 큐 원소 추가

`PriorityQueue()`의 매서드인 put()을 이용해서 원소를 추가할 수 있다

```python
# 방법1
que1.put(N)						# N 이라는 값을 추가한다.
que2.put([N,M])	

# 방법2
heapq.heappush(que3, N)			# N이라는 값을 추가한다.
heapq.heappush(que3, [N,M])		# N,M배열을 추가한다. 이 경우에는 우선순위가 N, M은 키가 된다.
```



### 4. 우선순위 큐 원소 삭제

`PriorityQueue()`의 매서드인 get()을 이용해서 원소를 삭제할 수 있다

``` python
# 방법1
print(que.get())

# 방법2
print(heapq.heappop(que3))
```





### 마지막 우선 순위 큐의 속성

만약 키의 값이 똑같은 것이 우선순위 큐에 삽입이 된다면, 그 큐 내부에서의 정렬은 value의 기준으로 정렬이 된다.