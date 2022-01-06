# git stash

작성일시: January 2, 2022 6:06 PM
최종 편집일시: January 2, 2022 7:05 PM



## 정의

> git의 커밋을 완료하지 않은 상태를 잠시 스택에 저장해서, git pull 저장한다.



## git Stash

아직 마무리 되지 않은 작업인 경우에, 커밋하고 git pull을 받는 것은 부담이 된다. 그럴 때, 아직 완료되지 않은 작업을 잠시 임시 보관소에 저장해두는 명령어

- git stash 명령어를 사용할 경우 수정된 파일 들만 저장한다.
- git이 추적하는 파일들만 저장한다.



## 스택에 저장

```bash
$ git stash
$ git stash save
$ git stash save [이름]
```



## 저장된 스택들 확인

```bash
$ git stash list
stash@{0}: On master: stash_test
stash@{1}: WIP on master: 9b104be git init
(END)
```

- stash는 스택 형태로 저장된다.
- `stash@{0}`은 가장 마지막에 저장됬다. 여기서 한번 더 저장한다면 `stash@{1}`로 저장된다.
- `stash@{0}`의 뒤의 설명은 `master branch`에서  `stash_test`라는 이름으로 저장된 stash
- `stash@{1}` 은 `master branch`에서  `9b104be git init`커밋 위에서 자동으로 저장된 stash



## 저장된 스택들 적용

```bash
$ git stash apply
$ git stash apply [이름]
$ git stash apply --index
$ git stash apply [이름] --index
```

- `git stash apply` 가장 최근에 저장된 stash를 불러온다
- `git stash apply [이름]` 해당 하는 stash를 가져온다. 이 이름은 `stash@{1}` 같은 이름을 말한다.
    - `$ git stash apply 0` 같이 번호만 지정해도 해당 stash를 불러온다
- `git stash apply --index` 저장된 stash의 stage 상태까지 복원한다.



## 저장된 stash 제거

```bash
$ git stash drop
$ git stash drop [이름]
$ git stash pop
```

- `git stash drop` 가장 위에 있는 stash를 제거한다.
- `git stash drop [이름]` 해당하는 스테시를 제거한다.
- `git stash pop` 가장 위에 있는 적용하는 동시에 제거한다.



## 적용된 stash 롤백

```bash
$ git stash show -p | git apply -R
$ git stash show -p [stash 이름] | git apply -R
```

- 가장 최근에 적용된 stash를 rollback한다.



## Reference

[https://gmlwjd9405.github.io/2018/05/18/git-stash.html](https://gmlwjd9405.github.io/2018/05/18/git-stash.html)