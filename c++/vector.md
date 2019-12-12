### vector

- 쉽게 생각하면 vector 컨테이너는 자동으로 메모리가 할당되는 배열.
- template를 사용하기 때문에 데이터 타입을 마음대로 설정 가능

<img src="https://t1.daumcdn.net/cfile/tistory/23249B335970004C14" alt="img" style="zoom:67%;" />

- 이런 구조로, 맨 뒤쪽에서 삽입과 삭제가 가능하다.
- 중간의 값을 삭제할 수도 있지만, 배열 기반이므로 삽입 삭제가 빈번하다면 비 효율적이다.



### vector의 사용

- <vector>  헤더 파일을 추가해야 한다.

- std네임스페이스 사용을 해주면 편리하다.

- vector의 선언은 vector<[data type]> [변수 이름]

  ```c++
  #include<vector>
  
  using namespace std;
  
  int main() {
      vector<int> v;
      vector<ll> long_long;	//ll은 long long 자료형
  }
  ```

  

### vector의 생성자와 연산자

- **vector v;
  **- 비어있는 vector v를 생성합니다.

  

- **vector v(5);
  **- 기본값(0)으로 초기화 된 5개의 원소를 가지는 vector v를 생성합니다.

  

- **vector v(5, 2);
  **- 2로 초기화된 5개의 원소를 가지는 vector v를 생성합니다.

  

- vector<int> v1(5, 2);
  **vector v2(v1);
  **- v2는 v1 vector를 복사해서 생성됩니다.

- vector<int> v1; , vector<int> v2;  가 있고, 내부에 인자들이 있다고 했을때.

  **연산자 : "==", "!=", "<", ">", "<=", ">=" 로 대소비교 가 가능합니다.**

  1. 상등 비교-> 요소의 개수와 모든 요소의 값이 일치할 때 같은 것으로 판단한다. 벡터가 생성되어 있는 메모리 위치나 추가할당여유분은 벡터의 실제 내용이 아니므로 상등 비교의 대상이 아니다.  들어 있는 내용만 같다면 같은 벡터로 취급.
  2. 대소비교-> 대응되는 각 요소들을 일대일로 비교하다가 최초로 다른 요소가 발견되었을 때 두 요소의 대소를 비교  한 결과를 리턴. 만약 한쪽 벡터의 길이가 더 짧아 먼저 끝을 만났다면 아직끝나지 않은 벡터가 더 큰 것으로 판별.

### vector의 멤버 함수

- v.assign(5, 2);

  - 2의 값으로 5개의 원소 할당.

- v.at(idx);

  - idx번째 원소를 참조합니다.
  - v[idx] 보다 속도는 느리지만, 범위를 점검하므로 안전합니다.

- v[idx];

  - idx 번째 원소를 참조합니다.
  - 범위를 점검하지 않으므로 속도가 v.at(idx)보다 빠릅니다.

- v.front();

  - 첫번째 원소를 참조합니다

- v.back();

  - 마지막 원소를 참조합니다.

  

- v.clear();

  - 모든 원소를 제거합니다.
  - 원소만 제거하고 메모리는 남아있습니다.
  - size만 줄어들고 capacity는 그대로 남아있습니다.

  

- v.push_back(7);

  - 마지막 원소 뒤에 원소 7을 삽입합니다.

  

- v.pop_back();

  - 마지막 원소를 제거합니다.

  

- v.begin();

  - 첫번째 원소를 가리킵니다. (iterator와 사용)

  ```c++
  vector<int>::iterator iter=v.begin();
  
  for (iter = v.begin(); iter != v.end(); ++iter){
      cout << *iter << endl;
  }
  
  ```

  

  

- v.end();

  - 마지막의 "다음"을 가리킵니다 (iterator와 사용)

  

- v.rbegin();

  - reverse begin을 가리킨다 (거꾸로 해서 첫번째 원소를 가리킵니다)
    \- iterator와 사용.

  

- v.rend();

  - reverse end 을 가리킨다 (거꾸로 해서 마지막의 다음을 가리킵니다)
    \- iterator와 사용.

  

- v.reserve(n);

  - n개의 원소를 저장할 위치를 예약합니다(미리 동적할당 해놉니다)

  

- v.resize(n);

  - 크기를 n으로 변경한다.
  - 더 커졌을 경우 default값인 0으로 초기화 한다.

- v.resize(n,3);
  - 크기를 n으로 변경한다.
  - 더 커졌을 경우 인자의 값을 3으로 초기화한다.

- v.size();

  - 원소의 갯수를 리턴한다.

  

- v.capacity();

  - 할당된 공간의 크기를 리턴한다.

  - 공간 할당의 기준은 점점 커지면서로 capacity를 할당하게 됩니다.

    

- v2.swap(v1);

  - v1과 v2의 원소와 capacity 바꿔줍니다. (모든걸 스왑해줌)
  - v1의 capacity를 없앨때 (할당한 메모리를 프로그램이 끝나기 전에 없애고 싶을때) 사용하기도 합니다.
  - v2를 capacity가 0인 임시 객체로 만들어서 스왑을 해줍니다.
  - vector<int>().swap(v1);

  

- v.insert(2, 3, 4);

  - 2번째 위치에 3개의 4값을 삽입합니다. (뒤엣놈들은 뒤로 밀림)

  

- v.insert(2, 3);

  - 2번째 위치에 3의 값을 삽입합니다.
  - 삽입한 곳의 iterator를 반환합니다.

  

- v.erase(iter);

  - iter 가 가리키는 원소를 제거합니다.
  - size만 줄어들고 capacity(할당된 메모리)는 그대로 남습니다.

```c++
v.erase(iter); 
// 파라미터를 하나 받는 경우에는 iter위치의 인자 삭제
v.erase(start,end); 
// 파라미터를 두개 받는 경우는 [start,end)벙위 인자 삭제
// start 이상, end 미만의 범위 인자를 삭제한다는 뜻이다.
// start <= n < end
```



- v.empty()
  - vector가 비었으면 리턴 true
  - 비어있다의 기준은 size가 0이라는 것이지, capacity와는 상관이없습니다.



- v.size();

  - 원소의 갯수 리턴

- v.capacity();

  - 할당된 공간의 크기를 리턴
  - 공간 할당은 기존 메모리 *2로 증가 하게 된다.

  ```c++
  // 예를 들면
  //원소 갯수 1개일때
  v.capacity(); // 1
  //원소 갯수 2개일때
  v.capacity(); // 2
  //원소 갯수 3개일때
  v.capacity(); // 4
  //원소 갯수 4개일때
  v.capacity(); // 4
  //원소 갯수 5개일때
  v.capacity(); // 8
  ```

  

<img src="https://t1.daumcdn.net/cfile/tistory/2461DA33597002310C" alt="img" style="zoom:67%;" />



#### vector의 멤버 형식

- iterator - 반복자 형식

- reverse_iterator- 역 반복자 형식

- value_type - 원소의 형식

- size_type- 원소의 개수의 형식



```c++
vector<int>::iterator iter;
for(iter = v.begin(); iter != v.end() ; iter++){
    cout << *iter << " ";
}
```





### 출처

https://blockdmask.tistory.com/70