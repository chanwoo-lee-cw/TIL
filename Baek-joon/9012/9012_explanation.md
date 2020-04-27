## 백준 9012번 풀이

#https://www.acmicpc.net/problem/9012

### 문제

*괄호 문자열(Parenthesis String, PS)은 두 개의 괄호 기호인 ‘(’ 와 ‘)’ 만으로 구성되어 있는 문자열이다. 그 중에서 괄호의 모양이 바르게 구성된 문자열을 올바른 괄호 문자열(Valid PS, VPS)이라고 부른다. 한 쌍의 괄호 기호로 된 “( )” 문자열은 기본 VPS 이라고 부른다. 만일 x 가 VPS 라면 이것을 하나의 괄호에 넣은 새로운 문자열 “(x)”도 VPS 가 된다. 그리고 두 VPS x 와 y를 접합(concatenation)시킨 새로운 문자열 xy도 VPS 가 된다. 예를 들어 “(())()”와 “((()))” 는 VPS 이지만 “(()(”, “(())()))” , 그리고 “(()” 는 모두 VPS 가 아닌 문자열이다.* 

*여러분은 입력으로 주어진 괄호 문자열이 VPS 인지 아닌지를 판단해서 그 결과를 YES 와 NO 로 나타내어야 한다.* 



### 입력

*입력 데이터는 표준 입력을 사용한다. 입력은 T개의 테스트 데이터로 주어진다. 입력의 첫 번째 줄에는 입력 데이터의 수를 나타내는 정수 T가 주어진다. 각 테스트 데이터의 첫째 줄에는 괄호 문자열이 한 줄에 주어진다. 하나의 괄호 문자열의 길이는 2 이상 50 이하이다.* 



### 출력

*출력은 표준 출력을 사용한다. 만일 입력 괄호 문자열이 올바른 괄호 문자열(VPS)이면 “YES”, 아니면 “NO”를 한 줄에 하나씩 차례대로 출력해야 한다.*



***

### 풀이

괄호 검사는 대표적인 스택 문제이다.



```cpp
template<typename T> class Stack {
private:
    T* stack;
    int top;
    int size;

public :
    Stack() {
        size = MAX_STACK_SIZE;
        top = -1;
        stack = new T[size];
    }
    ~Stack() {
        delete[] stack;
    }
    bool is_empty() {
        return top == -1 ? true : false;
    }
    bool is_full() {
        return top == MAX_STACK_SIZE - 1 ? true : false;
    }
    void push(T item) {
        if (is_full()) {
            //printf("다 찬 스택");
            return;
        }
        else {
            stack[++top] = item;
        }
    }
    T pop() {
        if (is_empty()) {
            //비어 있는 스택
            return NULL;
        }
        else {
            return stack[top--];
        }
    }
};
```

일단 검사를 위한 스택을 선언한다.



```cpp
int testcase;    
Stack<char> *stk;
```

테스트 케이스 끝날때마다 이전에 있던 스택을 지우고 새로운 스택을 선언해야 되니, 스택을 동적으로 선언한다.

```cpp
	for (int i = 0; i < testcase; i++) {
        // 만약 에러를 찾아 반복문이 취소 된거인지 확인하는 플래그
        check = false;
        // 테이스 케이스를 입력받는다.
        cin >> line;
        // 입력받은 줄의 갯수가 몇개인지 확인
        lineLen = line.length();
        // 괄호의 갯수를 세기 위한 것
        stk = new Stack<char>;
        
		....
            
	}
```

테스트 케이스를 입력받고 이게 오류로 캔슬된건지 아니면 읽는게 끝나서 그런건지 확인하기 위한 플래그 변수를 하나 선언한다. 그리고 이전에 선언하였던 포인터에 스택을 선언해서 연결한다.



```cpp
for (int j = 0; j < lineLen; j++) {
            // ( 괄호하면 스택에 넣는다.
            if (line[j] == '(') {
                stk->push('(');
            }
            else {
                // 스택이 비어있는지 확인
                if (stk->is_empty()) {
                // 빈 스택이라면 (가 없다는 의미므로 괄호에서 탈출및 NO를 출력했다는 의미로 플래그를 바꿔준다.
                    cout << "NO" << endl;
                    check = true;
                    break;
                }
                else
                {
                    //만약 있다면 스택에서 뽑는다.
                    stk->pop();
                }
            }
        }
        if (!stk->is_empty())
            cout << "NO" << endl;
        else
            if(!check)
                cout << "YES" << endl;
```

그리고 검사한다.





### 전체 코드

```cpp
#include <iostream>
using namespace std;
#define MAX_STACK_SIZE 10001;

template<typename T> class Stack {
private:
    T* stack;
    int top;
    int size;

public :
    Stack() {
        size = MAX_STACK_SIZE;
        top = -1;
        stack = new T[size];
    }
    ~Stack() {
        delete[] stack;
    }
    bool is_empty() {
        return top == -1 ? true : false;
    }
    bool is_full() {
        return top == MAX_STACK_SIZE - 1 ? true : false;
    }
    void push(T item) {
        if (is_full()) {
            //printf("다 찬 스택");
            return;
        }
        else {
            stack[++top] = item;
        }
    }
    T pop() {
        if (is_empty()) {
            //비어 있는 스택
            return NULL;
        }
        else {
            return stack[top--];
        }
    }
};

int main()
{
    
    int testcase;
    string line;
    int lineLen;
    Stack<char> *stk;
    int check = false;

    // 테스트 케이스가 몇개인지 입력받는다.
    cin >> testcase;

    for (int i = 0; i < testcase; i++) {
        // 만약 에러를 찾아 반복문이 취소 된거인지 확인하는 변수
        check = false;
        // 테이스 케이스를 입력받는다.
        cin >> line;
        // 입력받은 줄의 갯수가 몇개인지 확인
        lineLen = line.length();
        // 괄호의 갯수를 세기 위한 것
        stk = new Stack<char>;

        for (int j = 0; j < lineLen; j++) {
            // ( 괄호하면 스택에 넣는다.
            if (line[j] == '(') {
                stk->push('(');
            }
            else {
                // 스택이 비어있는지 확인
                if (stk->is_empty()) {
                    // 빈 스택이라면 (가 없다는 의미므로 괄호에서 탈출및 NO를 출력했다는 의미로 플래그를 바꿔준다.
                    cout << "NO" << endl;
                    check = true;
                    break;
                }
                else
                {
                    //만약 있다면 스택에서 뽑는다.
                    stk->pop();
                }
            }
        }
        if (!stk->is_empty())
            cout << "NO" << endl;
        else
            if(!check)
                cout << "YES" << endl;
        delete stk;
    }

    return 0;
}
```

