package model;

public class Room {
	String roomId;
	String ownerId;
	String address;
	String totalComment;
	Integer rentalCost;
	String totalWatt;
	String floorNumber;
	Integer totalRoomArea;
	Integer totalToiletArea;
	
	public Room(String ownerId, String address, String totalComment, Integer rentalCost, String totalWatt,
			String floorNumber, Integer totalRoomArea, Integer totalToiletArea) {
		super();
		this.ownerId = ownerId;
		this.address = address;
		this.totalComment = totalComment;
		this.rentalCost = rentalCost;
		this.totalWatt = totalWatt;
		this.floorNumber = floorNumber;
		this.totalRoomArea = totalRoomArea;
		this.totalToiletArea = totalToiletArea;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTotalComment() {
		return totalComment;
	}
	public void setTotalComment(String totalComment) {
		this.totalComment = totalComment;
	}
	public Integer getRentalCost() {
		return rentalCost;
	}
	public void setRentalCost(Integer rentalCost) {
		this.rentalCost = rentalCost;
	}
	public String getTotalWatt() {
		return totalWatt;
	}
	public void setTotalWatt(String totalWatt) {
		this.totalWatt = totalWatt;
	}
	public String getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}
	public Integer getTotalRoomArea() {
		return totalRoomArea;
	}
	public void setTotalRoomArea(Integer totalRoomArea) {
		this.totalRoomArea = totalRoomArea;
	}
	public Integer getTotalToiletArea() {
		return totalToiletArea;
	}
	public void setTotalToiletArea(Integer totalToiletArea) {
		this.totalToiletArea = totalToiletArea;
	}
}
