package model;

import org.apache.hadoop.hbase.util.Bytes;

public class Student {

	String vRow;
	String vname;
	String vcity;
	String vdesignation;
	Integer vsalary;
	public Student(String vname, String vcity, String vdesignation,
			Integer vsalary) {
		super();
		this.vname = vname;
		this.vcity = vcity;
		this.vdesignation = vdesignation;
		this.vsalary = vsalary;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getVcity() {
		return vcity;
	}
	public void setVcity(String vcity) {
		this.vcity = vcity;
	}
	public String getVdesignation() {
		return vdesignation;
	}
	public void setVdesignation(String vdesignation) {
		this.vdesignation = vdesignation;
	}
	public Integer getVsalary() {
		return vsalary;
	}
	public void setVsalary(Integer vsalary) {
		this.vsalary = vsalary;
	}
	public String getvRow() {
		return vRow;
	}
	public void setvRow(String vRow) {
		this.vRow = vRow;
	}	
}
