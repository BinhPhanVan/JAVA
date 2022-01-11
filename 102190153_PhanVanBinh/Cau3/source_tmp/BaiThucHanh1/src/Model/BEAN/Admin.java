package Model.BEAN;

public class Admin {
	private String username;
	private String password;
	public Admin(String name, String pass) {
		this.username = name;
		this.password = pass;
	}
	public void setUsername(String name)
	{
		this.username = name;
	}
	public String getUsername()
	{
		return this.username;
	}
	public void setPassword(String pass)
	{
		this.password = pass;
	}
	public String getPassword()
	{
		return this.password;
	}
}
