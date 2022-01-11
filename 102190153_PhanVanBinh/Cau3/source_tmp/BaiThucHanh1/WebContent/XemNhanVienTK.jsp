<%@page import="Model.BEAN.NhanVien"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>XemNhanVien</title>
</head>
<body>
   <h2>DANH SÁCH NHÂN VIÊN TÌM KIẾM</h2>
   <table border="1">
   <tr>
      <td>IDNV</td>
      <td>Họ và tên</td>
      <td>IDPB</td>
      <td>Địa Chỉ</td>
      
   
   </tr>
   <%
   ArrayList<NhanVien> ListNhanVienTK =(ArrayList<NhanVien>)request.getAttribute("ListNhanVienTK");
   for (int i=0;i<ListNhanVienTK.size(); i++) {
   %>
   <tr>
      <td> <%= ListNhanVienTK.get(i).getIDNV() %></td>
      <td> <%= ListNhanVienTK.get(i).getHoTen() %></td>
      <td> <%= ListNhanVienTK.get(i).getIDPB() %></td>
      <td> <%= ListNhanVienTK.get(i).getDiaChi() %></td>
   </tr>
   <% } %>
  </table>
  <br>
  <a href="<%=request.getContextPath()%>/TimKiemNhanVien.jsp" ><span>Quay lại</span></a>
</body>
</html>