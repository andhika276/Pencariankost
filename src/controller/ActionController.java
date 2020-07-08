package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Barang;
import model.Owner;
import model.Room;

public class ActionController extends HttpServlet {

	private static final long serialVersionUID = 1L;
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
			
			System.out.println("ROW DELETED = " + row);

			boolean result = hbaseUtils.deleteowner(row);
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
				showAllData(request, response, hbaseUtils);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
		} else if ("delete_room".equals(action)) {
			String id = request.getParameter("roomId");
			System.out.println("ROW DELETED = " + id);

			boolean result = hbaseUtils.deleteroom(id);
			if (result) {
				showAllData(request, response, hbaseUtils);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
		} else if ("update_room".equals(action)) {
			String ownerId = request.getParameter("ownerId");
			String roomid = request.getParameter("roomId");
			String address = request.getParameter("address");
		//	String totalComment = request.getParameter("totalComment");
			int rentalCost = Integer.parseInt(request.getParameter("rentalCost"));
			String city = request.getParameter("city");
		//	String totalWatt = request.getParameter("totalWatt");
		//	String floorNumber = request.getParameter("floorNumber");
			int totalRoomArea = Integer.parseInt(request.getParameter("totalRoomArea"));
		//	int totalToiletArea = Integer.parseInt(request.getParameter("totalToiletArea"));
			List<Barang> kelengkapan = hbaseUtils.getBarang(roomid);
			Room room = new Room(ownerId, address, city, rentalCost, totalRoomArea,kelengkapan);
			room.setRoomId(roomid);
			System.out.println(address);
			request.setAttribute("ownerId", ownerId);
			request.setAttribute("room",room);
			request.getRequestDispatcher("/editRoomDetail.jsp").forward(request, response);
		} else if ("edit_room".equals(action)) {
			String roomid = request.getParameter("roomID");
			String ownerId = request.getParameter("ownerId");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
		//	String totalComment = request.getParameter("totalWatt");
			int rentalCost = Integer.parseInt(request.getParameter("rentalCost"));
		//	String totalWatt = request.getParameter("totalWatt");
		//	String floorNumber = request.getParameter("floorNumber");
			int totalRoomArea = Integer.parseInt(request.getParameter("totalRoomArea"));
		//	int totalToiletArea = Integer.parseInt(request.getParameter("totalToiletArea"));
			List<String> kelengkapan = null;
			boolean result = hbaseUtils.updateroom(ownerId, roomid, address, city, totalRoomArea, rentalCost, kelengkapan);
			if (result) {
				RequestDispatcher rd = request.getRequestDispatcher("/daftarPemilik.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
		} else if ("tambah_barang".equals(action)) {
			String roomid = request.getParameter("roomId");
			request.setAttribute("roomId", roomid);
			RequestDispatcher rd = request.getRequestDispatcher("/InputDataBarang.jsp");
			rd.forward(request, response);
		} else if ("insert_barang".equals(action)) {
			String roomid = request.getParameter("idRoom");
			String namaBarang = request.getParameter("namaBarang");
			int jumlah = Integer.parseInt(request.getParameter("jumlah"));
			System.out.println(roomid);
			System.out.println(jumlah);
			boolean result = hbaseUtils.insertBarang(roomid, namaBarang, jumlah);
			if (result) {
				showAllData(request, response, hbaseUtils);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
		} else if ("to_filter_owner".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/FilterOwner.jsp");
			rd.forward(request, response);
		} else if ("filter_jumlah_kamar".equals(action)) {
			int jumlah = Integer.parseInt(request.getParameter("jumlahKamar"));
			try {
				ArrayList<Owner> listOwner = hbaseUtils.getOwnerByJumlah(jumlah);
				request.setAttribute("ownerInfo", listOwner);
				request.getRequestDispatcher("/daftarPemilik.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("search_room_menu".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/CariKamar.jsp");
			rd.forward(request, response);
		} else if ("room_by_city".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/CariKamar.jsp");
			rd.forward(request, response);
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