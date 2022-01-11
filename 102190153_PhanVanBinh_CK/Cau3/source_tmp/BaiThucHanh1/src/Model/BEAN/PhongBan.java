package Model.BEAN;

public class PhongBan {
	private String IDPB;
	private String TenPB;
	private String MoTa;
	
	public PhongBan(String iDPB, String tenPB, String moTa) {
		IDPB = iDPB;
		TenPB = tenPB;
		MoTa = moTa;
	}
	public String getIDPB() {
		return IDPB;
	}
	public void setIDPB(String iDPB) {
		IDPB = iDPB;
	}
	public String getTenPB() {
		return TenPB;
	}
	public void setTenPB(String tenPB) {
		TenPB = tenPB;
	}
	public String getMoTa() {
		return MoTa;
	}
	public void setMoTa(String moTa) {
		MoTa = moTa;
	}
	
}
