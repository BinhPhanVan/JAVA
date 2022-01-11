<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="Model.BEAN.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div >
   <h2>PHÒNG BAN</h2>

 <%PhongBan pb =(PhongBan)request.getAttribute("pb"); %> 
        <form action="updatePhongBan" method="post">
            <label>IDPB</label>
            <br>
            <input type="text"  name="id" value=<%= pb.getIDPB() %>>  
            <br>
            <label>Tên Phòng Ban</label>
            <br> 
            <input type="text"  name="name" value=<%= pb.getTenPB() %>>
            <br>                 
            <label>Mô tả</label> 
            <br>
            <input type="text"  name="address" value=<%= pb.getMoTa() %> />
            <br>
        	<br>
            <button type="submit" class="btn btn-success">Update</button>
        </form>
        <br>
              
		<%
		if(session.getAttribute("username")!="")
		{
			%>
			<a href="<%=request.getContextPath()%>/Admin.jsp" ><span>Home</span></a>
		<%}
		else
		{
			 %>
		 	<a href="<%=request.getContextPath()%>/index.jsp" ><span>Home</span></a>
		<%}
		
		%>     
  </div>
</body>
</html>