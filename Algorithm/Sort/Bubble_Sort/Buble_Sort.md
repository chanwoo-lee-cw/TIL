# 정렬
## 버블 정렬
현재 인덱스와 그 다음 인덱스 값을 비교하여 현재 인덱스 값이 크다면 스왑한다.



### 코드

```python
def bubbleSort(arr) :
    arrLen = len(arr)
    for i in range(arrLen - 1) :
        for j in range(arrLen -i -1) :
            if(arr[j]> arr[j+1]) :
                temp = arr[j]
                arr[j] = arr[j+1]
                arr[j+1] = temp
    return arr
```



### 시간 복잡도

O(n^2) : 가장 느린 형태의 정렬

무슨 정렬이던지 버블 소트의 형식이 되는 것이 worst case이다.