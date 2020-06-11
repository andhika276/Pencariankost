package model;

public class Owner {
	String vownerId;
	String vname;
	String vaddress;
	String vcontact;
	Integer vroomTotal;
	public Owner(String vname, String vaddress, String vcontact, Integer vroomTotal) {
		super();
		this.vname = vname;
		this.vaddress = vaddress;
		this.vcontact = vcontact;
		this.vroomTotal = vroomTotal;
	}
	public String getVownerId() {
		return vownerId;
	}
	public void setVownerId(String vownerId) {
		this.vownerId = vownerId;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getVaddress() {
		return vaddress;
	}
	public void setVaddress(String vaddress) {
		this.vaddress = vaddress;
	}
	public String getVcontact() {
		return vcontact;
	}
	public void setVcontact(String vcontact) {
		this.vcontact = vcontact;
	}
	public Integer getVroomTotal() {
		return vroomTotal;
	}
	public void setVroomTotal(Integer vroomTotal) {
		this.vroomTotal = vroomTotal;
	}
	
	
	
}
