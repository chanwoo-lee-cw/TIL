eclipse -webproject - edu

imsi.html

http://localhost:8000/edu/imsi.html

[^edu/imsi.html]: URI

  --> URL 문자열



```html
<form action="..." method="">
	<input type ="text" placeholder = "이름을 입력하세요///" required name= "gname">
	<input type="submit" value="전송">
</form>
```

[http://localhost:8000/edu/htmlexam/...?gname=%EC%96%BC%ED%8B%B0%EB%B0%8B](http://localhost:8000/edu/htmlexam/...?gname=얼티밋)

​																		-----------------------Query String ---------------------------



Query String  : 

- WebClient 가 Web server에게 정보(페이지)를 요청할 때 함께 전달 가능한 name과 value 구성 되는 문자열

- W3C가 정해 놓기를

  ---> Query String Encoding 규칙

  1. name = value 로 구성되어야 한다.
  2. 여러개의 name=value를 전달하는 경우에는 &기호로 분리한다
  3. 영문과 숫자 그리고 일부 특수 문자를 제외하고는 %기호와 코드 값으로 전달된다.
  4. 공백은 +기호로 전달된다



radio.나 check box는 value 값이 필수임.