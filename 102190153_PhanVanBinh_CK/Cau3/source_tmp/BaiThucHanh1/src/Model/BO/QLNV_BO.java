package Model.BO;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.BEAN.NhanVien;
import Model.DAO.QLNV_DAO;

public class QLNV_BO {

	QLNV_DAO qlnvDAO = new QLNV_DAO();
	
	public ArrayList<NhanVien> getNVList() throws SQLException
	{
		return qlnvDAO.getAllNhanVien();
	}
	public ArrayList<NhanVien> getNV_IDPB(String id) throws SQLException
	{
		return qlnvDAO.getAllNhanVienIDPB(id);
	}
	public ArrayList<NhanVien> searchNhanVien(String find, String name) throws SQLException
	{
		return qlnvDAO.searchNhanVien(find,name);
	}
	
	public void insertNV( String IdNV, String name, String IdPB, String address) throws SQLException
	{
		NhanVien nv = new NhanVien( IdNV,  name,  IdPB,  address);
		qlnvDAO.insertNhanVien(nv);
	}
	
	public void deleteNV(String id) throws SQLException
	{
		 qlnvDAO.deleteNhanVien(id);
	}
}
