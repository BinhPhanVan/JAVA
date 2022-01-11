package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.SinhVien;
import Model.BO.QuanLy_BO;


@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    private static QuanLy_BO qlnv = new QuanLy_BO();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		switch (action) {
		case "/login": {
			showLoginForm(request,response);
			break;
		}
		case "/ShowNhanVien": {
			try {
				showNhanvien(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
		default:
			break;
		}
	}
	private void showLoginForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
	private void showNhanvien(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
		ArrayList<SinhVien> ListNhanVien = qlnv.getSVList();
        request.setAttribute("ListSinhVien", ListNhanVien);
        RequestDispatcher dispatcher = request.getRequestDispatcher("XemSinhVien.jsp");
        dispatcher.forward(request, response);
    }
	
}
