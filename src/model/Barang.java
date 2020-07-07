package model;

public class Barang {
	private String namaBarang;
	private int jumlah;
	
	public Barang(String nama, int jumlah) {
		this.namaBarang = nama;
		this.jumlah = jumlah;
	}
	
	public String getNamaBarang() {
		return namaBarang;
	}
	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		jumlah = jumlah;
	}
}