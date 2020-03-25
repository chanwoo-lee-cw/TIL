#문제1
exam1 <- function() {
  return(paste0(c(LETTERS),c(letters)));
}
exam1()

#문제2
exam2 <- function(data) {
  return(sum(1:data));
}
exam2(5)

#문제3
exam3 <- function(data1,data2) {
  return(abs(data1-data2));
}
exam3(10,20)
exam3(20,5)

#문제4
exam4 <- function(data1,data2,calc) {
  
  result <- switch(EXPR=calc,
                "+"=as.character(data1+data2),
                "-"=as.character(data1-data2),
                "*"=as.character(data1*data2),
                "/"=as.character(data1/data2)
                )
  if(calc=='%/%' | calc=='%%') {
    if(data1==0) {
      result<-"오류1"
    }else if(data2==0) {
      result<-"오류2"
    }else {
      if(calc=='%/%') {
        result<-data1%/%data2
      }else {
        result<-data1%%data2
      }
    }
  }
  return(result)
}
exam4(10,20,'+')
exam4(10,20,'-')
exam4(10,20,'*')
exam4(10,20,'/')
exam4(10,20,'%/%')
exam4(10,20,'%%')
exam4(0,20,'%/%')
exam4(10,0,'%/%')
exam4(0,20,'%%')
exam4(10,0,'%%')

#문제6
exam6 <- function(...) {
  inputItem<-c(...)
  for (data in inputItem) {
    if(is.na(data)) {
      cat("NA는 출력불가\n")
    } else if(data<=100 & 85<=data) {
      cat("상\n")
    } else if(data<=84 & 70<=data) {
      cat("중\n")
    } else if(data<=69) {
      cat("하\n")
    }
  }
}
exam6(c(50,40,50,80,10,NA,60))
