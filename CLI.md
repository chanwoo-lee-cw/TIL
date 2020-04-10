# CLI

## 명령어

- `ls` : 현재 디렉토리 내부의 파일 & 디렉토리를 보여줌(**list**)
  - `ls -a` : 숨김 디렉토리 또는 파일 보여줌(**a**ll)
  - `ls -la` : 숨긴 디렉토리 또는 파일 포함한 모든 것의 상세 정보까지 보여줌
  
- `pwd` : 현재 위치를 출력함(**p**rint **w**orking **d**irectory) - 절대(최상단)

- `mkdir [디렉토리 명]` : 디렉토리(폴더)를 생성(**m**a**k**e **dir**ectoy)

- `cd [경로]` : 디렉토리를 변경/이동(**c**hange **d**irectory)

- `cd ..` : 상위 디렉토리로 이동(..)

- `cd .` : 현재 디렉토리로 이동(현재 폴더를 나타내는 것)

- ~~`cd /` : 루트 디렉토리로 이동(**/**)~~

  [^루트 디렉토리]: 리눅스 시스템 상에서의 루트 디렉토리, c:\가 아니다

- `cd ~` : 홈 디렉토리로 이동

- `rm [파일명]` : 파일을 삭제함(**r**e**m**ove)

  - `rm -r [디렉토리 명]` :  디렉토리를 삭제(**r**ecursion)
  - `rm -rf [디렉토리 명]` : 

- `code` : VScode를 연다 

  - `code . ` : 현재 디렉토리를 기준으로 VScode를 연다.



# Git

- `git init` : 현재 디렉토리를 기준으로
- `git status` : 무엇을 staging 할 수 있는지, 무엇을 staging 되어 있는지 상태 확인
- `git add [파일명/폴더명]` :  staging area에 파일을 추가(**add**)
- `git rm --cached [파일명]` : staging area에 있는 파일을 다시 내린다
  - `git rm --cached -r [디렉토리명]` : staging area에 있는 디렉토리를 다시 내린다
  - `git restore --cached [파일명]` : staging area에 있는 파일을 다시 내린다.(rm의 다른 표현이다)
- `git reset [파일명]` : 똑같이 staging area에 있는 파일을 다시 내리긴 한데, 복원에 가깝다. staging area에서 내리기 위해서만 쓰기엔 위험한 명령어

- `git commit -m "커밋메세지"` : 현재 상태를 저장한다.

  - 현재 명령어로 쓴다.

    ex) git commit -m "Modify README.md"

- `git log`

  - `git log --oneline` : 깃 로그를 한줄로 보여준다.
  - `git log --oneline -1` : 마지막 커밋을 한줄로 보여준다.



`-`  2개는 보통 static 한 명령어를 쓸 때 쓴다. 보통 풀네밍

`-` 한개일 때는 보통 약어일 경우가 많다. 보통 약어

물론, 규칙이 그렇게 정해져 있는게 많다.