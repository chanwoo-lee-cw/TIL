# 문제 1
(m1 <- matrix(seq(10, 38, by=2), nrow=3, ncol=5))
(m2 <- m1+100)
m_max_v<-max(m1)
m_min_v<-min(m1)
row_max <- apply(m1, 2, max)
col_max <- apply(m1, 1, max)
m1; m2; m_max_v; m_min_v; row_max; col_max

#문제2
n1 <- c(1,2,3)
n2 <- c(4,5,6)
n3 <- c(7,8,9)
m2 <- cbind(n1,n2,n3)

#문제3
[1:9]
(m3<-matrix(seq(1, 9), nrow=3, byrow=T))

#문제4
m4<-m3
rownames(m4) <- c("row1","row2","row3")
colnames(m4) <- c("col1","col2","col3")

# 문제5
(alpha <- matrix(letters[1:6],ncol=3))
(alpha2 <-rbind(alpha,c("x","y","Z")))
(alpha3 <-cbind(alpha,c("s","p")))

# 문제6
(a<- array(1:24, dim=c(2,3,4)))
a[,2,]
a[1,,]
a[,,3]
(a+100);(a-100);(a*100);(a/100)
(a[,,4]*100)
a[1,,];a[,2:3,]
a[,2,2] <- a[,2,2]+100
a[,,1] <- a[,,1]-2
a<- a*10
rm(a)
