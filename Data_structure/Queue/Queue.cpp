template<typename T> class Queue
{
private:
	T* queue;
	int maxsize;
	int front;
	int rear;

public:
	Queue() 
	{
		maxsize = QUEUESIZE;
		queue = new T[maxsize];
		front = 0;
		rear = 0;
	}

	~Queue()
	{
		maxsize = NULL;
		delete(queue);
		front = NULL;
		rear = NULL;
	}

	void push(T item)
	{
		if (isFull())
		{
			return;
		}
		else
		{
			rear = (rear + 1) % maxsize;
			queue[rear] = item;
		}
	}

	T pop()
	{
		if (isEmpty()) 
		{
			return NULL;
		}
		else
		{
			front = (front + 1) % maxsize;
			return queue[front];
		}
	}

	bool isFull()
	{
		if ((rear + 1) % maxsize == front)
			return true;
		else
			return false;
	}

	bool isEmpty()
	{
		if (front == rear)
			return true;
		else
			return false;
	}

	int isSize()
	{
		int tmp = rear - front;
		return tmp >= 0 ? tmp : maxsize + tmp;
	}
};