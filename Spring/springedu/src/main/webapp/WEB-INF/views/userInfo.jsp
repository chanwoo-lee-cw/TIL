<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Output member information</title>
</head>
<body>
<h2>${msg}</h2>
<hr>
<ul>
<li>Name : ${ userVO.userName } </li>
<li>Address : ${ userVO.address } </li>
<li>
Favorite Fruit : <c:forEach var="fruit" items="${userVO.favoriteFruit}">
                   ${fruit} &nbsp;
                 </c:forEach>
</ul>
</body>
</html>