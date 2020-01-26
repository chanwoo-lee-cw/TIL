<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



	<%
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
	%>
	<h1><%=request.getParameter("rervname")%>님의 예약내용
	</h1>
	<hr>
	<ul>
		<li>룸 : <%=request.getParameter("rervroom")%></li>
		<li>
			<%
				String option[] = request.getParameterValues("resvoption");
				String str = "";
				if (option != null) {
					for (int i = 0; i < option.length; i++) {
						str += option[i] + ", ";
					}
					out.print(str.substring(0, str.length() - 2));
				} else {
					out.print("없음");
				}
			%>
		</li>
		<li>
			<%
				String[] day = request.getParameter("rervday").split("-");
			
//			String date = request.getParameter("date");
//			String pattern = "(\\d{4})-(\\d{2})-(\\d{2})";
//			Pattern p = Pattern.compile(pattern);
//			Matcher m = p.matcher(date);
//			String datestr="";
//			while(m.find()) {
//				datestr = String.format("%s년%s월%s일", m.group(1), m.group(2), m.group(3));
//			}
//			이런 방법으로도 된다.
			
			%> 예약날짜 : <%=day[0]%>년 <%=day[1]%>월 <%=day[2]%>일
		</li>
	</ul>
	<a href=<%=request.getHeader("referer")%>>입력 화면으로</a>
s
</body>
</html>