package Model.BO;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.BEAN.SinhVien;
import Model.DAO.QuanLy_DAO;


public class QuanLy_BO {

	QuanLy_DAO qlnvDAO = new QuanLy_DAO();
	
	public ArrayList<SinhVien> getSVList() throws SQLException
	{
		return qlnvDAO.getAllSinhVien();
	}
}