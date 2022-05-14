# 정렬
## 버블 정렬
현재 인덱스와 그 다음 인덱스 값을 비교하여 현재 인덱스 값이 크다면 스왑한다.



### 코드

```python
def buble_sort(arr):
    arr_len = len(arr)
    for i in range(arr_len - 1):
        for j in range((arr_len - 1) - i):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
    return arr
```



### 시간 복잡도

O(n^2) : 가장 느린 형태의 정렬

무슨 정렬이던지 버블 소트의 형식이 되는 것이 worst case이다.