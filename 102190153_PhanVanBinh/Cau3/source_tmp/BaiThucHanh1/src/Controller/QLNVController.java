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

import Model.BEAN.NhanVien;
import Model.BEAN.PhongBan;
import Model.BO.QLNV_BO;
import Model.BO.QLPB_BO;


@WebServlet("/")
public class QLNVController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    private static QLNV_BO qlnv = new QLNV_BO();
    private static QLPB_BO qlpb = new QLPB_BO();

	
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
		case "/ShowListPB": {
			try {
				showPhongBan(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/findNV": {
			showFindForm(request,response);
			break;
		}
		case "/searchNhanVien": {
			try {
				showFindNhanVien(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/listNVPB": {
			try {
				showListNVPB(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/newNV": {
			try {
				showFormInsert(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/insertNhanVien": {
			try {
				InsertNhanVien(request,response);
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/updatePB": {
			try {
				showUpdateForm(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/listNVPBUpdate": {
			try {
				showUpdateFormDetail(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/updatePhongBan": {
			try {
				updatePhongBan(request,response);
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/delNV": {
			try {
				FormDelete(request,response);
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/delete": {
			try {
				showListNVDelete(request,response);
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/delAllNV": {
			try {
				showDeleteAll(request,response);
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/deleteAll": {
			try {
				DeleteAll(request,response);
			} catch (IOException | SQLException e) {
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
		ArrayList<NhanVien> ListNhanVien = qlnv.getNVList();
        request.setAttribute("ListNhanVien", ListNhanVien);
        RequestDispatcher dispatcher = request.getRequestDispatcher("XemNhanVien.jsp");
        dispatcher.forward(request, response);
    }
	private void FormDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
		ArrayList<NhanVien> ListNhanVien = qlnv.getNVList();
        request.setAttribute("ListNhanVien", ListNhanVien);
        RequestDispatcher dispatcher = request.getRequestDispatcher("XoaNhanVien.jsp");
        dispatcher.forward(request, response);
    }
	private void showDeleteAll(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
		ArrayList<NhanVien> ListNhanVien = qlnv.getNVList();
        request.setAttribute("ListNhanVien", ListNhanVien);
        RequestDispatcher dispatcher = request.getRequestDispatcher("XoaNhieuNhanVien.jsp");
        dispatcher.forward(request, response);
    }
	
    
	private void showListNVPB(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
		String id = (request.getParameter("IDPB"));
		ArrayList<NhanVien> ListNhanVien = qlnv.getNV_IDPB(id);
        request.setAttribute("ListNhanVien", ListNhanVien);
        RequestDispatcher dispatcher = request.getRequestDispatcher("XemNhanVien.jsp");
        dispatcher.forward(request, response);
    }
	
	private void showPhongBan(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
		ArrayList<PhongBan> ListPhongBan = qlpb.selectAllPB();
        request.setAttribute("ListPhongBan", ListPhongBan);
        RequestDispatcher dispatcher = request.getRequestDispatcher("XemPhongBan.jsp");
        dispatcher.forward(request, response);
    }
			
	private void showFindForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("TimKiemNhanVien.jsp");
        dispatcher.forward(request, response);
    }
	private void showFindNhanVien(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
		String find = request.getParameter("select-find");
        String name = request.getParameter("infor-find");
		ArrayList<NhanVien> ListNhanVienTK = qlnv.searchNhanVien(find, name);
        request.setAttribute("ListNhanVienTK", ListNhanVienTK);
        RequestDispatcher dispatcher = request.getRequestDispatcher("XemNhanVienTK.jsp");
        dispatcher.forward(request, response);
    }
	private void showFormInsert(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
		ArrayList<PhongBan> ListPhongBan = qlpb.selectAllPB();
        request.setAttribute("ListPhongBan", ListPhongBan);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ThemNhanVien.jsp");
        dispatcher.forward(request, response);
    }
	private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
		ArrayList<PhongBan> ListPhongBan = qlpb.selectAllPB();
        request.setAttribute("ListPhongBan", ListPhongBan);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CapNhatPhongBan.jsp");
        dispatcher.forward(request, response);
    }
	
	private void showUpdateFormDetail(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
		String id = request.getParameter("IDPB");
		PhongBan pb = qlpb.selectPB_ID(id);
        request.setAttribute("pb", pb);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CapNhatPhongBan_detail.jsp");
        dispatcher.forward(request, response);
    }
	
	private void InsertNhanVien(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        String name = request.getParameter("NAME");
    	        String address = request.getParameter("ADDRESS");
    	        String IdNV = request.getParameter("IDNV");
    	        String IdPB = request.getParameter("IDPB");
    	        qlnv.insertNV(IdNV, name, IdPB, address);
    	        response.sendRedirect("ShowNhanVien");
    	    }
	private void updatePhongBan(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        String id =(request.getParameter("id"));
    	        String name = request.getParameter("name");
    	        String address = request.getParameter("address");
    	        qlpb.updatePhongBan(id,name, address);
    	        response.sendRedirect("ShowListPB");
    	    }
	private void showListNVDelete(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
				String id = (request.getParameter("IDNV"));
		        qlnv.deleteNV(id);
		        response.sendRedirect("ShowNhanVien");
    	    }
	private void DeleteAll(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
    	String ID[] =  request.getParameterValues("checkdelete");
        for (String id : ID)
        {
            qlnv.deleteNV(id);

        }
        response.sendRedirect("ShowNhanVien");
    }
    	    

}
