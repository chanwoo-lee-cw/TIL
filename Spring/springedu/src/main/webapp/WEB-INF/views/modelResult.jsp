<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 테스트 예제</title>
</head>
<body>
<h2>Receiving data from the controller</h2>
<hr>
<ul>
<li>${ v1 }</li>
<li>Array</li>
	<ul>
	<li>${ v2[0] }</li>
	<li>${ v2[1] }</li>
	<li>${ v2[2] }</li>
	<li>${ v2[3] }</li>
	</ul>
<li>${ v3 }</li>
<li>${ v4 }</li>
<li>List</li>
	<ul>
	<li>${ v5[0] }</li>
	<li>${ v5[1] }</li>
	<li>${ v5[2] }</li>	
	</ul>
</ul>
</body>
</html>


