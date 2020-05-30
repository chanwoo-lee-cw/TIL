// https://www.acmicpc.net/problem/18258
#include <iostream>

#define QUESIZE  2000001;

using namespace std;

template<typename T> class Queque
{
private:
	T* que;
	int item;
	int head;
	int tail;
	int curSize;
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

	bool isFull()
	{
		if (head==( (tail + 1) % curSize) ) {
			return true;
		}
		else {
			return false;
		}
	}

	bool isEmpty()
	{
		if (head == tail)
			return true;
		return false;
	}

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

	int size()
	{
		return item;
	}
};

int main()
{
	cin.tie(nullptr);
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
			scanf("%d", &num);
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