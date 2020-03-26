# 교재 81페이지
# 모든 라이브러리를 보여준다.
library()
# 라이브러리를 콘솔창(출력)에 보여준다.
installed.packages()
# 로드가 끝난 라이브러리를 보여준다.
search()
# excel 라이브러리를 읽어온다.
read_excel()
install.packages("readxl")
library(readxl) # require(readxl)
# 읽어서 대입연산을 한다.
excel_data_ex <- read_excel("book/data_ex.xls")
# 현재 패키지의 위치를 출력한다.
getwd()
View(excel_data_ex)
search()
str(excel_data_ex)

# 웹 크롤링과 스크래핑

install.packages("rvest") 
library(rvest)

url <- "http://unico2013.dothome.co.kr/crawling/tagstyle.html"
text <- read_html(url)
text

nodes <- html_nodes(text, "div")
nodes
title <- html_text(nodes)
title

node1 <- html_nodes(text, "div:nth-of-type(1)")
node1
html_text(node1)
html_attr(node1, "style")

node2 <- html_nodes(text, "div:nth-of-type(2)")
node2
html_text(node2)
html_attr(node2, "style")

node3 <- html_nodes(text, "div:nth-of-type(3)")
node3
html_text(node3)


# 단일 페이지(rvest 패키지 사용)
install.packages("rvest"); 
library(rvest)
text<- NULL
url<- "http://movie.naver.com/movie/point/af/list.nhn?page=1"
text <- read_html(url,  encoding="CP949")
text
# 영화제목
nodes <- html_nodes(text, ".movie")
title <- html_text(nodes)
title
# 영화평점
nodes <- html_nodes(text, ".title em")
point <- html_text(nodes)
point
# 영화리뷰 
# //는 조상이 누가 있던 간에 - 조상이 누구던 간에 태그를 찾아라.
# 조상이 누구던간에 id는 저거의 테이블의 티 바으의 
# 가끔 리뷰가 10개가 아닌 이유... 그냥 가끔 리뷰가 없는게 있당.
nodes <- html_nodes(text, xpath="//*[@id='old_content']/table/tbody/tr/td[2]/text()")
imsi <- html_text(nodes, trim=TRUE)
# nchar이라는 함수를 이용해서 필요가 없으면 갖다 버린다.
# nchar은 문자열의 크기를 구하는 함수.
review <- imsi[nchar(imsi) > 0] 
review
if(length(review) == 10) {
  page <- cbind(title, point)
  page <- cbind(page, review)
  write.csv(page, "movie_reviews.csv")
} else {
  cat("리뷰글이 생략된 데이터가 있네요ㅜㅜ\n")
}