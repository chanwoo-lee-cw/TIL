# R

#### 대입 연산자

```R
# 1~10 까지 벡터로 만들어서 v1에 넣는다
v1 <- 1:10

# v1에 저장된 모든 벡터에 100을 더한다.
v1 <- v1 + 100

# 새로 벡터를 만들어서 넣는다.
v1 <- c(4, 1, 8, 6, 10)
```



# rep

반복하는 함수

1. 첫 번째 매개변수 :  범위가 값
2. 두 번째 매개변수 :  반복 횟수
   1. times : 모든 결과물 반목
   2. each : 각각 반복한다.

```R
?rep
# 1을 100개 넣은 벡터를 생성
rep(1, 100)
# 1~3을 5번 반복한 벡터 생성
rep(1:3, 5)
# 1~3 을 5번 반복
rep(1:3, times=5)
# 각각 항목을 5번 반복 [1,1,1,1,1,2,2,2,2,2,3,3,3,3]
rep(1:3, each=5)
```



#### 상용값

```R
#대문자 알파벳, 소문자 알파벳, 달, 요일, 파이값이다.,
LETTERS
letters
month.name
month.abb
pi
```



#### 벡터 함수

```R
x <- c(10,2,7,4,15)
# 벡터의 타입이 뭔지 알려준다. 이 경우에는 numeric
class(x)
# 벡터를 뒤집는다
rev(x)
# 벡터의 최소값과 최대값을 알려준다.
range(x)
# 정렬 decreasing 옵션을 키면 감소한다.
sort(x)
sort(x, decreasing = TRUE)
sort(x, decreasing = T)
# 각각 바꿔야 하는 자리의 값, 예를 들면 2번째로 작은 수고 4번째레 있음.,
order(x)
```



#### 최대, 최소, 합계, 평균, 그리고 요약

```R
max(x);min(x);mean(x);sum(x)
# Min. 1st Qu.  Median    Mean 3rd Qu.    Max. 를 요약해 보여준다.
# 원소마다 이름을 붙혀서 보여준다.
summary(x)
```



#### 원하는 항목만 추출

```R
x[c(2,4)] # x[2], x[4]
x[c(F,T,F,T,F)] 
x[c(T,F)] 
# 즉 5보다 큰 부분은 True 추출
x > 5
# Ture 인 부분의 항목 추출
x[x > 5] 
# & 연산은 각각 원소마다 전부 비교하고, &&은 첫번째 항목만 비교해서 맞으면 전부 뽑음
x[x > 5 & x < 15]
x[x > 5 && x < 15]
x[x > 5 | x < 15]
```



#### names

```R
names(x)
# 네이밍을 붙힌다.
names(x) <- LETTERS[1:4]
names(x) <- NULL
# 인덱스로 뽑을 수도 있고, 붙어진 이름으로 뽑을 수도 있다.
# 물론 스트링형으로 넣어야 한다. X[B]를 하면 객체 B를 찾는다.
x[2];x["B"]
```



##### which

```R
# rainfall이 100보다 분 부분의 인덱스 반환
which(rainfall > 100)

# 각각 month 라는 네이밍을 붙혀서 반환한다.
# [1] "May"       "June"      "July"      "August"    "September"
month.name[which(rainfall > 100)]
# 각각 달의 줄임 말을 출력한다
month.abb[which(rainfall > 100)]
# 비온 날이 최대인 날의 이름을 출력한다
month.korname[which.max(rainfall)]
# 비 온날이 최소인 날을 출력한다.
month.korname[which.min(rainfall)]
# 만약 이름을 붙혀 놨으면 최대인 날의 이름을 출력한다
names(which.min(rainfall))
```



##### 문자열 결합

```R
#I'm Duli !!
paste("I'm","Duli","!!")
#I'mDuli!!
paste("I'm","Duli","!!", sep="")
#I'mDuli!!
paste0("I'm","Duli","!!")


fruit <- c("Apple", "Banana", "Strawberry")
food <- c("Pie","Juice", "Cake")

# 각각 항목을 1:1로 결합한다.
# "ApplePie"       "BananaJuice"    "StrawberryCake"
paste(fruit, food, sep="")
paste(fruit, food, sep=":::")

# 모든 항목을 일괄적으로 이어 붙힌다.
#"ApplePie-BananaJuice-StrawberryCake"
paste(fruit, food, sep="", collapse="-")
paste(fruit, food, sep="", collapse="")
```





### 배열

```R
# 행이 2인 배열을 만든다.
x1 <-matrix(1:8, nrow = 2)
# 모든 항목이 3을 곱한다
x1<-x1*3

#3,3엔 1이 들어가는데 갯수가 부족해서 임의로 첫번째 숫자인 1을 집어 넣는다.
x2 <-matrix(1:8, nrow =3)
# 행과 열을 반환한다
# 2 4
dim(x1)
```



```R
vec1 <- c(1,2,3)
vec2 <- c(4,5,6)
vec3 <- c(7,8,9)
# rbind 행 단위로 연결한다. cbind 열 단위로 연결한다.
#     [,1] [,2] [,3]
#vec1    1    2    3
#vec2    4    5    6
#vec3    7    8    9
mat1 <- rbind(vec1,vec2,vec3);
#     vec1 vec2 vec3
#[1,]    1    4    7
#[2,]    2    5    8
#[3,]    3    6    9
mat2 <- cbind(vec1,vec2,vec3);
# 아래에 붙힌다.
#     [,1] [,2] [,3]
#vec1    1    2    3
#vec2    4    5    6
#vec3    7    8    9
mat1 <- rbind(mat1,vec1);

# 1행 1열 매트릭스가 필요 없다면 벡터로 추출한다.
mat1[1,1]
#똑같이 뽑는데도 다른 이유는 이름이 있다면 이름도 같이 뽑는다.
mat1[2,];mat1[,3]
# 행 혈리 필요 없어도 drop 속성으로 강제로 매트릭스 구조 유지
mat1[1,1,drop=F]
mat1[2,,drop=F];mat1[,3,drop=F]
```



#### name

```R
# 행과 열의 이름 벡터를 삭제한다.
rownames(mat1) <- NULL
colnames(mat2) <- NULL
mat1;mat2
rownames(mat1) <- c("row1","row2","row3")
colnames(mat1) <- c("col1","col2","col3")

rowSums(x2); colSums(x2)
```



