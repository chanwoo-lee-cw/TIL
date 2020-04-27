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