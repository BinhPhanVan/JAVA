
<%@page import="Model.BEAN.PhongBan"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>XemPhongBan</title>
</head>
<body>
   <h2>DANH SÁCH PHÒNG BAN</h2>
   <table border="1">
   <tr>
      <td>IDPB</td>
      <td>Tên Phòng Ban</td>
      <td>Mô Tả</td>
      <td>Danh sách</td>
      
   
   </tr>
   <%
   ArrayList<PhongBan> ListPhongBan =(ArrayList<PhongBan>)request.getAttribute("ListPhongBan");
   for (int i=0;i<ListPhongBan.size(); i++) {
   %>
   <tr>
      <td> <%= ListPhongBan.get(i).getIDPB() %></td>
      <td> <%= ListPhongBan.get(i).getTenPB() %></td>
      <td> <%= ListPhongBan.get(i).getMoTa() %></td>
      <td><a href="listNVPB?IDPB=<%=  ListPhongBan.get(i).getIDPB()%>" ><span>Xem danh sách</span></a></td>
   </tr>
   <% } %>
  </table>
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
</body>
</html>