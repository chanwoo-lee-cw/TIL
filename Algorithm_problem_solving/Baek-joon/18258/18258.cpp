// https://www.acmicpc.net/problem/18258
#include <iostream>

#define QUESIZE 2000001;

using namespace std;

// 큐 클래스
template<typename T> class Queque
{
private:
	T* que;             // 큐를 저장할 리스트
	int item;           // 큐의 들어가 있는 item의 갯수
	int head;           // 원형 큐를 위한 머리
	int tail;           // 원형 큐를 위한 꼬리 
	int curSize;        // 큐의 사이즈
public:
	Queque()
	{
		curSize = QUESIZE
		que = new T[curSize];
		head = 0;
		tail = 0;
		item = 0;
	}
	~Queque()
	{
		delete que;
		head = NULL;
		tail = NULL;
		item = NULL;
	}

    // 큐가 꽉 차있는지 확인 하는 함수
    // 총 MAX개의-1 만큼 차 있어야 한다.
	bool isFull()
	{
		if (head==( (tail + 1) % curSize) ) {
			return true;
		}
		else {
			return false;
		}
	}

    // 큐가 비어있는지 확인하는 함수
	bool isEmpty()
	{
		if (head == tail)
			return true;
		return false;
	}

    // 큐에 tail에 push
	void push(T value)
	{
		if (!isFull())
		{
			que[tail] = value;
			tail = (tail + 1) % curSize;
			item++;
		}
		else
		{
			//cout << "Que is Full" << endl;
			return;
		}
	}

    // head에서 pop
	T pop()
	{
		if (!isEmpty())
		{
			int value = que[head];
			head = (head + 1) % curSize;
			item--;
			return value;

		}
		else
		{
			//cout << "Que is empty" << endl;
			return -1;
		}
	}

    // head에 있는 값(큐의 제일 앞에 있는 값)을 출력
	T front()
	{
		if (!isEmpty())
		{
			return que[head];
		}
		else
		{
			//cout << "Que is empty" << endl;
			return -1;
		}
	}

    // tail에 있는 값(큐의 가장 마지막에 있는 값)을 출력
	T back()
	{
		if (!isEmpty())
		{
			return que[tail-1];
		}
		else
		{
			//cout << "Que is empty" << endl;
			return -1;
		}
	}

    // 현재 큐에 들어가 있는 값의 갯수
	int size()
	{
		return item;
	}
};

int main()
{
    // 빠른 입출력을 위한 함수
	cin.tie(nullptr);
    ios::sync_with_stdio(false);
    
	int n;
	Queque<int> queue;
	string command;
	int num;

	cin >> n;

	for (int i = 0; i < n; i++)
	{
		cin >> command;

		if (command.compare("push") == 0)
		{
			num = 0;
			cin >>num;
			queue.push(num);
		}
		else if (command.compare("pop") == 0)
		{
			printf("%d\n", queue.pop());
		}
		else if (command.compare("size") == 0)
		{
			printf("%d\n", queue.size() );
		}
		else if (command.compare("empty") == 0)
		{
			printf("%d\n", queue.isEmpty() );
		}
		else if (command.compare("front") == 0)
		{
			printf("%d\n", queue.front());
		}
		else if (command.compare("back") == 0)
		{
			printf("%d\n", queue.back());
		}
	}

	return 0;
}