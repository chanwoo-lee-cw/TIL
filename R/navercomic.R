comicTitle <- NULL
comicSummary <- NULL
comicRating <- NULL
for( i in 1:5) {
  
  text<- NULL
  url<- paste0("https://comic.naver.com/genre/bestChallenge.nhn?&page=",i)
  text <- read_html(url)
  text
  
  (comicTitleNode <- html_nodes(text, "div.challengeInfo > h6 > a"))
  (comicSummaryNode <- html_nodes(text, "div.challengeInfo > div.summary"))
  comicRatingNode <- html_nodes(text, "div.challengeInfo > div.rating_type > strong")

  
  comicTitleTemp <- html_text(comicTitleNode, trim=TRUE)
  (comicSummaryTemp <- html_text(comicSummaryNode, trim=TRUE))
  comicRatingTemp <- html_text(comicRatingNode)
  
  (comicTitle <-c(comicTitle,comicTitleTemp))
  (comicSummary <- c(comicSummary,comicSummaryTemp))
  (comicRating <-c( comicRating,comicRatingTemp))
  
}

comicDf<- data.frame(comicTitle, comicSummary,comicRating)
write.csv(
  comicDf,
  file="navercomic.csv"
)

