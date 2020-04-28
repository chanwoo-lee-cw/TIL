#include<iostream>
#include <algorithm>

using namespace std;

int gcd (int a, int b)
{
    int divisor = 0;
    while (b)
    {
        divisor = a % b;
        if (divisor == 0) 
        {
            divisor = b;
            break;
        }
        a = b;
        b = divisor;
    }
    return divisor;
}

int lcm(int a, int b)
{
    int divisor = gcd(a,b);

    // int multiple = (a/divisor) * (b/divisor) * divisor
    int multiple = a/divisor * b;

    return multiple;
}

int main () {
    int num1, num2;
    int divisor = 0 , multiple= 0;

    cin >> num1 >> num2;

    if (num1 < num2) swap(num1 , num2);

    divisor = gcd(num1,num2);

    multiple = lcm(num1,num2);

    cout << divisor << endl;
    cout << multiple << endl;

    return 0;
}