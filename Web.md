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





## CSS

전역적인 스타일 설정

- \<head>태그 안에 \<style> 태그를 사용한다.

- css정의 방법

  ```css
  css 선택자 {
  	css 속성명 : 속성값;
      css 속성명 : 속성값;
      css 속성명 : 속성값;
      css 속성명 : 속성값;
  }
  ```

### CSS 선택자

- 전체 선택자
- 태그 선택자
- class선택자
- id 선택자
- 자식 선택자
- 자손 선택자
- 첫번째 동생 선택자
- 모든 동생 선택자



- CSS를 다르게 적용하려는 태그들 또는 태그의 컨텐트에 정의하는 용도의 태그들
  - \<div> - 여러 태그들을 묶거나 또는 태그에 대하여 CSS를 적용하고 싶을때.
  - <span> 