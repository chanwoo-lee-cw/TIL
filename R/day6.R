# 정규표현식 사용
# 매개 변수가 패턴이면 정규표현식 사용 가능능
word <- "JAVA javascript Aa 가나다 AAaAaA123 %^&*"
#sub와 gsub의 차이 첫번째만 바꾸느냐 전부 바꾸느냐
gsub("", "", word)
sub("A", "", word)
gsub("A", "", word) 
gsub("a", "", word) 
gsub("Aa", "", word) 
# ()는 그룹핑 - 즉 둘이 같이 나오는 것을 안쓰는 것과 똑같은데
gsub("(Aa)", "", word) 
# (Aa){2}- Aa 2개가 연속으로 나올때만 삭제한다.
gsub("(Aa){2}", "", word) 
# Aaa
gsub("Aa{2}", "", word)
# Aa~a 즉 a가 2번 이상나오는 것들 삭제
gsub("(Aa){2,}", "", word)
# or 같은 기능이 [] 안에 다 삭제
gsub("[Aa]", "", word) 
# 한글 전부 삭제
gsub("[가-힣]", "", word) 
# 한글이 아닌 에들 전부 삭제
gsub("[^가-힣]", "", word) 
gsub("[&^%*]", "", word) 
# 특문 전부 삭제
gsub("[[:punct:]]", "", word) 
# 알파벳 뉴머릭
gsub("[[:alnum:]]", "", word) 
gsub("[1234567890]", "", word) 
gsub("[0-9]", "", word) 
# 숫자를 전부 삭제
gsub("\\d", "", word)
# 숫자 제외 전부 삭제
gsub("\\D", "", word)
gsub("[[:digit:]]", "", word) 
gsub("[^[:alnum:]]", "", word) 
# 스페이스 전부 삭제. 그 스페이스에는 \r같은 캐리지 리턴도 포함된다.
gsub("[[:space:]]", "", word) 
gsub("[[:digit:][:alnum:]]", "", word) 
# 보통 전화번호는 \d{3}-\d{3,4}-\d{3}


install.packages("RSelenium")
library(RSelenium)
remDr<-remoteDriver(remoteServerAddr= "localhost" ,
                    port = 4445, browserName= "chrome")
remDr$open()
remDr$navigate("http://www.google.com/")
# 선택자는 css, q라는 이름의 태그를 찾는다.
# CSS는 대괄호 안에 작성한다.
webElem<-remDr$findElement(using = "css", "[name = 'q']")
# 자바 입력하고 enter입력
webElem$sendKeysToElement(list("JAVA", key = "enter"))
# 즉 구글창에 검색 창에 java를 입력한다.

remDr$open()
remDr$navigate("http://www.naver.com/")

webElem<-remDr$findElement(using = "css", "[name = 'query']")
webElem$sendKeysToElement(list("JAVA", key = "enter"))

# [ 네이버 웹툰 댓글 읽기 ]
url<-'http://comic.naver.com/comment/comment.nhn?titleId=570503&no=135'
remDr$navigate(url)

#단수형으로 노드 추출
more<-remDr$findElement(using='css','#cbox_module > div > div.u_cbox_sort > div.u_cbox_sort_option > div > ul > li:nth-child(2) > a')
more$getElementTagName()
more$getElementText()
more$clickElement()


# 2페이지부터 10페이지까지 링크 클릭하여 페이지 이동하기 
for (i in 4:12) {
  nextCss <- paste0("#cbox_module>div>div.u_cbox_paginate>div> a:nth-child(",i,") > span")
  nextPage<-remDr$findElement(using='css',nextCss)
  nextPage$clickElement()
  Sys.sleep(2)
}

#복수형으로 노드 추출 
url<-'http://comic.naver.com/comment/comment.nhn?titleId=570503&no=135'
remDr$navigate(url)
#베스트 댓글 내용 읽어오기
bestReviewNodes<-remDr$findElements(using ="css selector","ul.u_cbox_list span.u_cbox_contents")
sapply(bestReviewNodes,function(x){x$getElementText()})

#전체 댓글 링크 클릭후에 첫 페이지 내용 읽어오기
totalReview <- remDr$findElement(using='css','#cbox_module > div > div.u_cbox_sort > div.u_cbox_sort_option > div > ul > li:nth-child(2) > a')
totalReview$clickElement()
totalReviewNodes<-remDr$findElements(using ="css selector","ul.u_cbox_list span.u_cbox_contents")
sapply(totalReviewNodes,function(x){x$getElementText()})
