import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ValueFilter2 {
	public static void main(String[] args) throws IOException {
	    Configuration conf = HBaseConfiguration.create();

	    HTable table = new HTable(conf, "tabel1");

	    List<Filter> filters = new ArrayList<Filter>();

	    Filter famFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL,
	              new BinaryComparator(Bytes.toBytes("professional")));
	    filters.add(famFilter);

	    Filter colFilter = new QualifierFilter(CompareFilter.CompareOp.EQUAL,
	      new BinaryComparator(Bytes.toBytes("salary")));

	    filters.add(colFilter);

	    Filter valFilter = new ValueFilter(CompareFilter.CompareOp.GREATER,
	              new BinaryComparator(Bytes.toBytes("5")));

	    filters.add(valFilter);

	    FilterList fl = new FilterList( FilterList.Operator.MUST_PASS_ALL, filters);


	    Scan scan = new Scan();
	    scan.setFilter(fl);
	    ResultScanner scanner = table.getScanner(scan);
	    System.out.println("Scanning table... ");
	    for (Result result : scanner) {
	        for (KeyValue kv : result.raw()) {
	        	System.out.println("kv:"+kv +", Key: " + Bytes.toString(kv.getRow())  + ", Value: " +Bytes.toString(kv.getValue()));
	        }
	    }   

	    scanner.close();
	    System.out.println("Completed ");
	  }
}
