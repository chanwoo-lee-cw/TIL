# error

1. `fatal: refusing to merge unrelated histories`

	- ```bash
		git pull [원격레포티토리명] [브렌치명] --allow-unrelated-histories
		```
	- 원인 : 이미 존재하는 두 깃 레포지토리를 합칠 때 사용된다. 
	  
	  - 즉, 서로 관련 기록이 없는 두 프로젝트를 병합할 때, 거부당하는 것을 허용해주는 명형어

