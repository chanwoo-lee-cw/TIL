## 백준 10816 풀이

### 문제

*숫자 카드는 정수 하나가 적혀져 있는 카드이다. 상근이는 숫자 카드 N개를 가지고 있다. 정수 M개가 주어졌을 때, 이 수가 적혀있는 숫자 카드를 상근이가 몇 개 가지고 있는지 구하는 프로그램을 작성하시오.*



### 입력

*첫째 줄에 상근이가 가지고 있는 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)이가 주어진다. 둘째 줄에는 숫자 카드에 적혀있는 정수가 주어진다. 숫자 카드에 적혀있는 수는 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.*

*셋째 줄에는 M(1 ≤ M ≤ 500,000)이 주어진다. 넷째 줄에는 상근이가 몇 개 가지고 있는 숫자 카드인지 구해야 할 M개의 정수가 주어지며, 이 수는 공백으로 구분되어져 있다. 이수도 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.*



### 출력

*첫째 줄에 입력으로 주어진 M개의 수에 대해서, 각 수가 적힌 숫자 카드를 상근이가 몇 개 가지고 있는지를 공백으로 구분해 출력한다.*



### 풀이 전개 과정

문제를 본 이후에 생각한 풀이는 수들을 정렬 후 이분 탐색으로 for 문으로 같은 것들을 찾으면 될 것이라고 생각함.



그래서 퀵 정렬을 구현

```java
public void quickSort(int[] arr, int start, int end) {
	int left, right, pivot, temp;
	if (start < end) {
		left = start;
		right = end - 1;
		pivot = end;
		while (left < right) {
			while (arr[left] < arr[pivot])
				left++;
			while (arr[right] > pivot && left < right)
				right++;
			temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
		}
		quickSort(arr, start, left - 1);
		quickSort(arr, left + 1, end - 1);
	}
}
```



하지만, 퀵 정렬를 해본 결과, 퀵 정렬은 중복되는 수가 있을 경우 제대로 작동하지 않는다는 것을 알 수 있었다.

그래서, 다음으로 병합 정렬을 구현해서 테스트 해보았다.

``` java
void merge(int left, int right)
{
	int mid = (left + right) / 2;

	int i = left;
	int j = mid + 1;
	int k = left;
	while (i <= mid && j <= right)
	{
		if (arr[i] <= arr[j]) 
			arr2[k++] = arr[i++]; 
		else
			arr2[k++] = arr[j++];
	}

	int tmp = i>mid ? j : i;
	
	while(k<=right) arr2[k++] = arr[tmp++];

	for (int i=left;i<=right;i++) arr[i] = arr2[i];
}

void partition(int left,int right)
{
	int mid;
	if (left < right)
	{
		mid = (left + right) / 2; 
		partition(left, mid);
		partition(mid + 1, right);
		merge(left, right);
	}
}
```

하지만, 여전히 시간 초과가 났다.



그래서 더 효율적인 sort를 찾아보는 와중에 자바에서 기본으로 제공 되는 Array의 sort가 Dual Pivot Quick Sort라는 것을 보고 이것을 사용해서 해본 결과 또 시간 초과가 났다.

```java
Arrays.sort(arr);
```



그럼 이제 정렬이 문제가 아니라, 다른 부분이 문제라는 것을 알 수가 있었고,

``` java
while(true) {
    if(arr[left]!=find && arr[right] != find) 
        break;
    else {
        if(arr[left]==find) {
            if(left>0) {
            	count++;
            	left--;
        	}
        }
        if(arr[right]==find) {
            if(right < arr.length - 1)
                count++;
            	right++;
        }
    }
}
```

찬찬히 체크해본 결과 수를 세는 부분, 최악의 경우에는 O(n)만큼 시간이  소요되는 장소를 줄여야 되겠다고 생각했다.



그래서 이 부분은 찾아본 결과 upper_bound - lower_bound로 중복되는 수의 갯수를 구하면 된다는 것을 알았다.

#### lower_bound :

- 이진 탐색의 방법 - 찾으려 하는 키 값과 일치하는 배열의 첫 위치를 찾는다.  없으면 key값보다 큰 가장 작은 정수 값을 찾는다. **즉, 찾고자 하는 값을 초과하는 값이 처음으로 나타나는 위치**

#### upper_bound :

- 이진 탐색의 방법 - 찾으려 하는 키 값과 일치하는 배열의 마지막 위치를 찾는다. 없으면 key값보다 작은 가장 큰 정수 값을 찾는다. **즉, 찾고자 하는 값 이상이 처음 나타나는 위치**

이 것으로 처음 알게된 upper_bound와 lower_bound에 대한 설명은 Algorithm\Binary Search에 정리해두었다.

```java
private static int upper_bound(int[] arr, int s, int e, int check) {
	int m;
	while (e - s > 0) {
		m = (s + e) / 2;
		if (arr[m] <= check)
			s = m + 1;
		else
			e = m;
	}
	return e + 1;
}

private static int lower_bound(int[] arr, int s, int e, int check) {

	int m;
	while (e - s > 0) {
		m = (s + e) / 2;
		if (arr[m] < check)
			s = m + 1;
		else
			e = m;
	}
	return e + 1;
}
```


### 시사점

1. Quicksort는 중복 된 수가 있을 경우 제대로 작동하지 않는다.

   - Quick sort : 평균 시간 복잡도 - O(NlogN), 최악은 O(N^2)
   - Merge sort : 시간 복잡도 - nlog₂n(비교) + 2nlog₂n(이동)  = O(nlog₂n)
   - 자바 기본으로 제공되는 sort는 Dual Pivot Quick Sort, 시간 복잡도는 Quick sort이니 똑같다.

2. lower_bound :

   - 이진 탐색의 방법 - 찾으려 하는 키 값과 일치하는 배열의 첫 위치를 찾는다.  없으면 key값보다 큰 가장 작은 정수 값을 찾는다. **즉, 찾고자 하는 값을 초과하는 값이 처음으로 나타나는 위치**

3. upper_bound :

   - 이진 탐색의 방법 - 찾으려 하는 키 값과 일치하는 배열의 마지막 위치를 찾는다. 없으면 key값보다 작은 가장 큰 정수 값을 찾는다. **즉, 찾고자 하는 값 이상이 처음 나타나는 위치**