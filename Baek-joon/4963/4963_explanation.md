## 백준 4936번 풀이

https://www.acmicpc.net/problem/4936

### 문제

*정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다. 섬의 개수를 세는 프로그램을 작성하시오.*

![img](https://www.acmicpc.net/upload/images/island.png)

*한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다.* 

*두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다. 지도는 바다로 둘러쌓여 있으며, 지도 밖으로 나갈 수 없다.*

### 입력

*입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. w와 h는 50보다 작거나 같은 양의 정수이다.*

*둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다이다.*

*입력의 마지막 줄에는 0이 두 개 주어진다.*

### 출력

*각 테스트 케이스에 대해서, 섬의 개수를 출력한다.*

***



### 풀이

그래프 문제이다.

일단 접근은 가장 왼쪽 위쪽인 (0,0) 부터 차례대로 다른 모든 섬을 접근 하는 것을 생각했다.

그래서 만약 접근한 matrix가 1이라면 섬이라는 뜻이고 인접한 모든 섬을 전부 0으로 바꿔줘야한다.

```java
for (int i = 0; i < h; i++) {
	for (int j = 0; j < w; j++) {
		if (matrix[i][j] == 1) {
			cnt++;
		search(matrix, i, j, w, h);
		}
	}
}
```

일단 섬은 1개가 세졌고 이제 인접한 섬들을 찾기 위해서 현재의 위치와  섬의 넓이를 전달했다.



그럼 이미 센 섬들을 어떻게 해야 하느냐면, 일단 센 섬들은 1대신 0을 채워서 없다고 생각하면 된다.

```java
if(matrix[i][j]==1) 
	matrix[i][j]=0;
```

인접한 섬들의 각각 i,j값은 이렇다.

<img src="C:\Users\student\Personal_learning\Bae-joon\4963\그림1.png" alt="그림1" style="zoom:75%;" />

그러니까 재귀로 각각 포인트를 찾아준다.

```java
search(matrix, i-1, j-1, w, h);
search(matrix, i, j-1, w, h);
search(matrix, i-1, j, w, h);
search(matrix, i+1, j+1, w, h);
search(matrix, i, j+1, w, h);
search(matrix, i+1, j, w, h);
search(matrix, i-1, j+1, w, h);
search(matrix, i+1, j-1, w, h);
```

하지만 이렇게 된다면 i,j가 범위 바깥으로 넘어갈 가능성이 있다.

만약 범위 바깥으로 넘어간다면 그것을 확인해줄 필요성이 있다.

search 함수의 최종적인 모습은 이런 형태가 된다.

```java
	private static void search(int[][] matrix, int i, int j, int w, int h) {
		if (0 <= i && i < h && 0 <= j && j < w) {
			if(matrix[i][j]==1) {
				matrix[i][j]=0;
				search(matrix, i-1, j-1, w, h);
				search(matrix, i, j-1, w, h);
				search(matrix, i-1, j, w, h);
				search(matrix, i+1, j+1, w, h);
				search(matrix, i, j+1, w, h);
				search(matrix, i+1, j, w, h);
				search(matrix, i-1, j+1, w, h);
				search(matrix, i+1, j-1, w, h);
			}
			else return;
		} else
			return;
	}
```





### 전체 코드

```java
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		int w, h;

		Scanner in = new Scanner(System.in);

		String[] line;

		while (true) {
			line = in.nextLine().split(" ");

			w = Integer.parseInt(line[0]);
			h = Integer.parseInt(line[1]);
			int matrix[][] = new int[h][w];
			if (w == 0 && h == 0)
				break;
			for (int i = 0; i < h; i++) {
				line = in.nextLine().split(" ");
				for (int j = 0; j < w; j++) {
					matrix[i][j] = Integer.parseInt(line[j]);
				}
			}
			int cnt = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (matrix[i][j] == 1) {
						cnt++;
						search(matrix, i, j, w, h);
					}
				}
			}
			System.out.println(cnt);
			cnt=0;
		}
	}

	private static void search(int[][] matrix, int i, int j, int w, int h) {
		if (0 <= i && i < h && 0 <= j && j < w) {
			if(matrix[i][j]==1) {
				matrix[i][j]=0;
				search(matrix, i-1, j-1, w, h);
				search(matrix, i, j-1, w, h);
				search(matrix, i-1, j, w, h);
				search(matrix, i+1, j+1, w, h);
				search(matrix, i, j+1, w, h);
				search(matrix, i+1, j, w, h);
				search(matrix, i-1, j+1, w, h);
				search(matrix, i+1, j-1, w, h);
			}
			else return;
		} else
			return;
	}
}
```

