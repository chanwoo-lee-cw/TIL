#문제1
countEvenOdd <- function(vector) {
  even <- 0
  odd <- 0
  for(data in vector) {
    if(data%%2 == 1) {
      odd <- odd + 1
    }else {
      even <- even + 1
    }
  }
  return(list(even,odd))
}

countEvenOdd(c(10,20,11,5))
countEvenOdd(c(1,3,5,7))
countEvenOdd(c(10,20,40,60))

#문제2
vmSum <- function(input) {
  if(is.vector(input)  && !is.list(input)) {
    tryCatch({
      result <- 0
      result <- sum(input)
    },error = function(e) {
      print("숫자벡터를 리턴하숑")
    },finally ={
      return(result)
    })
  }else {
    return("벡터를 전달하숑")
  }
}
vmSum(c(10,20,30))
vmSum("가나다라")
vmSum(list())

#문제3
vmSum2 <- function(input) {
  tryCatch({
    if(is.vector(input)  && !is.list(input)) {
      stop("벡터만 전달하숑!")
    }
    result <- 0
    for(data in input) {
      if(is.numeric(data)) result <- result + input
      else warning("숫자벡터를 리턴하숑!")
    }
  },error= function(w){
    result <- 0
  },error = function(e) {
    result <- 0
  },finally ={
    return(result)
  })
}
vmSum(c(10,20,30))
vmSum("가나다라")
vmSum(list())

#문제4
mySum <- function(input) {
  if(is.null(input)) return(NULL)
  else if(is.vector(input)  && !is.list(input)) {
    oddSum <- 0
    evenSum <- 0
    for(data in input) {
      if(is.na(data)) {
        warning("NA를 최저값으로 변경하여 처리함!!")
        data <- -Inf
      }
      if(data%%2 == 1) {
        oddSum <- oddSum + data
      }else {
        evenSum <- evenSum + data
      }
    }
    return(list(oddSum,evenSum))
  }else {
    stop("벡터만 처리가능!")
  }
}
mySum(c(11,22,55,50))
mySum(c(11,NA,55,50))
mySum(list())
mySum(NULL)

#문제5
myExpr <- function(x) {
  if(is.function(x)) {
    x(sample(1:45,6))
  }else {
    stop("수행 안할꺼임!!")
  }
  x(sample(1:45,6))
}
myExpr(mySum)

#문제6
createVector1 <- function(...) {
  argu <- c(...)
  if(length(argu)==0) {
    result <- NULL
  }else {
    result <- c()
    i=1
    for (data in argu) {
      if(is.na(data)) return(NA)
      result[i] <- data
      i= i+1
    }
  }
  return(result)
}

createVector1(10,20,30)
createVector1("강아지","고양이")
createVector1(NA)
createVector1()

createVector2<- function(...) {
  argu <- c(...)
  if(length(argu)==0) {
    result <- NULL
  }else {
    numericVector <- c()
    numericint <-1
    characterVector <- c()
    characterint <- 1
    logicalVector <- c()
    logicalint<-1
    for (data in argu) {
      if(is.numeric(data)){
        numericVector[numericint] <- data
        numericint <- numericint + 1
      }
      else if(is.character(data)) {
        characterVector[characterint] <- data
        characterint <- characterint + 1
      }
      else if(is.logical(data)) {
        logicalVector[logicalint] <- data
        logicalint <- logicalint + 1
      }
    }
    result <- list(numericVector,characterVector,logicalVector)
  }
  return(result)
}
createVector2(10,20,30)
createVector2("강아지","고양이")
