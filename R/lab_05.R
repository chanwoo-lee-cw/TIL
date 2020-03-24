# 문제1
(grade <- sample(1:6,1))
if(grade<=4) {
  cat(grade," 학년은 저학년입니다.","\n")
}else {
  cat(grade," 학년은 고학년입니다.","\n")
}

#문제2
(choice <- sample(1:5,1))
if(choice == 1) {
  result <- 300+50
}else if(choice == 2) {
  result <- 300-50
}else if(choice == 3) {
  result <- 300*50
}else if(choice == 4) {
  result <- 300/50
}else if(choice == 5) {
  result <- 300%%50
}
cat("결과값 : ",result)

#문제3
count <- sample(3:10,1)
deco <- sample(1:3,1)
if(deco == 1) {
  deco <- "*"
}else if(deco == 2) {
  deco <- "$"
}else if(deco == 3) {
  deco <- "#"
}
for(i in 1:count) {
  cat(deco)
}

#문제4
score <- sample(0:100,1)
level<-switch (EXPR=as.character(score%/%10),
  "10" =,"9"="A","8"="B","7"="C","6"="D","F"
)
cat(score," 점은 " ,level, " 등급입니다.")

#문제5
# 이거 말고 딴거 쓰자
for(i in 1:length(letters)) {
  alpha[i]<-paste(LETTERS[i],letters[i],sep="")
}
alpha

#다른거
alpha <- paste0(c(LETTERS),c(letters))
alpha