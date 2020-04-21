#include <iostream>
using namespace std;

template<typename T> struct Node {
public:
    T item;
    Node<T>* next;

    Node(int item) : item(item), next(nullptr) {}
};

template<typename T> class SingleLinkedList {
private :
    int length;
    Node<T>* head;
    Node<T>* tail;

public:
    // 생성자 함수
    SingleLinkedList() : head(nullptr), tail(nullptr) {
        length = 0;
    }
    

    // 소멸자 함수
    ~SingleLinkedList() {
        

        Node<T>* parent = head;
        Node<T>* child = head;

        while (child->next != nullptr)
        {
            parent = child;
            child = child->next;
            delete parent;
        }

        delete child;
    }


    // 링크드 리스트의 마지막에 추가
    void AddLast(T item) {
        Node<T>* newNode = new Node<T>(item);

        // 만약 링크드 리스트가 비어 있다면 그냥 생성해서 헤드로 삼는다
        if (head == nullptr) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail->next = newNode;
            tail = newNode;
        }
        length = length + 1;
    }


    // 루트 부분에 추가
    void AddFirst(T item) {
        
        Node<T>* newNode = new Node<T>(item);

        // 만약 링크드 리스트가 비어 있다면 그냥 생성해서 헤드로 삼는다
        if (head == nullptr) {
            head = newNode;
            tail = newNode;
        }
        else {
            // 링크드 리스트의 헤드를 대체 하기 전에 새로운 노드의 next가 헤드였던 노드를 가리키게 한 다음에 대체한다.
            newNode->next = head;
            head = newNode;
        }
        length = length + 1;
    }


    // 해당 위치에 노트 추가
    void add(int pos, T item) {
        Node<T>* newNode = new Node<T>(item);

        if (pos > length || pos < 0)
            // 만약 넣고자 하는 위치가 현재 링크드 리스트보다 작거나
            // 음수라면 에러 메세지를 리턴한다
            cout << "삽입할 수 없는 위치" <<endl;
        else if (pos == 0)
            // 만약 넣으려는 위치가 head부분이라면
            // AddFirst함수를 호출한다.
            AddFirst(item);
        else if (pos == length)
            // 만약 넣으려는 위치가 tail부분이라면
            // AddLast함수를 호출한다.
            AddLast(item);
        else {
            // 넣으려는 위치의 바로 앞 노드의 next노드를 바꾸고
            // 찾으려는 위치를 구해야 하니 child 노드도 같이 데려간다.
            Node<T>* parent = head;
            Node<T>* child = head;

            for (int i = 0; i < pos; i++) {
                // 원하는 위치만큼 간다.
                parent = child;
                child = child->next;
            }

            // 바로 새로운 노드는 child를 가르키게 하고
            // 앞 노드는 새로운 노드를 가르키게 바꾼다.
            newNode->next = child;
            parent->next = newNode;
           
            length = length + 1;
        }
    }


    // 첫 번째 노드의 삭제
    void deleteFirst() {
        if (isEmpty()) {
            cout << "리스트가 비어있습니다." <<endl;
            return;
        }

        Node<T>* ptr = head;

        head = head->next;
        length = length - 1;

        delete ptr;
    }


    // 마지막 노드를 삭제한다.
    void deleteLast() {
        if (isEmpty()) {
            cout << "리스트가 비어있습니다." << endl;
            return;
        }

        Node<T>* parent = head;
        Node<T>* child = head;

        while (child->next != nullptr)
        {
            parent = child;
            child = child->next;
        }

        parent->next = nullptr;
        length = length - 1;

        delete child;
    }


    // 모든 노드 삭제
    void claer() {
        if (isEmpty()) {
            cout << "리스트가 비어있습니다." << endl;
            return;
        }

        Node<T>* parent = head;
        Node<T>* child = head;

        while (child->next != nullptr)
        {
            parent = child;
            child = child->next;
            delete parent;
        }
        length = 0;

        delete child;
    }


    // 해당 위치의 요소 삭제 
    void deletePos(int pos) {
        if (isEmpty()) {
            cout << "리스트가 비어있습니다." << endl;
            return;
        }
        else if (pos == 0) {
            deleteFirst();
        }
        else if (pos == length - 1) {
            deleteLast();
        }
        else {
            Node<T>* parent = head;
            Node<T>* child = head;

            for (int i = 0; i < pos; i++) {
                // 원하는 위치만큼 간다.
                parent = child;
                child = child->next;
            }

            // 노드를 삭제하기 전에 부모의 다음을 삭제하려는 노드의 다음으로 지정한다.
            parent->next = child->next;
            length = length - 1;

            delete child;
        }
    }


    // 삭제하길 원하는 값을 찾아서 지원다.
    void deleteSearch(T item) {
        Node<T>* parent = head;
        Node<T>* child = head;

        // 원하는 값이 나올 때까지 링크를 타며 찾는다.
        while (child != nullptr) {
            if (child->item == item) {
                break;
            }
            else {
                parent = child;
                child = child->next;
            }
        }

        // 만약 끝까지 왔는데도 찾을 수 없었다면. 없다고 출력
        if (child == nullptr) {
            cout << "존재하지 않는 값입니다." << endl;
        }
        // 찾았다면 자식 노드가 가르키고 있는 것을 대신 가르키고 자식 노드 삭제
        else {
            parent->next = child->next;
            delete child;
            length = length - 1;
        }
    }


    // 해당 위치의 요소를 바꾼다
    void replace(int pos,int item) {

        Node<T>* ptr = head;

        for (int i = 0; i < pos; i++) {
            if (ptr == nullptr) {
                break;
            }
            else {
                ptr = ptr->next;
            }
        }

        // 만약 끝까지 왔는데도 찾을 수 없었다면. 없다고 출력
        if (ptr == nullptr) {
            cout << "리스트에 해당 위치가 존재하지 않습니다." << endl;
        }
        else {
            ptr->item = item;
        }

    }


    // 리스트 안에 값이 있는지 찾는다. 
    bool is_in_list(int item) {
        Node<T>* ptr = head;

        while (ptr != nullptr) {
            if (ptr>item == item) {
                break;
            }
            else {
                ptr = ptr->next;
            }
        }
        if (ptr == nullptr) {
            return false;
        }
        else {
            return true;
        }

    }


    // 해당 위치의 값을 반환
    T getEntry(int pos) {
        Node<T>* parent = head;
        Node<T>* child = head;

        for (int i = 0; i < pos; i++) {
            if (child == nullptr) {
                break;
            }
            else {
                parent = child;
                child = child->next;
            }
        }
        if (child == nullptr) {
            cout << "리스트에 해당 위치가 존재하지 않습니다." << endl;
        }
        else {
            return child->item;
        }
    }


    // 리스트의 길이를 출력한다.
    int getLength() {
        return length;
    }


    // 해당 위치의 요소 삭제 
    bool isEmpty() {
        return head == nullptr ? true : false;
    }


    // 모든 요소 출력 
    void display() {
        Node<T>* ptr = head;

        while (ptr!=nullptr)
        {
            cout << ptr->item << endl;
            ptr = ptr->next;
        }
    }
};



int main()
{
    
    SingleLinkedList<int> list;

    list.add(0, 11);
    list.add(0, 12);
    list.add(0, 13);
    list.add(0, 14);
    list.replace(0, 10);
    cout << "0번 위치" << list.getEntry(0) << endl;

    list.display();
    list.deleteFirst();
    list.display();
    list.deleteLast();
    list.display();

    return 0;
}