package Model.BEAN;

public class Khoa {
	private int MaKhoa;
	private String TenKhoa;
	
	public Khoa(int maKhoa, String tenKhoa) {
		super();
		MaKhoa = maKhoa;
		TenKhoa = tenKhoa;
	}
	public int getMaKhoa() {
		return MaKhoa;
	}
	public void setMaKhoa(int maKhoa) {
		MaKhoa = maKhoa;
	}
	public String getTenKhoa() {
		return TenKhoa;
	}
	public void setTenKhoa(String tenKhoa) {
		TenKhoa = tenKhoa;
	}
}
