# 전체 매장 갯수 추출 코드
sizeCss<-'#container > div > form > fieldset > div > section > article.find_store_cont > article > article:nth-child(4) > div.loca_step3 > div.result_num_wrap > span'
#container > div > form > fieldset > div > section > article.find_store_cont > article > article:nth-child(4) > div.loca_step3 > div.result_num_wrap > span
size<-remDr$findElements(using='css selector', sizeCss)
limit<-sapply(size.function(x){x$getElementText()})

# 3개의 매장 정보를 읽고 세번째 매장 DOM 객체 위에서 스크롤 이벤트 발생
# 마지막 매장에 도달한 경우에는 더 이상 스크롤 이벤트 발생 불필요.
if(index %% 3 == 0 && index !=limit) {
  remDr$executeScript(
    "var dom = document.querySelectorAll('#mCSB_3_container > ul > li')[arguments[0]];
    dom.scrollIntoView();", list(index)
  )
}
# 주어진 문자열을 개행문자를 기준으로 자르기
rm(list = ls())
library(RSelenium)
remDr<-remoteDriver(remoteServerAddr= "localhost" ,
                    port = 4445, browserName= "chrome")
remDr$open()
url <- "https://www.istarbucks.co.kr/store/store_map.do?disp=locale"
remDr$navigate(url)
Sys.sleep(3)
seoulLink <- remDr$findElements(using='css',"#container > div > form > fieldset > div > section > article.find_store_cont > article > article:nth-child(4) > div.loca_step1 > div.loca_step1_cont > ul > li:nth-child(1) > a")
remDr$executeScript("arguments[0].click();",seoulLink)
Sys.sleep(3)
seoulLink <- remDr$findElements(using='css',"#mCSB_2_container > ul > li:nth-child(1) > a")
remDr$executeScript("arguments[0].click();",seoulLink)
Sys.sleep(3)
sizeCss <- "#container > div > form > fieldset > div > section > article.find_store_cont > article > article:nth-child(4) > div.loca_step3 > div.result_num_wrap > span"
size <- remDr$findElements(using='css selector',sizeCss)
limit <-as.numeric(sapply(size,function(x){x$getElementText()}))
Sys.sleep(3)

shopname <- NULL
lat <- NULL
lng  <- NULL
addr <- NULL
telephone <- NULL

for(index in 1:limit) {
  fullContentLinkCSS <- paste("#mCSB_3_container > ul > li:nth-child(",index,")", sep='')
  doms<-remDr$findElements(using='css selector',  fullContentLinkCSS)
  reple_v <- sapply(doms, function (x) {x$getElementText()})
  splistr <-unlist(strsplit(unlist(reple_v),"\n"))
  
  lat <- c(lat,unlist(sapply(doms, function(x) {x$getElementAttribute("data-lat")})))
  lng <- c(lng,unlist(sapply(doms, function(x) {x$getElementAttribute("data-long")})))
  
  shopname <- c(shopname,splistr[1])
  addr <- c(addr,splistr[2])
  telephone<- c(telephone,splistr[3])
  
  if(index==limit) break;
  if(index %% 3 == 0 && index != limit)
    remDr$executeScript(
      "var dom=document.querySelectorAll('#mCSB_3_container > ul > li')[arguments[0]];
    dom.scrollIntoView();", list(index))
}
df <- data.frame(shopname,lat,lng,addr,telephone)
View(df)
write.csv(
  df,
  file="starbucks.csv",
  fileEncoding = "cp949"
)
