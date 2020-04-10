# 정렬

## 병합 정렬

분할 정복을 이용한 정렬 방법

들어온 배열을 중간을 기준으로 재귀로 반으로 나눠준다.

재귀로 더이상 나눠지지 않을때까지 나눈다음에, 마지막까지 도달하면 그냥 단일 배열 그대로 리턴

리턴된 왼쪽 배열과 오른쪽 배열을 새로운 배열로 선언하고 큐 형식으로 앞쪽부터 더 작은 수로 하나씩 채워 나간다.

만들어진 배열을 리턴



### 장점

데이터의 상태에 별 영향을 받지 않는다

 -> 굉장히 좋은 장점.

### 단점

속도가 퀵 소트 보단 느리다.

메모리를 많이 먹는다.



## 코드

```python
def merge(left, right):
    result = []
    while len(left) > 0 or len(right) > 0:
        if len(left) > 0 and len(right) > 0:
            if left[0] <= right[0]:
                result.append(left[0])
                left = left[1:]
            else:
                result.append(right[0])
                right = right[1:]
        elif len(left) > 0:
            result.append(left[0])
            left = left[1:]
        elif len(right) > 0:
            result.append(right[0])
            right = right[1:]
    return result

def mergeSort(arr) :
    arrLen = len(arr)
    if arrLen <= 1 :
        return arr
    mid = arrLen//2
    leftSorted = mergeSort(arr[:mid])
    rightSorted = mergeSort(arr[mid:])

    result = merge(leftSorted, rightSorted)
    return result
```

