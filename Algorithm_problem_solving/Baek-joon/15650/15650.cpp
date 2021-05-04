#include <stdio.h>

using namespace std;

class Combination
{
private:
    int n;
    int m;
    int *arr;
    int arrSize;

public:
    Combination(int n, int m)
    {
        this->n = n;
        this->m = m;
        this->arr = new int[n];
        this->arrSize = 0;
    }

    void dfs(int position)
    {
        if (arrSize == m)
        {
            for (int i = 0; i < m; i++)
            {
                printf("%d ", arr[i]);
            }
            printf("\n");
        }
        else
        {
            for (int i = position; i < n; i++)
            {
                arr[arrSize] = i + 1;
                arrSize++;
                dfs(i + 1);
                arrSize--;
            }
        }
    }
};

int main(void)
{
    int n, m;

    scanf("%d %d", &n, &m);
    Combination *combination = new Combination(n, m);

    combination->dfs(0);
    return 0;
}