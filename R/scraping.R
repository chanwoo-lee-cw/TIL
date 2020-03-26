url <- "http://unico2013.dothome.co.kr/crawling/exercise_bs.html"

text <- read_html(url, encoding="UTF-8")
text
#h1
h1Node <- html_nodes(text, "h1")
(h1Content <- html_text(h1Node))
#a
aNode <- html_nodes(text, "a")
(aContent <- html_text(aNode, trim=TRUE))
(aHref <- html_attr(aNode, "href", default = ""))
#img
imgNode <- html_nodes(text, "img")
(imgHref <- html_attr(imgNode, "src", default = ""))
# 첫 번째 <h2> 태그의 컨텐츠
node4 = html_nodes(text, "h2:nth-of-type(1)")
html_text(node4)

#<ul> 태그의 자식 태그들 중 style 속성의 값이 green으로 끝나는 태그의 컨텐츠
ul.nodes <- html_nodes(text,"ul>*[style$=green]")
ul.style <- html_text(ul.nodes)

# 두 번째 <h2> 태그의 컨텐츠
node6 = html_nodes(text, "h2:nth-of-type(2)")
html_text(node6)

# <ul> 태그의 모든 자식 태그들의 컨텐츠 
node7 = html_nodes(text, "ol > *")
html_text(node7)

# <table> 태그의 모든 자손 태그들의 컨텐츠 
node8 = html_nodes(text, "table > *")
html_text(node8)

# name이라는 클래스 속성을 갖는 <tr> 태그의 컨텐츠
node9 = html_nodes(text, ".name")
html_text(node9)

# target이라는 아이디 속성을 갖는 <td> 태그의 컨텐츠
node10 = html_nodes(text, "#target")
html_text(node10)
