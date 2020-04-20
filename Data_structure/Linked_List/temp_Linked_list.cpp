#include <iostream>
using namespace std;

template<typename T> struct Node {
public:
    T* item;
    Node<T>* next;

    Node(int item) : item(item), next(nullptr) {}
};

template<typename T> class SingleLinkedList {
private :
    int length;
    Node<T>* head;
    Node<T>* tail;

public:
    SingleLinkedList() : head(nullptr), tail(nullptr) {}
    ~SingleLinkedList() {

        // clear();

        Node<T>* ptr = head;
        Node<T>* next = head->next;
        
        do {
            delete ptr;
        } while (head->next != nullptr);
        
    }
    void AddLast(T item) {
        Node<T> *newNode = new Node<T>(item);
        if (head == nullptr) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail->next = newNode;
        }
        length = length + 1;
    }
    void AddFirst(T item) {
        Node<T>* newNode = new Node<T>(item);
        if (head == nullptr)
        {
            head = newNode;
            tail = newNode;
        }
        else {
            newNode->next = head;
            head = newNode;
        }
        length = length + 1;
    }
    // 해당 위치에 노트 추가
    // add(pos,item)

    // 해당 위치의 요소 삭제 
    // delete(pos)

    // 모든 노드 삭제
    // claer()

    // 해당 위치의 요소 삭제 
    // delete(pos)

    // 해당 위치의 요소를 바꾼다
    // replace(pos,item)

    // 리스트 안에 값이 있는지 찾는다. 
    // is_in_list(item)

    // 해당 위치의 요소 삭제 
    // getEntry(pos위치의 요소를 반환한다.)

    // 해당 위치의 요소 삭제 
    // getLength

    // 해당 위치의 요소 삭제 
    // isEmpty()

    // 해당 위치의 요소 삭제 
    // isFull

    // 모든 요소 출력 
    // display()
};



int main()
{
    

    return 0;
}