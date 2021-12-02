<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>detail.jsp</h2>
	${members.m_number}
	${members.m_id}
	${members.m_password}
	${members.m_name}
	${members.m_email}
	${members.m_phone}
	
	<a href ="findAll">목록으로 돌아가기</a>
	
	
	<!--http://localhost:8082/member/detail?m_number=3  -->
	<a href= "/">홈(/)</a>  <!--http://localhost:8082/  -->
	<a href= "./">홈(./)</a> <!-- http://localhost:8082/member/  -->
	<a href= "../">홈(../)</a> <!-- http://localhost:8082/  -->

</body>
</html>