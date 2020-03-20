v1 <- 1:10
v2 < - v1 + 100
print(v2)

v1 <- c(4, 1, 8, 6, 10)
print(v1)
# 새로 박아 넣으면 이전의 값은 삭제 된다.

?rep
rep(1, 100)
rep(1:3, 5)
rep(1:3, times=5)
rep(1:3, each=5)
# rep(1, 100) 1을 100번 반복한다.
# 키워드 파라미터를 줄 수 있다. 전달 되어야하는 매개 변수를 직접 지정.
# times 모든 항몽의 결과를을 5번씩 반복한다. each 각각 반복해라./

LETTERS
letters
month.name
month.abb
pi
#대문자 알파벳, 소문자 알파벳, 달, 요일, 파이값이다.,

LETTERS;letters;month.name;month.abb;pi

LETTERS[1]; LETTERS[c(3,4,5)];
#앞의 값이 작으면역순으로 출력한다.
LETTERS[3:5]; LETTERS[5:3];
# -는 그 항목을 빼고 출력한다.
LETTERS[-1]; LETTERS[c(-2,-4)]

length(LETTERS)
length(month.name)
length(pi)


x <- c(10,2,7,4,15)
x
print(x)
class(x)
rev(x)
range(x)
sort(x)
sort(x, decreasing = TRUE)
sort(x, decreasing = T)
#x <- sort(x)
# 각각 바꿔야 하는 자리의 값, 예를 들면 2번째로 작은 수고 4번째레 있음.,
order(x)



x[3] <- 20
x
x + 1
x <- x + 1
max(x);min(x);mean(x);sum(x)
# Min. 1st Qu.  Median    Mean 3rd Qu.    Max. 를 요약해 보여준다.
# 원소마다 이름을 붙혀서 보여준다.
summary(x)

x[c(2,4)] # x[2], x[4]
# ture 인 곳의 값을 출력한다.
x[c(F,T,F,T,F)] 
x[c(T,F)] 
x > 5
x[x > 5] 
# & 연산은 각각 원소마다 전부 비교하고, &&은 첫번째 항목만 비교해서 맞으면 전부 뽑음
x[x > 5 & x < 15]
x[x > 5 && x < 15]
x[x > 5 | x < 15]

names(x)
# 네이밍을 붙힌다.
names(x) <- LETTERS[1:4]
names(x) <- NULL
# 인덱스로 뽑을 수도 있고, 붙어진 이름으로 뽑을 수도 있다.
# 물론 스트링형으로 넣어야 한다. X[B]를 하면 객체 B를 찾는다.
x[2];x["B"]


# &, &&
c(T, T, F, F) & c(T, F, T, F)
c(T, T, F, F) | c(T, F, T, F)
c(T, T, F, F) && c(T, F, T, F)
c(T, T, F, F) || c(T, F, T, F)


ls()
rm(x)
x
class(x)
rainfall <- c(21.6, 23.6, 45.8, 77.0, 
              102.2, 133.3,327.9, 348.0, 
              137.6, 49.3, 53.0, 24.9)
rainfall > 100
rainfall[rainfall > 100]
# which 가  FALSE FALSE FALSE FALSE  TRUE  TRUE  TRUE  TRUE  TRUE FALSE FALSE FALSE
#형식으로 전달 되고 각각 항목의 인덱스를 출력
which(rainfall > 100)
# 나온 인덱스를 네임스에서 바로 나온다.
month.name[which(rainfall > 100)]
month.abb[which(rainfall > 100)]
month.korname <- c("1월","2월","3월",
                   "4월","5월","6월",
                   "7월","8월","9월",
                   "10월","11월","12월")
month.korname[which(rainfall > 100)]
which.max(rainfall)
which.min(rainfall)
month.korname[which.max(rainfall)]
month.korname[which.min(rainfall)]

# 앞의 범위 안에서 무작위로 뒤의 매개 변수 만큼 뽑는다.
sample(1:20, 3)
sample(1:45, 6)
sample(1:10, 7)
# replace=T 중복값 트루
sample(1:10, 7, replace=T)

count <- sample(1:100,7)
month.korname <- c("일요일", "월요일", "화요일",
                   "수요일", "목요일", 
                   "금요일", "토요일")

# 문자열 결합하는 함수 paste
paste(month.korname, count, sep = ":")
month.korname[which.max(count)]
month.korname[which.min(count)]
month.korname[which(count > 50)]

paste(month.korname, count, sep = " : ")

paste("I'm","Duli","!!")
paste("I'm","Duli","!!", sep="")
# 기본적으로 사용되는 델리미터가 없다.
paste0("I'm","Duli","!!")

fruit <- c("Apple", "Banana", "Strawberry")
food <- c("Pie","Juice", "Cake")
paste(fruit, food)

paste(fruit, food, sep="")
paste(fruit, food, sep=":::")
paste(fruit, food, sep="", collapse="-")
paste(fruit, food, sep="", collapse="")
paste(fruit, food, collapse=",")