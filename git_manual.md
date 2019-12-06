## git 사용법

기본적인 명령어는 리눅스와 똑같음. 리눅와 같은 명령어가 아닌 경우에는 git이 앞에 붙는다.



##### git config --global user.name " Your name"

##### git config - -global user.email “Your email address”

전역 사용자명/ 이메일 구성하는 것, 최초로 한번만 해주면 된다.

ex) git config --lobal user.name "me"

error) git: 'commist' is not a git command. See 'git --help'.



##### mkdir

파일 생성 하는 명령어 

ex) mkdir 파일명



##### cd

디렉토리로 이동하는 명령어. 작성하는 도중에 tap을 누르면 자동 완성 지원, 두 번 누르면 앞 부분이 적어놓은 디렉토리와 일치하는 디렉토리 목록을 보여줌.



ex) cd .. 	//상위 디렉토리로 이동

cd "디렉토리 명"/"디렉토리 명" 	//현재 디렉토리에서 하위 디렉토리로 이동

cd /c/"디렉토리 명"/"디렉토리 명"	//절대 경로로 이동



##### pwd

현재 경로 출력



##### ls

현재 디렉토리에서 있는 디렉토리, 파일 목록 전부를 보여준다.

ex) ls		// 목록을 전부 보여주는 명령어

ls -a		/숨긴 파일도 전부 보여주는 명령어



##### vi "FileName"

파일 수정을 위해 vi편집기 실행

ex) vi -R "FileName"	//읽기 전용으로 실행, view "FileName"과 동일함.

1. 입력 모드 - 

   * I : Insert, 현재 커서의 위치에 글자를 삽입
   * i :  Insert, 커서가 있는 줄(line)의 맨 앞에 글자를 삽입
   * A : Append, 커서가 있는 줄(line)의 맨 뒤에 글자를 추가
   * a : Append, 현재 커서 위치의 다음 칸에 글자를 추가
   * O : Open line, 현재의 줄 앞에 새로운 줄을 삽입
   * o : Open line, 현재의 줄 다음에 새로운 줄을 삽입
   * insert등을 입력하여 사용

2. 명령모드 - vi실행기를 처음 실행 시켰을때 초기 상태, 글을 입력 시키는 것이 아닌 수정과 편집을 할 수 있는 상태, 위의 명령어를 통해 입력 모드에 들어 가거나 u를 통해 undo, 즉 복구를 할 수 있다.

3. 쿨론 모드 - :를 입력해 들어간다.

   * w : 저장

   * q : 종료

   * ! : 강제

     ex) : wq!



##### git init

git 저장소로, 지정 및 초기화.



##### git status

변경 사항 체크 



##### git add "FileName"

특정 파일 스테이징, 수정 되었던 파일들을 올리는 단계

git add * // 변경 되었던 모든 파일을 스테이징 한다.



##### git rm "FileName"

add되었던 파일을 다시 내리는 명령어



##### git commit -m "Explanation"

변경된 사항을 저장 하는 명령어, ""안에는 이것을 무슨 이름으로 지정할 것인지를 알려주는 과정.  나중에 문제가 생기면 이 부분으로 돌아올 수 있다.

[master dd3e513] First commit
 1 file changed, 21 insertions(+)
 create mode 100644 myfile.java

이런 식으로 파일 하나가 21 줄이 추가 되었음을 알려준다.

ex) git commit -am "Explanation"	//add와 commit을 한번해 해주는 명령어



##### git log

commit 된 내역을 볼 수 있는 명령어.





