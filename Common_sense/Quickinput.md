# 빠른 입력
## C++
### cin.tie(nullptr);
- 코드가 순서대로 작동하는 것을 해제한다.
- 출력 되기전에 입력을 기다리는 것이 가능해진다.
- 입력과 출력을 여러번 반복해야 되는 경우 필수적이다.

### ios_base::sync_with_stdio(false);
- cpp의 iostream을 c의 stdio와 동기화를 끊는 함수.
- cpp만의 독립적인 버퍼를 생성해서 빨라진다.

## Java
- Scanner는 한글자 한글자씩 입력받는다.
    - 즉각 적인 반응이 필요한 곳에서 적합
- 버퍼리더는 한줄을 통째로 입력받는다.
    - 속도가 빠르다.
### BufferReader
- 입력받은 데이터를 String으로 고정한다.
- 예외 처리가 필수
### BufferWriter
- 단. 마지막에 bufferWriter.flush로 비워줘야 할 필요성이 있다.

```java
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));//선언
bw.write(s+"\n");//출력
bw.flush();//남아있는 데이터를 모두 출력시킴
bw.close();//스트림을 닫음
```

## python
### sys.stdin.readline
1. nput()은 선택적으로 실행되는 interpreter가 있다면 보여주는 prompt parameter를 가지고 있다. 이것은 prompt가 비어 있는 경우에도 overhead를 초래한다.

2. input()은 개행 문자를 제거해준다. readline()은 strip()을 추가해줘야 한다.

3. input()은 더이상 입력을 하지 않을 때 EOFError를 증가시킨다.
   하지만 readline()은 EOF에서 빈 문자열을 반환한다.