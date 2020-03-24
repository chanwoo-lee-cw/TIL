# matrix 실습
x1 <-matrix(1:8, nrow = 2)
x1
x1<-x1*3
x1

sum(x1); min(x1);max(x1);mean(x1)

#3,3엔 1이 들어가는데 갯수가 부족해서 임의로 첫번째 숫자인 1을 집어 넣는다.
x2 <-matrix(1:8, nrow =3)
x2

(chars <- letters[1:10])

mat1 <-matrix(chars)
mat1; dim(mat1)
matrix(chars, nrow=1)
# 행이 5열
matrix(chars, nrow=5)
# 행이 5열이고, 가로부터 채운다.
matrix(chars, nrow=5, byrow=T)
matrix(chars, ncol=5)
matrix(chars, ncol=5, byrow=T)
# 행이 3열 렬이 5열
matrix(chars, nrow=3, ncol=5)
matrix(chars, nrow=3)


vec1 <- c(1,2,3)
vec2 <- c(4,5,6)
vec3 <- c(7,8,9)
# rbind 행 단위로 연결한다. cbind 열 단위로 연결한다.
mat1 <- rbind(vec1,vec2,vec3); mat1
mat2 <- cbind(vec1,vec2,vec3); mat2
# 1행 1열 매트릭스가 필요 없다면 벡터로 추출한다.
mat1[1,1]
#똑같이 뽑는데도 다른 이유는 이름이 있다면 이름도 같이 뽑는다.
mat1[2,];mat1[,3]
# 행 혈리 필요 없어도 drop 속성으로 강제로 매트릭스 구조 유지
mat1[1,1,drop=F]
mat1[2,,drop=F];mat1[,3,drop=F]

# 행과 열의 이름 벡터를 삭제한다.
rownames(mat1) <- NULL
colnames(mat2) <- NULL
mat1;mat2
rownames(mat1) <- c("row1","row2","row3")
colnames(mat1) <- c("col1","col2","col3")
mat1
ls()
mean(x2)
sum(x2)
min(x2)
max(x2)
summary(x2)

mean(x2[2,])
sum(x2[2,])
rowSums(x2); colSums(x2)

# 1.x2가지고 행단위으로 sum 실행한다. = rowSums와 같다.
# apply(매트릭스, 1=행 or 2= 열, sum 을 한다.)
apply(x2, 1, sum); apply(x2, 2, sum)  
?apply
apply(x2, 1, max)
apply(x2, 1, min)
apply(x2, 1, mean)

apply(x2, 2, max)
apply(x2, 2, min)
apply(x2, 2, mean)

#array 싱습
# 2행 3열 5층 생성
a1<- array(1:30, dim=c(2,3,5))
a1

# 4층의 1행 3열 값 추출
a1[1,3,4]
# 모든 3층의 값을 추출
a1[,,3]
# 모든 층의 2열의 값을 추출
a1[,2,]
# 모든 층의 1행 값을 추출
a1[1,,]
a1[,2,]

# 세션능 하나 열게됨. 현재의 디렉토리를 열게 된다.
getwd()

# factor 실습

score <- c(1,3,2,4,2,1,3,5,1,3,3,3)
# 객체의 종류를 알려주는 함수
# 다른건 다 데이터 프레임 배열 그런거 알려주는데 오로지 벡터만 타입을 알려줌.
class(score)
summary(score)

#WEb웹이 정해져 있는 벡터
#저장할 수 있는 범위가 지정되어 있다.
f_score <- factor(score)
class(f_score)
f_score
# 데이터의 빈도수를 알려준다.
summary(f_score)
# 레벨들만 출력한다.
levels(f_score)

#산재도 그려준다.
plot(score)
plot(f_score)


data1 <- c("월","수","토","월",
           "목","화")
data1
class(data1)
# 전체 갯수, 클래스 타입, 모드에 대한 결과
summary(data1)
day1 <- factor(data1)
day1
class(day1)
#각 레벨별 데이터 갯수 출력
summary(day1)
levels(day1)

# 
week.korabbname <- c("일", "월", "화",
                     "수", "목", "금", "토")
# 레벨을 지정히재누다.
day2 <- factor(data1, 
               levels=week.korabbname)
day2
summary(day2)
levels(day2)


btype <- factor(
  c("A", "O", "AB", "B", "O", "A"), 
  levels=c("A", "B", "O"))
btype
summary(btype)
levels(btype)

gender <- factor(c(1,2,1,1,1,2,1,2), 
                 levels=c(1,2), 
                 labels=c("남성", "여성"))
gender
summary(gender)
levels(gender)

# 내장 데이터셋
# 현재 내장하고 있는 데이터 셋을 보여준다.
data()
# 앞에서부터 6개 뒤에서 부터 6개, 뒤에서부터 6개ㄱ
iris; head(iris);tail(iris) 
View(iris)
# structure obs의 대이터의 갯수 variables 속성의 갯수
# 데이터 프레임에서는 필수 함수

str(iris)

library()

#Dataframe 실습
no <- c(1,2,3,4)
name <- c('Apple','Banana','Peach','Berry')
qty <- c(5,2,7,9)
price <- c(500,200,200,500)
fruit <- data.frame(no, name, qty, price)
str(fruit)
View(fruit)

fruit[1,]
fruit[-1,]
fruit[,2]
fruit[,3] # fruit[,3, drop=F]
fruit[, c(3,4)]
fruit[3,2]
fruit[3,1]

fruit[,3]
# 자바의. 같은 연산자가 R에서는 $
fruit$qty
fruit[[3]]
# 나머지는 벡터 구조로 나온다.
fruit[3]  # 데이터프레임 형식 유지

# 벡터로 qty을 출력한다
str(fruit$qty)
# 데이터 프레임 형태로 출력한다.
str(fruit[3])

# dataframe exam1
english <- c(90, 80, 60, 70)
math <- c(50, 60, 100, 20)
classnum <- c(1,1,2,2)
df_midterm <- data.frame(
  english, math, classnum)
df_midterm
str(df_midterm)
# 자동적으로 네니밍이 붙는다. 잉글리퀴 매치, 등등 과 1,2,3,4
colnames(df_midterm)
rownames(df_midterm)
names(df_midterm)
# 각각의 열의 값
mean(df_midterm$english)
mean(df_midterm$math)

#이렇게 해도 만들어 지긴 하는데, 들어가는 칼럼명ㅇ.  c.90..80..60..7같은 식으로 들어간다,
df_midterm2 <- data.frame(
  c(90, 80, 60, 70), 
  c(50, 60, 100, 20), 
  c(1,1,2,2))
colnames(df_midterm2)
rownames(df_midterm2)
names(df_midterm2)
df_midterm2
df_midterm2 <- data.frame(
  영어=c(90, 80, 60, 70), 
  수학=c(50, 60, 100, 20), 
  클래스=c(1,1,2,2))
df_midterm2
df_midterm2$영어;

# 2개의 갯수가 만 맞는 다는 에러이다.
df <- data.frame(var1=c(4,3,8), 
                 var2=c(2,6)) # 오류
#이건 된다.
df <- data.frame(var1=c(4,3,8), 
                 var2=c(2,6,1))
str(df)
# var1과 var2의 칼럼 값을 더해서 sum 이라는 칼럼을 프레임에 추가한다.
df$var_sum <- df$var1 + df$var2
df$var_mean <- df$var_sum/2
# 3항 연산자 같은 기능능
df$result <- ifelse(df$var1>df$var2, 
                    "var1이 크다", "var1이 작다")

getwd() # setwd('xxx')

#csv파일열기
score <- read.csv("data/score.csv")
score
str(score)
score$sum <- 
  score$math+score$english+score$science
score$result <- ifelse(score$sum >= 200, 
                       "pass", "fail")
score
# 팩터로 바꿔준 다음에 해야 갯수 세준다.
summary(score$result)
#무조건 갯수를 산다. 팩터가 됬던 아니던 무조건 세준다.
table(score$result)
summary(factor(score$result))
score$result = factor(score$result) 
str(score)
summary(score)
#각각 칼럼에 맞춰 변환한다.
score$id = as.character(score$id)
score$class = factor(score$class)

score$grade<-ifelse(score$sum >= 230,"A",
                    ifelse(score$sum >= 215,"B", 
                           ifelse(score$sum >=200,"C","D")))
score

# order() 와 sort()
v <- c(10,3,7,4,8)
sort(v)
order(v)

emp <- read.csv(file.choose(),
                stringsAsFactors = F)
emp
str(emp)

# emp에서 직원 이름
emp$ename
emp[,2]
emp[,"ename"] 
emp[,2, drop=FALSE] 
emp[,"ename",drop=F] 
emp[2]
emp["ename"] 

# emp에서 직원이름, 잡, 샐러리
emp[,c(2,3,6)]
emp[,c("ename","job","sal")]
subset(emp,select = c(ename, job, sal))
?subset
# emp에서 1,2,3 행 들만
emp[1:3,]
emp[c(1,2,3),]
# 헤드 함수 데이터 셋만 보낼 수 있다.
?head
head(emp)
# 1개만 나온다.
head(emp, n=1)
# ename이 "KING"인 직원의 모든 정보
emp[9,] 
# 이름이 king  밸루만 true를 반환한다.
emp$ename=="KING"
emp[c(F,F,F,F,F,F,F,F,T,F,F,F,
      F,F,F,F,F,F,F,F),]
emp[emp$ename=="KING",]
subset(emp,subset=emp$ename=="KING")
subset(emp,emp$ename=="KING") 

emp[emp$ename=="KING",] 

# 커미션을 받는 직원들의 모든 정보 출력
# comm 이 na항을 제외하고 뽑는다.
emp[!is.na(emp$comm),]
subset(emp,!is.na(emp$comm)) 

# select ename,sal from emp where sal>=2000
subset(emp, select=c("ename","sal"), 
       subset= emp$sal>= 2000)
subset(emp, emp$sal>= 2000, 
       c("ename","sal"))
emp[emp$sal>=2000,c("ename","sal")]

# select ename,sal from emp where sal between 2000 and 3000
# &는 하나짜리 싸야 전부 비교한다.
subset(emp, select=c("ename","sal"), subset=(sal>=2000 & sal<=3000))
emp[emp$sal>=2000 & emp$sal <=3000, c("ename","sal")]


# 리스트
y <- c(0,25,50,75,100)
z <- c(50, 50, 50, 50,50)
y == z
y != z
y > z
y < z
y >= z
y <= z
y == 50 # c(50, 50, 50, 50, 50)
y > 50

num1 <- 11 # c(11)
num2 <- 3  # c(3)
num1 / num2
num1 %% num2
num1 %/% num2

