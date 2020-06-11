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
	/*
	Configuration config = HBaseConfiguration.create();

	public HBaseUtils() {

	}

	public ArrayList<Employee> getEmployee() throws IOException {
		
		ArrayList<Employee> resultList = new ArrayList<>();
		HTable table = new HTable(config, "employee");

		// Instantiating the Scan class
		Scan scan = new Scan();

		// Scanning the required columns
		scan.addColumn(Bytes.toBytes("personal"), Bytes.toBytes("name"));
		scan.addColumn(Bytes.toBytes("personal"), Bytes.toBytes("city"));
		scan.addColumn(Bytes.toBytes("professional"),
				Bytes.toBytes("designation"));
		scan.addColumn(Bytes.toBytes("professional"), Bytes.toBytes("salary"));

		// Getting the scan result
		ResultScanner scanner = table.getScanner(scan);

		// Reading values from scan result
		for (Result result = scanner.next(); result != null; result = scanner
				.next()) {
			byte[] value1 = result.getValue(Bytes.toBytes("personal"),
					Bytes.toBytes("name"));
			byte[] value2 = result.getValue(Bytes.toBytes("personal"),
					Bytes.toBytes("city"));
			byte[] value3 = result
					.getValue(Bytes.toBytes("professional"),
							Bytes.toBytes("designation"));
			byte[] value4 = result.getValue(Bytes.toBytes("professional"),
					Bytes.toBytes("salary"));
			System.out.println("Name:" + Bytes.toString(value1) + " City:"
					+ Bytes.toString(value2) + " Designation:"
					+ Bytes.toString(value3) + " Salary:"
					+ Bytes.toInt(value4));
			// Printing the values
			String vname = Bytes.toString(value1);
			String vcity = Bytes.toString(value2);
			String vdesignation = Bytes.toString(value3);
			Integer vsalary = Bytes.toInt(value4);
			String vRow = Bytes.toString(result.getRow());

			Employee employee = new Employee(vname, vcity, vdesignation, vsalary);
			employee.setvRow(vRow);
			resultList.add(employee);
		}
		scanner.close();

		return resultList;
	}
	public boolean insertData(String name, String city, String designation, int salary) {
		try {
			int lastrow=0;
			// Instantiating HTable class
			HTable hTable = new HTable(config, "employee");
			
			Scan scan = new Scan();
			scan.addColumn(Bytes.toBytes("professional"), Bytes.toBytes("salary"));
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
			p.add(Bytes.toBytes("personal"), Bytes.toBytes("city"), Bytes.toBytes(city));
			p.add(Bytes.toBytes("professional"), Bytes.toBytes("designation"), Bytes.toBytes(designation));
			p.add(Bytes.toBytes("professional"), Bytes.toBytes("salary"), Bytes.toBytes(salary));
			
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
	
	
	public boolean delete(String row) {
		
		try {
			HTable table = new HTable(config, "employee");
			// Instantiating Delete class
			Delete delete = new Delete(Bytes.toBytes(row));
			//delete.deleteColumn(Bytes.toBytes("personal"), Bytes.toBytes("name"));
			delete.deleteFamily(Bytes.toBytes("professional"));
			delete.deleteFamily(Bytes.toBytes("personal"));
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
	
	public boolean update(String row,String name, String city, String designation, int salary) {
		try {
			HTable table = new HTable(config, "employee");
			
			Put p = new Put(Bytes.toBytes(row));
			p.add(Bytes.toBytes("personal"), Bytes.toBytes("name"), Bytes.toBytes(name));
			p.add(Bytes.toBytes("personal"), Bytes.toBytes("city"), Bytes.toBytes(city));
			p.add(Bytes.toBytes("professional"), Bytes.toBytes("designation"), Bytes.toBytes(designation));
			p.add(Bytes.toBytes("professional"), Bytes.toBytes("salary"), Bytes.toBytes(salary));
			
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
	}*/
}