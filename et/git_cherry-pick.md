# git cherry-pick



git 을 이용해 코드 관리를 하다보면 다른 브렌치의 일부분을 가져와서 커밋을 해야하는 경우가 종종 생긴다.

특히 내가 다니고 있는 회사 같은 경우는 각 팀마다 각자 테스트 서버를 돌리고 있는데, 그렇다보니 보통 팀의 테스트 서버의 브렌치를 따서 진행하지만, 가끔 팀 각자의 진행도가 달라서 내가 만든 코드의 일부만 적용해야 되는 경우가 생긴다.



## 본론

`git cherry-pick` 는 다른 브렌치에 있는 커밋을 선택적으로 내 브렌치에 적용 시키는 명령어이다.

```bash
git cherry-pick <commit-hash-code>
```

- 사용 예시

```bash
git cherry-pick 4f555a7
```

- 여러 개의 커밋을 한꺼번에 cherry-pick 하고 싶은 경우

```bash
git cherry-pick 4f555a7 fc25e99
```



- 예시

![스크린샷 2022-04-06 오후 9.55.56.png](https://ritualforrain.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fcfe98162-48b4-4ef5-8eaf-f9f5b94d4068%2F%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-04-06_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_9.55.56.png?table=block&id=9b99a3ab-4766-4498-88db-a02a73158ab1&spaceId=3534bf20-7a07-42e1-b6b0-c55e873160bb&width=2000&userId=&cache=v2)

그림처럼 master 브렌치와 branch2 브렌치가 2개가 있다. 이런 경우에 branch2의 b2-1 브렌치만 merge하고 싶다고 생각해보자.

이런 경우에

```bash
git cherry-pick 4f555a7
```

명령어를 사용했을 경우 하단과 같은 경우가 된다.

![스크린샷 2022-04-06 오후 9.56.53.png](https://ritualforrain.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F581fcf5b-b19f-4438-89cf-24347904fd31%2F%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-04-06_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_9.56.53.png?table=block&id=d03284d8-0bf9-4c71-8ae8-91a5964854b9&spaceId=3534bf20-7a07-42e1-b6b0-c55e873160bb&width=2000&userId=&cache=v2)

![스크린샷 2022-04-06 오후 10.07.23.png](https://ritualforrain.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F8d386422-4f29-406e-97f9-956e42d1f94a%2F%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-04-06_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_10.07.23.png?table=block&id=9095da3a-1fc9-4e85-a15b-25473eb124f5&spaceId=3534bf20-7a07-42e1-b6b0-c55e873160bb&width=2000&userId=&cache=v2)

완료 후 브렌치의 모양은 위와 같이 된다.



## conflict가 발생하는 경우

보통 2가지 방법으로 해결한다.

### -abort

아예 cherry-pick를 중단하는 방법이다. cherry-pick를 중단하고 cherry-pick시작 이전의 상태로 되돌아간다.

```bash
git cherry-pick -abort
```

### -continue

conflict를 해결하고 이어서 하는 방법이다.

conflict를 해결하고 `git add`를 통해 스테이징한다. **단, commit은 할 필요 없다.**

그 이후에 `git cherry-pick -continue` 를 입력하면 다음 단계부터 다시 진행하기 시작한다.

```bash
git cherry-pick -continue
```

