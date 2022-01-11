package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.BEAN.PhongBan;

public class QLPB_DAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/qlnv";
	private String jdbcUsername = "root";
	private String jdbcPassWord = "";
	
	private static final String SELECT_All_PhongBan ="select * from phongban";
	private static final String SELECT_PhongBan ="select * from phongban where IDPB = ? ;";
	private static final String Update_PhongBan = "Update phongban set TenPB=?, MoTa=? where IDPB = ?";

	protected Connection getConnection()
	{
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassWord);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return connection;
	}
	
	public boolean updatePhongBan(PhongBan phongban) throws SQLException
	{
		boolean Updaterow;
		Connection connection = getConnection();
		PreparedStatement  prepare = connection.prepareStatement(Update_PhongBan);
		prepare.setString(1, phongban.getTenPB());
		prepare.setString(2, phongban.getMoTa());
		prepare.setString(3, phongban.getIDPB());
		Updaterow =  prepare.executeUpdate() > 0;
		return Updaterow;
	}
	
	public ArrayList<PhongBan> selectAllPB() throws SQLException
	{
		ArrayList<PhongBan> result =new ArrayList<PhongBan>();
		Connection connection = getConnection();
		PreparedStatement prepare = connection.prepareStatement(SELECT_All_PhongBan);
		ResultSet rs = prepare.executeQuery();
		while(rs.next())
		{
			PhongBan pb = null;
			String IDPB = rs.getString("IDPB");
			String TenPB = rs.getString("TenPB");
			String MoTa = rs.getString("MoTa");
			pb = new PhongBan(IDPB, TenPB, MoTa);
			result.add(pb);
		}
		return result;
	}
	public PhongBan selectPB_ID(String ID) throws SQLException
	{
		Connection connection = getConnection();
		PreparedStatement prepare = connection.prepareStatement(SELECT_PhongBan);
		prepare.setString(1, ID);
		ResultSet rs = prepare.executeQuery();
		PhongBan pb = null;
		while(rs.next())
		{
			String IDPB = rs.getString("IDPB");
			String TenPB = rs.getString("TenPB");
			String MoTa = rs.getString("MoTa");
			pb = new PhongBan(IDPB, TenPB, MoTa);
		}
		return pb;
	}
	
}
