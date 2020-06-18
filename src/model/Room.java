package model;

public class Room {
	String vroomId;
	String vownerId;
	String vaddress;
	String vtotalComment;
	Integer vrentalCost;
	String vtotalWatt;
	String vfloorNumber;
	Integer vtotalRoomArea;
	Integer vtotalToiletArea;
	public Room(String vownerId, String vaddress, String vtotalComment, Integer vrentalCost, String vtotalWatt,
			String vfloorNumber, Integer vtotalRoomArea, Integer vtotalToiletArea) {
		super();
		this.vownerId = vownerId;
		this.vaddress = vaddress;
		this.vtotalComment = vtotalComment;
		this.vrentalCost = vrentalCost;
		this.vtotalWatt = vtotalWatt;
		this.vfloorNumber = vfloorNumber;
		this.vtotalRoomArea = vtotalRoomArea;
		this.vtotalToiletArea = vtotalToiletArea;
	}
	public String getVroomId() {
		return vroomId;
	}
	public void setVroomId(String vroomId) {
		this.vroomId = vroomId;
	}
	public String getVownerId() {
		return vownerId;
	}
	public void setVownerId(String vownerId) {
		this.vownerId = vownerId;
	}
	public String getVaddress() {
		return vaddress;
	}
	public void setVaddress(String vaddress) {
		this.vaddress = vaddress;
	}
	public String getVtotalComment() {
		return vtotalComment;
	}
	public void setVtotalComment(String vtotalComment) {
		this.vtotalComment = vtotalComment;
	}
	public Integer getVrentalCost() {
		return vrentalCost;
	}
	public void setVrentalCost(Integer vrentalCost) {
		this.vrentalCost = vrentalCost;
	}
	public String getVtotalWatt() {
		return vtotalWatt;
	}
	public void setVtotalWatt(String vtotalWatt) {
		this.vtotalWatt = vtotalWatt;
	}
	public String getVfloorNumber() {
		return vfloorNumber;
	}
	public void setVfloorNumber(String vfloorNumber) {
		this.vfloorNumber = vfloorNumber;
	}
	public Integer getVtotalRoomArea() {
		return vtotalRoomArea;
	}
	public void setVtotalRoomArea(Integer vtotalRoomArea) {
		this.vtotalRoomArea = vtotalRoomArea;
	}
	public Integer getVtotalToiletArea() {
		return vtotalToiletArea;
	}
	public void setVtotalToiletArea(Integer vtotalToiletArea) {
		this.vtotalToiletArea = vtotalToiletArea;
	}
	
}
