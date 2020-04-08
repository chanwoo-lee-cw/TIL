# SNS의 Open API 활용
library(httr)
library(rvest)
library(XML)
library(httr)
rm(list=ls())
# 네이버에서 블로그 검색할때 쓰라고 제공하는 URL문자열
searchUrl<- "https://openapi.naver.com/v1/search/blog.xml"
Client_ID <- "izGsqP2exeThwwEUVU3x"
Client_Secret <- "WrwbQ1l6ZI"
# 한글 같은 것은 인코딩 해야 된다.
# 코로나 라는 단어를 euc-kr로 인식해서 uTF로 인코딩해라
query <- URLencode(iconv("봄","euc-kr","UTF-8"))
# get방식으로 홍신, url 뒤에 붙힌다.
# 헤더를 API 제공자가 규정한대로 제공한다.
# 100개를 받아오겠다고 선언
# 요청 헤더에 인증키를 붙혀야 되서 브라우저에서는 검색 불가.
url <- paste0(searchUrl, "?query=", query, "&display=100")
doc <- GET(url, add_headers("Content_Type" = "application/xml",
                            "X-Naver-client-Id" = Client_ID, "X-naver-Client-Secret" = Client_Secret))
# 블로그 내용에 대한 리스트 만들기		
doc2 <- htmlParse(doc, encoding="UTF-8")
# xpathSApply에 있는 데이터를 얻어온다.
# xmlValue를 실행해 달라.xmlValue는 읽기
text<- xpathSApply(doc2, "//item/description", xmlValue)
text
# B태그 삭제제
# <?b> 적유 표현식 <b> </b> 둘다 없애야한다.
text <- gsub("</?b>", "", text)
# ? ----> 0,1
# * ----> 1 이상
# + ----> 0 이상
# 정규표현식에서 * : 한번 이상, 정규표현식에서 +0번 이상
# 즉 . (무엇이던지) 1번 이상 나오는거 전부 지워라라
# &nt; &gt; 을 삭제해라는 의미이다.
text <- gsub("&.+t;", "", text)
text

# 네이버 뉴스 연동  
searchUrl<- "https://openapi.naver.com/v1/search/news.xml"
Client_ID <- "izGsqP2exeThwwEUVU3x"
Client_Secret <- "WrwbQ1l6ZI"
query <- URLencode(iconv("코로나","euc-kr","UTF-8"))
url <- paste0(searchUrl, "?query=", query, "&display=100")
doc <- GET(url, add_headers("Content_Type" = "application/xml",
                            "X-Naver-client-Id" = Client_ID, "X-naver-Client-Secret" = Client_Secret))

# 네이버 뉴스 내용에 대한 리스트 만들기		
doc2 <- htmlParse(doc, encoding="UTF-8")
text<- xpathSApply(doc2, "//item/description", xmlValue); 
text
text <- gsub("</?b>", "", text)
text <- gsub("&.+t;", "", text)
text

# 트위터 글 읽어오기
# 요즘 트윗 검색은 R트윗으로 바뀌었으니 이거 쓰자/. 트위터에서 제공하는 Rest엘레멘트
install.packages("rtweet")
library(rtweet) 
appname <- "edu_data_collection"
api_key <- "RvnZeIl8ra88reu8fm23m0bST"
api_secret <- "wTRylK94GK2KmhZUnqXonDaIszwAsS6VPvpSsIo6EX5GQLtzQo"
access_token <- "959614462004117506-dkWyZaO8Bz3ZXh73rspWfc1sQz0EnDU"
access_token_secret <- "rxDWfg7uz1yXMTDwijz0x90yWhDAnmOM15R6IgC8kmtTe"
# 토큰을 만든다.
twitter_token <- create_token(
  app = appname,
  consumer_key = api_key,
  consumer_secret = api_secret,
  access_token = access_token,
  access_secret = access_token_secret)

key <- "코로나"
key <- enc2utf8(key)
# 토큰으로 검색한다.
# 트위터가 정해 놓은 객체가 반환이 된다.
result <- search_tweets(key, n=500, token = twitter_token)
str(result)
# 벋은 토큰을 가공한다
result$retweet_text
content <- result$retweet_text
content <- gsub("[[:lower:][:upper:][:digit:][:punct:][:cntrl:]]", "", content)   
content


library(XML)
API_key  <- "%2BjzsSyNtwmcqxUsGnflvs3rW2oceFvhHR8AFkM3ao%2Fw50hwHXgGyPVutXw04uAXvrkoWgkoScvvhlH7jgD4%2FRQ%3D%3D"
bus_No <- "402"
url <- paste("http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?ServiceKey=", API_key, "&strSrch=", bus_No, sep="")
doc <- xmlParse(url, encoding="UTF-8")
top <- xmlRoot(doc)
top
# 받은 도= xml 형식을 데이터 프레임으로 바꾼다.
df <- xmlToDataFrame(getNodeSet(doc, "//itemList"))
df
str(df)
View(df)
busRouteId <- df$busRouteId
busRouteId




url <- paste("http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?ServiceKey=", API_key, "&busRouteId=", busRouteId, sep="")
doc <- xmlParse(url, encoding="UTF-8")
top <- xmlRoot(doc)
top
# 현재 운영되고 있는 버스의 위치를 xml파일로 리턴해준다.
df <- xmlToDataFrame(getNodeSet(doc, "//itemList"))
df
View(df)
# 서울시 빅데이터- XML 응답 처리
# http://openapi.seoul.go.kr:8088/796143536a756e69313134667752417a/xml/LampScpgmtb/1/100/

library(XML)
key = '796143536a756e69313134667752417a'
contentType = 'xml'
startIndex = '1'
endIndex = '200'
# 멀캠에서는 8088이 안나감
url = paste0('http://openapi.seoul.go.kr:8088/',key,'/',contentType,'/LampScpgmtb/',startIndex,'/',endIndex,'/')

con <- url(url, "rb") 
imsi <- read_html(con)
t <- htmlParse(imsi, encoding="UTF-8")
upNm<- xpathSApply(t,"//row/up_nm", xmlValue) 
pgmNm<- xpathSApply(t,"//row/pgm_nm", xmlValue)
targetNm<- xpathSApply(t,"//row/target_nm", xmlValue)
price<- xpathSApply(t,"//row/u_price", xmlValue)

df <- data.frame(upNm, pgmNm, targetNm, price)
View(df)
write.csv(df, "edu.csv")

# 한국은행 결제 통계시스템 Open API - JSON 응답 처리
# jsonlite은 기본 패키지가 아니다 
library(jsonlite)
key = '/4WQ7X833TXC370SUTDX4/'
contentType = 'json/'
startIndex = '1'
endIndex = '/100/'

url <- paste0('http://ecos.bok.or.kr/api/KeyStatisticList',key,contentType,'kr/',startIndex,endIndex)
response <- GET(url)
json_data <- content(response, type = 'text', encoding = "UTF-8")
# json으로부터 객체를 만들어 내는것
# tojson 이면 데이터 프레임으로 부터 json을 만들어 내는것것
json_obj <- fromJSON(json_data)
df <- data.frame(json_obj)
df <- df[-1]
names(df) <- c("className", "unitName", "cycle", "keystatName", "dataValue")
View(df)

