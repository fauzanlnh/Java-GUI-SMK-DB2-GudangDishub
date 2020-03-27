/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LaporanMutasi2017;

import com.mysql.jdbc.Connection;
import gudang.clas.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fauzan13
 */
public class Triwulan4 extends javax.swing.JFrame {

    /**
     * Creates new form Triwulan4
     */
    Connection koneksi;

    public Triwulan4() {
        initComponents();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        koneksi = (Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
        showDataATK();
    }

    DefaultTableModel dtm;

    public void showDataATK() {

        String[] kolom = {"NO", "NAMA BARANG", "VOLUME", "HARGA", "JUMLAH", "PENERIMAAN", "PENGELUARAN", "SISA", "HARGA", "JUMLAH"};

        dtm = new DefaultTableModel(null, kolom);

        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT t_persediaan.nama_barang,t_persediaan.jumlaht3,t_bmasuk.harga,(t_bmasuk.harga*t_bmasuk.jumlah_masuk) as total,"
                    + "t_bmasuk.jumlah_masuk,SUM(t_transaksi.keluar) as pengeluaran"
                    + "   FROM t_bmasuk,t_persediaan,t_transaksi "
                    + " WHERE t_transaksi.kode_barang=t_bmasuk.kode_barang and t_transaksi.kode_barang = t_persediaan.kode_barang "
                    + "and  t_transaksi.tanggal BETWEEN '2017-10-1' AND '2017-12-31'"
                    + "GROUP BY t_persediaan.kode_barang ";
//,sum(t_transaksi.keluar) as pengeluaran   
            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {

                String nama = rs.getString("nama_barang");
                String kd = rs.getString("jumlaht3");
                String kd1 = rs.getString("harga");
                String keluar = rs.getString("total")                       ;
                String keluar1 = rs.getString("pengeluaran");
                String pemasukan = rs.getString("jumlah_masuk");

                dtm.addRow(new String[]{no + "", nama, kd, kd1, keluar,pemasukan,keluar1});
                no++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tblATK.setModel(dtm);
    }

    DefaultTableModel dtm1;

    public void showDataListrik() {

        String[] kolom = {"NO", "Nama Bidang", "Nama Barang", "Total Yang Diambil"};

        dtm1 = new DefaultTableModel(null, kolom);

        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT t_pelanggan.kd_pelanggan,t_transaksi.nama_barang,SUM(t_transaksi.keluar) as total FROM t_transaksi,t_pelanggan "
                    + "where t_pelanggan.kd_pelanggan = t_transaksi.kd_pelanggan and tanggal BETWEEN '2017-10-1'    AND '2017-12-31'  "
                    + "GROUP BY kd_pelanggan, nama_barang";

            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {

                String nama = rs.getString("nama_barang");
                String kd = rs.getString("kd_pelanggan");
                String keluar = rs.getString("total");

                dtm1.addRow(new String[]{no + "", kd, nama, keluar});
                no++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tblListrik.setModel(dtm1);
    }

    DefaultTableModel dtm12;

    public void showDataKebersihan() {

        String[] kolom = {"NO", "Nama Bidang", "Nama Barang", "Total Yang Diambil"};

        dtm12 = new DefaultTableModel(null, kolom);

        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT t_pelanggan.kd_pelanggan,t_transaksi.nama_barang,SUM(t_transaksi.keluar) as total FROM t_transaksi,t_pelanggan "
                    + "where t_pelanggan.kd_pelanggan = t_transaksi.kd_pelanggan and tanggal BETWEEN '2017-10-1'    AND '2017-12-31'  "
                    + "GROUP BY kd_pelanggan, nama_barang";

            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {

                String nama = rs.getString("nama_barang");
                String kd = rs.getString("kd_pelanggan");
                String keluar = rs.getString("total");

                dtm12.addRow(new String[]{no + "", kd, nama, keluar});
                no++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tblKebersihan.setModel(dtm12);
    }

    DefaultTableModel dtm13;

    public void showDataCetakan() {

        String[] kolom = {"NO", "Nama Bidang", "Nama Barang", "Total Yang Diambil"};

        dtm13 = new DefaultTableModel(null, kolom);

        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT t_pelanggan.kd_pelanggan,t_transaksi.nama_barang,SUM(t_transaksi.keluar) as total FROM t_transaksi,t_pelanggan "
                    + "where t_pelanggan.kd_pelanggan = t_transaksi.kd_pelanggan and tanggal BETWEEN '2017-10-1'    AND '2017-12-31'  "
                    + "GROUP BY kd_pelanggan, nama_barang";

            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()) {

                String nama = rs.getString("nama_barang");
                String kd = rs.getString("kd_pelanggan");
                String keluar = rs.getString("total");

                dtm13.addRow(new String[]{no + "", kd, nama, keluar});
                no++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tblCetakan.setModel(dtm13);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblListrik = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblATK = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCetakan = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKebersihan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblListrik.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "NAMA BARANG", "VOLUME", "HARGA", "JUMLAH", "PENERIMAAN", "PENGELUARAN", "SISA", "HARGA", "JUMLAH"
            }
        ));
        jScrollPane1.setViewportView(tblListrik);

        jLabel3.setText("TRIWULAN IV");

        jLabel1.setText("LAPORAN MUTASI PERSEDIAAN");

        jLabel2.setText("DINAS PERHUBUNGAN KOTA BANDUNG");

        jLabel4.setText("ALAT TULIS KANTOR");

        tblATK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "NAMA BARANG", "VOLUME", "HARGA", "JUMLAH", "PENERIMAAN", "PENGELUARAN", "SISA", "HARGA", "JUMLAH"
            }
        ));
        jScrollPane2.setViewportView(tblATK);

        jLabel5.setText("ALAT LISTRIK");

        jLabel6.setText("ALAT KEBERSIHAN");

        tblCetakan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "NAMA BARANG", "VOLUME", "HARGA", "JUMLAH", "PENERIMAAN", "PENGELUARAN", "SISA", "HARGA", "JUMLAH"
            }
        ));
        jScrollPane3.setViewportView(tblCetakan);

        jLabel7.setText("CETAKAN");

        tblKebersihan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "NAMA BARANG", "VOLUME", "HARGA", "JUMLAH", "PENERIMAAN", "PENGELUARAN", "SISA", "HARGA", "JUMLAH"
            }
        ));
        jScrollPane4.setViewportView(tblKebersihan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(279, 279, 279)
                .addComponent(jLabel2))
            .addGroup(layout.createSequentialGroup()
                .addGap(308, 308, 308)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel3))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Triwulan4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Triwulan4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Triwulan4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Triwulan4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Triwulan4().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblATK;
    private javax.swing.JTable tblCetakan;
    private javax.swing.JTable tblKebersihan;
    private javax.swing.JTable tblListrik;
    // End of variables declaration//GEN-END:variables
}
