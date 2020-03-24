#문제1
L1 <- list(
  name = "scott",
  sal = 300
)
result<-L1$sal*2

#문제2
L2 <- list(
  "scott",
  seq(100,300,100)
)

#문제3
L3<-list(
  c(3,5,7), 
  c("A", "B", "C")
)

L3[[2]][1] <- "Alpha"

#문제4
L4<-list(alpha=0:4, beta=sqrt(1:5), gamma=log(1:5))
L4$alpha+10

#문제5
L5 <- list(math=list(95, 90), writing=list(90, 85), reading=list(85, 80))
mean(unlist(L5))

#문제6
time <- 32150
paste(time%/%3600,"시",(time%%3600)%/%60,"분",(time%%3600)%%60,"초")
