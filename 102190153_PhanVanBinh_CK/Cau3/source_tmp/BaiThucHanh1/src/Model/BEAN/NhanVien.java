package Model.BEAN;

public class NhanVien {
	private String IDNV;
	private String HoTen;
	private String IDPB;
	private String DiaChi;
	
	public NhanVien() {
		this.IDNV = "";
		this.HoTen = "";
		this.DiaChi = "";
		this.IDPB = "";
	}
	public NhanVien(String idnv, String hoten, String idpb, String diachi) {
		this.IDNV = idnv;
		this.HoTen = hoten;
		this.DiaChi = diachi;
		this.IDPB = idpb;
	}
	public void setIDNV(String id)
	{
		this.IDNV = id;
	}
	public String getIDNV()
	{
		return this.IDNV;
	}
	public void setHoTen(String hoten)
	{
		this.HoTen = hoten;
	}
	public String getHoTen()
	{
		return this.HoTen;
	}
	public void setIDPB(String id)
	{
		this.IDPB = id;
	}
	public String getIDPB()
	{
		return this.IDPB;
	}
	public void setDiaChi(String diachi)
	{
		this.DiaChi = diachi;
	}
	public String getDiaChi()
	{
		return this.DiaChi;
	}
}
