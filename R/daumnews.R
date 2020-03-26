text<- NULL
url<- "https://media.daum.net/ranking/popular/"
text <- read_html(url)
text

newsTitleNode <- html_nodes(text, "#mArticle > div.rank_news > ul.list_news2 > * > div.cont_thumb > strong > a")
#mArticle > div.rank_news > ul.list_news2 > li:nth-child(1) > div.cont_thumb > strong > a
#reviewNode <- html_nodes(text, xpath = "//*[@id='mArticle']/div[2]/div[2]/div[1]/ul/li/div/p")
#reviewNode <- html_nodes(text, ".main_detail > ul > li > div > p")
newPressNode <- html_nodes(text, xpath = "//*[@id='mArticle']/div[2]/ul[3]/li/div[2]/strong/span")

newsTitle <- html_text(newsTitleNode, trim=TRUE)
newPress <- html_text(newPressNode, trim=TRUE)

reviewDf<- data.frame(newsTitle, newPress)
reviewDf

write.csv(
  reviewDf, 
  file="daumnews.csv"
)

