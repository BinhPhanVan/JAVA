package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import Model.BO.checkLogin_BO;

@WebServlet("/checkLogin")
public class checkLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String destination=null;
		
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");

    	
    	checkLogin_BO checkLoginBO= new checkLogin_BO();

    	try {
			if (checkLoginBO.isValidUser(username, password))
			{   		
				destination ="/Admin.jsp";	
				HttpSession session = request.getSession();
		    	session.setAttribute("username", username);
		    	session.setAttribute("password", password);
			}
			else
			{
				destination="/login.jsp";
				HttpSession session = request.getSession();
		    	session.setAttribute("username", "");
		    	session.setAttribute("password", "");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	RequestDispatcher rd =getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	}

}
