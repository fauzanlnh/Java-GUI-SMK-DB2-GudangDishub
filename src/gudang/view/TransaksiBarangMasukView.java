/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gudang.view;

import gudang.clas.*;
import gudang.view.*;
import com.mysql.jdbc.Connection;
import gudang.clas.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Fauzan13
 */
public class TransaksiBarangMasukView extends javax.swing.JFrame {

    /**
     * Creates new form TransaksiBarangMasukView
     */
    Connection koneksi;

    public TransaksiBarangMasukView() {
        initComponents();
        koneksi = (Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
        disableForm();
        showData();
        setLocationRelativeTo(this);
        SelectComboBox();
    }

    public void SelectComboBox() {

        try {

            String query = "SELECT * FROM t_kategori";
            java.sql.Statement st = koneksi.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                cmbKategori.addItem(rs.getString("kode_kategori"));

            }

            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();

        } catch (SQLException e) {
        }
    }

    private void disableForm() {
        btnNew.setEnabled(true);
        btnInsert.setEnabled(false);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(false);
        btnInsert.setEnabled(false);
        btnReset.setEnabled(true);

        btnEdit.setEnabled(false);
        txtStok.setVisible(true);

        txtKd.setEnabled(false);
        txtNama.setEnabled(false);
        txtHarga.setEnabled(false);
        txtMasuk.setEnabled(false);
        txtTanggal.setEnabled(false);
        cmbKategori.setEnabled(false);
        btnLihat.setEnabled(false);
        cmbTriwulan.setEnabled(false);

        txtStok.setText("0");
        txtKd.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        txtMasuk.setText("");
        cmbKategori.setSelectedItem("");
    }

        private void itung() {
        String a = txtStok.getText();
        String m = txtMasuk.getText();

        if (txtMasuk.getText() == null) {
            txtStok.setText(a);
        } else {
                int b = Integer.parseInt(a);
            int h = Integer.parseInt(m);
            int jumlah = b + h;
            String s = "" + jumlah;

            txtStok.setText(s);
        }
    }

    public void simpan1() {

        String kode = txtKd.getText();
        String nama = txtNama.getText();
        String harga = txtHarga.getText();
        String jumlah = txtMasuk.getText();
        String kat = cmbKategori.getSelectedItem().toString();
        String stok = txtStok.getText();
        
        String tanggalLahir = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tanggalLahir);
        String tgl = String.valueOf(fm.format(txtTanggal.getDate()));

        try {
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO t_persediaan(kode_barang,nama_barang,kode_kategori,harga,jumlah,jumlaht2,jumlaht3,jumlaht4,tahun)"
                    + "VALUES('" + kode + "','" + nama + "','" + kat + "','" + harga + "','" + stok + "','" + stok + "','" + stok + "','" + stok + "','" + tgl + "')";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if (berhasil == 1) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Dimasukan");
            } else {
                JOptionPane.showMessageDialog(null, "Data Gagal DImasukan");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void simpan2() {
        String kode = txtKd.getText();
        String nama = txtNama.getText();
        String harga = txtHarga.getText();
        String jumlah1 = txtStok.getText();
        String jumlah = txtMasuk.getText();
        String kat = cmbKategori.getSelectedItem().toString();

        try {
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO t_persediaan(kode_barang,nama_barang,kode_kategori,harga,jumlah,jumlaht2,jumlaht3,jumlaht4,tahun)"
                    + "VALUES('" + kode + "','" + nama + "','" + kat + "','" + harga + "','" + jumlah1 + "','" + jumlah + "','" + jumlah + "','" + jumlah + "','" + jumlah + "')";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if (berhasil == 1) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Dimasukan");
            } else {
                JOptionPane.showMessageDialog(null, "Data Gagal DImasukan");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void simpan3() {
        String kode = txtKd.getText();
        String nama = txtNama.getText();
        String harga = txtHarga.getText();
        String jumlah1 = txtStok.getText();
        String jumlah2 = txtStok.getText();
        String jumlah = txtMasuk.getText();
        String kat = cmbKategori.getSelectedItem().toString();
        try {
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO t_persediaan(kode_barang,nama_barang,kode_kategori,harga,jumlah,jumlaht2,jumlaht3,jumlaht4,tahun)"
                    + "VALUES('" + kode + "','" + nama + "','" + kat + "','" + harga + "','" + jumlah1 + "','" + jumlah2 + "','" + jumlah + "','" + jumlah + "')";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if (berhasil == 1) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Dimasukan");
            } else {
                JOptionPane.showMessageDialog(null, "Data Gagal DImasukan");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void simpan4() {
        String kode = txtKd.getText();
        String nama = txtNama.getText();
        String harga = txtHarga.getText();
        String jumlah1 = txtStok.getText();
        String jumlah2 = txtStok.getText();
        String jumlah3 = txtStok.getText();
        String jumlah = txtMasuk.getText();
        String kat = cmbKategori.getSelectedItem().toString();
        try {
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO t_persediaan(kode_barang,nama_barang,kode_kategori,harga,jumlah,jumlaht2,jumlaht3,jumlaht4)"
                    + "VALUES('" + kode + "','" + nama + "','" + kat + "','" + harga + "','" + jumlah1 + "','" + jumlah2 + "','" + jumlah3 + "','" + jumlah + "')";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if (berhasil == 1) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Dimasukan");
            } else {
                JOptionPane.showMessageDialog(null, "Data Gagal DImasukan");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void simpanwow() throws SQLException {
        if (cmbTriwulan.getSelectedIndex() == 0) {
            simpan1();
        } else if (cmbTriwulan.getSelectedIndex() == 1) {
            simpan2();
        } else if (cmbTriwulan.getSelectedIndex() == 2) {
            simpan3();
        } else if (cmbTriwulan.getSelectedIndex() == 3) {
            simpan4();

        }
    }

    public void simpanbmasuk1() {

        String tanggalLahir = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tanggalLahir);
        String tanggal = String.valueOf(fm.format(txtTanggal.getDate()));

        String kode = txtKd.getText();
        String nama = txtNama.getText();
        String harga = txtHarga.getText();
        String jumlah = txtMasuk.getText();
        String jumlah1 = txtStok.getText();
        String kat = cmbKategori.getSelectedItem().toString();

        try {
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO t_bmasuk(kode_barang,nama_barang,kode_kategori,harga,harga2,harga3,harga4,jumlah_masuk,jumlah_masuk2,jumlah_masuk3,jumlah_masuk4,tanggal,tanggal2,tanggal3,tanggal4)"
                    + "VALUES('" + kode + "','" + nama + "','" + kat + "','" + harga + "','" + jumlah1 + "','" + jumlah1 + "','" + jumlah1 + "','" + jumlah + "','" + jumlah1 + "','" + jumlah1 + "','" + jumlah1 + "','" + tanggal + "','" + jumlah1 + "','" + jumlah1 + "','" + jumlah1 + "')";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if (berhasil == 1) {
            } else {
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Pada Database");
        }
    }

    public void simpanbmasuk2() {

       
        String tanggalLahir = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tanggalLahir);
        String tanggal = String.valueOf(fm.format(txtTanggal.getDate()));

        String kode = txtKd.getText();
        String nama = txtNama.getText();
        String harga = txtHarga.getText();
        String jumlah = txtMasuk.getText();
        String kat = cmbKategori.getSelectedItem().toString();


        try {
            Statement stmt = koneksi.createStatement();
            String query = "UPDATE t_bmasuk SET harga2 = '" + harga + "'," + "jumlah_masuk2 = '" + jumlah + "'," + "tanggal2 = '" + tanggal + "'"
                    + "WHERE kode_barang = '" + kode + "'";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if (berhasil == 1) {
            } else {
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Pada Database");
        }
    }

    public void simpanbmasuk3() {

        String tanggalLahir = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tanggalLahir);
        String tanggal = String.valueOf(fm.format(txtTanggal.getDate()));

        String kode = txtKd.getText();
        String nama = txtNama.getText();
        String harga = txtHarga.getText();
        String jumlah = txtMasuk.getText();
        String jumlah1 = txtStok.getText();
        String kat = cmbKategori.getSelectedItem().toString();
        String trw = cmbTriwulan.getSelectedItem().toString();

        try {
            Statement stmt = koneksi.createStatement();
           String query = "UPDATE t_bmasuk SET harga3 = '" + harga + "'," + "jumlah_masuk3 = '" + jumlah + "'," + "tanggal3 = '" + tanggal + "'"
                    + "WHERE kode_barang = '" + kode + "'";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if (berhasil == 1) {
            } else {
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Pada Database");
        }
    }

    public void simpanbmasuk4() {

        String tanggalLahir = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tanggalLahir);
        String tanggal = String.valueOf(fm.format(txtTanggal.getDate()));

        String kode = txtKd.getText();
        String nama = txtNama.getText();
        String harga = txtHarga.getText();
        String jumlah = txtMasuk.getText();
        String jumlah1 = txtStok.getText();
        String kat = cmbKategori.getSelectedItem().toString();
        String trw = cmbTriwulan.getSelectedItem().toString();

        try {
            Statement stmt = koneksi.createStatement();
           String query = "UPDATE t_bmasuk SET harga4 = '" + harga + "'," + "jumlah_masuk4 = '" + jumlah + "'," + "tanggal4 = '" + tanggal + "'"
                    + "WHERE kode_barang = '" + kode + "'";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if (berhasil == 1) {
            } else {
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Pada Database");
        }
    }

    private void bmasukwow() throws SQLException {
        if (cmbTriwulan.getSelectedIndex() == 0) {
            simpanbmasuk1();
        } else if (cmbTriwulan.getSelectedIndex() == 1) {
            simpanbmasuk2();
        } else if (cmbTriwulan.getSelectedIndex() == 2) {
            simpanbmasuk3();
        } else if (cmbTriwulan.getSelectedIndex() == 3) {
            simpanbmasuk4();

        }
    }

    DefaultTableModel dtm;

    public void showData() {

        String[] kolom = {"NO", "Kode", "Nama", "Kategori", "Harga", "Jumlah", "Tanggal Masuk"};

        dtm = new DefaultTableModel(null, kolom);

        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT  t_bmasuk.kode_barang,t_bmasuk.nama_barang,t_bmasuk.kode_kategori,t_bmasuk.harga,t_bmasuk.jumlah_masuk,t_bmasuk.tanggal FROM t_bmasuk";
            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {
                String kode = rs.getString("kode_barang");
                String nama = rs.getString("nama_barang");
                String kategori = rs.getString("kode_kategori");
                String harga = rs.getString("harga");
                String jumlah = rs.getString("jumlah_masuk");
                String jumlahh = rs.getString("tanggal");

                dtm.addRow(new String[]{no + "", kode, nama, kategori, harga, jumlah, jumlahh});
                no++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tblPengguna.setModel(dtm);
    }

    public void showData2() {

        String[] kolom = {"NO", "Kode", "Nama", "Kategori", "Harga", "Jumlah", "Tanggal Masuk"};

        dtm = new DefaultTableModel(null, kolom);

        try {
            Statement stmt = koneksi.createStatement();
  
            String query = "SELECT  t_bmasuk.kode_barang,t_bmasuk.nama_barang,t_bmasuk.kode_kategori,t_bmasuk.harga2,t_bmasuk.jumlah_masuk2,t_bmasuk.tanggal2 FROM t_bmasuk";       
            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {
                String kode = rs.getString("kode_barang");
                String nama = rs.getString("nama_barang");
                String kategori = rs.getString("kode_kategori");
                String harga = rs.getString("harga2");
                String jumlah = rs.getString("jumlah_masuk2");
                String jumlahh = rs.getString("tanggal2");

                dtm.addRow(new String[]{no + "", kode, nama, kategori, harga, jumlah, jumlahh});
                no++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tblPengguna.setModel(dtm);
    }

    public void showData3() {

        String[] kolom = {"NO", "Kode", "Nama", "Kategori", "Harga", "Jumlah", "Tanggal Masuk"};

        dtm = new DefaultTableModel(null, kolom);

        try {
            Statement stmt = koneksi.createStatement();
        
            String query = "SELECT  t_bmasuk.kode_barang,t_bmasuk.nama_barang,t_bmasuk.kode_kategori,t_bmasuk.harga3,t_bmasuk.jumlah_masuk3,t_bmasuk.tanggal3 FROM t_bmasuk";    ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {
                String kode = rs.getString("kode_barang");
                String nama = rs.getString("nama_barang");
                String kategori = rs.getString("kode_kategori");
                String harga = rs.getString("harga3");
                String jumlah = rs.getString("jumlah_masuk3");
                String jumlahh = rs.getString("tanggal3");

                dtm.addRow(new String[]{no + "", kode, nama, kategori, harga, jumlah, jumlahh});
                no++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tblPengguna.setModel(dtm);
    }

    public void showData4() {

        String[] kolom = {"NO", "Kode", "Nama", "Kategori", "Harga", "Jumlah", "Tanggal Masuk"};

        dtm = new DefaultTableModel(null, kolom);

        try {
            Statement stmt = koneksi.createStatement();
     
            String query = "SELECT  t_bmasuk.kode_barang,t_bmasuk.nama_barang,t_bmasuk.kode_kategori,t_bmasuk.harga4,t_bmasuk.jumlah_masuk4,t_bmasuk.tanggal4 FROM t_bmasuk"; 
            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {
                String kode = rs.getString("kode_barang");
                String nama = rs.getString("nama_barang");
                String kategori = rs.getString("kode_kategori");
                String harga = rs.getString("harga4");
                String jumlah = rs.getString("jumlah_masuk4");
                String jumlahh = rs.getString("tanggal4");

                dtm.addRow(new String[]{no + "", kode, nama, kategori, harga, jumlah, jumlahh});
                no++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tblPengguna.setModel(dtm);
    }
    
    private void showwow() throws SQLException {
        if (cmbTriwulan1.getSelectedIndex() == 0) {
            showData();
        } else if (cmbTriwulan1.getSelectedIndex() == 1) {
            showData2();
        } else if (cmbTriwulan1.getSelectedIndex() == 2) {
            showData3();
        } else if (cmbTriwulan1.getSelectedIndex() == 3) {
            showData4();

        }
    }

    public void cari() {

        String[] kolom = {"NO", "Kode", "Nama", "Jumlah"};

        dtm = new DefaultTableModel(null, kolom);

        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM t_persediaan ";

            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {

                String kode = rs.getString("kode_barang");
                String nama = rs.getString("nama_barang");
                String jumlah = rs.getString("jumlah");

                dtm.addRow(new String[]{no + "", kode, nama,  jumlah});
                no++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tblPengguna.setModel(dtm);
    }
    
    public void cari2() {

        String[] kolom = {"NO", "Kode", "Nama", "Kategori", "Harga", "Jumlah"};

        dtm = new DefaultTableModel(null, kolom);

        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM t_bmasuk WHERE nama_barang like  '%" + txtCari.getText() + "%' OR kode_kategori like '%" + txtCari.getText() + "%'";

            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {

                String kode = rs.getString("kode_barang");
                String nama = rs.getString("nama_barang");
                String kategori = rs.getString("kode_kategori");
                String harga = rs.getString("harga2");
                String jumlah = rs.getString("jumlah_masuk2");

                dtm.addRow(new String[]{no + "", kode, nama, kategori, harga, jumlah});
                no++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tblPengguna.setModel(dtm);
    }
    
    public void cari3() {

        String[] kolom = {"NO", "Kode", "Nama", "Kategori", "Harga", "Jumlah"};

        dtm = new DefaultTableModel(null, kolom);

        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM t_bmasuk WHERE nama_barang like  '%" + txtCari.getText() + "%' OR kode_kategori like '%" + txtCari.getText() + "%'";

            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {

                String kode = rs.getString("kode_barang");
                String nama = rs.getString("nama_barang");
                String kategori = rs.getString("kode_kategori");
                String harga = rs.getString("harga3");
                String jumlah = rs.getString("jumlah_masuk3");

                dtm.addRow(new String[]{no + "", kode, nama, kategori, harga, jumlah});
                no++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tblPengguna.setModel(dtm);
    }
    
    public void cari4() {

        String[] kolom = {"NO", "Kode", "Nama", "Kategori", "Harga", "Jumlah"};

        dtm = new DefaultTableModel(null, kolom);

        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM t_bmasuk WHERE nama_barang like  '%" + txtCari.getText() + "%' OR kode_kategori like '%" + txtCari.getText() + "%'";

            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {

                String kode = rs.getString("kode_barang");
                String nama = rs.getString("nama_barang");
                String kategori = rs.getString("kode_kategori");
                String harga = rs.getString("harga4");
                String jumlah = rs.getString("jumlah_masuk4");

                dtm.addRow(new String[]{no + "", kode, nama, kategori, harga, jumlah});
                no++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tblPengguna.setModel(dtm);
    }
    
       private void lihatBarangwow() {
        if (cmbTriwulan1.getSelectedIndex() == 0) {
        cari();
        } else if (cmbTriwulan1.getSelectedIndex() == 1) {
       cari2();
        } else if (cmbTriwulan1.getSelectedIndex() == 2) {
        cari3();
        } else if (cmbTriwulan1.getSelectedIndex() == 3) {
       cari4();
        }
    }

    

    public void AutoNumber() {
        try {
            Statement stmt = koneksi.createStatement();
            String query_bukaTabel = "select right(kode_barang , 1) as no_urut from t_bmasuk";
            ResultSet line_result = stmt.executeQuery(query_bukaTabel);
            if (line_result.first() == false) {
                txtKd.setText("BRG0001");
            } else {
                line_result.last();
                int no = line_result.getInt(1) + 1;
                String nomor = String.valueOf(no);
                int oto = nomor.length();
                for (int i = 0; i < 2 - oto; i++) {
                    nomor = "000" + nomor;
                }
                txtKd.setText("BRG" + nomor);
            }
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }
    

    private void klik() {
        String getKode = tblPengguna.getValueAt(tblPengguna.getSelectedRow(), 1).toString();
        String getNama = tblPengguna.getValueAt(tblPengguna.getSelectedRow(), 2).toString();
        String getUser = tblPengguna.getValueAt(tblPengguna.getSelectedRow(), 4).toString();
        String getBagian = tblPengguna.getValueAt(tblPengguna.getSelectedRow(), 3).toString();
        String getTelepon = tblPengguna.getValueAt(tblPengguna.getSelectedRow(), 5).toString();

        txtKd.setText(getKode);
        txtNama.setText(getNama);
        cmbKategori.setSelectedItem(getBagian);
        txtMasuk.setText(getTelepon);
        txtHarga.setText(getUser);

    }

    public void edit() {

        try {
            String kode = txtKd.getText();
            String nama = txtNama.getText();
            String harga = txtHarga.getText();
            String jumlah = txtMasuk.getText();
            String kat = cmbKategori.getSelectedItem().toString();
           
            
            Statement stmt = koneksi.createStatement();
            String query = "UPDATE t_bmasuk SET nama_barang = '" + nama + "'," + "kategori = '" + kat + "'," + "harga = '" + harga + "'," + "jumlah = '" + jumlah + "'"
                    + "WHERE kode_barang = '" + kode + "'";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if (berhasil == 1) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            } else {
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Pada Database");
        }
    }

    public void edit1() {

        try {
            String kode = txtKd.getText();
            String nama = txtNama.getText();
            String harga = txtHarga.getText();
            String jumlah = txtMasuk.getText();
            String kat = cmbKategori.getSelectedItem().toString();
            
            
            Statement stmt = koneksi.createStatement();
            String query = "UPDATE t_bmasuk SET nama_barang = '" + nama + "'," + "kategori = '" + kat + "'," + "harga = '" + harga + "'," + "jumlah = '" + jumlah + "'"
                    + "WHERE kode_barang = '" + kode + "'";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if (berhasil == 1) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            } else {
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Pada Database");
        }
    }
    
    DefaultTableModel tabel = new DefaultTableModel();

    private void lihatBarangATK() {
        
            jDialog1.setLocationRelativeTo(null);
        tabel.addColumn("Kode Barang");
        tabel.addColumn("Nama Barang");
        tabel.addColumn("Kategori");

        jTable1.setModel(tabel);

        TableColumn lebar_kolom;

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);

        lebar_kolom = jTable1.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = jTable1.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = jTable1.getColumnModel().getColumn(2);
        lebar_kolom.setPreferredWidth(100);

        tabel.getDataVector().removeAllElements();
        tabel.fireTableDataChanged();
        try {
            Statement stmt = koneksi.createStatement();
            String query_bukaTabel = "select kode_barang,nama_barang, kode_kategori from t_barang WHERE kode_kategori = 'ATK'";
            ResultSet rs = stmt.executeQuery(query_bukaTabel);
            while (rs.next()) {
                Object[] getO = new Object[3];
                getO[0] = rs.getString("kode_barang");
                getO[1] = rs.getString("nama_barang");
                getO[2] = rs.getString("kode_kategori");
                

                tabel.addRow(getO);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
        }
    }
    private void lihatBarangAKBR() {
        
        tabel.addColumn("Kode Barang");
        tabel.addColumn("Nama Barang");
        tabel.addColumn("Kategori");

        jTable1.setModel(tabel);

        TableColumn lebar_kolom;

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);

        lebar_kolom = jTable1.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = jTable1.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = jTable1.getColumnModel().getColumn(2);
        lebar_kolom.setPreferredWidth(100);

        tabel.getDataVector().removeAllElements();
        tabel.fireTableDataChanged();
        try {
            Statement stmt = koneksi.createStatement();
            String query_bukaTabel = "select kode_barang,nama_barang, kode_kategori from t_barang WHERE kode_kategori = 'AKBR'";
            ResultSet rs = stmt.executeQuery(query_bukaTabel);
            while (rs.next()) {
                Object[] getO = new Object[3];
                getO[0] = rs.getString("kode_barang");
                getO[1] = rs.getString("nama_barang");
                getO[2] = rs.getString("kode_kategori");

                tabel.addRow(getO);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
        }
    }
    private void lihatBarangALST() {
        
        tabel.addColumn("Kode Barang");
        tabel.addColumn("Nama Barang");
        tabel.addColumn("Kategori");

        jTable1.setModel(tabel);

        TableColumn lebar_kolom;

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);

        lebar_kolom = jTable1.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = jTable1.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = jTable1.getColumnModel().getColumn(2);
        lebar_kolom.setPreferredWidth(100);

        tabel.getDataVector().removeAllElements();
        tabel.fireTableDataChanged();
        try {
            Statement stmt = koneksi.createStatement();
            String query_bukaTabel = "select kode_barang,nama_barang, kode_kategori from t_barang WHERE kode_kategori = 'ALST'";
            ResultSet rs = stmt.executeQuery(query_bukaTabel);
            while (rs.next()) {
                Object[] getO = new Object[3];
                getO[0] = rs.getString("kode_barang");
                getO[1] = rs.getString("nama_barang");
                getO[2] = rs.getString("kode_kategori");

                tabel.addRow(getO);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
        }
    }
    private void lihatBarangCTKN() {
        
        tabel.addColumn("Kode Barang");
        tabel.addColumn("Nama Barang");
        tabel.addColumn("Kategori");

        jTable1.setModel(tabel);

        TableColumn lebar_kolom;

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);

        lebar_kolom = jTable1.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = jTable1.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = jTable1.getColumnModel().getColumn(2);
        lebar_kolom.setPreferredWidth(100);

        tabel.getDataVector().removeAllElements();
        tabel.fireTableDataChanged();
        try {
            Statement stmt = koneksi.createStatement();
            String query_bukaTabel = "select kode_barang,nama_barang, kode_kategori from t_barang WHERE kode_kategori = 'CTKN'";
            ResultSet rs = stmt.executeQuery(query_bukaTabel);
            while (rs.next()) {
                Object[] getO = new Object[3];
                getO[0] = rs.getString("kode_barang");
                getO[1] = rs.getString("nama_barang");
                getO[2] = rs.getString("kode_kategori");

                tabel.addRow(getO);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
        }
    }
    
    
     private void lihatbarangwow() throws SQLException {
        if (jComboBox1.getSelectedIndex() == 0) {
            jDialog1.setLocationRelativeTo(null);
            lihatBarangATK();
            jDialog1.setVisible(true);
            jDialog1.setSize(519, 390);
        } else if (jComboBox1.getSelectedIndex() == 1) {
            jDialog1.setLocationRelativeTo(null);
            lihatBarangAKBR();
            jDialog1.setVisible(true);
            jDialog1.setSize(519, 390);
        } else if (jComboBox1.getSelectedIndex() == 2) {
             jDialog1.setVisible(true);
            jDialog1.setSize(519, 390);
            lihatBarangALST();
        } else if (jComboBox1.getSelectedIndex() == 3) {
             jDialog1.setVisible(true);
            jDialog1.setSize(519, 390);
            lihatBarangCTKN();

        }
    }
    
    //cmbtriwulan

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLihat = new javax.swing.JButton();
        txtKd = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        cmbKategori = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtMasuk = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtTanggal = new com.toedter.calendar.JDateChooser();
        txtStok = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        cmbTriwulan1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPengguna = new javax.swing.JTable();
        cmbTriwulan = new javax.swing.JComboBox<>();
        btnNew = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        jButton6.setText("Kembali");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ATK", "AKBR", "ALST", "CTKN" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addContainerGap())
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addGap(36, 36, 36))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(27, 31, 46));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gudang/img/dishub2.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Barang Masuk");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Kode Barang :");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Nama Barang :");

        btnLihat.setText("..");
        btnLihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLihatActionPerformed(evt);
            }
        });

        txtNama.setEnabled(false);

        cmbKategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih salah satu -" }));
        cmbKategori.setEnabled(false);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Kategori :");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Harga Beli :");

        txtHarga.setEnabled(false);

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Jumlah :");

        txtMasuk.setEnabled(false);
        txtMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMasukActionPerformed(evt);
            }
        });

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Tanggal Masuk :");

        txtStok.setEnabled(false);
        txtStok.setVisible(true);

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Pencarian Data Barang Masuk");

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariKeyTyped(evt);
            }
        });

        cmbTriwulan1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Triwulan 1", "Triwulan 2", "Triwulan 3", "Triwulan 4" }));
        cmbTriwulan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTriwulan1ActionPerformed(evt);
            }
        });

        tblPengguna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Nama "
            }
        ));
        jScrollPane1.setViewportView(tblPengguna);

        cmbTriwulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Triwulan 1", "Triwulan 2", "Triwulan 3", "Triwulan 4" }));

        btnNew.setBackground(new java.awt.Color(0, 255, 0));
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnInsert.setBackground(new java.awt.Color(51, 51, 255));
        btnInsert.setForeground(new java.awt.Color(255, 255, 255));
        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(255, 204, 0));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(204, 0, 0));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setBackground(java.awt.Color.magenta);
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnEdit.setBackground(java.awt.Color.blue);
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Triwulan :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel9))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(cmbTriwulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                                    .addComponent(txtMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txtKd, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(btnLihat)))))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btnNew)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnInsert)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(605, 605, 605))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(646, 646, 646)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbTriwulan1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(145, 145, 145))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel7)))
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbTriwulan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtKd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLihat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbTriwulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNew)
                            .addComponent(btnInsert)
                            .addComponent(btnUpdate)
                            .addComponent(btnDelete)
                            .addComponent(btnReset)
                            .addComponent(btnEdit))
                        .addGap(56, 56, 56)))
                .addComponent(jLabel5)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1213, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int baris;
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jDialog1.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        String getKodeA = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String getKodeAa = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        String getKodeAaa = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();

        txtKd.setText(getKodeA);
        txtNama.setText(getKodeAa);
        cmbKategori.setSelectedItem(getKodeAaa);
        jDialog1.setVisible(false);
    }//GEN-LAST:event_jTable1MousePressed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        try {
            lihatbarangwow();
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiBarangMasukView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnLihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatActionPerformed
        jDialog1.setLocationRelativeTo(null);
        try {
            lihatbarangwow();
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiBarangMasukView.class.getName()).log(Level.SEVERE, null, ex);
        }
        jDialog1.setVisible(true);
        jDialog1.setSize(519, 390);
    }//GEN-LAST:event_btnLihatActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        // TODO add your handling code here:
        lihatBarangwow();
    }//GEN-LAST:event_txtCariKeyPressed

    private void txtCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyTyped
        // TODO add your handling code here:
        lihatBarangwow();
    }//GEN-LAST:event_txtCariKeyTyped

    private void cmbTriwulan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTriwulan1ActionPerformed
        try {
            showwow();
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiBarangMasukView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbTriwulan1ActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
      txtKd.setText("");
        txtNama.setText("");
        txtMasuk.setText("");
        txtHarga.setText("");
        cmbKategori.setSelectedItem("");

        txtKd.setEnabled(false);
        txtNama.setEnabled(true);
        txtMasuk.setEnabled(true);
        txtHarga.setEnabled(true);
        cmbKategori.setEnabled(true);
        txtTanggal.setEnabled(true);
        btnInsert.setEnabled(true);
        btnLihat.setEnabled(true);
        cmbTriwulan.setEnabled(true);

        btnEdit.setEnabled(false);

        AutoNumber();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
   try {
            bmasukwow();
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiBarangMasukView.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            simpanwow();
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiBarangMasukView.class.getName()).log(Level.SEVERE, null, ex);
        }
        showData();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
       try {
            klik();
            btnNew.setEnabled(true);
            btnInsert.setEnabled(false);
            btnDelete.setEnabled(true);
            btnEdit.setEnabled(true);

            txtNama.setEnabled(true);
            txtHarga.setEnabled(true);
            txtMasuk.setEnabled(true);
            cmbKategori.setEnabled(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang dipilih !");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
  String idWhoWantToBeDelete = tblPengguna.getValueAt(baris, 1).toString();
        try {
            Statement stmt = koneksi.createStatement();
            String query = "DELETE FROM t_bmasuk WHERE kode_barang = '" + idWhoWantToBeDelete + "';";
            int berhasil = stmt.executeUpdate(query);
            if (berhasil == 1) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                dtm.getDataVector().removeAllElements();
                showData();
            } else {
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        showData();
        disableForm();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        disableForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
edit();
    }//GEN-LAST:event_btnEditActionPerformed

    private void txtMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMasukActionPerformed
        // TODO add your handling code here:
itung();
    }//GEN-LAST:event_txtMasukActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransaksiBarangMasukView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiBarangMasukView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiBarangMasukView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiBarangMasukView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiBarangMasukView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLihat;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cmbKategori;
    private javax.swing.JComboBox<String> cmbTriwulan;
    private javax.swing.JComboBox<String> cmbTriwulan1;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblPengguna;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtKd;
    private javax.swing.JTextField txtMasuk;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtStok;
    private com.toedter.calendar.JDateChooser txtTanggal;
    // End of variables declaration//GEN-END:variables
}
