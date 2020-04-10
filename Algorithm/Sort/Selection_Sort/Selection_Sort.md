# 정렬

## 선택 정렬

n의 항목을 전부 한번씩 보면서 최소값을 만나면 정렬한다.

![img](http://4.bp.blogspot.com/-89uV3LBnRlA/VS10mBeOXjI/AAAAAAAAADE/hsc7LQYy6FE/s1600/Selection%2Bejemplo.gif)

```python
def selectionSort(arr) :
    arrLen = len(arr)
    for i in range(arrLen) :
        min = arr[i]
        for j in range(i,arrLen) :
            if arr[i] > arr[j] :
                temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
    return arr
```



## 시간 복잡도

O(n^2)



## 참고

http://seleccionenc.blogspot.com/