## 백준 10828번 풀이

https://www.acmicpc.net/problem/10828

### 문제

*정수를 저장하는 스택을 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.*

*명령은 총 다섯 가지이다.*

- *push X: 정수 X를 스택에 넣는 연산이다.*
- *pop: 스택에서 가장 위에 있는 정수를 빼고, 그 수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.*
- *size: 스택에 들어있는 정수의 개수를 출력한다.*
- *empty: 스택이 비어있으면 1, 아니면 0을 출력한다.*
- *top: 스택의 가장 위에 있는 정수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.*

### 입력

*첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다. 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다. 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다. 문제에 나와있지 않은 명령이 주어지는 경우는 없다.*

### 출력

*출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.*

***

### 풀이

이름 그대로 스택을 직접 구현하는 문제이다.



한번 C++을 연습하고 싶어서 C++로 구현했다.



### 전체 코드

```cpp
#include <iostream>
#define MAX_STACK_SIZE 10001;

using namespace std;

template<typename T> class Stack {
private :
    // 어떤 형식이던 사용할 수 있도록 템플릿(제네릭) 형식으로 선언한다.
    T *stack;
    int top;
    int size;

public :
    Stack() {
        // 스택을 초기화 하는 부분
        size = MAX_STACK_SIZE;
        top = -1;
        stack = new T[size];
    }
    ~Stack() {
        // 스택을 삭제한다.
        delete[] stack;
    }
    int is_empty() {
        // 스택이 비어 있는지 확인한다. 만약 비어 있다면 1 출력
        return top == -1 ? 1 : 0;
    }
    int is_full() {
        // 스택이 꽉 차 있는지 확인한다. 차 있으면 1 출력
        return top - 1 == size ? 1 : 0;
    }
    int is_size() {
        // 현재 스택에 몇개나 되는 item이 들어가 있는지 확인한다.
        return top + 1;
    }
    void push(T item) {
        // 스택의 최상단에 값을 넣는다.
        if (is_full()) {
            //printf_s("스택 포화 에러\n");
            return;
        }
        else stack[++top] = item;
    }
    T pop() {
        // 스택의 최 상단의 값을 꺼내오고, 그리고 그 값을 삭제한다.
        if (is_empty()) {
            //printf_s("스택 공백 에러\n");
            return -1;
        }
        else return stack[top--];
    }
    T peak() {
        // 스택의 최상단에 값을 뽑아온다.
        if (is_empty()) {
            //  printf_s("스택 공백 에러\n");
            return -1;
        }
        else return stack[top];
    }
};

int main()
{
    int n = NULL;
    string command;
    int num = NULL;
    Stack<int> stk;

    cin >> n;

    for (int i = 0; i < n; i++) {
        cin >> command;
        
        if (command == "push") {
            // 값을 입력 받은 후에 줄 바꿈을 하지 않기 위해서 scnaf형식으로 받았다.
            // cin형식은 개행 문자까지 값을 받기 때문에
            scanf("%d", &num);
            stk.push(num);
        } 
        else if (command=="pop") {
            cout << stk.pop() << endl;
        }
        else if (command=="size") {
            cout << stk.is_size() << endl;
        }
        else if (command=="empty") {
            cout << stk.is_empty() << endl;
        }
        else if (command=="top") {
            cout << stk.peak() << endl;
        }
    }
    
    return 0;
}
```


