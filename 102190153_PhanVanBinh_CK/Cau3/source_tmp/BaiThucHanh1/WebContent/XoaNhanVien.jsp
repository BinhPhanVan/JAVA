<%@page import="Model.BEAN.NhanVien"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>XoaNhanVien</title>
</head>
<body>
   <h2>XÓA THÔNG TIN NHÂN VIÊN</h2>
   <table border="1">
   <tr>
      <td>IDNV</td>
      <td>Họ và tên</td>
      <td>IDPB</td>
      <td>Địa Chỉ</td>
      <td>Xóa nhân viên</td>
      
   
   </tr>
   <%
   ArrayList<NhanVien> ListNhanVien =(ArrayList<NhanVien>)request.getAttribute("ListNhanVien");
   for (int i=0;i<ListNhanVien.size(); i++) {
   %>
   <tr>
      <td> <%= ListNhanVien.get(i).getIDNV() %></td>
      <td> <%= ListNhanVien.get(i).getHoTen() %></td>
      <td> <%= ListNhanVien.get(i).getIDPB() %></td>
      <td> <%= ListNhanVien.get(i).getDiaChi() %></td>
      <td> <a href="delete?IDNV=<%= ListNhanVien.get(i).getIDNV() %>">Xóa</a></td>
   </tr>
   <% } %>
  </table>
  <br>
  <a href="<%=request.getContextPath()%>/Admin.jsp" ><span>Quay lại</span></a>
</body>
</html>