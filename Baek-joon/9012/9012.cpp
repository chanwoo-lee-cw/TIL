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