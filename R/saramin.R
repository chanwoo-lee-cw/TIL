text<- NULL
url<- "http://www.saramin.co.kr/zf_user/search?search_area=main&search_done=y&search_optional_item=n&searchType=default_mysearch&searchword=Java"
text <- read_html(url)

titleNodes <- html_nodes(text, ".swiper-slide > input")
title <- html_attr(nodes, "data-description", default = "")
title<-title[1:length(title)-1]

uploadNodes <- html_nodes(text, ".swiper-slide > label > .count")
upload <- gsub("\\D","",html_text(uploadNodes))

jopDf<- data.frame(title, upload)
jopDf

write.csv(
  jopDf,
  file="saramin.csv",
  row.names = FALSE
)
