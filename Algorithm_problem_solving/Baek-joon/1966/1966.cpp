#include <iostream>
#include <string>
#include <queue>

using namespace std;

class PrintQueue
{
private:
    queue<int> que;
    priority_queue<int> pq;
    int m;

public:
    PrintQueue(int n, int m, int *arr)
    {
        for (int i = 0; i < n; i++)
        {
            que.push(arr[i]);
            pq.push(arr[i]);
        }
        this->m = m;
    }

    int getPrintM()
    {
        int currentPrint;
        int printCnt = 0;
        while (true)
        {
            m--;
            if (que.front() != pq.top())
            {
                currentPrint = que.front();
                que.pop();
                que.push(currentPrint);
                if (m < 0)
                    m = que.size() - 1;
            }
            else
            {
                currentPrint = que.front();
                que.pop();
                pq.pop();
                printCnt++;
                if (m < 0)
                    return printCnt;
            }
        }
    }
};

int main(void)
{
    int t;
    scanf("%d", &t);
    {
        int n, m;
        int arr[100];
        PrintQueue *que;
        for (int test = 0; test < t; test++)
        {
            scanf("%d %d", &n, &m);
            for (int i = 0; i < n; i++)
            {
                scanf("%d", &arr[i]);
            }
            que = new PrintQueue(n, m, arr);
            cout << que->getPrintM() << endl;
        }
    }

    return 0;
}