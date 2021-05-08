#include <iostream>
#include <string>
#include <queue>

using namespace std;

int board[1000][1000];
bool visited[1000][1000][2];

struct Player
{
    int y;
    int x;
    int moveCnt;
    bool broken;
};

class Solution
{
private:
    int way[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int n, m;
    // int (*board)[1000];

public:
    Solution(int n, int m /*, int board[][1000]*/)
    {
        this->n = n;
        this->m = m;
        // this->board = board;
    }

    int getMinDistance()
    {
        queue<Player> que;
        Player curr{0, 0, 1, false};

        que.push(curr);
        visited[curr.y][curr.x][0] = true;

        int nextY, nextX;
        while (!que.empty())
        {
            curr = que.front();
            que.pop();
            if (curr.y == n - 1 && curr.x == m - 1)
            {
                return curr.moveCnt;
            }
            for (int i = 0; i < 4; i++)
            {
                nextY = curr.y + way[i][0];
                nextX = curr.x + way[i][1];
                if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m)
                    continue;
                else if (visited[nextY][nextX][curr.broken ? 1 : 0])
                    continue;
                else if (board[nextY][nextX] == 1)
                {
                    if (curr.broken)
                        continue;
                    else
                    {
                        que.push(Player{nextY, nextX, curr.moveCnt + 1, true});
                        visited[nextY][nextX][1] = true;
                    }
                }
                else
                {
                    que.push(Player{nextY, nextX, curr.moveCnt + 1, curr.broken});
                    visited[nextY][nextX][curr.broken ? 1 : 0] = true;
                }
            }
        }
        return -1;
    }
};

int main(void)
{
    int n, m;

    scanf("%d %d", &n, &m);

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            scanf("%1d", &board[i][j]);
        }
    }

    Solution *solution = new Solution(n, m);
    cout << solution->getMinDistance() << endl;
    return 0;
}