// https://www.acmicpc.net/problem/2630
// 색종이 만들기

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

// 리턴된 4개를 전부 비교해서 하나라도 다른거 있으면 -1 이 분면은 쓸모 없다는 의미로 -1리턴한다.
// -1 리턴된 경우 제외한 나머지 숫자를 비교해서 종이 ++

// 분할 정복 함수
int Divide_conquer(int xs, int xe , int ys ,int ye) 
{
    // x축과 y축의 중앙을 구한다.
    int xmid = (xs + xe)/2;
    int ymid = (ys + ye)/2;

    // 더이상 나눌 수 없게 한칸만 가리키면 그냥 그 값 반환.
    if(xs == xe && ys == ye) 
    {
        return matrix[xs][ys];
    }

    // 분할 정복을 해서 각각의 값을 벡터에 넣는다.
    vector<int> vc ;
    vc.push_back(Divide_conquer(xs, xmid, ys, ymid));
    vc.push_back(Divide_conquer(xmid + 1, xe, ys, ymid));
    vc.push_back(Divide_conquer(xs, xmid, ymid + 1, ye));
    vc.push_back(Divide_conquer(xmid+1, xe, ymid + 1, ye));

    // 반환된 모든 값이 같다면 그냥 0을 반환한다.
    if(vc[0] == vc[1] && vc[1] == vc[2] && vc[2] == vc[3]) 
    {
        return vc[0];
    }
    // 만약 하나라도 다른게 나올 경우 이제 색종이의 갯수를 구한다.
    else 
    {
        for(int i=0; i<4;i++) {
            if(vc[i]==0)
                white++;
            else if(vc[i]==1)
                blue++;
        }
        // 다른게 나왔으니 이제 이 분면은 더이상 필요 없다는 의미로 -1 리턴
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

    // 분할 정복이 끝난 후에 모두 같은 값으로 되어 있는 하나의 종이였을 경우 그 경우도 센다.   
    int paper = Divide_conquer(0,n-1,0,n-1);

    if(paper == 0)
        white++;
    else if (paper == 1)
        blue++;

    printf("%d\n%d",white,blue);

    return 0;
}