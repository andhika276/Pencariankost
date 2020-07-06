package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Owner;
import model.Room;

public class ActionController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String row;
	private String ownerid;

	public ActionController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		System.out.println("ACTION = " + action);

		HBaseUtils hbaseUtils = new HBaseUtils();

		if ("retrieve_owner".equals(action)) {
			showAllData(request, response, hbaseUtils);
		} else if ("to_input_owner".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/InputDataPemilik.jsp");
			rd.forward(request, response);
		} else if ("insert_owner".equals(action)) {
			// TODO
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String contact = request.getParameter("contact");
			int roomtotal = Integer.parseInt("0");
			boolean result = hbaseUtils.insertDataOwner(name, address, contact, roomtotal);
			if (result) {
				showAllData(request, response, hbaseUtils);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
		} else if ("delete_owner".equals(action)) {
			String row = request.getParameter("id");
			List<Room> roomList = hbaseUtils.getRoomById(row);
			String roomid = request.getParameter("roomId");
			
			System.out.println("ROW DELETED = " + row);

			boolean result = hbaseUtils.deleteowner(row,roomid);
			if (result) {
				showAllData(request, response, hbaseUtils);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
		} else if ("update_owner".equals(action)) {
			String row = request.getParameter("id");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String contact = request.getParameter("contact");
			int roomtotal = Integer.parseInt("0");
			Owner own = new Owner(name, address, contact, roomtotal);
			own.setOwnerId(row);
			request.setAttribute("info", own);
			request.getRequestDispatcher("/editDataPemilik.jsp").forward(request, response);
		} else if ("edit_owner".equals(action)) {
			String row = request.getParameter("id");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String contact = request.getParameter("contact");
			int roomtotal = Integer.parseInt("0");
			boolean result = hbaseUtils.updateowner(row, name, address, contact, roomtotal);
			if (result) {
				showAllData(request, response, hbaseUtils);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
		} else if ("roomInfo".equals(action)) {
			//TODO
			String ownerId = request.getParameter("id");
			List<Room> roomList = hbaseUtils.getRoomById(ownerId);
			ownerid=ownerId;
			//System.out.println(ownerid);
			request.setAttribute("roomList", roomList);
			request.setAttribute("ownerId", ownerId);
			RequestDispatcher rd = request.getRequestDispatcher("/daftarKamar.jsp");
			rd.forward(request, response);
		} else if ("retrieve_room".equals(action)) {
			String ownerId = request.getParameter("id");
			List<Room> roomList = hbaseUtils.getRoomById(ownerId);
			request.setAttribute("roomList", roomList);
			request.setAttribute("ownerId", ownerId);
			request.getRequestDispatcher("/daftarKamar.jsp").forward(request, response);
		} else if ("retrieve_all_room".equals(action)) {
			showAllroom(request,response,hbaseUtils);
		} else if ("to_input_room".equals(action)) {
			String ownerId = request.getParameter("id");
			request.setAttribute("ownerId", ownerId);
			RequestDispatcher rd = request.getRequestDispatcher("/InputDataRoom.jsp");
			rd.forward(request, response);
		} else if ("insert_room".equals(action)) {
			// TODO
			String ownerId = request.getParameter("id");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			//String totalComment = request.getParameter("totalWatt");
			int rentalCost = Integer.parseInt(request.getParameter("rentalCost"));
			//String totalWatt = request.getParameter("totalWatt");
			//String floorNumber = request.getParameter("floorNumber");
			int totalRoomArea = Integer.parseInt(request.getParameter("totalRoomArea"));
			//int totalToiletArea = Integer.parseInt(request.getParameter("totalToiletArea"));
			List<String> kelengkapan = null;
			boolean result = hbaseUtils.insertDataRoom(ownerId, address, city, rentalCost, totalRoomArea, kelengkapan);
			if (result) {
				RequestDispatcher rd = request.getRequestDispatcher("/daftarPemilik.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
		} else if ("delete_room".equals(action)) {
			String row = request.getParameter("roomId");
			System.out.println("ROW DELETED = " + row);

			boolean result = hbaseUtils.deleteroom(row);
			if (result) {
				RequestDispatcher rd = request.getRequestDispatcher("/daftarPemilik.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
		} else if ("update_room".equals(action)) {
			String ownerId = request.getParameter("id");
			String roomid = request.getParameter("roomId");
			ownerId=ownerid;
			String address = request.getParameter("address");
		//	String totalComment = request.getParameter("totalComment");
			int rentalCost = Integer.parseInt(request.getParameter("rentalCost"));
			String city = request.getParameter("city");
		//	String totalWatt = request.getParameter("totalWatt");
		//	String floorNumber = request.getParameter("floorNumber");
			int totalRoomArea = Integer.parseInt(request.getParameter("totalRoomArea"));
		//	int totalToiletArea = Integer.parseInt(request.getParameter("totalToiletArea"));
			List<String> kelengkapan = null;
			row=roomid;
			Room room = new Room(ownerId, address, city, rentalCost, totalRoomArea,kelengkapan);
			room.setRoomId(roomid);
			//System.out.println(ownerId);
			//System.out.println(roomid);
			//System.out.println(address);
			//System.out.println(city);
			//System.out.println(rentalCost);
			//System.out.println(totalRoomArea);
			request.setAttribute("ownerId", ownerId);
			request.setAttribute("roomList",room);
			request.getRequestDispatcher("/editRoomDetail.jsp").forward(request, response);
		} else if ("edit_room".equals(action)) {
			String roomid = request.getParameter("roomId");
			String ownerId = request.getParameter("id");
			ownerId=ownerid;
			roomid=row;
			String address = request.getParameter("address");
			String city = request.getParameter("city");
		//	String totalComment = request.getParameter("totalWatt");
			int rentalCost = Integer.parseInt(request.getParameter("rentalCost"));
		//	String totalWatt = request.getParameter("totalWatt");
		//	String floorNumber = request.getParameter("floorNumber");
			int totalRoomArea = Integer.parseInt(request.getParameter("totalRoomArea"));
		//	int totalToiletArea = Integer.parseInt(request.getParameter("totalToiletArea"));
			List<String> kelengkapan = null;
			//System.out.println(ownerId);
			//System.out.println(roomid);
			//System.out.println(address);
			//System.out.println(city);
			//System.out.println(rentalCost);
			//System.out.println(totalRoomArea);
			boolean result = hbaseUtils.updateroom(ownerId, roomid, address, city, totalRoomArea, rentalCost, kelengkapan);
			if (result) {
				RequestDispatcher rd = request.getRequestDispatcher("/daftarPemilik.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
		}
	}
	
	public void showAllData(HttpServletRequest request, HttpServletResponse response, HBaseUtils hbaseUtils) {
		try {
			ArrayList<Owner> listOwner = hbaseUtils.getOwner();
			request.setAttribute("ownerInfo", listOwner);
			request.getRequestDispatcher("/daftarPemilik.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showAllroom(HttpServletRequest request, HttpServletResponse response, HBaseUtils hbaseUtils) {
		try {
			ArrayList<Room> listroom = hbaseUtils.getRoom();
			request.setAttribute("roomList",listroom);
			request.getRequestDispatcher("/daftarKamar.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}