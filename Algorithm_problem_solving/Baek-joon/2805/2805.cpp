#include <stdio.h>
#include <algorithm>

int tree[1000001];

using namespace std;

int main(void)
{
    int n, m;
    int answer;
    scanf("%d %d", &n, &m);

    for (int i = 0; i < n; i++)
    {
        scanf("%d", &tree[i]);
    }
    sort(tree, tree + n);

    /*
	* 일단 차분히 생각해 보자면...
	* 일단 처음에 자르는건 mid의 길이로 자른다.
	* 만약 전제 자른 길이가 M보다 크거나 같다면 cutting을 저장하고, s = m+1
	* 만약 전체 자른 길이가 m보다 작다면 e = s
	*/

    {
        int s = 0;
        int e = tree[n - 1];
        int cutting = s;
        long sum = 0;
        long temp;
        while (e - s > 0)
        {
            cutting = (s + e) / 2;
            sum = 0;
            for (int i = 0; i < n; i++)
            {
                temp = tree[i] - cutting;
                if (temp > 0)
                    sum += temp;
            }
            if (sum >= m)
            {
                answer = cutting;
                s = cutting + 1;
            }
            else
            {
                e = cutting;
            }
        }
    }

    printf("%d", answer);
    return 0;
}