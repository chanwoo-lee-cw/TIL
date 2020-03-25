x <- 70
func5 <- function() {
  x <- 10
  y <- 20
  x <<- 40  # 외부 변수 x 를 수정. 전역변수를 수정
  return (x+y)
}
func5()  
x  

#전역변수/지역변수
a<-3;b<-7;c<-11 
ft<-function(a){
  b<-a+10     
  c<<-a+10   # 전역대입연산 
  d<-a
  print(a);print(b);print(c);print(d)
  return()  # NULL
}
print(ft(100))
print(a);print(b);print(c);print(d) 

#invisible()함수 

ft.1 <- function(x) return()
ft.2 <- function(x) return(x+10)
# 리턴 결과를 변수에 담아서 사용하게만 한다.
ft.3 <- function(x) invisible(x+10)

ft.1(100)
ft.2(100)
ft.3(100)

r1 <- ft.1(1000);r1
r2 <- ft.2(1000);r2
r3 <- ft.3(1000);r3


testParamType <- function(x){
  if(is.vector(x)) print("벡터를 전달했군요!")
  if(is.data.frame(x)) print("데이터프레임을 전달했군요!")
  if(is.list(x)) print("리스트를 전달했군요!")
  if(is.matrix(x)) print("매트릭스를 전달했군요!")
  if(is.array(x)) print("배열을 전달했군요!")
  if(is.function(x)) print("함수를 전달했군요!")
}
#dataframe이 list에, list는 vector에 포함 됨 
#list는 원소 1개 짜리 1차원 배열과 같다.
testParamType(100)
testParamType(LETTERS)
# 데이터 프레임은 리스트 형식이기도 하다
# 데이터 프레임은 같은 길이의 리스트를 모아놓은 것
testParamType(data.frame())
# 매트릭스는 배열의 확장
testParamType(matrix())
# 리스트틑 벡터의 확징
# 만약 벡터만 사용하고 싶으며면 is.vector는 찬 is.list는 거짓을 찾으면 된다.
testParamType(list())
# 그냥 배열
testParamType(array())
testParamType(mean)


#testParamType
testParamType1 <- function(x){
  result <- NULL
  if(is.vector(x)  && !is.list(x)) result <-"벡터를 전달했군요!"
  else if(is.data.frame(x)) result <- "데이터프레임을 전달했군요!"
  else if(is.list(x)) result <- "리스트를 전달했군요!"
  else if(is.matrix(x)) result <- "매트릭스를 전달했군요!"
  else if(is.array(x)) result <- "배열을 전달했군요!"
  else if(is.function(x)) result <- "함수를 전달했군요!"
  return(result)
}

#dataframe이 list에, list는 vector에 포함 됨 #list는 원소 1개 짜리 1차원 배열과 같다.....?

testParamType1(100)
testParamType1(LETTERS)
testParamType1(data.frame())
testParamType1(matrix())
testParamType1(list())
testParamType1(array())
testParamType1(function(){})

#stop() 함수
testError1 <- function(x){
  if(x<=0)
    stop("양의 값만 전달 하숑!! 더 이상 수행 안할거임..")
  return(rep("테스트",x))
}

testError1(5)
testError1(0)



#warning() 함수
testWarn <- function(x){
  if(x<=0)
    stop("양의 값만 전달 하숑!! 더 이상 수행 안할거임..")
  if(x>5){
    x<-5
    warning("5보다 크면 안됨!! 하여 5로 처리했삼...!!")
  }
  return(rep("테스트",x))
}

#에러처리를 하고 중단하고 싶으면 stop
# 에러 처리를 하고 중단하고 싶지 않으면 warning
testWarn(3)
testWarn(10)

test1 <-function(p){
  cat("난 수행함\n")
  testError(-1)
  cat("나 수행할 까요? \n")
}

test1()

#try()
test2 <- function(p){
  cat("난 수행함\n")
  #try에러가 나도 그대로 실행한다.
  #스크래핑 할때, 에러가 나서 중단되지 않게 하기 위해서.
  try(testError(-1))
  cat("나 수행할 까요? \n")
}
# 
test2()

testAll <-function(p){
  # 첫 번째 에러 보내준것
  tryCatch({
    if(p=="오류테스트"){
      testError1(-1)
    }else if (p =="경고테스트"){
      testWarn(6)
    }else{
      cat("정상 수행..\n")
      print(testError1(2))
      print(testWarn(3))
    }
  # warning 이 발생하면 경고 메세지
  },warning = function(w){
    print(w)
    cat("-.-;;\n")
    # 에러가 발생하면 에러 출력
  }, error = function(e){
    print(e)
    cat("ㅠㅠ \n")
  },finally ={
    cat("오류, 경고 발생 여부를 따라서 반드시 수행되는 부분입니다요..\n")
  })
}

testAll("오류테스트")
testAll("경고테스트")
testAll("아무거나")


f.case1 <- function(x) {
  if(is.na(x)) 
    return("NA가 있슈")
  else
    return("NA가 없슈")
}
f.case1(100)
f.case1(NA)
f.case1(1:6)
f.case1(c(10,20,30))
f.case1(c(NA, 20))
f.case1(c(10, NA, 20))

f.case2 <- function(x) {
  if(any(is.na(x))) 
    return("NA가 있슈")
  else
    return("NA가 없슈")
}
f.case2(100)
f.case2(NA)
f.case2(1:6)
f.case2(c(10,20,30))
f.case2(c(NA, 20))
f.case2(c(10, NA, 20))

f.case3 <- function(x) {
  if(all(is.na(x))) 
    return("모두 NA임")
  else
    return("모두 NA인 것은 아님")
}
f.case3(100)
f.case3(LETTERS)
f.case3(NA)
f.case3(c(NA, NA, NA))
f.case3(c(NA, NA, 10))


#Sys.sleep(초시간) 함수
testSleep <- function(x) {
  for(data in 6:10) {       
    cat(data,"\n")
    if(x)
      # 
      Sys.sleep(1)
  }
  return()
}
testSleep(FALSE)
testSleep(TRUE)




# 가변형 인자 테스트
funcArgs <- function(...) {
  p <- c(...)
  data <- 1:10
  opts <- ifelse(length(p), p, "")
# 이렇게 하면 스칼라 값만이 전달된다.
#  opts <- ifelse(length(p)>0, p, "")
  print(p)
  print(opts)
  if(opts[1] == "")
    print(data)
  else 
    for(opt in opts) {
      switch(EXPR=opt,
             SUM=, Sum=, sum= print(sum(data)),
             MEAN=, Mean=, mean= print(mean(data)),
             DIFF=, Diff=, diff= print(max(data) - min(data)),
             MAX=, Max=, max= print(max(data)),
             MIN=, Min=, min= print(min(data)),
             SORT=, Sort=, sort= print(sort(data))
      )
    }
}
funcArgs(1111,2222)
funcArgs("mean", "sum")

