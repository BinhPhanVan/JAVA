<%@page import="Model.BEAN.SinhVien"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>XemSinhVien</title>
</head>
<body>
   <h2>DANH SÁCH SINH VIÊN</h2>
   <table border="1">
   <tr>
      <td>IDNV</td>
      <td>Họ và tên</td>
      <td>IDPB</td>
      <td>Địa Chỉ</td>
      
   
   </tr>
   <%
   ArrayList<SinhVien> ListNhanVienTK =(ArrayList<SinhVien>)request.getAttribute("ListSinhVien");
   for (int i=0;i<ListNhanVienTK.size(); i++) {
   %>
   <tr>
      <td> <%= ListNhanVienTK.get(i).getMSSV() %></td>
      <td> <%= ListNhanVienTK.get(i).getNameSV() %></td>
      <td> <%= ListNhanVienTK.get(i).getGender() %></td>
      <td> <%= ListNhanVienTK.get(i).getMaKhoa() %></td>
   </tr>
   <% } %>
  </table>
  <br>
  <a href="<%=request.getContextPath()%>/TimKiemNhanVien.jsp" ><span>Quay lại</span></a>
</body>
</html>