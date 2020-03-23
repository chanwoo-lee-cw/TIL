#문제1
str(airquality)

#문제2
df1 <- data.frame(x=c(1,2,3,4,5),
                  y=c(2,4,6,8,10))

#문제3
df2 <- data.frame(col1 = c(1,2,3,4,5),
                  col2 = c(letters[1:5]),
                  col3 = c(6,7,8,9,10))

#문제4
제품명 <- c("사과","딸기","수박")
가격 <- c(1800,1500,3000)
판매량 <- c(24,38,13)

df3 <- data.frame(제품명, 가격, 판매량,stringsAsFactors = F)

# 문제5
mean(df3$가격)
mean(df3$판매량)  

#문제6
name <- c("Potter", "Elsa", "Gates", "Wendy", "Ben")
gender <- factor(c("M", "F", "M", "F", "M"))
math <- c(85, 76, 99, 88, 40)
df4 <- data.frame(name,gender,math,stringsAsFactors = F)
str(df4)

stat <- c(76, 73, 95, 82, 35)
df4$stat <- stat
df4$score <- df4$math + df4$stat
df4$grade <-ifelse(df4$score >= 150,"A",
                   ifelse(df4$score >= 100,"B", 
                          ifelse(df4$score >=70,"C","D")))

#문제7
str(emp)
#문제8
emp[3:5,]
#문제9
emp["ename"]
#문제10
subset(emp,select = c("ename", "sal"))
#문제11
subset(emp, select=c("ename","sal","job"), 
       subset= emp$job=="SALESMAN")
#문제12
subset(emp, select=c("ename","sal","job"), 
       subset= (100<=emp$sal&emp$sal <=3000))
#문제13
subset(emp, select=c("ename","sal","job"), 
       subset= emp$job!="ANALYST")

#subset(emp, select=c("ename","sal","job"), 
#       subset=! emp$job=="ANALYST")
#문제14
subset(emp, select=c("ename","sal","job"), 
       subset = (emp$job=="SALESMAN" |emp$job=="ANALYST"))
#문제15
subset(emp,is.na(emp$comm)) 

#문제16
emp[order(emp$sal),]

