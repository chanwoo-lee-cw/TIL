<%@ page contentType="text/html; charset=utf-8"   %>
<!DOCTYPE html>
<html>
<HEAD>
<meta charset="UTF-8">
<TITLE>forward 액션 태그를 이용한 분기 예제  </TITLE>
</HEAD>
<BODY>
<% if(request.getParameter("type")==null || 
           request.getParameter("type").equals("admin") ){ %>
  <jsp:forward page="admin_new.jsp">
  <!-- request foward와 똑같다 -->
  	<jsp:param name="message" value="Hi! Administrator" />
  	<!-- /를 줘서 꼭 닫는다는 표시를 해줘야 한다 -->
  </jsp:forward>
<% } else  {%>
  <jsp:forward page="user_new.jsp">
  	<jsp:param name="message" value="Hi! User" />
  </jsp:forward>
<%} %> 
</BODY>
</HTML>
