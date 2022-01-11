
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
   <h2>XEM THÔNG TIN PHÒNG BAN CẬP NHẬT</h2>
   <table border="1">
   <tr>
      <td>IDPB</td>
      <td>Tên Phòng Ban</td>
      <td>Mô Tả</td>
      <td>Cập nhật</td>
      
   
   </tr>
   <%
   ArrayList<PhongBan> ListPhongBan = (ArrayList<PhongBan>)request.getAttribute("ListPhongBan");
   for (int i=0;i<ListPhongBan.size(); i++) {
   %>
   <tr>
      <td> <%= ListPhongBan.get(i).getIDPB() %></td>
      <td> <%= ListPhongBan.get(i).getTenPB() %></td>
      <td> <%= ListPhongBan.get(i).getMoTa() %></td>
      <td><a href="listNVPBUpdate?IDPB=<%=  ListPhongBan.get(i).getIDPB()%>" ><span>Cập nhật</span></a></td>
   </tr>
   <% } %>
  </table>
  <br>
  <a href="<%=request.getContextPath()%>/Admin.jsp" ><span>Home</span></a>
</body>
</html>