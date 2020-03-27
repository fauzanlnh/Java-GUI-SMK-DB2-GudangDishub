/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gudang.clas;

/**
 *
 * @author Fauzan13
 */
public class TransaksiMasuk {
private String nojual,tanggal,pelanggan,pengambil,namabarang, keluar;
public TransaksiMasuk(){}   

public TransaksiMasuk(String nojual,String tanggal,String pelanggan,String pengambil,String namabarang,String keluar) {
    this.nojual = nojual;
    this.tanggal = tanggal;
    this.pelanggan = pelanggan;        
    this.pengambil = pengambil;
    this.namabarang = namabarang;
    this.keluar = keluar;
}
public String getNoJual() {
    return nojual;
}

public String getTanggal() {
    return tanggal;
}

public String getPelanggan() {
    return pelanggan;
}

public String pengambil() {
    return pengambil;
}

public String getNamaBarang() {
    return namabarang;
}
public String getKeluar() {
    return keluar;
}}