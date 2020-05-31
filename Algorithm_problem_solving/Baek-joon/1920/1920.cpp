// https://www.acmicpc.net/problem/18258
#include <iostream>
#include<vector>
#include<algorithm>

using namespace std;

#define HEAPMAXSIZE	100001

// MaxHeap
template<typename T> class Heap
{
private:
	T	*arr;			// heap array
	int	maxsize;		// heap max size
	int	len;			// heap current size
public:
	// heap init
	Heap()
	{
		maxsize = HEAPMAXSIZE;
		len = 0;
		arr = new T[HEAPMAXSIZE];
	}

	//heap delete
	~Heap()
	{
		len = 0;
		delete arr;
	}

	// item push
	void push(T item)
	{
		int i;
		i = ++len;			
		// put the value in the last position of heap

		// exchange it with prants to find a location for item to enter
		while ((i != 1) && (item > arr[i / 2]))
		{
			arr[i] = arr[i / 2];
			i /= 2;
		}

		// put item in the location you find it
		arr[i] = item;
	}

	// pop the maximum value in the current heap
	T pop()
	{
		int parent, child;		
		// position for exploration
		T item, temp;			
		// item : output value
		// temp : value kept to fill a location that has disappered from the heap

		item = arr[1];
		temp = arr[len--];
		parent = 1;
		child = 2;

		while (child <= len)
		{
			if ((child < len) && (arr[child] < arr[child + 1]))
			// whidh of the child is the lower value
				child++;
			if (temp >= arr[child])
			// if you find the place you want, break
				break;
			// exchange the two value of parent and child
			arr[parent] = arr[child];
			// reset each position
			parent = child;
			child *= 2;
		}
		// put the stored values in the location
		arr[parent] = temp;
		return item;
	}

	void printAll()
	{
		for (int i = 0; i < len; i++)
		{
			cout << arr[i + 1] << " ";
		}
		cout << "\n";
	}

	int getLength()
	{
		return len;
	}
};

// binary Search : search array, Start position, End position, Find Value
int binarySearch(vector<long> *arr, int s, int e, long search) {
	int mid = NULL;

	// Find util the start and end position are equal
	while (e - s > 0) {
		mid = (s + e) / 2;
		if (arr->at(mid) == search) 
		// return 1, if found value
		{
			return 1;
		}
		// if the arr[mid] value is not the value you are looking for, narrow it down and search agin
		else if (arr->at(mid) < search)
		{
			s = mid +1;
		}
		else
		{
			e = mid ;
		}
	}
	return 0;
}


int main()
{
	cin.tie(nullptr);
	ios::sync_with_stdio(false);

	int	N;
	int M;
	vector<long> A;

	long temp = NULL;
	cin >> N;
	
	Heap<long> heap;

	// insert all fo the values entered for alignments into the heap
	for (int i = 0; i < N; i++) 
	{
		cin >> temp;
		heap.push(-temp);
	}
	for (int i = 0; i < N; i++)
	{
		A.push_back(-heap.pop());
	}

	cin >> M;

	for (int i = 0; i < M; i++)
	{
		cin >> temp;
		cout << binarySearch(&A, 0, N, temp) <<"\n";
	}
	
	return 0;
}