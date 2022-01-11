<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<%
		session.setAttribute("username", "");
		session.setAttribute("password", "");
	%>
	<h2 class="title">ĐĂNG NHẬP</h2>
	<form action="checkLogin" method ="post">
		Username: 
		<br>
		<input class = "_input" type="text" name="username">
		<br>
		Password: 
		<br>
		<input class = "_input" type="password" name="password">
		<br><br>
		<input class = "btn" type="submit" value="LOGIN">
		<input class = "btn" type="reset" value="Reset">
	</form>
	<br>
	<a href="<%=request.getContextPath()%>/index.jsp" ><span>Home</span></a> 

</body>
</html>