package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.BEAN.NhanVien;

public class checkLogin_DAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/qlnv";
	private String jdbcUsername = "root";
	private String jdbcPassWord = "";
	
	private static final String SELECT_admin ="select * from admin where username = ? and password = ?";
	
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
	public boolean checkAdmin(String user,  String pass) throws SQLException
	{
		Connection connection = getConnection();
		PreparedStatement prepare = connection.prepareStatement(SELECT_admin);
		prepare.setString(1, user);
		prepare.setString(2, pass);
		ResultSet rs = prepare.executeQuery();
		while(rs.next())
		{
			return true;
		}
		return false;
	}
	
	
	
}
