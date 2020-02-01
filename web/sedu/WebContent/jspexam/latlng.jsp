<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${ param.pos == null }">
			<h2>위치를 입력하십시오</h2>
			<hr>
			<form method="GET" action="/sedu/jspexam/latlng.jsp">
				위치정보 : <input type="text" name="pos"> <input type="submit"
					value="알아보기">
			</form>
		</c:when>
		<c:otherwise>
			<c:catch var="err">
				<h2>위치를 입력하십시오</h2>
				<hr>
				<c:import var="location"
					url="https://maps.googleapis.com/maps/api/geocode/xml">
					<c:param name="address" value="${ param.pos }" />
					<c:param name="language" value="ko" />
					<c:param name="key" value="AIzaSyD8k2DWC_7yFHCrH6LDR3RfITsmWMEqC8c" />
				</c:import>
				<x:parse xml="${location}" var="location" />
				<x:choose>
					<x:when select="$location//status = 'OK'">
						주소 : <x:out select="$location//formatted_address" />
						<br>
						위도 : <x:out select="$location//lat" />
						<br>
						경도 : <x:out select="$location//lng" />
						<br>
					</x:when>
					<x:when select="$location//status = 'INVALID_REQUEST'">
						<h2>주소를 전달하세요.</h2>
					서버로 부터 전달된 오류 메시지 : Invalid request. Missing the 'address', 'components', 'latlng' or 'place_id' parameter.
				</x:when>
					<x:when select="$location//status = 'REQUEST_DENIED'">
						<h2>서비스 사용허가를 받지 못했습니다.</h2>
					서버로 부터 전달된 오류 메시지 : The provided API key is invalid.
				</x:when>
					<x:otherwise>오류가 발생했어요....</x:otherwise>
				</x:choose>
			</c:catch>
			<c:if test="${ !empty err }">
				<h3>오류 발생 : ${err}</h3>
			</c:if>
			<hr>
		</c:otherwise>
	</c:choose>
</body>
</html>