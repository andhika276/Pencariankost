package controller;

import java.io.IOException;
import java.util.ArrayList;

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
		scan.addColumn(Bytes.toBytes("public"), Bytes.toBytes("name"));
		scan.addColumn(Bytes.toBytes("public"), Bytes.toBytes("address"));
		scan.addColumn(Bytes.toBytes("public"),Bytes.toBytes("contact"));
		scan.addColumn(Bytes.toBytes("public"), Bytes.toBytes("roomTotal"));

		// Getting the scan result
		ResultScanner scanner = table.getScanner(scan);

		// Reading values from scan result
		for (Result result = scanner.next(); result != null; result = scanner
				.next()) {
			byte[] value1 = result.getValue(Bytes.toBytes("public"),
					Bytes.toBytes("name"));
			byte[] value2 = result.getValue(Bytes.toBytes("public"),
					Bytes.toBytes("address"));
			byte[] value3 = result.getValue(Bytes.toBytes("public"),
							Bytes.toBytes("contact"));
			byte[] value4 = result.getValue(Bytes.toBytes("public"),
					Bytes.toBytes("roomTotal"));
			System.out.println("Name:" + Bytes.toString(value1) + " Address:"
					+ Bytes.toString(value2) + " Contact:"
					+ Bytes.toString(value3) + " RoomTotal:"
					+ Bytes.toInt(value4));
			// Printing the values
			String vname = Bytes.toString(value1);
			String vaddress = Bytes.toString(value2);
			String vcontact = Bytes.toString(value3);
			Integer vroomTotal = Bytes.toInt(value4);
			String vownerid = Bytes.toString(result.getRow());

			Owner owner = new Owner(vname, vaddress, vcontact, vroomTotal);
			owner.setVownerId(vownerid);
			resultList.add(owner);
		}
		scanner.close();

		return resultList;
	}
	public boolean insertData(String name, String address, String contact, int roomtotal) {
		try {
			int lastrow=0;
			// Instantiating HTable class
			HTable hTable = new HTable(config, "owner");
			
			Scan scan = new Scan();
			scan.addColumn(Bytes.toBytes("public"), Bytes.toBytes("roomtotal"));
			ResultScanner result = hTable.getScanner(scan);

			for(Result rs = result.next(); rs !=null; rs = result.next()){
				++lastrow;
			}
			
			// Instantiating Put class
			// accepts a row name.
			Put p = new Put(Bytes.toBytes("row"+(lastrow +1)));
			// adding values using add() method
			// accepts column family name, qualifier/row name ,value
			p.add(Bytes.toBytes("personal"), Bytes.toBytes("name"), Bytes.toBytes(name));
			p.add(Bytes.toBytes("personal"), Bytes.toBytes("city"), Bytes.toBytes(address));
			p.add(Bytes.toBytes("professional"), Bytes.toBytes("designation"), Bytes.toBytes(contact));
			p.add(Bytes.toBytes("professional"), Bytes.toBytes("salary"), Bytes.toBytes(roomtotal));
			
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
	
	
	public boolean delete(String id) {
		
		try {
			HTable table = new HTable(config, "owner");
			// Instantiating Delete class
			Delete delete = new Delete(Bytes.toBytes(id));
			//delete.deleteColumn(Bytes.toBytes("public"), Bytes.toBytes("name"));
			delete.deleteFamily(Bytes.toBytes("public"));
			delete.deleteFamily(Bytes.toBytes("public"));
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
	
	public boolean update(String row,String name, String address, String contact, int roomtotal) {
		try {
			HTable table = new HTable(config, "owner");
			
			Put p = new Put(Bytes.toBytes(row));
			p.add(Bytes.toBytes("personal"), Bytes.toBytes("name"), Bytes.toBytes(name));
			p.add(Bytes.toBytes("personal"), Bytes.toBytes("city"), Bytes.toBytes(address));
			p.add(Bytes.toBytes("professional"), Bytes.toBytes("designation"), Bytes.toBytes(contact));
			p.add(Bytes.toBytes("professional"), Bytes.toBytes("salary"), Bytes.toBytes(roomtotal));
			
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