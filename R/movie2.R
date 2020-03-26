ratingPoint<-NULL
ratingtext<-NULL
for( i in 1:10) {
  
  text<- NULL
  url<- paste0("https://movie.daum.net/moviedb/grade?movieId=127122&type=netizen&page=",i)
  text <- read_html(url)
  text
  
  ratingNode <- html_nodes(text, ".raking_grade em")
  #reviewNode <- html_nodes(text, xpath = "//*[@id='mArticle']/div[2]/div[2]/div[1]/ul/li/div/p")
  #reviewNode <- html_nodes(text, ".main_detail > ul > li > div > p")
  reviewNode <- html_nodes(text, ".main_detail > ul > * > div > p")
  
  textTemp <- html_text(reviewNode, trim=TRUE)
  ratingTemp <- html_text(ratingNode)
  
  select <- nchar(textTemp) > 0
  
  ratingPoint <-c(ratingPoint,ratingTemp[select])
  ratingtext <- c(ratingtext,textTemp[select] )
  
  
  reviewDf<- data.frame(ratingPoint, ratingtext)
}


write.csv(
  reviewDf,
  file="daummovie2.csv"
)

