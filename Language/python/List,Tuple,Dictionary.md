# List

## list란?

- 순서가 있는 수정 가능한 객체의 집합
- 수정, 삭제, 추가가 가능함
- 선언은 []로 선언된다. 원소는 ,로 구분함

```python
list1 = []
list2 = [1,2,3]
print(type(list2))
-----------------------------
<class 'list'>
```



## 리스트 함수

### 데이터 추가

#### append

- 리스트의 끝에 데이터를 추가한다.

```python
list1.append(1)
list1.append(2)

print(list1)
-----------------------------
[1,2]
```

#### insert

```python
a = [1, 3, 5, 5, 7,2]
a.insert(1, 5)
print(a)
----------------------
[1, 5, 3, 5, 5, 7,2]
```

#### extend

```python
a = [1,2,3]
a.extend([4,5,6])
b = [4,5,6]
b = b + [1,2,3]

print(a)
print(b)
---------------------
[1,2,3,4,5,6]
[4,5,6,1,2,3]
```

### 



### 인덱싱

- 리스트의 위치에 있는 값을 꺼내온다

```python
print(list1[0])
print(list1[1])
----------------------------
1
2
```



```python
# 역순 인덱스
print(list1[-1])
print(list1[-2])
----------------------------
2
1
```



```python
listing = [1,2,3,4,5,6,7,8,9,10]

# listing 안에서 5의 위치 찾기
print(listing.index(5))
# listing 2이상 9미만의 범위 사이에서 5의 위치 찾기
print(listing.index(5,2,8))

# 없으면 에러난다. 꼭 in으로 안에 있는지 확인한다.
-----------------------
4
4
```



```python
a= '123445'
print(a.index(2))
----------------------
1
```



### 슬라이싱

```python
a = [1,2,3,4,5,6,7,8,9]

print(a[2:4])
print(a[2:-1])
print(a[2:])
print(a[:4])
--------------------
[3,4]
[3,4,5,6,7,8]
[3,4,5,6,7,8,9]
[1,2,3,4]
```



```python
print("같은 리스트") if a[2:] + a[:2] == a) else print("다른 리스트") 
--------------------------
같은 리스트
```

#### 얕은 복사

```python
# 얕은 복사
newlist = a[:]
# 얕은 복사
newlist2 = a.copy()
newlist3 = list(a)
```

2씩 띄워서 복사

```python
anotherlist = a[::2]
------------------------
[1,3,5,7,9]
```

역순으로 복사

```python
reverselist = a[::-1]
reverselist2 = a.reverse()
```



#### 깊은 복사

```python
import copy

b = copy.deepcopy(a)
```



### count

```python
a = [1,2,3,1,1,2,3,5]

print(a.count(1))
-----------------------
3
```



### 원소 삭제

```python
a = [1,2,3,1,1,2,3,5]
del a[1]

print(a)
--------------------
[1,3,1,1,2,3,5]
```



```python
a = [1,2,3,1,1,2,3,5]
a.remove(3)

print(a)
--------------------
[1,2,1,1,2,3,5]
```

둘 다 없으면 error 발생



# tuple

## tuple이란?

- 불변한 순서가 있는 리스트
- 한번 정해지면 못 바꿈
- 요일 같은거 쓸 때 좋다

## tuple 함수

- **대부분의 함수는 리스트와 거의 비슷하다.**

- 중요한 함수 몇가지

  - 다중 리턴

  ```python
  def adding(a,b) :
  	result = a+b
  	return result, a, b
  
  result, a, b= adding(a,b)
  ```

  이런식으로 여러개를 한꺼번에 받을 수 있다.

- 튜플 할당 해제를 이용한 값 교환

  ```python
  a= 5
  b= 10
  
  b, a = a, b
  
  print(a,b)
  
  -----------
  10, 5
  ```

  

# Dictionary

- Key와 Value 한쌍으로 이루어진 순서가 없는 집합
- 순서가 없으니 당연히 index 못씀

