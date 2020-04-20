#include<iostream>
using namespace std;

int binomial_coefficient(n,k);
int dp[11][11];

int main() {
    int n ,k;

    cin  >> n >> k;
    memset(dp, 0, sizeof(dp));

    cout << binomial_coefficient(n,k)) <<endl;

    return 0;
}

int binomial_coefficient(int n,int k) {
    if(dp[n][k] != 0) 
        return dp[n][k];

    if( n == k || k == 0 )
        dp[n][k] = 1
    else 
        dp[n][k] = (binomial_coefficient(n-1,k-1) + binomial_coefficient(n-1,k));
    
    return dp[n][k]
}