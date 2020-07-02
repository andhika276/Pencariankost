package controller;



import java.io.IOException;
import java.util.ArrayList;

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
	

	public ActionController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		System.out.println("ACTION = "+action);

		HBaseUtils hbaseUtils = new HBaseUtils();

		if("retrieve_owner".equals(action)){
			ArrayList<Owner> ownerInfo = hbaseUtils.getOwner();
			request.setAttribute("dataList", ownerInfo);
			request.getRequestDispatcher("/daftarPemilik.jsp").forward(request, response);
		}else if("insert_owner".equals(action)){
			// TODO
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String contact = request.getParameter("contact");
			int roomtotal = Integer.parseInt(request.getParameter("roomtotal"));			
			boolean result = hbaseUtils.insertDataOwner(name, address, contact, roomtotal);
			if(result) {
				RequestDispatcher rd = request.getRequestDispatcher("/daftarPemilik.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}			
		}else if("delete_owner".equals(action)){
			String row = request.getParameter("ownerId");
			System.out.println("ROW DELETED = "+row);
			
			boolean result = hbaseUtils.deleteowner(row);
			if(result) {
				RequestDispatcher rd = request.getRequestDispatcher("/daftarPemilik.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
		}else if("to_input_owner".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/InputDataPemilik.jsp");
			rd.forward(request, response);
		}else if("update_owner".equals(action)) {
			String row = request.getParameter("id");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String contact = request.getParameter("contact");
			int roomtotal = Integer.parseInt(request.getParameter("roomtotal"));
			Owner own = new Owner(name, address, contact, roomtotal);
			own.setVownerId(row);
			request.setAttribute("dataList", own);
			request.getRequestDispatcher("/edit.jsp").forward(request, response);
		}else if("edit_owner".equals(action)) {
			String row = request.getParameter("row");
			String name = request.getParameter("name");
			String address = request.getParameter("city");
			String contact = request.getParameter("designation");
			int roomtotal = Integer.parseInt(request.getParameter("salary"));			
			boolean result = hbaseUtils.updateowner(row, name, address, contact, roomtotal);
			if(result) {
				RequestDispatcher rd = request.getRequestDispatcher("/daftarPemilik.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}			
		}else if("retrieve_room".equals(action)){
			ArrayList<Room> listRoom = hbaseUtils.getRoom();
			request.setAttribute("dataList", listRoom);
			request.getRequestDispatcher("/daftarPemilik.jsp").forward(request, response);
		}else if("insert_room".equals(action)){
			// TODO
			String ownerId = request.getParameter("ownerId");
			String address = request.getParameter("address");
			String totalComment = request.getParameter("totalWatt");
			int rentalCost =Integer.parseInt(request.getParameter("rentalCost"));
			String totalWatt = request.getParameter("totalWatt");
			String floorNumber = request.getParameter("floorNumber");
			int totalRoomArea =Integer.parseInt(request.getParameter("totalRoomArea"));
			int totalToiletArea = Integer.parseInt(request.getParameter("totalToiletArea"));			
			boolean result = hbaseUtils.insertDataRoom(ownerId,address,totalComment,rentalCost,totalWatt,floorNumber,totalRoomArea,totalToiletArea);
			if(result) {
				RequestDispatcher rd = request.getRequestDispatcher("/daftarPemilik.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}			
		}else if("delete_room".equals(action)){
			String row = request.getParameter("roomid");
			System.out.println("ROW DELETED = "+row);
			
			boolean result = hbaseUtils.deleteowner(row);
			if(result) {
				RequestDispatcher rd = request.getRequestDispatcher("/daftarPemilik.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
		}else if("to_input_room".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/InputDataRoom.jsp");
			rd.forward(request, response);
		}else if("update_room".equals(action)) {
			String row = request.getParameter("roomId");
			String ownerId = request.getParameter("ownerId");
			String address = request.getParameter("address");
			String totalComment = request.getParameter("totalWatt");
			int rentalCost =Integer.parseInt(request.getParameter("rentalCost"));
			String totalWatt = request.getParameter("totalWatt");
			String floorNumber = request.getParameter("floorNumber");
			int totalRoomArea =Integer.parseInt(request.getParameter("totalRoomArea"));
			int totalToiletArea = Integer.parseInt(request.getParameter("totalToiletArea"));
			Room room = new Room(ownerId,address,totalComment,rentalCost,totalWatt,floorNumber,totalRoomArea,totalToiletArea);
			room.setVownerId(row);
			request.setAttribute("dataList", room);
			request.getRequestDispatcher("/edit.jsp").forward(request, response);
		}else if("edit_room".equals(action)) {
			String row = request.getParameter("roomId");
			String ownerId = request.getParameter("ownerId");
			String address = request.getParameter("address");
			String totalComment = request.getParameter("totalWatt");
			int rentalCost =Integer.parseInt(request.getParameter("rentalCost"));
			String totalWatt = request.getParameter("totalWatt");
			String floorNumber = request.getParameter("floorNumber");
			int totalRoomArea =Integer.parseInt(request.getParameter("totalRoomArea"));
			int totalToiletArea = Integer.parseInt(request.getParameter("totalToiletArea"));				
			boolean result = hbaseUtils.updateroom(row,ownerId,address,totalComment,rentalCost,totalWatt,floorNumber,totalRoomArea,totalToiletArea);
			if(result) {
				RequestDispatcher rd = request.getRequestDispatcher("/daftarPemilik.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}			
		}
	}

}