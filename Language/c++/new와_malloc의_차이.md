# C++

## new와 malloc의 차이

### 선언

둘 다 반환 값이 주소값이다.

그래서 포인터로 받는다.



그리고 이 것을 힙에 저장한다.

**둘 다 무조건 다 쓰고 난 후에 메모리 해제 시켜주자. **



1. new의 생성

   ```c++
   #include<iostream>
   
   template<typename T> struct Node {
   public:
       T item;
       Node<T>* next;
   
       Node(int item) : item(item), next(nullptr) {}
   };
   
   void main() {
       Node*<int>* a = new Node<int>();
       int* b = new int[10];
       
       delete a;
       delete[] b;
   }
   ```

   **주의점** - 배열을 삭제 할때는 반드시 delete[]로 삭제해주자. -> 제대로 삭제 안되거나, 힙 손상의 위험

   

2. maolloc의 생성

   ```c++
   #include<iostream>
   
   template<typename T> struct Node {
   public:
       T item;
       Node<T>* next;
   
       Node(int item) : item(item), next(nullptr) {}
   };
   
   void main() {
       Node<int>* a = (struct Node<int>*)malloc(sizeof(struct Node<int>));
       int* b = (int *)malloc(sizeof(int) * 10);
       
       free(a);
       free(b);
   }
   ```



### 차이점

1. 선언 형식

   1. `malloc`은 리턴 자료형이 `(void *)`이기 때문에 **반드시 자료형을 선언해 줘야 한다.**
   2. `new`는 자동적으로 해당 객체에 맞는 포인터로 반환해 준다.

2. 초기값의 여부

   1. `malloc`은 생성자 호출 기능이 없다.
   2. `new`를 이용해 객체를 생성하면, 생성자를 호출하게 되므로 초기값 설정 가능.

3. 객체 생성시 에러

   1. `malloc`은 에러 발생 시 NULL값을 반환하게 됩니다.
   2. `new`를 통해 객체생성시 에러가 발생하면 예외처리를 한다.

4. 메모리 크기 변경 여부

   1. `malloc`은 `realloc`으로 할당된 **메모리 크기를 재조정이 가능**.
   2. `new`는 할당된 크기에 대한 **메모리 재조정이 불가능**.

5. delete와 free의 차이

   1. `free` - 그냥 메모리만 삭제한다.
   2. `delete` - 소멸자를 호출하기 때문에 삭제 전에 할일을 정해줄 수 있다.

   

## 결론

대부분의 상황에서 `new`가 더 좋다.

그냥 `new`쓰자.



단, 메모리를 변경해야 되는 경우라면  `malloc`이 더 낫다.

`realloc`으로 할당된 메모리 재조정해주자.





## 참고

https://hijuworld.tistory.com/59

[https://hashcode.co.kr/questions/318/malloc-new-%EB%91%98-%EC%A4%91-%EC%96%B4%EB%8A%90-%EA%B1%B8-%EC%8D%A8%EC%95%BC-%ED%95%98%EB%82%98%EC%9A%94](https://hashcode.co.kr/questions/318/malloc-new-둘-중-어느-걸-써야-하나요)