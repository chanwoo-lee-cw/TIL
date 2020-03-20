# No.1
v1 <- 1:10
v2 <- v1*2
max_v <- max(v2)
min_v <- min(v2)
avg_v <- mean(v2)
sum_v <- sum(v2)
v3 <- v2[-5]

v1; v2; v3; max_v; min_v; avg_v; sum_v;

# No.2
seq(from=1, to=10, by=2)
#seq(1, 10, 2)
rep(1,5)
rep(1:3,times=3)
#rep(1:3,3)
rep(1:3, each=3)

#No.3
nums <- sample(1:100,10)
sort(nums)
sort(nums, decreasing = T)
nums[nums>50]
which(nums<=50)
which.max(nums)
which.min(nums)

#No.4
v8 <- seq(1, 10, 3)
names(v8) <- LETTERS[1:4]

#No.5
score <- sample(1:20,5)
myFriend <- c("둘리", "또치", "도우너", "희동", "듀크" )
paste(score,myFriend,sep="-")
myFriend[which.max(score)]
myFriend[which.min(score)]
myFriend[which(score>10)]
