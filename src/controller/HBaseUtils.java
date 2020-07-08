package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import model.Room;
import model.Barang;
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

	public ArrayList<Room> getRoomById(String id) throws IOException {
		ArrayList<Room> resultList = new ArrayList<>();

		HTable table = new HTable(config, "room");

		List<Filter> filters = new ArrayList<Filter>();

		//Filter
		SingleColumnValueFilter colValFilter = new SingleColumnValueFilter(Bytes.toBytes("general"), Bytes.toBytes("ownerId")
	            , CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(id)));
	    colValFilter.setFilterIfMissing(false);
	    filters.add(colValFilter);
		System.out.println(id);

		FilterList fl = new FilterList( FilterList.Operator.MUST_PASS_ALL, filters);
		
		// Instantiating the Scan class
		Scan scan = new Scan();
		
		// Scanning the required columns
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("ownerId"));
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("address"));
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("city"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("daftarBarang"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("toiletType"));
		scan.setFilter(fl);

		// Getting the scan result
		ResultScanner scanner = table.getScanner(scan);
		// Reading values from scan result
		for (Result result = scanner.next(); result != null; result = scanner.next()) {
			byte[] value1 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("ownerId"));
			byte[] value2 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("address"));
			byte[] value3 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("city"));
			byte[] value4 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"));
			byte[] value5 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
			byte[] value6 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("daftarBarang"));
			byte[] value7 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));
			byte[] value8 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("toiletType"));
			System.out.println("ownerId:" + Bytes.toString(value1) + " address:" + Bytes.toString(value2) + " city:"
					+ Bytes.toString(value3) + " totalRoomArea:" + Bytes.toInt(value4) + " rentalCost:" + Bytes.toInt(value5));
			//Printing the values
			String vownerId = Bytes.toString(value1);
			String vcity = Bytes.toString(value2);
			String vaddress = Bytes.toString(value3);
			int vtotalRoomArea = Bytes.toInt(value4);
			int vrentalCost = Bytes.toInt(value5);
			int vtoiletArea = Bytes.toInt(value7);
			String vtoiletType = Bytes.toString(value8);
			String roomId = Bytes.toString(result.getRow());
			
			//Scan perlengkapan
			List<Barang> vKelengkapan = getBarang(roomId);

			Room room = new Room(vownerId, vcity, vaddress, vtotalRoomArea, vrentalCost, vKelengkapan, vtoiletArea);
			room.setRoomId(roomId);
			room.setToiletType(vtoiletType);
			resultList.add(room);
		}
		scanner.close();

		return resultList;
	}
	
	public List<Barang> getBarang(String roomId) throws IOException {
		List<Barang> vKelengkapan = new ArrayList<Barang>();
		HTable table = new HTable(config, "room");

		Get get = new Get(Bytes.toBytes(roomId));
		get.addFamily(Bytes.toBytes("private"));
		
		Result result = table.get(get);
		byte [] value = result.getValue(Bytes.toBytes("private"),Bytes.toBytes("daftarBarang"));
		
		String daftarBarang = Bytes.toString(value);
		
		if(daftarBarang == null || daftarBarang == "") {
			return null;
		}
		
		String[] columns = daftarBarang.split(",");
		
		for(int i=0; i<columns.length; i++) {
			byte [] value1 = result.getValue(Bytes.toBytes("private"),Bytes.toBytes(columns[i]));
			String barang = columns[i];
			int jumlah = Bytes.toInt(value1);
			
			Barang newBarang = new Barang(barang, jumlah);
			vKelengkapan.add(newBarang);
		}

		// closing the HTable object
		table.close();
		return vKelengkapan;
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
		//scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("totalComment"));

		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
		//scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalWatt"));
		//scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("floorNumber"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("toiletType"));

		// Getting the scan result
		ResultScanner scanner = table.getScanner(scan);

		// Reading values from scan result
		for (Result result = scanner.next(); result != null; result = scanner.next()) {
			byte[] value1 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("ownerId"));
			byte[] value2 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("address"));
			byte[] value3 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("city"));
			//byte[] value4 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("totalComment"));

			byte[] value4 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
			//byte[] value6 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalWatt"));
			//byte[] value7 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("floorNumber"));
			byte[] value5 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"));
			byte[] value6 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));
			byte[] value7 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("toiletType"));
			// System.out.println(" ownerId: " + Bytes.toString(value1) + "address: " +
			// Bytes.toString(value2)
			// + " totalComment: " + Bytes.toInt(value3) + "rentalCost: " +
			// Bytes.toString(value4) + "totalWatt: "
			// + Bytes.toString(value5) + "floorNumber: " + Bytes.toString(value6) +
			// "totalRoomArea: "
			// + Bytes.toInt(value7) + "totalToiletArea" + Bytes.toInt(value8));
			// Printing the values
			String vownerId = Bytes.toString(value1);
			String vaddress = Bytes.toString(value2);
			String vcity = Bytes.toString(value3);
			int vrentalCost = Bytes.toInt(value4);
			//String vtotalWatt = Bytes.toString(value5);
			//String vfloorNumber = Bytes.toString(value6);
			int vtotalRoomArea = Bytes.toInt(value5);
			int vtotalToiletArea = Bytes.toInt(value6);
			String toiletType = Bytes.toString(value7);
			String vroomId = Bytes.toString(result.getRow());
			
			List<Barang> vKelengkapan = getBarang(vroomId);
			
			Room room = new Room(vownerId, vaddress, vcity, vtotalRoomArea, vrentalCost, vKelengkapan, vtotalToiletArea);
			room.setToiletType(toiletType);
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

	public boolean insertDataRoom(String ownerid, String address, String city, int totalroomarea, int rentalCost, List<String> Kelengkapan, String toiletType, int toiletArea) {
		try {
			int lastrow = 0;
			// Instantiating HTable class
			HTable hTable = new HTable(config, "room");
			Put p = null;

			Scan scan = new Scan();
			String Row = null;
			ResultScanner scanner = hTable.getScanner(scan);
			for (Result result = scanner.next(); result != null; result = scanner.next()) {
				Row = Bytes.toString(result.getRow());
			}
			if (Row == null) {
				p = new Put(Bytes.toBytes("rowRoom0"));
			} else {
				String[] parts = Row.split("rowRoom");
				int data = Integer.parseInt(parts[1]);
				// System.out.println(data + 1);

				// Instantiating Put class
				// accepts a row name.
				p = new Put(Bytes.toBytes("rowRoom" + (data + 1)));
			}
			// adding values using add() method
			// accepts column family name, qualifier/row name ,value

			p.add(Bytes.toBytes("general"), Bytes.toBytes("ownerId"), Bytes.toBytes(ownerid));
			p.add(Bytes.toBytes("general"), Bytes.toBytes("address"), Bytes.toBytes(address));
			p.add(Bytes.toBytes("general"), Bytes.toBytes("city"), Bytes.toBytes(city.toUpperCase()));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"), Bytes.toBytes(totalroomarea));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"), Bytes.toBytes(rentalCost));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"), Bytes.toBytes(toiletArea));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("toiletType"), Bytes.toBytes(toiletType));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("daftarBarang"), Bytes.toBytes(""));
			// p.add(Bytes.toBytes("private"), Bytes.toBytes("floorNumber"),
			// Bytes.toBytes(floornumber));
			// p.add(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"),
			// Bytes.toBytes(totalroomarea));
			// p.add(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"),
			// Bytes.toBytes(totaltoiletarea));
			// Saving the put Instance to the HTable.
			hTable.put(p);
			System.out.println("data inserted" + rentalCost + totalroomarea);

			// closing HTable
			hTable.close();
			
			//Menambah Jumlah Kamar
			HTable table = new HTable(config, "owner");
			
			Get get = new Get(Bytes.toBytes(ownerid));
			get.addFamily(Bytes.toBytes("info"));
			
			Result result = table.get(get);
			byte [] value = result.getValue(Bytes.toBytes("info"),Bytes.toBytes("roomtotal"));
			
			int roomtotal = Bytes.toInt(value);
			roomtotal += 1;
			
			p = new Put(Bytes.toBytes(ownerid));
			p.add(Bytes.toBytes("info"), Bytes.toBytes("roomtotal"), Bytes.toBytes(roomtotal));

			// Saving the put Instance to the HTable.
			table.put(p);
			// closing the HTable object
			table.close();
		} catch (IOException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public boolean deleteowner(String ownerid) {

		try {
			//tambah delete all room
			HTable table = new HTable(config, "room");

			List<Filter> filters = new ArrayList<Filter>();

			//Filter
			SingleColumnValueFilter colValFilter = new SingleColumnValueFilter(Bytes.toBytes("general"), Bytes.toBytes("ownerId")
		            , CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(ownerid)));
		    colValFilter.setFilterIfMissing(false);
		    filters.add(colValFilter);
			System.out.println(ownerid);

			FilterList fl = new FilterList( FilterList.Operator.MUST_PASS_ALL, filters);
			
			// Instantiating the Scan class
			Scan scan = new Scan();
			
			// Scanning the required columns
			scan.addFamily(Bytes.toBytes("general"));
			scan.addFamily(Bytes.toBytes("private"));
			scan.addFamily(Bytes.toBytes("public"));
			scan.addFamily(Bytes.toBytes("other"));
			scan.setFilter(fl);

			// Getting the scan result
			ResultScanner scanner = table.getScanner(scan);
			// Reading values from scan result
			for (Result result = scanner.next(); result != null; result = scanner.next()) {
				String roomId = Bytes.toString(result.getRow());
				deleteroom(roomId);
			}
			scanner.close();
			
			table = new HTable(config, "owner");
			// Instantiating Delete class
			Delete delete = new Delete(Bytes.toBytes(ownerid));
			delete.deleteFamily(Bytes.toBytes("info"));
			delete.deleteFamily(Bytes.toBytes("roominfo"));
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
			/* ----------- Get Owner ID by Room ID ----------------- */
			HTable table = new HTable(config, "room");
			
			Get get = new Get(Bytes.toBytes(roomid));
			get.addFamily(Bytes.toBytes("general"));
			
			Result result = table.get(get);
			byte [] value = result.getValue(Bytes.toBytes("general"),Bytes.toBytes("ownerId"));
			
			String owner = Bytes.toString(value);

			// closing the HTable object
			table.close();
			
			/* ----------- Mengurangi Jumlah Kamar ----------------- */
			table = new HTable(config, "owner");
			Put p = null;
			
			Get getOwner = new Get(Bytes.toBytes(owner));
			get.addFamily(Bytes.toBytes("info"));
			
			Result OwnerResult = table.get(getOwner);
			byte [] value2 = OwnerResult.getValue(Bytes.toBytes("info"),Bytes.toBytes("roomtotal"));
			
			int roomtotal = Bytes.toInt(value2);
			roomtotal -= 1;
			
			p = new Put(Bytes.toBytes(owner));
			p.add(Bytes.toBytes("info"), Bytes.toBytes("roomtotal"), Bytes.toBytes(roomtotal));

			// Saving the put Instance to the HTable.
			table.put(p);
			// closing the HTable object
			table.close();
			
			/* ----------- Delete Room ----------------- */
			table = new HTable(config, "room");
			// Instantiating Delete class
			Delete delete = new Delete(Bytes.toBytes(roomid));
			// delete.deleteColumn(Bytes.toBytes("public"), Bytes.toBytes("name"));
			delete.deleteFamily(Bytes.toBytes("general"));
			delete.deleteFamily(Bytes.toBytes("private"));
			delete.deleteFamily(Bytes.toBytes("public"));
			delete.deleteFamily(Bytes.toBytes("other"));
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
			p.add(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes(name));
			p.add(Bytes.toBytes("info"), Bytes.toBytes("address"), Bytes.toBytes(address));
			p.add(Bytes.toBytes("info"), Bytes.toBytes("contact"), Bytes.toBytes(contact));
			p.add(Bytes.toBytes("info"), Bytes.toBytes("roomtotal"), Bytes.toBytes(roomtotal));

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

	public boolean updateroom(String ownerid, String roomid, String address, String city, int totalroomarea, int rentalCost,
			List<String> Kelengkapan, int totaltoiletarea, String toiletType) {
		try {
			HTable table = new HTable(config,"room");

			Put p = new Put(Bytes.toBytes(roomid));
			p.add(Bytes.toBytes("general"), Bytes.toBytes("ownerId"), Bytes.toBytes(ownerid));
			p.add(Bytes.toBytes("general"), Bytes.toBytes("address"), Bytes.toBytes(address));
			p.add(Bytes.toBytes("general"), Bytes.toBytes("city"), Bytes.toBytes(city));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"), Bytes.toBytes(totalroomarea));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"), Bytes.toBytes(rentalCost));
			//p.add(Bytes.toBytes("private"), Bytes.toBytes("totalWatt"), Bytes.toBytes(totalwatt));
			//p.add(Bytes.toBytes("private"), Bytes.toBytes("floorNumber"), Bytes.toBytes(floornumber));
			//p.add(Bytes.toBytes("general"), Bytes.toBytes("totalComment"), Bytes.toBytes(totalcomment));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"), Bytes.toBytes(totaltoiletarea));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("toiletType"), Bytes.toBytes(toiletType));
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

	public boolean insertBarang(String roomid, String namaBarang, int jumlah) {
		try {
			HTable table = new HTable(config, "room");
			namaBarang = namaBarang.toLowerCase();

			Get get = new Get(Bytes.toBytes(roomid));
			get.addFamily(Bytes.toBytes("private"));
			
			Result result = table.get(get);
			byte [] value = result.getValue(Bytes.toBytes("private"),Bytes.toBytes("daftarBarang"));
			
			String daftarBarang = Bytes.toString(value);
			
			if(daftarBarang == null) {
				daftarBarang = namaBarang.concat(",");
			}
			else {
				daftarBarang = daftarBarang.concat(namaBarang + ",");
			}
					
			Put p = new Put(Bytes.toBytes(roomid));
			p.add(Bytes.toBytes("private"), Bytes.toBytes("daftarBarang"), Bytes.toBytes(daftarBarang));
			p.add(Bytes.toBytes("private"), Bytes.toBytes(namaBarang), Bytes.toBytes(jumlah));

			// Saving the put Instance to the HTable.
			table.put(p);
			// closing the HTable object
			table.close();
			System.out.println("barang inserted.....");
		} catch (IOException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public ArrayList<Owner> getOwnerByJumlah(int jumlah) throws IOException{
		ArrayList<Owner> resultList = new ArrayList<>();
		HTable table = new HTable(config, "owner");

		List<Filter> filters = new ArrayList<Filter>();

		//Filter
		SingleColumnValueFilter colValFilter = new SingleColumnValueFilter(Bytes.toBytes("info"), Bytes.toBytes("roomtotal")
	            , CompareFilter.CompareOp.GREATER_OR_EQUAL, new BinaryComparator(Bytes.toBytes(jumlah)));
	    colValFilter.setFilterIfMissing(false);
	    filters.add(colValFilter);

		FilterList fl = new FilterList( FilterList.Operator.MUST_PASS_ALL, filters);
		
		// Instantiating the Scan class
		Scan scan = new Scan();
		
		// Scanning the required columns
		scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));
		scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("address"));
		scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("contact"));
		scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("roomtotal"));
		scan.setFilter(fl);

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

	public List<Room> getRoomByToilet(String toiletType) throws IOException{
		List<Room> resultList = new ArrayList<Room>();

		HTable table = new HTable(config, "room");

		List<Filter> filters = new ArrayList<Filter>();

		//Filter
		SingleColumnValueFilter colValFilter = new SingleColumnValueFilter(Bytes.toBytes("private"), Bytes.toBytes("toiletType")
	            , CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(toiletType)));
	    colValFilter.setFilterIfMissing(false);
	    filters.add(colValFilter);
		System.out.println(toiletType);

		FilterList fl = new FilterList( FilterList.Operator.MUST_PASS_ALL, filters);
		
		// Instantiating the Scan class
		Scan scan = new Scan();
		
		// Scanning the required columns
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("ownerId"));
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("address"));
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("city"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("daftarBarang"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("toiletType"));
		scan.setFilter(fl);

		// Getting the scan result
		ResultScanner scanner = table.getScanner(scan);
		// Reading values from scan result
		for (Result result = scanner.next(); result != null; result = scanner.next()) {
			byte[] value1 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("ownerId"));
			byte[] value2 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("address"));
			byte[] value3 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("city"));
			byte[] value4 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"));
			byte[] value5 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
			byte[] value6 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("daftarBarang"));
			byte[] value7 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));
			byte[] value8 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("toiletType"));
			System.out.println("ownerId:" + Bytes.toString(value1) + " address:" + Bytes.toString(value2) + " city:"
					+ Bytes.toString(value3) + " totalRoomArea:" + Bytes.toInt(value4) + " rentalCost:" + Bytes.toInt(value5));
			//Printing the values
			String vownerId = Bytes.toString(value1);
			String vcity = Bytes.toString(value2);
			String vaddress = Bytes.toString(value3);
			int vtotalRoomArea = Bytes.toInt(value4);
			int vrentalCost = Bytes.toInt(value5);
			int vtoiletArea = Bytes.toInt(value7);
			String vtoiletType = Bytes.toString(value8);
			String roomId = Bytes.toString(result.getRow());
			
			//Scan perlengkapan
			List<Barang> vKelengkapan = getBarang(roomId);

			Room room = new Room(vownerId, vcity, vaddress, vtotalRoomArea, vrentalCost, vKelengkapan, vtoiletArea);
			room.setRoomId(roomId);
			room.setToiletType(vtoiletType);
			resultList.add(room);
		}
		scanner.close();

		return resultList;
	}

	public List<Room> getRoomByCity(String city) throws IOException{
		List<Room> resultList = new ArrayList<Room>();

		HTable table = new HTable(config, "room");

		List<Filter> filters = new ArrayList<Filter>();

		//Filter
		SingleColumnValueFilter colValFilter = new SingleColumnValueFilter(Bytes.toBytes("general"), Bytes.toBytes("city")
	            , CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(city)));
	    colValFilter.setFilterIfMissing(false);
	    filters.add(colValFilter);
		System.out.println(city);

		FilterList fl = new FilterList( FilterList.Operator.MUST_PASS_ALL, filters);
		
		// Instantiating the Scan class
		Scan scan = new Scan();
		
		// Scanning the required columns
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("ownerId"));
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("address"));
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("city"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("daftarBarang"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("toiletType"));
		scan.setFilter(fl);

		// Getting the scan result
		ResultScanner scanner = table.getScanner(scan);
		// Reading values from scan result
		for (Result result = scanner.next(); result != null; result = scanner.next()) {
			byte[] value1 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("ownerId"));
			byte[] value2 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("address"));
			byte[] value3 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("city"));
			byte[] value4 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"));
			byte[] value5 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
			byte[] value6 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("daftarBarang"));
			byte[] value7 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));
			byte[] value8 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("toiletType"));
			System.out.println("ownerId:" + Bytes.toString(value1) + " address:" + Bytes.toString(value2) + " city:"
					+ Bytes.toString(value3) + " totalRoomArea:" + Bytes.toInt(value4) + " rentalCost:" + Bytes.toInt(value5));
			//Printing the values
			String vownerId = Bytes.toString(value1);
			String vcity = Bytes.toString(value2);
			String vaddress = Bytes.toString(value3);
			int vtotalRoomArea = Bytes.toInt(value4);
			int vrentalCost = Bytes.toInt(value5);
			int vtoiletArea = Bytes.toInt(value7);
			String vtoiletType = Bytes.toString(value8);
			String roomId = Bytes.toString(result.getRow());
			
			//Scan perlengkapan
			List<Barang> vKelengkapan = getBarang(roomId);

			Room room = new Room(vownerId, vcity, vaddress, vtotalRoomArea, vrentalCost, vKelengkapan, vtoiletArea);
			room.setRoomId(roomId);
			room.setToiletType(vtoiletType);
			resultList.add(room);
		}
		scanner.close();

		return resultList;
	}

	public List<Room> getRoomByCost(int min, int max) throws IOException{
		List<Room> resultList = new ArrayList<Room>();

		HTable table = new HTable(config, "room");

		List<Filter> filters = new ArrayList<Filter>();

		//Filter
		SingleColumnValueFilter colValFilter = new SingleColumnValueFilter(Bytes.toBytes("private"), Bytes.toBytes("rentalCost")
	            , CompareFilter.CompareOp.GREATER_OR_EQUAL, new BinaryComparator(Bytes.toBytes(min)));
	    colValFilter.setFilterIfMissing(false);
	    filters.add(colValFilter);
	    SingleColumnValueFilter colValFilter2 = new SingleColumnValueFilter(Bytes.toBytes("private"), Bytes.toBytes("rentalCost")
	            , CompareFilter.CompareOp.LESS_OR_EQUAL, new BinaryComparator(Bytes.toBytes(max)));
	    colValFilter.setFilterIfMissing(false);
	    filters.add(colValFilter2);

		FilterList fl = new FilterList( FilterList.Operator.MUST_PASS_ALL, filters);
		
		// Instantiating the Scan class
		Scan scan = new Scan();
		
		// Scanning the required columns
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("ownerId"));
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("address"));
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("city"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("daftarBarang"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("toiletType"));
		scan.setFilter(fl);

		// Getting the scan result
		ResultScanner scanner = table.getScanner(scan);
		// Reading values from scan result
		for (Result result = scanner.next(); result != null; result = scanner.next()) {
			byte[] value1 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("ownerId"));
			byte[] value2 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("address"));
			byte[] value3 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("city"));
			byte[] value4 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"));
			byte[] value5 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
			byte[] value6 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("daftarBarang"));
			byte[] value7 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));
			byte[] value8 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("toiletType"));
			System.out.println("ownerId:" + Bytes.toString(value1) + " address:" + Bytes.toString(value2) + " city:"
					+ Bytes.toString(value3) + " totalRoomArea:" + Bytes.toInt(value4) + " rentalCost:" + Bytes.toInt(value5));
			//Printing the values
			String vownerId = Bytes.toString(value1);
			String vcity = Bytes.toString(value2);
			String vaddress = Bytes.toString(value3);
			int vtotalRoomArea = Bytes.toInt(value4);
			int vrentalCost = Bytes.toInt(value5);
			int vtoiletArea = Bytes.toInt(value7);
			String vtoiletType = Bytes.toString(value8);
			String roomId = Bytes.toString(result.getRow());
			
			//Scan perlengkapan
			List<Barang> vKelengkapan = getBarang(roomId);

			Room room = new Room(vownerId, vcity, vaddress, vtotalRoomArea, vrentalCost, vKelengkapan, vtoiletArea);
			room.setRoomId(roomId);
			room.setToiletType(vtoiletType);
			resultList.add(room);
		}
		scanner.close();

		return resultList;
	}

	public List<Room> getRoomByStuff(String[] stuffList) throws IOException{
		List<Room> resultList = new ArrayList<Room>();

		HTable table = new HTable(config, "room");

		List<Filter> filters = new ArrayList<Filter>();
		
		// Instantiating the Scan class
		Scan scan = new Scan();
		
		//Filter
		SingleColumnValueFilter colValFilter;
		for(int i = 0; i < stuffList.length; i++) {
		    System.out.println(stuffList[i]);
		    colValFilter = new SingleColumnValueFilter(Bytes.toBytes("private"), Bytes.toBytes(stuffList[i])
		            , CompareFilter.CompareOp.GREATER, new BinaryComparator(Bytes.toBytes(0)));
		    colValFilter.setFilterIfMissing(true);
		    filters.add(colValFilter);
		    scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes(stuffList[i]));
		}

		FilterList fl = new FilterList( FilterList.Operator.MUST_PASS_ALL, filters);
		
		// Scanning the required columns
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("ownerId"));
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("address"));
		scan.addColumn(Bytes.toBytes("general"), Bytes.toBytes("city"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("daftarBarang"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));
		scan.addColumn(Bytes.toBytes("private"), Bytes.toBytes("toiletType"));
		scan.setFilter(fl);

		// Getting the scan result
		ResultScanner scanner = table.getScanner(scan);
		// Reading values from scan result
		for (Result result = scanner.next(); result != null; result = scanner.next()) {
			byte[] value1 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("ownerId"));
			byte[] value2 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("address"));
			byte[] value3 = result.getValue(Bytes.toBytes("general"), Bytes.toBytes("city"));
			byte[] value4 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalRoomArea"));
			byte[] value5 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("rentalCost"));
			byte[] value6 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("daftarBarang"));
			byte[] value7 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("totalToiletArea"));
			byte[] value8 = result.getValue(Bytes.toBytes("private"), Bytes.toBytes("toiletType"));
			System.out.println("ownerId:" + Bytes.toString(value1) + " address:" + Bytes.toString(value2) + " city:"
					+ Bytes.toString(value3) + " totalRoomArea:" + Bytes.toInt(value4) + " rentalCost:" + Bytes.toInt(value5));
			//Printing the values
			String vownerId = Bytes.toString(value1);
			String vcity = Bytes.toString(value2);
			String vaddress = Bytes.toString(value3);
			int vtotalRoomArea = Bytes.toInt(value4);
			int vrentalCost = Bytes.toInt(value5);
			int vtoiletArea = Bytes.toInt(value7);
			String vtoiletType = Bytes.toString(value8);
			String roomId = Bytes.toString(result.getRow());
			
			//Scan perlengkapan
			List<Barang> vKelengkapan = getBarang(roomId);

			Room room = new Room(vownerId, vcity, vaddress, vtotalRoomArea, vrentalCost, vKelengkapan, vtoiletArea);
			room.setRoomId(roomId);
			room.setToiletType(vtoiletType);
			resultList.add(room);
		}
		scanner.close();

		return resultList;
	}
}