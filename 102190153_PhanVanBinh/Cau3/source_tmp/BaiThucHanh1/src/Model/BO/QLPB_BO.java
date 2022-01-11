package Model.BO;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.BEAN.PhongBan;
import Model.DAO.QLPB_DAO;

public class QLPB_BO {
	
	QLPB_DAO qlpbDAO = new QLPB_DAO();
	
	public ArrayList<PhongBan> selectAllPB() throws SQLException
	{
		return qlpbDAO.selectAllPB();
	}
	public PhongBan selectPB_ID(String id) throws SQLException
	{
		return qlpbDAO.selectPB_ID(id);
	}
	public boolean updatePhongBan(String iDPB,String tenPB, String moTa) throws SQLException
	{
		PhongBan phongban = new PhongBan(iDPB, tenPB, moTa);
		return qlpbDAO.updatePhongBan(phongban);
		
	}

}
