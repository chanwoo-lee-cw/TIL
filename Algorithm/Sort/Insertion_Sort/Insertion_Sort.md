# 정렬

## 삽입 정렬

### 이론

해당 인덱스를 기준으로 자신보다 작은 값이 있는 인덱스가 있다면 메모한다.

마지막에 현재 시작 위치와 갱신된 인덱스 값을 서로 스왑한다.

![img](https://upload.wikimedia.org/wikipedia/commons/9/94/Selection-Sort-Animation.gif)

### 구현상 이론

1. 현재의 인덱스의 값을 키에 저장한다.
2.  현재 인덱스보다 앞에 있는 인덱스들을 역순으로 순차적으로 검사해가면서 만약 키보다 값이 작다면 스왑한다.
3. 만약 현재 키보다 크다면 반복문 해제

4. 다음 키 값을 정하고 반복

## 코드

```python
def insertionSort(arr) :
    arrLen = len(arr)
    for i in range(1,arrLen) :
        key = arr[i]
        for j in range(i-1, -1, -1) :
            if(arr[j] > key) :
                arr[j + 1] = arr[j]
                arr[j] = key
            else :
                break
    return arr
```

