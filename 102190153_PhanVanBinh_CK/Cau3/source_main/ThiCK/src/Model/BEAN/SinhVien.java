package Model.BEAN;

public class SinhVien {
	private int MSSV;
	private String NameSV;
	private int Gender;
	private int MaKhoa;
	public int getMSSV() {
		return MSSV;
	}
	public void setMSSV(int mSSV) {
		MSSV = mSSV;
	}
	public String getNameSV() {
		return NameSV;
	}
	public void setNameSV(String nameSV) {
		NameSV = nameSV;
	}
	public int getGender() {
		return Gender;
	}
	public void setGender(int gender) {
		Gender = gender;
	}
	public int getMaKhoa() {
		return MaKhoa;
	}
	public void setMaKhoa(int maKhoa) {
		MaKhoa = maKhoa;
	}
	public SinhVien(int mSSV, String nameSV, int gender, int maKhoa) {
		super();
		MSSV = mSSV;
		NameSV = nameSV;
		Gender = gender;
		MaKhoa = maKhoa;
	}
	public SinhVien() {
		super();
	}
	
	
	
}
