<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ page language="java" import="java.util.ArrayList" %>   
 <html>
 <body>
     <div >
           <h2>TÌM KIẾM NHÂN VIÊN</h2>
        
                 <form action="searchNhanVien" method="post">
                                      
                     <label>Title: </label> 
                     <select name="select-find">
                       <option value="IDNV">IDNV</option>
                       <option value="HoTen">HoTen</option>
                       <option value="IDPB">IDPB</option>
                       <option value="DiaChi">DiaChi</option>
                     </select>
                       <br><br>
                     <label>Nhập thông tin: </label> <input type="text"  name="infor-find" /><br>
                 	 <br>
                    <button type="submit" class="btn">Search</button>
                    <button type="reset" class="btn">Reset</button>
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