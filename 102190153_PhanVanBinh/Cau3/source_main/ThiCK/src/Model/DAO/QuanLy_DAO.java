package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.BEAN.SinhVien;


public class QuanLy_DAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/test888";
	private String jdbcUsername = "root";
	private String jdbcPassWord = "";
	
	private static final String SELECT_All_sinhVien ="select * from nhanvien";
	
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
	public ArrayList<SinhVien> getAllSinhVien() throws SQLException
	{
		ArrayList<SinhVien> result =new ArrayList<SinhVien>();
		Connection connection = getConnection();
		PreparedStatement  prepare = connection.prepareStatement(SELECT_All_sinhVien);
		
		 ResultSet resultSet = prepare.executeQuery();
	        while (resultSet.next())
	        {
	        	SinhVien sv = new SinhVien();
	        	sv.setMSSV(resultSet.getInt("MSSV"));
	        	sv.setNameSV(resultSet.getString("Name"));
	        	sv.setGender((resultSet.getInt("Gender")));
	        	sv.setMaKhoa(resultSet.getInt("MaKhoa"));
	        	result.add(sv);
	        }     
		return result;
	}
	
}
