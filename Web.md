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





## JavaScript의 DOM 객체

- 각자 위치를 객체로 만든다.
- 즉, HTML객체, 그 하위의 객체 HEAD, BODY객체 그 하위의 TITLE 객체 등등 같이 각자 모든 요소를 트리구조의 객체로 만들고, javascript로 사용한다.





- img[src]
- img[src=duke.png]
- img[src=$png] 
  - png로 끝나는 모든 것을 지정한다



display : none , block, inline,inline-block



### 구문

1. javascript 코드 작성 방법
   1. 오로지 구문만 있으면 된다. main 이런거 필요없다.
2. 데이터 타입과 변수선언
   1. 숫자, 문자열타입, 논리타입, 객체타입(배열타입), undefined 타입

```javascript
var 변수명;
var 변수명 =초기값;
   var v1;
   v1=10 // number
   v1='10' //String
   v1= true; // boolean
      -----------------------> typeof 연산자가 필요
```

3. 연산자(java와 85% 비슷)   ----> ==, ===, !=, !==, && ,||, delete, typeof...
4. 제어문 : for foreach, while, do ~ while, break, continue
5. 함수의 정의와 활용 
   1. 생성자 함수로 정의 및 생성
   2. 객체 리터럴로 정의 및 생성
6. 예외처리
   1. 예외처리는 가능하면 하지 말자, 비용이 많이듬
7. API
   1. 표준 API: 함수, 생성자 함수
   2. BOM API 
      1. HTML5 API 
         - canvas API - 캔버스용도로 주로 씌으는  API
         - webstorage - 브라우저가 정해져있는 규격에 폴더에 저장함. 웹에 맡기지 않아도 된다.





### 삽입 위치

- JavaScript 코드는 가급적\<body> 태그의 마지막 부분 즉,\</body> 태그의 바로 위에 삽입한다.
  
- 자바 스크립트 코드가 도는 동안 HTML 코드가 정지 되기 때문에 , 자바 스크립트 코드의 처리 시간은 오래 걸리기 때문에 먼저 HTML부터 출력하고 자바 스크립트 코드를 처리한다.
  
- for…in 반복문 사용이 가능하다(for-each 문이라고도 핚다.)

  ```javascript
  
  ```

NaN의 약어 Not a number : 연산할 수 없는 상황이 아니면 Nan출력

불리언 형도 숫자로 간주 : true면 1 false면 0



`비교식1 && 비교식2`

`비교식1 || 비교식2`

- &&와  ||를 이용해서 if문 대신해서 구현 가능

```javascript
if(a>b)
    window.alert(a);

//---------> 같은 출력
a>b && window.alert(a);
a<=b && window.alert(a);
```



```javascript
window.alert()	// 경고메세지를 출력 하는 서브창을 디스플레이 
    			//-> 서브창을 클릭 안하면 아무것도 안됨
window.prompt()	// 사용으로부터 데이터를 입력받는 서브창을 디스플레이
window.confrim()	//확인받는 목적으로 사용되는 API로서
					//Yes/no형식으로 데이터를 입력받는 서브창을 디스플레이
```



### 배열

1. 다양한 타입의 데이터를 하나의 배열에 구성 가능
2. 배열 생성 후에도 크기 변경 가능



- 생성

```js
//리터럴을 이용해 생성
var a1 = [];
var a2 = [10,20,30];
var a3 = [true,'가나다',100];

a1.length
a2.length
a3.length
a3[1]
// API를 이용해 생성
var a4 = new Array();
var a5 = new Array(10);		//10이라는 배열의 크기 지정, int형 매개변수가 1개일 경우에 한해
var a6 = new Array(10,20);	// 배열을 생성해 10,20을 넣어라
var a7 = new Array(true,3.5,'aaa','aa');
```



```js
var fruits = [10,3,6,20,7];
document.getElementById("demo").innerHTML = fruits;

function myFunction() {
  fruits.sort();
  document.getElementById("demo").innerHTML = fruits;
}
//10,20,3,6,7 이 출력된다. 문자열을 기본으로 생각하기 때문에 '10','20' 같이 생각하기 때문 

//방법
var fruits = [10,3,6,20,7];
document.getElementById("demo").innerHTML = fruits;

function myFunction() {
  fruits.sort(function(a, b){return a-b});
  document.getElementById("demo").innerHTML = fruits;
}
//3,6,7,10,20

//역순
var fruits = [10,3,6,20,7];
document.getElementById("demo").innerHTML = fruits;

function myFunction() {
  fruits.sort(function(a, b){return b-a});
  document.getElementById("demo").innerHTML = fruits;
}
//20,10,7,6,3
```



```js
for(var i in a1)  // for(int data : ary)
	document.write("<h4>"+ a1[i] +"</h4>");
//for each 문은 undefined 아닌 값만 뽑는다. undefined 인 부분은 그냥 넘어간다.
```





## 함수와 매서드

- 함수 : 수행 문장을 담고 있는 호출 가능 모듈, 단독으로 호출 가능
- 매서드 : 수행문장들을 담고 있는 호출 가능 모듈, 객체를 통해서만 호출 가능, 객체 멤버 정의 되는 함수
- 전역코드 : scrpit 수행 문장들



### 선언적(명시적) 함수 정의

```js
function 함수이름([매개변수]) {
    ....
}
```



### 리터럴(표현식) 함수 정의

```js
function([매개변수]) {
    
}(아규먼트);
// 즉시 실행 되는 함수이다.
함수명([아규먼트]);
```



함수는 리턴 값이 없더라도 무조건 undefined가 출력된다



``` js
	var obj ={
			name : "듀크",
			eat : function(food) {
				writeColor(this.name + "가 " + food + "를 먹어요!!","h3","green");
			}
	}
	obj.eat("바나나");
	obj.eat("딸기");

	hr();
	
	writeColor(typeof obj,'h2',"red");
	obj.project = "자바 스크립트";
	obj.study = function() {
		writeColor(this.name + "가 " + obj.project + "를 동부해요!!","h3","magenta");
	}
	obj.study();

	hr();
	
	for(var key in obj)
		write(key + ":" + obj[key],"h4");

	hr();

	write(obj.project,"h4");
	write(obj["project"],"h4");
```

자바 스크립트는 같은 멤버 안에 있는 인스턴스 접근할때 무조건 this를 붙혀야 한다.

객체가 이미 선언 한 다음에 인스턴스를 추가하는게 가능하다.

이런식으로 일반 객체도 전부 뽑아 내는게 가능하다.

키 값을 직접 줘서 접근도 가능하다.



### prototype

- 생성자 함수만 사용이 가능하다.

``` js
function Student(name, sub1, sub2, sub3)
 this.name = name;
 this.sub1 = sub1;
 this.sub2 = sub2;
 this.sub3 = sub3;
}
Student.prototype.getName = function() {
 return this.name;
}
Student.prototype.geSum = function() {
 return this.sub1+this.sub2+this.sub3;
}
Student.prototype.getAvg = function() {
 this.getSum() / 3;
}
```

- student 안에 매서드를 선언한다면 각각 객체마다 매서드 영역이 할당 되지만, prototype을 사용한다면 하나의 매서드만 선언되고 모두가 공유하게 된다. static과 조금은 비슷하다고 생각하면 된다.





### BOM

- window,document,location(다른 웹 페이지로 이동 가능한 기능), history(뒤로가고 앞으로 가고, 브라우저 방문 후 정볼르 담고 있다.), navigator(브라우저 정보를 추출), screen

#### 꼭 기억해야 되는 명령어

- location.href : 페이지 이동을 구현하고자 할 때
- location.reload() : 새로고침을 요청한다.



```html
<body>
<form name="fm">
	<select id="choice" onchange="go();">
        <!-- onchange는 change 이벤트가 발생하면 ""안의 함수를 실행시켜라-->
		<option value="">---관심있는 기술을 선택해 주세요---</option>
		<option value="http://www.w3schools.com/js/default.asp">Learn JavaScript</option>
		<option value="http://www.w3schools.com/js/js_htmldom.asp">
            											Learn HTML DOM</option>
		<option value="http://www.w3schools.com/jquery/default.asp">Learn jQuery</option>
		<option value="http://www.w3schools.com/xml/ajax_intro.asp">Learn AJAX</option>
		<option value="http://www.w3schools.com/js/js_json_intro.asp">Learn JSON</option>
	</select>
</form>
<script>
function go(){	
    //옵션으로 선택된 주소의 값을 추출해서 location.href에 넣는다.
    //location.href 주소를 이동하는 bom 명령어
    
	//location.href = document.getElementById("choice").value;
    // document.getElementById 의 ()안의 아이디 즉 # 값을 얻어온다
	location.href = document.querySelector("#choice").value;
    // document.querySelector() ()안의  제공한 선택자 또는 선택자 뭉치와 일치하는 문서 내 첫 번째 Element를 반환합니다. 일치하는 요소가 없으면 null을 반환합니다.
	//location.href = "http://www.naver.com/";
}
</script>
</body>
```



```html
<script>
	write(navigator.platform, "h3");
    // 브라우저에 대한 정보를 얻어온다.
	write(navigator.userAgent, "h3");
    // 이용자의 정보를 얻어온다.
	var str = navigator.userAgent;
	if (str.match(/(ipad)|(iphone)|(ipod)|(android)|(webos)/i))
		write("모바일 디바이스 이군요", "h2");
	else
		write("모바일 디바이스가 아니군요", "h2");
</script>
```



- navigator.userAgent