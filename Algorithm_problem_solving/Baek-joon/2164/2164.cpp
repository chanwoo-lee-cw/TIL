//https://www.acmicpc.net/problem/2164
#include <iostream>

using namespace std;

// set max Queue size
#define QUEUESIZE 500001

// Queue Class
template <typename T>
class Queue
{
private:
    T *queue;    // Queue list
    int maxsize; // Queue max size
    int front;   // Head position
    int rear;    // tail position

public:
    // queue init
    Queue()
    {
        maxsize = QUEUESIZE;
        queue = new T[maxsize];
        front = 0;
        rear = 0;
    }

    // delete queue
    ~Queue()
    {
        maxsize = NULL;
        delete (queue);
        front = NULL;
        rear = NULL;
    }

    // put the item on the tail in the queue
    void push(T item)
    {
        // make sure queue is full
        if (isFull())
        {
            return;
        }

        // because it is circularque, place the item in the calculated position of front
        else
        {
            rear = (rear + 1) % maxsize;
            queue[rear] = item;
        }
    }

    // pop out from queue head
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

    // check queue is full
    bool isFull()
    {
        if ((rear + 1) % maxsize == front)
            return true;
        else
            return false;
    }

    // check queue is empty
    bool isEmpty()
    {
        if (front == rear)
            return true;
        else
            return false;
    }

    // Returns the number of items in the queue.
    int isSize()
    {
        int tmp = rear - front;
        return tmp >= 0 ? tmp : maxsize + tmp;
    }
};

int main()
{
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    int n = NULL;

    cin >> n;
    Queue<int> queue;

    // insert number from 1 to n into the queue
    for (int i = 0; i < n; i++)
    {
        queue.push(i + 1);
    }

    {
        // variable to store the item to be queued again
        int repush = NULL;

        // repeat until 1 item is left in the queue
        while (queue.isSize() > 1)
        {
            queue.pop();
            repush = queue.pop();
            queue.push(repush);
        }
    }
    cout << queue.pop();

    return 0;
}