<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ page language="java" import="java.util.ArrayList" %>   
<%@ page language="java" import="Model.BEAN.*" %> 
<html>

<body>
    <div >
          <h2> THÊM NHÂN VIÊN </h2>
                <form action="insertNhanVien" method="post">
                    <label>IDNV : </label> 
                    <br>
                    <input type="text" / name="IDNV">  <br>  <br>  
                    <label>NameNV</label>
                    <br>
                    <input type="text" / name="NAME">  <br>  <br>                 
                    <label>IDPB</label>
                    <br> 
                    <select name="IDPB">
                    <%
					  ArrayList<PhongBan> ListPhongBan =(ArrayList<PhongBan>)request.getAttribute("ListPhongBan");
					  for (int i=0;i<ListPhongBan.size(); i++) {
					  %>
					   <option value="<%= ListPhongBan.get(i).getIDPB()   %>"><%= ListPhongBan.get(i).getTenPB()   %>
					   </option>													      							      						      							      
					  <% } %>
                    </select>
                      <br><br>
                    <label>Address</label> <br> <input type="text"  name="ADDRESS" /><br>
                	<br>
                   <button type="submit" class="btn btn-success">Save</button>
                </form>
                <br>
				  <%
				  	if(session.getAttribute("username")!="" )
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