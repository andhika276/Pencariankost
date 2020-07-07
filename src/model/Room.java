package model;

import java.util.List;

public class Room {
	String roomId;
	String ownerId;
	String address;
	String city;
	String totalComment;
	int rentalCost;
	String totalWatt;
	String floorNumber;
	int totalRoomArea;
	int totalToiletArea;
	List<Barang> Kelengkapan;
	
	public Room(String vownerId, String vaddress, String vcity, int vtotalRoomArea, int vrentalCost, List<Barang> vKelengkapan) {
		this.ownerId = vownerId;
		this.city = vcity;
		this.address = vaddress;
		this.totalRoomArea = vtotalRoomArea;
		this.rentalCost = vrentalCost;
		this.Kelengkapan = vKelengkapan;
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

	public int getTotalRoomArea() {
		return totalRoomArea;
	}

	public void setTotalRoomArea(int totalRoomArea) {
		this.totalRoomArea = totalRoomArea;
	}

	public Integer getTotalToiletArea() {
		return totalToiletArea;
	}

	public void setTotalToiletArea(Integer totalToiletArea) {
		this.totalToiletArea = totalToiletArea;
	}

	public List<Barang> getKelengkapan() {
		return Kelengkapan;
	}

	public void setKelengkapan(List<Barang> kelengkapan) {
		Kelengkapan = kelengkapan;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getBarangString() {
		return Kelengkapan.toString();
	}
}