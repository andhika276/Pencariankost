package model;

import java.util.List;

public class Owner {
	private String ownerId;
	private String name;
	private String address;
	private String contact;
	private int roomTotal;
	//private List<Room> roomList;

	public Owner(String vname, String vaddress, String vcontact, int vroomTotal) {
		super();
		this.name = vname;
		this.address = vaddress;
		this.contact = vcontact;
		this.roomTotal = vroomTotal;
		//this.roomList = roomList;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getRoomTotal() {
		return roomTotal;
	}

	public void setRoomTotal(int roomTotal) {
		this.roomTotal = roomTotal;
	}
	/*
	public List<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}*/
}
