package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import model.Room;
import model.Owner;

public class HBaseUtils {

	Configuration config = HBaseConfiguration.create();

	public HBaseUtils() {

	}

	public ArrayList<Owner> getOwner() throws IOException {

		ArrayList<Owner> resultList = new ArrayList<>();
		HTable table = new HTable(config, "owner");

		// Instantiating the Scan class
		Scan scan = new Scan();

		// Scanning the required columns
		scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));
		scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("address"));
		scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("contact"));
		scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("roomtotal"));

		// Getting the scan result
		ResultScanner scanner = table.getScanner(scan);

		// Reading values from scan result
		for (Result result = scanner.next(); result != null; result = scanner.next()) {
			byte[] value1 = result.getValue(Bytes.toBytes("info"), Bytes.toBytes("name"));
			byte[] value2 = result.getValue(Bytes.toBytes("info"), Bytes.toBytes("address"));
			byte[] value3 = result.getValue(Bytes.toBytes("info"), Bytes.toBytes("contact"));
			byte[] value4 = result.getValue(Bytes.toBytes("info"), Bytes.toBytes("roomtotal"));
			System.out.println("Name:" + Bytes.toString(value1) + " Address:" + Bytes.toString(value2) + " Contact:"
					+ Bytes.toString(value3) + " RoomTotal:" + Bytes.toInt(value4));
			// Printing the values
			String vname = Bytes.toString(value1);
			String vaddress = Bytes.toString(value2);
			String vcontact = Bytes.toString(value3);
			int vroomTotal = Bytes.toInt(value4);
			String vownerid = Bytes.toString(result.getRow());

			Owner owner = new Owner(vname, vaddress, vcontact, vroomTotal);
			owner.setOwnerId(vownerid);
			resultList.add(owner);
		}
		scanner.close();

		return resultList;
	}
	
	public ArrayList<Room> getRoomById(String id) throws IOException{
		ArrayList<Room> resultList = new ArrayList<>();
		HTable table = new HTable(config, "owner");

		// Instantiating the Scan class
		Scan scan = new Scan();

		// Scanning the required columns
		scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));
		scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("address"));
		scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("contact"));
		scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("roomtotal"));

		// Getting the scan result
		ResultScanner scanner = table.getScanner(scan);
		
		return resultList;
	}

	public ArrayList<Room> getRoom() throws IOException {

		ArrayList<Room> resultList = new ArrayList<>();
		HTable table = new HTable(config, "room");

		// Instantiating the Scan class
		Scan scan = new Scan();

		// Scanning the required columns
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("ownerId"));
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("address"));
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("city"));
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("totalComment"));

		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalWatt"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("floorNumber"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));

		// Getting the scan result
		ResultScanner scanner = table.getScanner(scan);

		// Reading values from scan result
		for (Result result = scanner.next(); result != null; result = scanner.next()) {
			byte[] value1 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("ownerId"));
			byte[] value2 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("address"));
			byte[] value3 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("city"));
			byte[] value4 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("totalComment"));

			byte[] value5 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
			byte[] value6 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalWatt"));
			byte[] value7 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("floorNumber"));
			byte[] value8 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"));
			byte[] value9 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));
			//System.out.println(" ownerId: " + Bytes.toString(value1) + "address: " + Bytes.toString(value2)
			//		+ " totalComment: " + Bytes.toInt(value3) + "rentalCost: " + Bytes.toString(value4) + "totalWatt: "
			//		+ Bytes.toString(value5) + "floorNumber: " + Bytes.toString(value6) + "totalRoomArea: "
			//		+ Bytes.toInt(value7) + "totalToiletArea" + Bytes.toInt(value8));
			// Printing the values
			String vownerId = Bytes.toString(value1);
			String vaddress = Bytes.toString(value2);
			String vtotalComment = Bytes.toString(value3);
			Integer vrentalCost = Bytes.toInt(value4);
			String vtotalWatt = Bytes.toString(value5);
			String vfloorNumber = Bytes.toString(value6);
			Integer vtotalRoomArea = Bytes.toInt(value7);
			Integer vtotalToiletArea = Bytes.toInt(value8);
			String vroomId = Bytes.toString(result.getRow());

			Room room = new Room(vownerId, vaddress, vtotalComment, vrentalCost, vtotalWatt, vfloorNumber,
					vtotalRoomArea, vtotalToiletArea);
			room.setRoomId(vroomId);
			resultList.add(room);
		}
		scanner.close();

		return resultList;
	}

	public boolean insertDataOwner(String name, String address, String contact, int roomtotal) {
		try {
			// Instantiating HTable class
			HTable hTable = new HTable(config, "owner");
			Put p = null;

			Scan scan = new Scan();
			String Row = null;
			ResultScanner scanner = hTable.getScanner(scan);
			for (Result result = scanner.next(); result != null; result = scanner.next()) {
				Row = Bytes.toString(result.getRow());
			}
			if (Row == null) {
				p = new Put(Bytes.toBytes("row0"));
			} else {
				String[] parts = Row.split("row");
				int data = Integer.parseInt(parts[1]);
				// System.out.println(data + 1);

				// Instantiating Put class
				// accepts a row name.
				p = new Put(Bytes.toBytes("row" + (data + 1)));
			}
			// adding values using add() method
			// accepts column family name, qualifier/row name ,value
			p.add(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes(name));
			p.add(Bytes.toBytes("info"), Bytes.toBytes("address"), Bytes.toBytes(address));
			p.add(Bytes.toBytes("info"), Bytes.toBytes("contact"), Bytes.toBytes(contact));
			p.add(Bytes.toBytes("info"), Bytes.toBytes("roomtotal"), Bytes.toBytes(roomtotal));
			
			// Saving the put Instance to the HTable.
			hTable.put(p);
			System.out.println("data inserted");

			// closing HTable
			hTable.close();
		} catch (IOException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public boolean insertDataRoom(String ownerid, String address, String city, int totalroomarea, String rentalCost,
			List<String> Kelengkapan) {
		try {
			int lastrow = 0;
			// Instantiating HTable class
			HTable hTable = new HTable(config, "room");

			Scan scan = new Scan();
			scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
			scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalRoomarea"));
			scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));
			ResultScanner result = hTable.getScanner(scan);

			for (Result rs = result.next(); rs != null; rs = result.next()) {
				++lastrow;
			}

			// Instantiating Put class
			// accepts a row name.
			Put p = new Put(Bytes.toBytes("row" + (lastrow + 1)));
			// adding values using add() method
			// accepts column family name, qualifier/row name ,value
			p.add(Bytes.toBytes("general"), Bytes.toBytes("ownerId"), Bytes.toBytes(ownerid));
			p.add(Bytes.toBytes("general"), Bytes.toBytes("address"), Bytes.toBytes(address));
			p.add(Bytes.toBytes("general"), Bytes.toBytes("totalComment"), Bytes.toBytes(totalcomment));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"), Bytes.toBytes(rentalcost));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("totalWatt"), Bytes.toBytes(totalwatt));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("floorNumber"), Bytes.toBytes(floornumber));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"), Bytes.toBytes(totalroomarea));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"), Bytes.toBytes(totaltoiletarea));

			// Saving the put Instance to the HTable.
			hTable.put(p);
			System.out.println("data inserted");

			// closing HTable
			hTable.close();
		} catch (IOException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public boolean deleteowner(String ownerid) {

		try {
			HTable table = new HTable(config, "owner");
			// Instantiating Delete class
			Delete delete = new Delete(Bytes.toBytes(ownerid));
			delete.deleteFamily(Bytes.toBytes("info"));
			// deleting the data
			table.delete(delete);

			// closing the HTable object
			table.close();
			System.out.println("data deleted.....");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteroom(String roomid) {

		try {
			HTable table = new HTable(config, "roomdelete.deleteFamily(Bytes.toBytes(\"info\"));");
			// Instantiating Delete class
			Delete delete = new Delete(Bytes.toBytes(roomid));
			// delete.deleteColumn(Bytes.toBytes("public"), Bytes.toBytes("name"));
			delete.deleteFamily(Bytes.toBytes("general"));
			delete.deleteFamily(Bytes.toBytes("private"));
			// deleting the data
			table.delete(delete);

			// closing the HTable object
			table.close();
			System.out.println("data deleted.....");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateowner(String ownerid, String name, String address, String contact, int roomtotal) {
		try {
			HTable table = new HTable(config, "owner");

			Put p = new Put(Bytes.toBytes(ownerid));
			p.add(Bytes.toBytes("public"), Bytes.toBytes("name"), Bytes.toBytes(name));
			p.add(Bytes.toBytes("public"), Bytes.toBytes("address"), Bytes.toBytes(address));
			p.add(Bytes.toBytes("public"), Bytes.toBytes("contact"), Bytes.toBytes(contact));
			p.add(Bytes.toBytes("public"), Bytes.toBytes("roomtotal"), Bytes.toBytes(roomtotal));

			// Saving the put Instance to the HTable.
			table.put(p);
			// closing the HTable object
			table.close();
			System.out.println("data updated.....");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateroom(String roomid, String ownerid, String address, String totalcomment, int rentalcost,
			String totalwatt, String floornumber, int totalroomarea, int totaltoiletarea) {
		try {
			HTable table = new HTable(config, "room");

			Put p = new Put(Bytes.toBytes(roomid));
			p.add(Bytes.toBytes("general"), Bytes.toBytes("ownerId"), Bytes.toBytes(ownerid));
			p.add(Bytes.toBytes("general"), Bytes.toBytes("address"), Bytes.toBytes(address));
			p.add(Bytes.toBytes("general"), Bytes.toBytes("totalComment"), Bytes.toBytes(totalcomment));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"), Bytes.toBytes(rentalcost));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("totalWatt"), Bytes.toBytes(totalwatt));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("floorNumber"), Bytes.toBytes(floornumber));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"), Bytes.toBytes(totalroomarea));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"), Bytes.toBytes(totaltoiletarea));

			// Saving the put Instance to the HTable.
			table.put(p);
			// closing the HTable object
			table.close();
			System.out.println("data updated.....");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}