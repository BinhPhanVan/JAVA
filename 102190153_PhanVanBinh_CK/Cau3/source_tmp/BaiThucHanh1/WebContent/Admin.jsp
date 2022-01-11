<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<style type="text/css">
	a:hover
	{
		color:blue;
	}
	a:active
	{
		color:blue;
	}
</style>
</head>

<body>
	<h2 style="color: blue;">CHỨC NĂNG ADMIN</h2>
	<br>
	<a href="<%=request.getContextPath()%>/ShowNhanVien" ><span>XEM THÔNG TIN NHÂN VIÊN</span></a> <br>
	
    <a href="<%=request.getContextPath()%>/findNV" ><span>TÌM KIẾM NHÂN VIÊN</span></a> <br>
    
    <a href="<%=request.getContextPath()%>/ShowListPB"><span>XEM THÔNG TIN PHÒNG BAN</span></a> <br>
     
    <a href="<%=request.getContextPath()%>/newNV" ><span>THÊM NHÂN VIÊN</span></a> 
    <br>
    <a href="<%=request.getContextPath()%>/updatePB" ><span>CẬP NHẬT THÔNG TIN PHÒNG BAN</span></a> 
    <br>
    <a href="<%=request.getContextPath()%>/delNV" ><span>XÓA NHÂN VIÊN</span></a> 
    <br>
    <a href="<%=request.getContextPath()%>/delAllNV" ><span>XÓA NHIỀU NHÂN VIÊN</span></a>
    <br>
    <a href="<%=request.getContextPath()%>/login.jsp" ><span>ĐĂNG XUẤT</span></a>
</body>
</html>