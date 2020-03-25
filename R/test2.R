#문제8
word <- scan("data/iotest2.txt",what = "")
freq<-sort(table(word), decreasing = T)
cat(names(freq[which.max(freq)]))

