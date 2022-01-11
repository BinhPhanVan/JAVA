package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.BEAN.NhanVien;

public class QLNV_DAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/qlnv";
	private String jdbcUsername = "root";
	private String jdbcPassWord = "";
	
	private static final String SELECT_All_NhanVien ="select * from nhanvien";
	private static final String SELECT_NhanVien_IDPB = "select * from nhanvien where IDPB= ?";
	private static final String DELETE_NhanVien = "Delete from nhanvien where IDNV=?";
	private static final String Update_NhanVien = "Update nhanvien set HoTen=?, DiaChi=?, IDPB=? where IDNV = ?";
	private static final String Insert_NhanVien = "insert into nhanvien" + "(IDNV, HoTen, IDPB, DiaChi) VALUES" + 
													"(?, ?, ?, ?);";
	
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
	
	public boolean updateNhanVien(NhanVien nhanvien) throws SQLException
	{
		boolean Updaterow;
		Connection connection = getConnection();
		PreparedStatement  prepare = connection.prepareStatement(Update_NhanVien);
		prepare.setString(4, nhanvien.getIDNV());
		prepare.setString(1, nhanvien.getHoTen());
		prepare.setString(2, nhanvien.getIDPB());
		prepare.setString(3, nhanvien.getDiaChi());
		Updaterow =  prepare.executeUpdate() > 0;
		return Updaterow;
	}
	
	public ArrayList<NhanVien> searchNhanVien(String find,  String content) throws SQLException
	{
		ArrayList<NhanVien> result =new ArrayList<NhanVien>();
		Connection connection = getConnection();
		String SELECT_NhanVien_Search = "select * from nhanvien where "+find+" = ?;";
		PreparedStatement prepare = connection.prepareStatement(SELECT_NhanVien_Search);
		prepare.setString(1, content);
		ResultSet rs = prepare.executeQuery();
		while(rs.next())
		{
			NhanVien nv = new NhanVien();
			String IDNV = rs.getString("IDNV");
			String HoTen = rs.getString("HoTen");
			String DiaChi = rs.getString("DiaChi");
			String IDPB = rs.getString("IDPB");
			nv = new NhanVien(IDNV, HoTen, IDPB, DiaChi);
			result.add(nv);
		}
		return result;
	}
	
	public ArrayList<NhanVien> getAllNhanVien() throws SQLException
	{
		ArrayList<NhanVien> result =new ArrayList<NhanVien>();
		Connection connection = getConnection();
		PreparedStatement  prepare = connection.prepareStatement(SELECT_All_NhanVien);
		
		 ResultSet resultSet = prepare.executeQuery();
	        while (resultSet.next())
	        {
	        	NhanVien sv = new NhanVien();
	        	sv.setIDNV(resultSet.getString("IDNV"));
	        	sv.setHoTen(resultSet.getString("HoTen"));
	        	sv.setIDPB((resultSet.getString("IDPB")));
	        	sv.setDiaChi(resultSet.getString("DiaChi"));
	        	result.add(sv);
	        }     
		return result;
	}
	
	public ArrayList<NhanVien> getAllNhanVienIDPB(String idpb) throws SQLException
	{
		ArrayList<NhanVien> result =new ArrayList<NhanVien>();
		Connection connection = getConnection();
		PreparedStatement  prepare = connection.prepareStatement(SELECT_NhanVien_IDPB);
		prepare.setString(1, idpb);
		 ResultSet resultSet = prepare.executeQuery();
	        while (resultSet.next())
	        {
	        	NhanVien sv = new NhanVien();
	        	sv.setIDNV(resultSet.getString("IDNV"));
	        	sv.setHoTen(resultSet.getString("HoTen"));
	        	sv.setIDPB((resultSet.getString("IDPB")));
	        	sv.setDiaChi(resultSet.getString("DiaChi"));
	        	result.add(sv);
	        }     
		return result;
	}
	public void insertNhanVien(NhanVien nhanvien) throws SQLException
	{
		Connection connection = getConnection();
		PreparedStatement  prepare = connection.prepareStatement(Insert_NhanVien);
		prepare.setString(1, nhanvien.getIDNV());
		prepare.setString(2, nhanvien.getHoTen());
		prepare.setString(3, nhanvien.getIDPB());
		prepare.setString(4, nhanvien.getDiaChi());
		prepare.executeUpdate();
	}
	public void deleteNhanVien(String idnv) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement  prepare = connection.prepareStatement(DELETE_NhanVien);
		prepare.setString(1, idnv);
		prepare.executeUpdate();
	}
	
	
}
