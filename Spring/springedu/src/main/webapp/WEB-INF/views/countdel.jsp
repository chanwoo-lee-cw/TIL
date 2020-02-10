<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.CountVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP test example</title>
</head>
<body>
<h2>Complete Result</h2>
<hr>
<h3>Objects stored in count1 names :
              <%= session.getAttribute("count1") %></h3>
<h3>Objects stored in count2 names :
              <%= session.getAttribute("count2") %></h3>
</body>
</html>







