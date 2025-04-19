# Git submodule

프로젝트를 만들다 보면 다른 프로젝트의 모듈을 가져와야할 때나, 혹은 프로젝트의 크기가 너무 커져서 프로젝트를 분리해야할 경우가 종종 있다.

이럴 때 프로젝트를 분리하기 위해 사용하는 것이 서브모듈이다.



## Submodule 추가하기

- 예시

```bash
git submodule add {submoude url}
```

```bash
git submodule add https://github.com/chanwoo-lee-cw/submoduleTest.git  
```

이런식으로 추가하면 새롭게 서브 모듈이 추가된 것을 알 수 있다.





![스크린샷 2025-04-14 오전 3.04.38](image/스크린샷 2025-04-14 오전 3.04.38.png)

서브모듈의 이름이 `submoduledTest` 라서 해당 서브 모듈 디렉토리를 그대로 가져오고, 해당 서브 모듈을 제어할 `.gitmodules` 해당 서브모듈 파일을 확인할 수 있다.



`.gitmodules` 을 들어가면 이렇게 생겼다.

```
[submodule "submoduleTest"]
	path = submoduleTest
	url = https://github.com/chanwoo-lee-cw/submoduleTest.git
```



해당 서브 모듈의 이름은 submoduleTest,
루트 디렉토리의 `submoduleTest` 경로 안에서 제어 되고
`https://github.com/chanwoo-lee-cw/submoduleTest.git` 의 URL에 있는 서브 모듈을 이용한다.



## 메인 Git을 처음 Pull 했을 때

처음이라면 Git의 메인 파일이 비어있고, 가져와야 하는걸로 알고 있고, 저번회사에서 Submodule을 썼을때는 정말로 그랬지만,

이번에 이 글을 작성하며 직접 해봤을 때는 `git submodule add` 만 해줘도 알아서 프로젝트를 가져왔다.

하지만, 만약 없다면 직접 초기화 시킨다. 

```bash
# 서브모듈 정보를 기반으로 로컬 환경설정 파일을 만들어준다.
git submodule init

# 서브모듈의 리모트 저장소에서 데이터를 가져오고 Checkout을 한다.
git submodule update
```



## 서브 모듈의 커밋

![스크린샷 2025-04-19 오후 10.59.22](image/스크린샷 2025-04-19 오후 10.59.22.png)

장점이자 단점, 서브모듈은 정말로 별개의 프로젝트라서 별개의 커밋으로 관리된다.

메인 브렌치만 커밋한다고 해서 서브모듈이 같이 커밋되지 않는다.

서브 모듈도 같이 커밋을 해줘야 버전 관리를 하기 좋다.



## 서브 모듈 업데이트

해당 서브 모듈을 다른 프로젝트에서 커밋했을 때 자동으로 반영되지 않는다.

1. 해당 서브 모듈에 들어가서 직업 pull

```bash
# 루트 디렉토리에서 시작

# 서브 모듈에 들어간다.
cd submoduleTest

# 원하는 브렌치의 pull
g pull origin master
```

2. `git pull --recurse-submodules`

```bash
git pull --recurse-submodules
```

서브 모듈이 업데이트 되면 이런식으로 서브모듈 디렉토리를 메인 브렌치에서 커밋 할거냐는게 나온다.

![스크린샷 2025-04-19 오후 11.39.43](image/스크린샷 2025-04-19 오후 11.39.43.png)

![스크린샷 2025-04-19 오후 11.44.24](image/스크린샷 2025-04-19 오후 11.44.24.png)

이건 메인 브렌치에서 어떤 서브 모듈의 어떤 커밋을 바라볼냐는 의미다. 즉, 커밋을 직접 확인해봐도 별 정보는 없다.

기본적으로는 하는 것이 추천되지만, 만약 해당 프로젝트를 여러 명이 쓰기 시작하면서 관리하기 시작하면 이 부분이 매일 충돌이 나고, Object인 만큼 병합 과정에서 비교해보는 것도 불가능하다.

그래서 보통 해당 부분은 커밋을 하지 않고, 배포 할 때 병합을 하는 편이 좋다.



## 서브 모듈의 브렌치

프로젝트는 1개지만 보통 실무를 하다보면 여러 프로젝트로 나뉘게 된다.

운영과 일치시킬 master, 개발을 위한 develop, QA팀을 위한 qa, 배포 관리를 위한 ticket



`.gitmodules` 을 들어가서 branch 항목을 추가한다.

```
[submodule "submoduleTest"]
	path = submoduleTest
	url = https://github.com/chanwoo-lee-cw/submoduleTest.git
	branch = master		// 원하는 브렌치 추가
```

똑같은 방법으로 `master`, `develop`, `qa` 브렌치 마다 각자 따로 추가하면 된다.





## 장단점

### 장점

1. 여러 프로젝트들끼리 공유되는 코드들의 재사용성 향상
2. 독립적인 버전 관리가 가능해진다. 만약 특정 프로제트가 특정 버전만 바라보게 하고 싶을 때는 굉장히 유용하다.
3. 프로젝트별 일관성 유지, 이전 회사에서는 사용자의 요청을 처리하는 JOB이라는 걸 사용했는데 해당 잡에 따라 어떤 서비스에서 어떤 프로세스를 할 지 정해지는 시스템이였다. 그렇다보니 JOB의 포멧의 변경이 있으면 모든 프로젝트가 포멧의 변경을 공유해야했는데, 이럴때 굉장히 유용

### 단점

1. 버전 불일치 및 동기화 문제, 정말로 별개의 프로젝트이기 때문에 메인 프로젝트, 서브모듈 둘 다 업데이트 했는데 메인 프로젝트만 Push 하면 에러 발생
2. 병합 충돌 가능성, 여러개의 프로젝트 여러명이 동시에 작업하는 것이다보니 병합 충돌이 굉장히 빈번하다



# 참고

https://git-scm.com/book/en/v2/Git-Tools-Submodules

https://data-engineer-tech.tistory.com/20

