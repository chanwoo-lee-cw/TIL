#include<iostream>
#include<vector>

using namespace std;

int n;
int matrix[129][129];
int blue = 0;
int white = 0;

// 4개로 나눠서 분할 정복할 경우
// 전부 1인 경우    1 리턴
// 전부 0인 경우    0 리턴
// 둘이 섞여 있을 경우  -1 리턴

// 리턴된 4개를 전부 비교해서 하나라도 다른거 있으면 -1 리턴하고
// -1 리턴된 경우 제외한 나머지 숫자를 비교해서 종이 ++

int Divide_conquer(int xs, int xe , int ys ,int ye) 
{
    int xmid = (xs + xe)/2;
    int ymid = (ys + ye)/2;

    if(xs == xe && ys == ye) 
    {
        return matrix[xs][ys];
    }
    vector<int> vc ;
    vc.push_back(Divide_conquer(xs, xmid, ys, ymid));
    vc.push_back(Divide_conquer(xmid + 1, xe, ys, ymid));
    vc.push_back(Divide_conquer(xs, xmid, ymid + 1, ye));
    vc.push_back(Divide_conquer(xmid+1, xe, ymid + 1, ye));

    if(vc[0] == vc[1] && vc[1] == vc[2] && vc[2] == vc[3]) 
    {
        return vc[0];
    }
    else 
    {
        for(int i=0; i<4;i++) {
            if(vc[i]==0)
                white++;
            else if(vc[i]==1)
                blue++;
        }
        return -1;
    }
}

int main() 
{
    cin >> n ;

    for (int i=0; i<n; i++)
    {
        for(int j=0;j<n;j++) 
        {
            scanf("%d", &matrix[i][j]);    
        }
    }
    
    int paper = Divide_conquer(0,n-1,0,n-1);

    if(paper == 0)
        white++;
    else if (paper == 1)
        blue++;

    printf("%d\n%d",white,blue);

    return 0;
}