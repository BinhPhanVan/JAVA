package Model.BO;

import java.sql.SQLException;

import Model.DAO.checkLogin_DAO;

public class checkLogin_BO {
	checkLogin_DAO checkLoginDAO =new checkLogin_DAO();
	public boolean isValidUser(String userName, String password) throws SQLException
	{
		return checkLoginDAO.checkAdmin(userName, password);
	}
}
