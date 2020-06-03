#include<iostream>

#define QUEMAX  500000

template<class T> class Queue
{
private:
    T* values;
    int front;
    int rear;
    int quesize;

public:
    Queue() 
    {
        quesize = QUEMAX;
        values = new T[quesize];
        front = 0;
        rear = 0;
    }

    ~Queue() 
    {
        delete(values);
        front = nullptr;
        rear= nullptr;
        quesize= nullptr;
    }

    void push(T item) 
    {
        if(!isFull())
        {
            values[]
        }
    }

};