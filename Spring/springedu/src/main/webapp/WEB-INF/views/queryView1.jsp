<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP for response</title>
</head>
<body>
<h2>From QueryStringController1
                Forward to queryView1.jsp</h2>
<hr>
<h3>Data passed as spring name(EL) : ${ spring }</h3>
<!-- null 이 전달 되었을때 EL은 빈 공간을 출력하고 -->
<h3>Data passed as spring name(Expression Tag) : 
                        <%= request.getAttribute("spring") %></h3>
                        <!-- 자바는 null을 출력한다. -->
</body>
</html>




