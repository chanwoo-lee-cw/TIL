# git 사용법

기본적인 명령어는 리눅스와 똑같다. 리눅와 같은 명령어가 아닌 경우에는 git이 앞에 붙는다.



#### git config --global user.name [ Your name]

#### git config - -global user.email “Your email address”

전역 사용자명/ 이메일 구성하는 것, 최초로 한번만 해주면 된다.

```Linux
git config --lobal user.name [me]
```

이메일 설정 잘 해줘야함. 이메일 틀리면 깃허브에 커밋을 하더라도 초록색으로 안 변함.



#### mkdir

파일 생성 하는 명령어 

  ``` Linux
$ mkdir 파일명
  ```





#### cd

디렉토리로 이동하는 명령어. 작성하는 도중에 tap을 누르면 자동 완성 지원, 두 번 누르면 앞 부분이 적어놓은 디렉토리와 일치하는 디렉토리 목록을 보여줌.

  ``` Linux
$ cd ..	//상위 디렉토리로 이동
$ cd [디렉토리 명]/[디렉토리 명]	//현재 디렉토리에서 하위 디렉토리로 이동
$ cd /c/[디렉토리 명]/[디렉토리 명]	//절대 경로로 이동
  ```





#### pwd

현재 경로 출력



#### ls

현재 디렉토리에서 있는 디렉토리, 파일 목록 전부를 보여준다.

  ``` Linux
$ ls		// 목록을 전부 보여주는 명령어
$ ls -a		/숨긴 파일도 전부 보여주는 명령어
  ```



#### vi [FileName]

파일 수정을 위해 vi편집기 실행

  ``` Linux
$ vi -R [FileName]	//읽기 전용으로 실행, view [FileName]과 동일함.
  ```

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



#### git init

git 저장소로, 지정 및 초기화.



#### git status

변경 사항 체크 



#### git add [FileName]

특정 파일 스테이징, 수정 되었던 파일들을 올리는 단계

  ``` Linux
$ git add * // 변경 되었던 모든 파일을 스테이징 한다.
  ```





#### git rm [FileName]

add되었던 파일을 다시 내리는 명령어



#### git commit -m [Explanation]

변경된 사항을 저장 하는 명령어, []안에는 이것을 무슨 이름으로 지정할 것인지를 알려주는 과정.  나중에 문제가 생기면 이 부분으로 돌아올 수 있다.

  ``` Linux
[master dd3e513] First commit
 1 file changed, 21 insertions(+)
 create mode 100644 myfile.java
  ```

이런 식으로 파일 하나가 21 줄이 추가 되었음을 알려준다.

  ``` Linux
$ git commit -am [Explanation]	//add와 commit을 한번해 해주는 명령어
  ```



#### git log

commit 된 내역을 볼 수 있는 명령어.



#### git remote add [저장소 명] [저장소 주소]

원격 저장소를 사용한다. 주로 깃 허브에 사용

  ``` Linux
$ git remote add origin https://github.com/[이름]/gitExample
  ```



#### git push [저장소 명] [브렌치 명]

원격 저장소에 현재 브렌치를 푸시한다.

  ``` Linux
$ git push learning master	
	//master에 있는 내용을 원격 저장소 learning으로 올린다.
  ```





#### git pull [저장소명] [브렌치 명]

다른 사람이 PR을 통해 코드를 업데이트 했더나 깃 허브를 통해 commit 했을때,

그 내용을 클라이언트로 내려 받는 명령어.

  ``` Linux
$ git pull learning master	//learning 에 있는 내용을 master에 내려받는다.
  ```



#### git clone [주소]

pull과 비슷하지만 클라이언트 상에 아무 것도 없을때, 서버의 프로젝트를 내려 받는 명령어. 저장소의 내용을 다운로드 받고 자동으로 init도 된다.

  ``` Linux
$ git clone https://github.com/Alphanewbie/Personal_learning
  ```



#### git diff

명령어를 옵션 없이 쓰면 Working Directory와 Index영역 사이의 변경사항을 표시합니다.
unstaged된 상태(Add가 안된 상태)에서 변경점이 확인 됩니다.
삭제 된 부분은 빨간 색으로 추가된 부분은 초록색으로 표시해서 보여준다.

  ``` Linux
$ git diff --cached
  	//Index영역과 Repository 영역을 비교하여 변경사항을 표시합니다.
  	//staged된 상태(Add가 된 상태)에서 변경점이 확인됩니다.
  	//commit 된 상태라면 아무것도 표시하지 않습니다.
$ git diff  <다른 branch명> `
	//로컬의 브랜치간 비교
$ git diff  ` 
	//로컬 브랜치와 원격 브랜치간의 비교
$ git diff  ` 
	//커밋끼리 비교
$ git diff <비교대상 1>..<비교대상 2> 
	//비교대상1과 비교대상2의 차이점 비교 .. 이 들어감
  ```





#### git checkout [항목명]

Modified 상태의 파일을 add하지 않고 Unmodified 상태로 되돌리는 명령어. 수정을 잘못했을 때 파일을 원 상태로 되돌리는 명령어

  ``` Linux
$ git check git_manual.md
  ```



#### git reset [옵션] [항목명]

Modified가 아닌 add까지 된 staged상태일 때 되돌리는 명령어. stage상태로부터 삭제 된다. commit한 이후에 되돌릴 때도 reset이 사용된다.

옵션 3가지

- --soft : 
  - index 보존(add한 상태, staged 상태), 워킹 디렉터리의 파일 보존. 즉 모두 보존.
- --mixed : 
  - index 보존(add한 상태, staged 상태), 워킹 디렉터리의 파일 보존. 즉 모두 보존.(기본 값)
- --hard : 
  - index 취소(add하기 전 상태, unstaged 상태), 워킹 디렉터리의 파일 삭제. 즉 모두 취소.

ex )

- ​	[방법 1] commit을 취소하고 해당 파일들은 staged 상태로 워킹 디렉터리에 보존

  ``` Linux
  $ git reset --soft HEAD^
  ```

- [방법 2] commit을 취소하고 해당 파일들은 unstaged 상태로 워킹 디렉터리에 보존

  
  ``` Linux
  $ git reset --mixed HEAD^ // 기본 옵션
  $ git reset HEAD^ // 위와 동일
  $ git reset HEAD~2 // 마지막 2개의 commit을 취소
  ```





-  [방법 3] commit을 취소하고 해당 파일들은 unstaged 상태로 워킹 디렉터리에서 삭제

   ``` Linux
$ git reset --hard HEAD^
   ```

- 원하는 commit으로 돌아가는 방법

  ```Lunux
  $ git reset --hard 61f21f3dac94ba8f0a322f34f8e8e2756bf5226a 
  	//로그는 git log 로 확인 가능
  ```



#### git revert

reset과 비슷하지만, 삭제 되기 이전의 내용을 새 commit으로 만들어 저장합니다.

보통 서버에 잘못된 커밋을 저장했을 때 사용함. 그래서 커밋을 덮어 씌우는 방식을 선택.



#### git GUI

위의 커밋간의 비교를 시각화 해서 보여준다.





## Branch

예를 들어 master이라는 브렌치에서 작업을 하다가 future 이라는 브렌치를 새로 만들면 새로운 브렌치를 만들기 직전까지의 내용은 같다. 그리고 만약 새로 만든 future 브렌치로 작업을 하는 도중에 master브렌치로 가면 수정 되기 이전의 내용으로 돌아간다.



#### 사용 예시

1. 혼자 작업 할 때 : 
   - 혼자 작업 하는 도중에 master 브렌치의 로그가 지나치게 많아 지는 걸  막거나, 홈페이지 유지 보수를 하는 도중에 여러가지 테스트가 필요하지만, 메인 서버에서는 테스트 하는 대신에 다른 브렌치를 생성해서 테스트하는 방법
2. 협업 할 때 :
   - 두 명이서 협업을 하는 와중에 github에서 코드를 올려 놓고 각자 다운로드 받아 코딩을 할 때, 다른 파일을 작업하면 상관 없지만, 다른 파일을 작업해서 commi해서 동시에 push하면 충돌이 일어나는 것을 방치 한다.



#### git branch

새로운 브렌치 만들기 그리고 브렌치 확인

```Linux
$ git branch [브렌치명] : 새로운 브렌치 만들기
$ git branch : 만들어진 브렌치 확인, 현재의 HEAD가 있는 브렌치는 *표시가 되었다.
```



#### git checkout [branchName]

다른 브렌치로 옮겨가기 위한 명령어

```Linux
$ git checkout study
```



#### git merge [branchName]

서로 다른 브렌치 둘을 병합. 

```Linux
student@M130116 MINGW64 ~/Personal_learning (master)
$ git merge future
	// 마스터 브렌치에서 future브렌치 병합
```

마스터 브렌치에 future의 작업 내용이 병합된다.

merge 결과에 Fast -forward라고 표시 되는 것은 future 브렌치가 master브렌치보다 한 커밋이 앞에 있기 때문에 commit도 당겨짐.

충돌이 나는 경우, 

각자 다른 브렌치가 같은 파일을 작업했던 경우.

병합 후에 **git reset HEAD~1**으로 브렌치를 뒤로 돌린 후 수정 하고 commit한 경우

만약 이런 식으로 충돌이 나게 된다면.

```java
<<<<<<< HEAD
  	for (int i = 0; i < ary.length; i++) {
		System.out.print(ary[i] + " ");
		ary[i] = ten += 10;
	}
=======
	for (int i = 0; i < ary.length; i++) {
		sum += ary[i] = (int) (Math.random() * 17) + 4;
	}
>>>>>>> future
```

이런식으로 윗 부분은 HEAD브렌치에서 수정된 부분

아랫 부부분은 future 브렌치에서 수정 되었던 부분, 이렇게 표시해서 보여준다.



#### git rebase

서로 다른 브렌치 둘을 병합. 

이건 충돌이 일어나면 경고를 해준다.

```Linux
student@M130116 MINGW64 ~/Personal_learning (master)
$ git rebase future
// 경고 메세지 
// 그 후에 수정이 끝난 후에 
$ git rebase --continue
// 병합을 재개해준다.
```



참고한 사이트 : 

https://www.zerocho.com/category/Git/post/580f633046f4fe09815b72a5

https://gmlwjd9405.github.io/2018/05/25/git-add-cancle.html


