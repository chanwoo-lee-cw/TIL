(memo <- readLines("data/memo.txt",encoding="UTF-8"))
vector <- c()
# 줄 1
vector[1]<-gsub("[&^%*!@#$%^&*()]", "", memo[1])
# 줄2
vector[2]<-gsub("e", "E", memo[2])
# 줄3
vector[3]<-gsub("\\d", "", memo[3])
# 줄4
vector[4]<-gsub("[A-z]", "", memo[4])
vector[4]<-gsub(" {2,}", " ", vector[4])
# 줄5
vector[5]<-gsub("[[:punct:]0-9]", "", memo[5])
#줄6
vector[6]<-gsub("[[:space:]]", "", memo[6])
#줄7
vector[7]<-tolower(memo[7])
vector[7]<-gsub(" {2,}", " ", vector[7])

vector

write.csv(
  vector,
  file="memo_new.txt",
  row.names = FALSE
)
