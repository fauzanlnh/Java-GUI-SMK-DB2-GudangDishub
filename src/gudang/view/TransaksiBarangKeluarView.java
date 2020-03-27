/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gudang.view;

import gudang.view.*;
import com.mysql.jdbc.Connection;
import gudang.clas.DatabaseConnection;
import gudang.clas.TransaksiMasuk;
import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Fauzan13
 */
public class TransaksiBarangKeluarView extends javax.swing.JFrame {

    ArrayList<TransaksiMasuk> listData = new ArrayList<TransaksiMasuk>();

    public ArrayList<TransaksiMasuk> getlistData() {
        return listData;
    }

    DefaultTableModel tabel = new DefaultTableModel();

    /**
     * Creates new form TransaksiBarangKeluar
     */
    Connection koneksi;

    public TransaksiBarangKeluarView() {
        initComponents();
        koneksi = (Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
        disableForm();
        setLocationRelativeTo(this);
        // String tanggal = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
    }


    public void AutoNumber() {
        try {
            Statement stmt = koneksi.createStatement();
            String query_bukaTabel = "select right(kd_transaksi ,1) as no_urut from t_transaksi";
            ResultSet line_result = stmt.executeQuery(query_bukaTabel);
            if (line_result.first() == false) {
                txtKode.setText("BN290001");
            } else {
                line_result.last();
                int no = line_result.getInt(1) + 1;
                String nomor = String.valueOf(no);
                int oto = nomor.length();
                for (int i = 0; i < 2 - oto; i++) {
                    nomor = "000" + nomor;
                }
                txtKode.setText("BN29" + nomor);
            }
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    private void disableForm() {
        btnBidang.setEnabled(false);
        btnNew.setEnabled(true);
        btnInsert.setEnabled(false);
        btnMasukan.setEnabled(false);
        btnInsert.setEnabled(false);
        btnReset.setEnabled(false);
        btnTambah.setEnabled(false);
        btnPrint.setEnabled(false);
        cmbTriwulan.setEnabled(false);
        txtkdBarang.setEnabled(false);
        txtNip.setEnabled(false);
        txtPangkat.setEnabled(false);
        
        txtKode.setEnabled(false);
        txtTanggal.setEnabled(false);
        txtNamaBarang.setEnabled(false);
        txtPelanggan.setEnabled(false);
        txtPengambil.setEnabled(false);
        txtKeluar.setEnabled(false);

        txtKode.setText("");
        txtNamaBarang.setText("");
        txtPelanggan.setText("");
        txtPengambil.setText("");
        txtKeluar.setText("");

    }

    public void proses() {
        DefaultTableModel tableModel = (DefaultTableModel) tblData.getModel();
        String[] data = new String[4];
        data[0] = txtkdBarang.getText();
        data[1] = txtNamaBarang.getText();
        data[2] = txtKeluar.getText();
        data[3] = txtStok.getText();
     
        tableModel.addRow(data);
    }
    private String simpan() {

        String tanggalLahir = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tanggalLahir);
        String tanggal = String.valueOf(fm.format(txtTanggal.getDate()));

        String kode = txtKode.getText();
        String pelanggan = txtPelanggan.getText();
        String pengambil = txtPengambil.getText();
        String nip = txtNip.getText();
        String pangkat = txtPangkat.getText();
        String Triwulan = cmbTriwulan.getSelectedItem().toString();
        int jumlah_baris = tblData.getRowCount();
        if (jumlah_baris == 0) {
            JOptionPane.showMessageDialog(rootPane, "Table Masih Kosong!");
        } else {
            try {
                int i = 0;
                while (i < jumlah_baris) {

                    Statement st = koneksi.createStatement();
                    String query = ("insert into t_transaksi (kd_transaksi , tanggal, kd_pelanggan, nm_pengambil,nip,pangkat,triwulan,kode_barang,nama_barang,keluar) values("
                            + "'" + kode + "','" + tanggal + "','" + pelanggan + "','" + pengambil + "','" + nip + "','" + pangkat + "','" + Triwulan + "',"
                            + "'" + tblData.getValueAt(i, 0) + "',"
                            + "'" + tblData.getValueAt(i, 1) + "',"
                            + "'" + tblData.getValueAt(i, 2) + "')");
                    i++;

                    System.out.println(query);
                    int berhasil = st.executeUpdate(query);
                    if (berhasil == 1) {
                    } else {
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Berhasil Menyimpan!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Gagal Menyimpan! Error : " + e);
            }
        }
        return null;
    }

    private void lihatBarang() {
        
        tabel.addColumn("Kode Barang");
        tabel.addColumn("Nama Barang");
        tabel.addColumn("Jumlah yang Tersedia");

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
            String query_bukaTabel = "select kode_barang,nama_barang, jumlah from t_persediaan";
            ResultSet rs = stmt.executeQuery(query_bukaTabel);
            while (rs.next()) {
                Object[] getO = new Object[3];
                getO[0] = rs.getString("kode_barang");
                getO[1] = rs.getString("nama_barang");
                getO[2] = rs.getString("jumlah");

                tabel.addRow(getO);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
        }
    }
    private void lihatBarang2() {

        tabel.addColumn("Kode Barang");
        tabel.addColumn("Nama Barang");
        tabel.addColumn("Jumlah yang Tersedia");

        jTable3.setModel(tabel);

        TableColumn lebar_kolom;

        jTable3.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);

        lebar_kolom = jTable3.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = jTable3.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = jTable3.getColumnModel().getColumn(2);
        lebar_kolom.setPreferredWidth(100);

        tabel.getDataVector().removeAllElements();
        tabel.fireTableDataChanged();
        try {
            Statement stmt = koneksi.createStatement();
            String query_bukaTabel = "select kode_barang,nama_barang, jumlaht2 from t_persediaan";
            ResultSet rs = stmt.executeQuery(query_bukaTabel);
            while (rs.next()) {
                Object[] getO = new Object[3];
                getO[0] = rs.getString("kode_barang");
                getO[1] = rs.getString("nama_barang");
                getO[2] = rs.getString("jumlaht2");

                tabel.addRow(getO);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
        }
    }
    private void lihatBarang3() {
         tabel.addColumn("Kode Barang");
        tabel.addColumn("Nama Barang");
        tabel.addColumn("Jumlah yang Tersedia");
        

        jTable4.setModel(tabel);

        TableColumn lebar_kolom;

        jTable4.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);

        lebar_kolom = jTable4.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = jTable4.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = jTable4.getColumnModel().getColumn(2);
        lebar_kolom.setPreferredWidth(100);

        tabel.getDataVector().removeAllElements();
        tabel.fireTableDataChanged();
        try {
            Statement stmt = koneksi.createStatement();
            String query_bukaTabel = "select kode_barang,nama_barang, jumlaht3 from t_persediaan";
            ResultSet rs = stmt.executeQuery(query_bukaTabel);
            while (rs.next()) {
                Object[] getO = new Object[3];
                getO[0] = rs.getString("kode_barang");
                getO[1] = rs.getString("nama_barang");
                getO[2] = rs.getString("jumlaht3");

                tabel.addRow(getO);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
        }
    }
   
    private void lihatBarang4() {

           tabel.addColumn("Kode Barang");
        tabel.addColumn("Nama Barang");
        tabel.addColumn("Jumlah yang Tersedia");

        jTable5.setModel(tabel);

        TableColumn lebar_kolom;

        jTable5.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);

        lebar_kolom = jTable5.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = jTable5.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = jTable5.getColumnModel().getColumn(2);
        lebar_kolom.setPreferredWidth(100);

        tabel.getDataVector().removeAllElements();
        tabel.fireTableDataChanged();
        try {
            Statement stmt = koneksi.createStatement();
            String query_bukaTabel = "select kode_barang,nama_barang, jumlaht4 from t_persediaan";
            ResultSet rs = stmt.executeQuery(query_bukaTabel);
            while (rs.next()) {
                Object[] getO = new Object[3];
                getO[0] = rs.getString("kode_barang");
                getO[1] = rs.getString("nama_barang");
                getO[2] = rs.getString("jumlaht4");

                tabel.addRow(getO);
            }
            rs.close();
            stmt.close();
        btnBidang.setEnabled(false);
        } catch (Exception e) {
        }
    }

    private void lihatPelanggan() {

        tabel.addColumn("Kode Pelanggan");
        tabel.addColumn("Nama Pelanggan");

        jTable2.setModel(tabel);

        TableColumn lebar_kolom;

        jTable2.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);

        lebar_kolom = jTable2.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = jTable2.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(200);

        tabel.getDataVector().removeAllElements();
        tabel.fireTableDataChanged();
        try {
            Statement stmt = koneksi.createStatement();
            String query_bukaTabel = "select kd_pelanggan, nama_pelanggan from t_pelanggan";
            ResultSet rs = stmt.executeQuery(query_bukaTabel);
            while (rs.next()) {
                Object[] getO = new Object[2];
                getO[0] = rs.getString("kd_pelanggan");
                getO[1] = rs.getString("nama_pelanggan");

                tabel.addRow(getO);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
        }
    }

    private void lihatBarangwow() {
        if (cmbTriwulan.getSelectedIndex() == 0) {
            jDialog1.setLocationRelativeTo(null);
            lihatBarang();
            jDialog1.setVisible(true);
            txtKeluar.setEditable(true);
            jDialog1.setSize(519, 390);
        } else if (cmbTriwulan.getSelectedIndex() == 1) {
            jDialog3.setLocationRelativeTo(null);
            lihatBarang2();
            jDialog3.setVisible(true);
            txtKeluar.setEditable(true);
            jDialog3.setSize(519, 390);
        } else if (cmbTriwulan.getSelectedIndex() == 2) {
            jDialog4.setLocationRelativeTo(null);
            lihatBarang3();
            jDialog4.setVisible(true);
            txtKeluar.setEditable(true);
            jDialog4.setSize(519, 390);
        } else if (cmbTriwulan.getSelectedIndex() == 3) {
            jDialog5.setLocationRelativeTo(null);
            lihatBarang4();
            jDialog5.setVisible(true);
            txtKeluar.setEditable(true);
            jDialog5.setSize(519, 390);
        }
    }

    public void edit1() throws SQLException {

        int jumlah_baris = tblData.getRowCount();
        if (jumlah_baris == 0) {
            JOptionPane.showMessageDialog(rootPane, "Table Masih Kosong!");
        } else {
            try {
                int i = 0;
                while (i < jumlah_baris) {

                    String nama = tblData.getValueAt(i, 0).toString();
                    String tersisa1 = tblData.getValueAt(i, 3).toString();

                    Statement stmt = koneksi.createStatement();
                    String query = "UPDATE t_persediaan SET jumlah = '" + tersisa1 + "'," + "jumlaht2 = '" + tersisa1 + "'," + "jumlaht3 = '" + tersisa1 + "'," + "jumlaht4 = '" + tersisa1 + "'"
                            + "WHERE kode_barang = '" + nama + "'";

                    i++;
                    System.out.println(query);
                    int berhasil = stmt.executeUpdate(query);
                    if (berhasil == 1) {
                    } else {
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Berhasil Menyimpan!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Gagal Menyimpan! Error : " + e);
            }
        }

    }

    public void edit2() throws SQLException {

        int jumlah_baris = tblData.getRowCount();
        if (jumlah_baris == 0) {
            JOptionPane.showMessageDialog(rootPane, "Table Masih Kosong!");
        } else {
            try {
                int i = 0;
                while (i < jumlah_baris) {

                    String nama = tblData.getValueAt(i, 0).toString();
                    String tersisa1 = tblData.getValueAt(i, 3).toString();

                    Statement stmt = koneksi.createStatement();
                    String query = "UPDATE t_persediaan SET jumlaht2 = '" + tersisa1 + "'," + "jumlaht3 = '" + tersisa1 + "'," + "jumlaht4 = '" + tersisa1 + "'"
                            + "WHERE kode_barang = '" + nama + "'";

                    i++;
                    System.out.println(query);
                    int berhasil = stmt.executeUpdate(query);
                    if (berhasil == 1) {
                    } else {
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Berhasil Menyimpan!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Gagal Menyimpan! Error : " + e);
            }
        }

    }

    public void edit3() throws SQLException {

        int jumlah_baris = tblData.getRowCount();
        if (jumlah_baris == 0) {
            JOptionPane.showMessageDialog(rootPane, "Table Masih Kosong!");
        } else {
            try {
                int i = 0;
                while (i < jumlah_baris) {

                    String nama = tblData.getValueAt(i, 0).toString();
                    
                    String tersisa1 = tblData.getValueAt(i, 3).toString();

                    Statement stmt = koneksi.createStatement();
                    String query = "UPDATE t_persediaan SET jumlaht3 = '" + tersisa1 + "'," + "jumlaht4 = '" + tersisa1 + "'"
                            + "WHERE kode_barang = '" + nama + "'";

                    i++;
                    System.out.println(query);
                    int berhasil = stmt.executeUpdate(query);
                    if (berhasil == 1) {
                    } else {
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Berhasil Menyimpan!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Gagal Menyimpan! Error : " + e);
            }
        }

    }

    public void edit4() throws SQLException {

        int jumlah_baris = tblData.getRowCount();
        if (jumlah_baris == 0) {
            JOptionPane.showMessageDialog(rootPane, "Table Masih Kosong!");
        } else {
            try {
                int i = 0;
                while (i < jumlah_baris) {

                    String nama = tblData.getValueAt(i, 0).toString();
                    String tersisa1 = tblData.getValueAt(i, 2).toString();

                    Statement stmt = koneksi.createStatement();
                    String query = "UPDATE t_persediaan SET jumlaht4 = '" + tersisa1 + "'"
                            + "WHERE kode_barang = '" + nama + "'";

                    i++;
                    System.out.println(query);
                    int berhasil = stmt.executeUpdate(query);
                    if (berhasil == 1) {
                    } else {
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Berhasil Menyimpan!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Gagal Menyimpan! Error : " + e);
            }
        }

    }

    private void editwow() throws SQLException {
        if (cmbTriwulan.getSelectedIndex() == 0) {
            edit1();
        } else if (cmbTriwulan.getSelectedIndex() == 1) {
            edit2();
        } else if (cmbTriwulan.getSelectedIndex() == 2) {
            edit3();
        } else if (cmbTriwulan.getSelectedIndex() == 3) {
            edit4();

        }
    }
    
     private void itung() {
        String a = txtStok.getText();
        String m = txtKeluar.getText();

        if (txtKeluar.getText() == null) {
            txtStok.setText(a);
        } else {
            int b = Integer.parseInt(a);
            int h = Integer.parseInt(m);
            int jumlah = b - h;
            String s = "" + jumlah;

            txtStok.setText(s);
        }
    }

// String tanggal = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jButton7 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jDialog3 = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jDialog4 = new javax.swing.JDialog();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jDialog5 = new javax.swing.JDialog();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtPengambil = new javax.swing.JTextField();
        txtPelanggan = new javax.swing.JTextField();
        txtTanggal = new com.toedter.calendar.JDateChooser();
        txtKode = new javax.swing.JTextField();
        cmbTriwulan = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtkdBarang = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        txtNamaBarang = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtKeluar = new javax.swing.JTextField();
        btnMasukan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNip = new javax.swing.JTextField();
        txtPangkat = new javax.swing.JTextField();
        btnBidang = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        txtStok = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

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

        jButton6.setText("Kembali");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(0, 55, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton7.setText("Kembali");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable3MousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        jButton8.setText("Kembali");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable4MousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(jTable4);

        jButton9.setText("Kembali");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable5MousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(jTable5);

        jButton10.setText("Kembali");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog5Layout = new javax.swing.GroupLayout(jDialog5.getContentPane());
        jDialog5.getContentPane().setLayout(jDialog5Layout);
        jDialog5Layout.setHorizontalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDialog5Layout.setVerticalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jDialog5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane7.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(27, 31, 46));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gudang/img/dishub2.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Transaksi Pengambilan Barang");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("No. Pengambilan :");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tanggal :");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Bidang :");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nama :");

        txtPelanggan.setEnabled(false);

        txtKode.setEnabled(false);

        cmbTriwulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Triwulan 1", "Triwulan 2", "Triwulan 3", "Triwulan 4" }));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nama");

        btnTambah.setText("Tambah Barang");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Keluar");

        txtKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeluarActionPerformed(evt);
            }
        });
        txtKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKeluarKeyReleased(evt);
            }
        });

        btnMasukan.setText("Masukan");
        btnMasukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasukanActionPerformed(evt);
            }
        });

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Barang", "Keluar", "Stok"
            }
        ));
        jScrollPane1.setViewportView(tblData);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NIP :");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Pangkat / Gol :");

        btnBidang.setText("...");
        btnBidang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBidangActionPerformed(evt);
            }
        });

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

        btnPrint.setBackground(new java.awt.Color(255, 204, 0));
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
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

        txtStok.setEnabled(false);

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Stok");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(335, 335, 335))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(63, 63, 63)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(cmbTriwulan, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(40, 40, 40)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPengambil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(40, 40, 40)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPangkat, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNip, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(txtkdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(btnTambah))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnMasukan)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBidang))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(btnNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTriwulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBidang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtPengambil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtNip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtPangkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(txtkdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMasukan)
                    .addComponent(jLabel11)
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnInsert)
                    .addComponent(btnPrint)
                    .addComponent(btnReset))
                .addGap(320, 320, 320))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        String getKodeA = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String getKodeAa = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        String getKodeAaa = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();

        txtkdBarang.setText(getKodeA);
        txtNamaBarang.setText(getKodeAa);
        txtStok.setText(getKodeAaa);
        jDialog1.setVisible(false);
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jDialog1.setVisible(false);
        txtKeluar.setEditable(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        // TODO add your handling code here:
        String getKodeB = jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString();

        txtPelanggan.setText(getKodeB);
        jDialog2.setVisible(false);

    }//GEN-LAST:event_jTable2MousePressed

    private void jTable3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MousePressed
       String getKodeA = jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString();
        String getKodeAa = jTable3.getValueAt(jTable3.getSelectedRow(), 1).toString();
        String getKodeAaa = jTable3.getValueAt(jTable3.getSelectedRow(), 2).toString();

        txtkdBarang.setText(getKodeA);
        txtNamaBarang.setText(getKodeAa);
        txtStok.setText(getKodeAaa);
        jDialog3.setVisible(false);

    }//GEN-LAST:event_jTable3MousePressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MousePressed
                String getKodeA = jTable4.getValueAt(jTable4.getSelectedRow(), 0).toString();
        String getKodeAa = jTable4.getValueAt(jTable4.getSelectedRow(), 1).toString();
        String getKodeAaa = jTable3.getValueAt(jTable3.getSelectedRow(), 2).toString();

        txtkdBarang.setText(getKodeA);
        txtNamaBarang.setText(getKodeAa);
        txtStok.setText(getKodeAaa);
        jDialog4.setVisible(false);
    }//GEN-LAST:event_jTable4MousePressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTable5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MousePressed
            String getKodeA = jTable5.getValueAt(jTable5.getSelectedRow(), 0).toString();
        String getKodeAa = jTable5.getValueAt(jTable5.getSelectedRow(), 1).toString();
        String getKodeAaa = jTable5.getValueAt(jTable5.getSelectedRow(), 2).toString();

        txtkdBarang.setText(getKodeA);
        txtNamaBarang.setText(getKodeAa);
        txtStok.setText(getKodeAaa);

        jDialog5.setVisible(false);

    }//GEN-LAST:event_jTable5MousePressed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed

        //jDialog1.setLocationRelativeTo(null);
        // lihatBarang();
        // jDialog1.setVisible(true);
        //txtKeluar.setEditable(true);
        //jDialog1.setSize(519,390);bet
        // lihatBarangwow();
        if (cmbTriwulan.getSelectedIndex() == 0) {
            jDialog1.setLocationRelativeTo(null);
            lihatBarang();
            jDialog1.setVisible(true);
            txtKeluar.setEditable(true);
            jDialog1.setSize(519, 390);
        } else if (cmbTriwulan.getSelectedIndex() == 1) {
            jDialog3.setLocationRelativeTo(null);
            lihatBarang2();
            jDialog3.setVisible(true);
            txtKeluar.setEditable(true);
            jDialog3.setSize(519, 390);
        } else if (cmbTriwulan.getSelectedIndex() == 2) {
            jDialog4.setLocationRelativeTo(null);
            lihatBarang3();
            jDialog4.setVisible(true);
            txtKeluar.setEditable(true);
            jDialog4.setSize(519, 390);
        } else if (cmbTriwulan.getSelectedIndex() == 3) {
            jDialog5.setLocationRelativeTo(null);
            lihatBarang4();
            jDialog5.setVisible(true);
            txtKeluar.setEditable(true);
            jDialog5.setSize(519, 390);
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void txtKeluarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeluarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeluarKeyReleased

    private void btnMasukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasukanActionPerformed
        // TODO add your handling code here:
        proses();
        btnPrint.setEnabled(true);
    }//GEN-LAST:event_btnMasukanActionPerformed

    private void btnBidangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBidangActionPerformed
        // TODO add your handling code here:
lihatPelanggan();
jDialog2.setLocationRelativeTo(null);
            jDialog2.setVisible(true);
            jDialog2.setSize(519, 390);
            
    }//GEN-LAST:event_btnBidangActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
       btnNew.setEnabled(false);
        btnInsert.setEnabled(true);
        btnMasukan.setEnabled(true);
        btnInsert.setEnabled(true);
        btnReset.setEnabled(true);
        btnTambah.setEnabled(true);
        cmbTriwulan.setEnabled(true);

        btnBidang.setEnabled(true);
        txtNip.setEnabled(true);
        txtPangkat.setEnabled(true);
        txtKode.setEnabled(false);
        txtTanggal.setEnabled(true);
        txtNamaBarang.setEditable(false);
        txtPelanggan.setEnabled(true);
        txtPengambil.setEnabled(true);
        txtKeluar.setEnabled(true);

        txtKode.setText("");
        txtNamaBarang.setText("");
        txtPelanggan.setText("");
        txtPengambil.setText("");
        txtKeluar.setText("");

        AutoNumber();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
      try {
            editwow();
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiBarangKeluarView.class.getName()).log(Level.SEVERE, null, ex);
        }
        simpan();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
           String reportSource = null;
        String reportDest = null;

        try {
            HashMap parameter = new HashMap();
            parameter.put("kd_transaksi",txtKode.getText());

            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
            reportSource = System.getProperty("user.dir") + "/report/Struk.jrxml";
            reportDest = System.getProperty("user.dir") + "/report/Struk.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        disableForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeluarActionPerformed
        // TODO add your handling code here:
itung();
    }//GEN-LAST:event_txtKeluarActionPerformed

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
            java.util.logging.Logger.getLogger(TransaksiBarangKeluarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiBarangKeluarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiBarangKeluarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiBarangKeluarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiBarangKeluarView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBidang;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnMasukan;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cmbTriwulan;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JDialog jDialog5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTree jTree1;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtKeluar;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtNip;
    private javax.swing.JTextField txtPangkat;
    private javax.swing.JTextField txtPelanggan;
    private javax.swing.JTextField txtPengambil;
    private javax.swing.JTextField txtStok;
    private com.toedter.calendar.JDateChooser txtTanggal;
    private javax.swing.JTextField txtkdBarang;
    // End of variables declaration//GEN-END:variables

    private void setSize(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
