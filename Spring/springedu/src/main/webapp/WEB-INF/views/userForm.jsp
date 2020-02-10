<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enter member information</title>
</head>
<body>
<form action="/springedu/userSave" method="post">
 <table border="1">
  <tr>
   <td>Name</td>
   <td><input type="text" name="userName" value=""/></td>
  </tr>
  <tr>
   <td>Address</td>
   <td><input type="text" name="address" value=""/></td>
  </tr>
  <tr>
   <td>Favorite Fruit</td>
   <td>
   
   <c:forEach var="fruit" items="${popularFruit}">
    <input type="checkbox" name="favoriteFruit" value="${fruit}"/>${fruit}<br>
   </c:forEach>
   </td>
  </tr>
 </table>
 <input type="submit" value="SEND"/>
</form>
</body>
</html>    