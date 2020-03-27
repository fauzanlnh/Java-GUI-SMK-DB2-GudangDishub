/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gudang.view;

import LaporanDistribusi2017.KEUT1;
import LaporanDistribusi2017.KEUT2;
import LaporanDistribusi2017.KEUT3;
import LaporanDistribusi2017.KEUT4;
import LaporanDistribusi2017.MTPT1;
import LaporanDistribusi2017.MTPT2;
import LaporanDistribusi2017.MTPT3;
import LaporanDistribusi2017.MTPT4;
import LaporanDistribusi2017.PDKTT1;
import LaporanDistribusi2017.PDKTT2;
import LaporanDistribusi2017.PDKTT3;
import LaporanDistribusi2017.PDKTT4;
import LaporanDistribusi2017.PPTT1;
import LaporanDistribusi2017.PPTT2;
import LaporanDistribusi2017.PPTT3;
import LaporanDistribusi2017.PPTT4;
import LaporanDistribusi2017.PROGT1;
import LaporanDistribusi2017.PROGT2;
import LaporanDistribusi2017.PROGT3;
import LaporanDistribusi2017.PROGT4;
import LaporanDistribusi2017.SEKRET1;
import LaporanDistribusi2017.SEKRET2;
import LaporanDistribusi2017.SEKRET3;
import LaporanDistribusi2017.SEKRET4;
import LaporanDistribusi2017.SOCCHMT1;
import LaporanDistribusi2017.SOCCHMT2;
import LaporanDistribusi2017.SOCCHMT3;
import LaporanDistribusi2017.SOCCHMT4;
import LaporanDistribusi2017.SOLPT1;
import LaporanDistribusi2017.SOLPT2;
import LaporanDistribusi2017.SOLPT3;
import LaporanDistribusi2017.SOLPT4;
import LaporanDistribusi2017.SPTT1;
import LaporanDistribusi2017.SPTT2;
import LaporanDistribusi2017.SPTT3;
import LaporanDistribusi2017.SPTT4;
import LaporanDistribusi2017.ST1;
import LaporanDistribusi2017.ST2;
import LaporanDistribusi2017.ST3;
import LaporanDistribusi2017.ST4;
import LaporanDistribusi2017.TMBT3;
import LaporanDistribusi2017.TMBT4;
import LaporanDistribusi2017.UPTPARKIRT1;
import LaporanDistribusi2017.UPTPARKIRT2;
import LaporanDistribusi2017.UPTPARKIRT3;
import LaporanDistribusi2017.UPTPARKIRT4;
import LaporanDistribusi2017.UPTTERMT1;
import LaporanDistribusi2017.UPTTERMT2;
import LaporanDistribusi2017.UPTTERMT3;
import LaporanDistribusi2017.UPTTERMT4;
import LaporanMutasi2017.Triwulan4;
import com.mysql.jdbc.Connection;
import gudang.clas.DatabaseConnection;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
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
public class Menu extends javax.swing.JFrame {
String hakakses;

    /**
     * Creates new form Menu
     */
    Connection koneksi;
    public Menu() {
        initComponents();
    mnGanti.setEnabled(false);
        mnTambah.setEnabled(false);
        mnLogout.setEnabled(false);
        setMenuLogout();
           koneksi = (Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
        jDialog1.setPreferredSize(new Dimension(481, 332));
        jDialog1.setMinimumSize(new Dimension(481, 332));
        jDialog1.setMaximumSize(new Dimension(481, 332));
            jDialog1.setLocationRelativeTo(this);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }


        private void cekUser(){
        try {
                   Statement stmt = koneksi.createStatement();
        String query = "SELECT bagian from t_pengguna WHERE username like  '"+txtUsername.getText()+"' and password like  '"+txtPassword.getText()+"'";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next())
        {
            hakakses = rs.getString(1);
        }
        if(hakakses.equals(""))
        {
            JOptionPane.showMessageDialog(null,"Username Atau Password Salah");
        }
        else{
            setMenuLogin();
    }}
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Data Tidak Ditambahkan Ke Database","Informasi",JOptionPane.INFORMATION_MESSAGE);
            System.out.println(e);
           
        }
    }
 
    private void setMenuLogin(){
        if(hakakses.equals("Admin")){
             mnBarang.setEnabled(true);
        mnKategori.setEnabled(true);
        mnIsiGudang.setEnabled(true);
        mnPemasok.setEnabled(true);
        mnPelanggan.setEnabled(true);
        mnBarangMasuk.setEnabled(true);
        mnBarangKeluar.setEnabled(true);
            
        mnLogin.setEnabled(false);
        mnLogout.setEnabled(true);
        mnGanti.setEnabled(true);
        mnTambah.setEnabled(true);
         JOptionPane.showMessageDialog(null,"Anda Masuk Sebagai Admin");
        }
        else if(hakakses.equals("User")){
       mnBarang.setEnabled(true);
        mnKategori.setEnabled(true);
        mnIsiGudang.setEnabled(true);
        mnPemasok.setEnabled(true);
        mnPelanggan.setEnabled(true);
        mnBarangMasuk.setEnabled(true);
        mnBarangKeluar.setEnabled(true);
            
        mnLogin.setEnabled(false);
        mnLogout.setEnabled(true);
        mnGanti.setEnabled(false);
        mnTambah.setEnabled(false);
         JOptionPane.showMessageDialog(null,"Anda Masuk Sebagai User");
        }
        else if(hakakses.equals("")){
                     JOptionPane.showMessageDialog(null,"Anda Masuk Sebagai User");

        }
    }
    private void setMenuLogout(){
        mnBarang.setEnabled(false);
        mnKategori.setEnabled(false);
        mnIsiGudang.setEnabled(false);
        mnPemasok.setEnabled(false);
        mnPelanggan.setEnabled(false);
        mnBarangMasuk.setEnabled(false);
        mnBarangKeluar.setEnabled(false);
        mnLogin.setEnabled(true);
        mnLogout.setEnabled(false);
        mnGanti.setEnabled(false);
        mnTambah.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu28 = new javax.swing.JMenu();
        jMenu29 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        PersediaanBarang1 = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTahun1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        PersediaanBarang2 = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtTahun2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        PersediaanBarang3 = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTahun3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        PersediaanBarang4 = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTahun4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        BarangMasukT1 = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTahun5 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        BarangMasukT2 = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtTahun6 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        BarangMasukT3 = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtTahun7 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        BarangMasukT4 = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtTahun8 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        BarangKeluarT1 = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtTahun9 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        BarangKeluarT2 = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtTahun10 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        BarangKeluarT3 = new javax.swing.JDialog();
        jPanel14 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtTahun11 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        BarangKeluarT4 = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtTahun12 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        bLogin2 = new javax.swing.JButton();
        bBatal3 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu11 = new javax.swing.JMenu();
        mnBarang = new javax.swing.JMenuItem();
        mnKategori = new javax.swing.JMenuItem();
        mnIsiGudang = new javax.swing.JMenuItem();
        mnPemasok = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnPelanggan = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnBarangMasuk = new javax.swing.JMenuItem();
        mnBarangKeluar = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        mnTransaksiBmasuk = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        mnTransaksiBkeluar = new javax.swing.JMenuItem();
        mnTransaksiBkeluar1 = new javax.swing.JMenuItem();
        mnTransaksiBkeluar2 = new javax.swing.JMenuItem();
        mnTransaksiBkeluar3 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        PBT1 = new javax.swing.JMenuItem();
        PBT2 = new javax.swing.JMenuItem();
        PBT3 = new javax.swing.JMenuItem();
        PBT4 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mnLogin = new javax.swing.JMenuItem();
        mnLogout = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnGanti = new javax.swing.JMenuItem();
        mnTambah = new javax.swing.JMenuItem();

        jMenuItem10.setText("jMenuItem10");

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jMenuItem12.setText("jMenuItem12");

        jMenuItem17.setText("jMenuItem17");

        jMenuItem5.setText("jMenuItem5");

        jMenu28.setText("File");
        jMenuBar3.add(jMenu28);

        jMenu29.setText("Edit");
        jMenuBar3.add(jMenu29);

        jMenuItem9.setText("jMenuItem9");

        jMenuItem13.setText("jMenuItem13");

        jPanel4.setBackground(new java.awt.Color(27, 31, 46));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Masukan Tahun :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(606, 606, 606))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtTahun1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTahun1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout PersediaanBarang1Layout = new javax.swing.GroupLayout(PersediaanBarang1.getContentPane());
        PersediaanBarang1.getContentPane().setLayout(PersediaanBarang1Layout);
        PersediaanBarang1Layout.setHorizontalGroup(
            PersediaanBarang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PersediaanBarang1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PersediaanBarang1Layout.setVerticalGroup(
            PersediaanBarang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(27, 31, 46));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Masukan Tahun :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(606, 606, 606))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtTahun2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTahun2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout PersediaanBarang2Layout = new javax.swing.GroupLayout(PersediaanBarang2.getContentPane());
        PersediaanBarang2.getContentPane().setLayout(PersediaanBarang2Layout);
        PersediaanBarang2Layout.setHorizontalGroup(
            PersediaanBarang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PersediaanBarang2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PersediaanBarang2Layout.setVerticalGroup(
            PersediaanBarang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PersediaanBarang2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(27, 31, 46));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));

        jButton3.setText("Print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Masukan Tahun :");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(606, 606, 606))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtTahun3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTahun3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout PersediaanBarang3Layout = new javax.swing.GroupLayout(PersediaanBarang3.getContentPane());
        PersediaanBarang3.getContentPane().setLayout(PersediaanBarang3Layout);
        PersediaanBarang3Layout.setHorizontalGroup(
            PersediaanBarang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PersediaanBarang3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PersediaanBarang3Layout.setVerticalGroup(
            PersediaanBarang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(27, 31, 46));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));

        jButton4.setText("Print");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Masukan Tahun :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(606, 606, 606))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtTahun4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTahun4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout PersediaanBarang4Layout = new javax.swing.GroupLayout(PersediaanBarang4.getContentPane());
        PersediaanBarang4.getContentPane().setLayout(PersediaanBarang4Layout);
        PersediaanBarang4Layout.setHorizontalGroup(
            PersediaanBarang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PersediaanBarang4Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PersediaanBarang4Layout.setVerticalGroup(
            PersediaanBarang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(27, 31, 46));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));

        jButton5.setText("Print");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Masukan Tahun :");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(606, 606, 606))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtTahun5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTahun5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout BarangMasukT1Layout = new javax.swing.GroupLayout(BarangMasukT1.getContentPane());
        BarangMasukT1.getContentPane().setLayout(BarangMasukT1Layout);
        BarangMasukT1Layout.setHorizontalGroup(
            BarangMasukT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarangMasukT1Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        BarangMasukT1Layout.setVerticalGroup(
            BarangMasukT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(27, 31, 46));
        jPanel9.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));

        jButton6.setText("Print");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Masukan Tahun :");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(606, 606, 606))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtTahun6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTahun6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout BarangMasukT2Layout = new javax.swing.GroupLayout(BarangMasukT2.getContentPane());
        BarangMasukT2.getContentPane().setLayout(BarangMasukT2Layout);
        BarangMasukT2Layout.setHorizontalGroup(
            BarangMasukT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarangMasukT2Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        BarangMasukT2Layout.setVerticalGroup(
            BarangMasukT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(27, 31, 46));
        jPanel10.setForeground(new java.awt.Color(255, 255, 255));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));

        jButton7.setText("Print");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Masukan Tahun :");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(606, 606, 606))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtTahun7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTahun7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout BarangMasukT3Layout = new javax.swing.GroupLayout(BarangMasukT3.getContentPane());
        BarangMasukT3.getContentPane().setLayout(BarangMasukT3Layout);
        BarangMasukT3Layout.setHorizontalGroup(
            BarangMasukT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarangMasukT3Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        BarangMasukT3Layout.setVerticalGroup(
            BarangMasukT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(27, 31, 46));
        jPanel11.setForeground(new java.awt.Color(255, 255, 255));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));

        jButton8.setText("Print");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Masukan Tahun :");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(606, 606, 606))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txtTahun8, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTahun8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout BarangMasukT4Layout = new javax.swing.GroupLayout(BarangMasukT4.getContentPane());
        BarangMasukT4.getContentPane().setLayout(BarangMasukT4Layout);
        BarangMasukT4Layout.setHorizontalGroup(
            BarangMasukT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarangMasukT4Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        BarangMasukT4Layout.setVerticalGroup(
            BarangMasukT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(27, 31, 46));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));

        jButton9.setText("Print");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Masukan Tahun :");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(606, 606, 606))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txtTahun9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTahun9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout BarangKeluarT1Layout = new javax.swing.GroupLayout(BarangKeluarT1.getContentPane());
        BarangKeluarT1.getContentPane().setLayout(BarangKeluarT1Layout);
        BarangKeluarT1Layout.setHorizontalGroup(
            BarangKeluarT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarangKeluarT1Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        BarangKeluarT1Layout.setVerticalGroup(
            BarangKeluarT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(27, 31, 46));
        jPanel13.setForeground(new java.awt.Color(255, 255, 255));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));

        jButton10.setText("Print");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Masukan Tahun :");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(606, 606, 606))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(txtTahun10, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTahun10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout BarangKeluarT2Layout = new javax.swing.GroupLayout(BarangKeluarT2.getContentPane());
        BarangKeluarT2.getContentPane().setLayout(BarangKeluarT2Layout);
        BarangKeluarT2Layout.setHorizontalGroup(
            BarangKeluarT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarangKeluarT2Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        BarangKeluarT2Layout.setVerticalGroup(
            BarangKeluarT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(27, 31, 46));
        jPanel14.setForeground(new java.awt.Color(255, 255, 255));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));

        jButton11.setText("Print");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Masukan Tahun :");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(606, 606, 606))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txtTahun11, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTahun11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout BarangKeluarT3Layout = new javax.swing.GroupLayout(BarangKeluarT3.getContentPane());
        BarangKeluarT3.getContentPane().setLayout(BarangKeluarT3Layout);
        BarangKeluarT3Layout.setHorizontalGroup(
            BarangKeluarT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarangKeluarT3Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        BarangKeluarT3Layout.setVerticalGroup(
            BarangKeluarT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(27, 31, 46));
        jPanel15.setForeground(new java.awt.Color(255, 255, 255));

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));

        jButton12.setText("Print");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Masukan Tahun :");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(606, 606, 606))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(txtTahun12, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton12)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTahun12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout BarangKeluarT4Layout = new javax.swing.GroupLayout(BarangKeluarT4.getContentPane());
        BarangKeluarT4.getContentPane().setLayout(BarangKeluarT4Layout);
        BarangKeluarT4Layout.setHorizontalGroup(
            BarangKeluarT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarangKeluarT4Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        BarangKeluarT4Layout.setVerticalGroup(
            BarangKeluarT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(27, 31, 46));

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Username :");

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Password :");

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        bLogin2.setBackground(java.awt.Color.cyan);
        bLogin2.setForeground(new java.awt.Color(255, 255, 255));
        bLogin2.setText("Login");
        bLogin2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLogin2ActionPerformed(evt);
            }
        });

        bBatal3.setBackground(new java.awt.Color(255, 0, 0));
        bBatal3.setForeground(new java.awt.Color(255, 255, 255));
        bBatal3.setText("Cancel");
        bBatal3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBatal3ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("LOGIN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bBatal3)
                        .addGap(18, 18, 18)
                        .addComponent(bLogin2))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bLogin2)
                    .addComponent(bBatal3))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setBackground(new java.awt.Color(255, 153, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gudang/img/DISHUB (3).png"))); // NOI18N

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));
        jMenuBar1.setBorder(null);

        jMenu11.setText("Master");

        mnBarang.setText("Barang");
        mnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBarangActionPerformed(evt);
            }
        });
        jMenu11.add(mnBarang);

        mnKategori.setText("Kategori");
        mnKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnKategoriActionPerformed(evt);
            }
        });
        jMenu11.add(mnKategori);

        mnIsiGudang.setText("Lihat Isi Gudang");
        mnIsiGudang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnIsiGudangActionPerformed(evt);
            }
        });
        jMenu11.add(mnIsiGudang);

        mnPemasok.setText("Pemasok");
        mnPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPemasokActionPerformed(evt);
            }
        });
        jMenu11.add(mnPemasok);
        jMenu11.add(jSeparator2);

        mnPelanggan.setText("Bidang");
        mnPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPelangganActionPerformed(evt);
            }
        });
        jMenu11.add(mnPelanggan);

        jMenuBar1.add(jMenu11);

        jMenu2.setText("Transaksi");

        mnBarangMasuk.setText("Barang Masuk");
        mnBarangMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBarangMasukActionPerformed(evt);
            }
        });
        jMenu2.add(mnBarangMasuk);

        mnBarangKeluar.setText("Barang Keluar");
        mnBarangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBarangKeluarActionPerformed(evt);
            }
        });
        jMenu2.add(mnBarangKeluar);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Report");

        jMenu7.setText("Transaksi");

        jMenu12.setText("Barang Masuk");

        mnTransaksiBmasuk.setText("Triwulan 1");
        mnTransaksiBmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnTransaksiBmasukActionPerformed(evt);
            }
        });
        jMenu12.add(mnTransaksiBmasuk);

        jMenuItem3.setText("Triwulan 2");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem3);

        jMenuItem7.setText("Triwulan 3");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem7);

        jMenuItem8.setText("Triwulan 4");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem8);

        jMenu7.add(jMenu12);

        jMenu13.setText("Barang Keluar");

        mnTransaksiBkeluar.setText("Triwulan 1");
        mnTransaksiBkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnTransaksiBkeluarActionPerformed(evt);
            }
        });
        jMenu13.add(mnTransaksiBkeluar);

        mnTransaksiBkeluar1.setText("Triwulan 2");
        mnTransaksiBkeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnTransaksiBkeluar1ActionPerformed(evt);
            }
        });
        jMenu13.add(mnTransaksiBkeluar1);

        mnTransaksiBkeluar2.setText("Triwulan 3");
        mnTransaksiBkeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnTransaksiBkeluar2ActionPerformed(evt);
            }
        });
        jMenu13.add(mnTransaksiBkeluar2);

        mnTransaksiBkeluar3.setText("Triwulan 4");
        mnTransaksiBkeluar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnTransaksiBkeluar3ActionPerformed(evt);
            }
        });
        jMenu13.add(mnTransaksiBkeluar3);

        jMenu7.add(jMenu13);

        jMenu5.add(jMenu7);

        jMenu8.setText("Inventory");

        jMenuItem4.setText("Data Barang");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem4);

        jMenu6.setText("Persediaan Barang");

        PBT1.setText("Triwulan 1");
        PBT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PBT1ActionPerformed(evt);
            }
        });
        jMenu6.add(PBT1);

        PBT2.setText("Triwulan 2");
        PBT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PBT2ActionPerformed(evt);
            }
        });
        jMenu6.add(PBT2);

        PBT3.setText("Triwulan 3");
        PBT3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PBT3ActionPerformed(evt);
            }
        });
        jMenu6.add(PBT3);

        PBT4.setText("Triwulan 4");
        PBT4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PBT4ActionPerformed(evt);
            }
        });
        jMenu6.add(PBT4);

        jMenu8.add(jMenu6);

        jMenu5.add(jMenu8);

        jMenuBar1.add(jMenu5);

        jMenu1.setText("System");

        mnLogin.setText("Login");
        mnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLoginActionPerformed(evt);
            }
        });
        jMenu1.add(mnLogin);

        mnLogout.setText("Logout");
        mnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLogoutActionPerformed(evt);
            }
        });
        jMenu1.add(mnLogout);
        jMenu1.add(jSeparator1);

        mnGanti.setText("Ganti Password");
        mnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGantiActionPerformed(evt);
            }
        });
        jMenu1.add(mnGanti);

        mnTambah.setText("Tambah Pengguna");
        jMenu1.add(mnTambah);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1062, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnKategoriActionPerformed

        KategoriView trsk = new KategoriView();
        trsk.show();
    }//GEN-LAST:event_mnKategoriActionPerformed

    private void mnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLoginActionPerformed
        // TODO add your handling code here:
txtUsername.setText("");
txtPassword.setText("");
        jDialog1.setVisible(true);
    }//GEN-LAST:event_mnLoginActionPerformed

    private void mnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBarangActionPerformed
        BarangView trsk1 = new BarangView();
        trsk1.show();
    }//GEN-LAST:event_mnBarangActionPerformed

    private void mnPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPemasokActionPerformed
        PemasokView trsk1 = new PemasokView();
        trsk1.show();
    }//GEN-LAST:event_mnPemasokActionPerformed

    private void mnPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPelangganActionPerformed
        PelangganView trsk1 = new PelangganView();
        trsk1.show();
    }//GEN-LAST:event_mnPelangganActionPerformed

    private void mnIsiGudangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnIsiGudangActionPerformed
        IsiGudangView trsk1 = new IsiGudangView();
        trsk1.show();
    }//GEN-LAST:event_mnIsiGudangActionPerformed

    private void mnBarangMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBarangMasukActionPerformed
        TransaksiBarangMasukView trsk1 = new TransaksiBarangMasukView();
        trsk1.show();
    }//GEN-LAST:event_mnBarangMasukActionPerformed

    private void mnBarangKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBarangKeluarActionPerformed
        TransaksiBarangKeluarView trsk1 = new TransaksiBarangKeluarView();
        trsk1.show();
    }//GEN-LAST:event_mnBarangKeluarActionPerformed

    private void mnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGantiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnGantiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
           String reportSource = null;
        String reportDest = null;

        try {
            HashMap parameter = new HashMap();
            parameter.put("tgl1",txtTahun1.getText());

            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
            reportSource = System.getProperty("user.dir") + "/report/PersediaanBarangT1.jrxml";
            reportDest = System.getProperty("user.dir") + "/report/PersediaanBarangT1.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void PBT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PBT1ActionPerformed
        // TODO add your handling code here:
            PersediaanBarang1.setLocationRelativeTo(null);
            PersediaanBarang1.setVisible(true);
            txtTahun1.setEditable(true);
            PersediaanBarang1.setSize(415, 180);
    }//GEN-LAST:event_PBT1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
           String reportSource = null;
        String reportDest = null;

        try {
            HashMap parameter = new HashMap();
            parameter.put("tgl1",txtTahun1.getText());

            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
            reportSource = System.getProperty("user.dir") + "/report/PersediaanBarangT2.jrxml";
            reportDest = System.getProperty("user.dir") + "/report/PersediaanBarangT2.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
           String reportSource = null;
        String reportDest = null;

        try {
            HashMap parameter = new HashMap();
            parameter.put("tgl1",txtTahun1.getText());

            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
            reportSource = System.getProperty("user.dir") + "/report/PersediaanBarangT3.jrxml";
            reportDest = System.getProperty("user.dir") + "/report/PersediaanBarangT3.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
           String reportSource = null;
        String reportDest = null;

        try {
            HashMap parameter = new HashMap();
            parameter.put("tgl1",txtTahun1.getText());

            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
            reportSource = System.getProperty("user.dir") + "/report/PersediaanBarangT4.jrxml";
            reportDest = System.getProperty("user.dir") + "/report/PersediaanBarangT4.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void PBT4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PBT4ActionPerformed
        // TODO add your handling code here:
            PersediaanBarang4.setLocationRelativeTo(null);
            PersediaanBarang4.setVisible(true);
            txtTahun4.setEditable(true);
            PersediaanBarang4.setSize(415, 180);

    }//GEN-LAST:event_PBT4ActionPerformed

    private void PBT3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PBT3ActionPerformed
        // TODO add your handling code here:
            PersediaanBarang3.setLocationRelativeTo(null);
            PersediaanBarang3.setVisible(true);
            txtTahun3.setEditable(true);
            PersediaanBarang3.setSize(415, 180);

    }//GEN-LAST:event_PBT3ActionPerformed

    private void PBT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PBT2ActionPerformed
        // TODO add your handling code here:
            PersediaanBarang2.setLocationRelativeTo(null);
            PersediaanBarang2.setVisible(true);
            txtTahun2.setEditable(true);
            PersediaanBarang2.setSize(415, 180);
    }//GEN-LAST:event_PBT2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        
           String reportSource = null;
        String reportDest = null;

        try {
            HashMap parameter = new HashMap();
            parameter.put("tgl1",txtTahun1.getText());

            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
            reportSource = System.getProperty("user.dir") + "/report/DataBarang.jrxml";
            reportDest = System.getProperty("user.dir") + "/report/DataBarang.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
           String reportSource = null;
        String reportDest = null;

        try {
            HashMap parameter = new HashMap();
            parameter.put("tahun",txtTahun5.getText());

            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
            reportSource = System.getProperty("user.dir") + "/report/BarangMasukT1.jrxml";
            reportDest = System.getProperty("user.dir") + "/report/BarangMasukT1.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
           String reportSource = null;
        String reportDest = null;

        try {
            HashMap parameter = new HashMap();
            parameter.put("tahun",txtTahun6.getText());

            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
            reportSource = System.getProperty("user.dir") + "/report/BarangMasukT2.jrxml";
            reportDest = System.getProperty("user.dir") + "/report/BarangMasukT2.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
           String reportSource = null;
        String reportDest = null;

        try {
            HashMap parameter = new HashMap();
            parameter.put("tahun",txtTahun7.getText());

            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
            reportSource = System.getProperty("user.dir") + "/report/BarangMasukT3.jrxml";
            reportDest = System.getProperty("user.dir") + "/report/BarangMasukT3.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
           String reportSource = null;
        String reportDest = null;

        try {
            HashMap parameter = new HashMap();
            parameter.put("tahun",txtTahun8.getText());

            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
            reportSource = System.getProperty("user.dir") + "/report/BarangMasukT44.jrxml";
            reportDest = System.getProperty("user.dir") + "/report/BarangMasukT44.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void mnTransaksiBmasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnTransaksiBmasukActionPerformed
        // TODO add your handling code here:
            BarangMasukT1.setLocationRelativeTo(null);
            BarangMasukT1.setVisible(true);
            BarangMasukT1.setSize(415, 180);
    }//GEN-LAST:event_mnTransaksiBmasukActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
            BarangMasukT2.setLocationRelativeTo(null);
            BarangMasukT2.setVisible(true);
            BarangMasukT2.setSize(415, 180);

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
            BarangMasukT3.setLocationRelativeTo(null);
            BarangMasukT3.setVisible(true);
            BarangMasukT3.setSize(415, 180);

    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
            BarangMasukT4.setLocationRelativeTo(null);
            BarangMasukT4.setVisible(true);
            BarangMasukT4.setSize(415, 180);

    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void mnTransaksiBkeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnTransaksiBkeluar1ActionPerformed
            BarangKeluarT2.setLocationRelativeTo(null);
            BarangKeluarT2.setVisible(true);
            BarangKeluarT2.setSize(415, 180);

    }//GEN-LAST:event_mnTransaksiBkeluar1ActionPerformed

    private void mnTransaksiBkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnTransaksiBkeluarActionPerformed
BarangKeluarT1.setLocationRelativeTo(null);
            BarangKeluarT1.setVisible(true);
            BarangKeluarT1.setSize(415, 180);

    }//GEN-LAST:event_mnTransaksiBkeluarActionPerformed

    private void mnTransaksiBkeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnTransaksiBkeluar2ActionPerformed
BarangKeluarT3.setLocationRelativeTo(null);
            BarangKeluarT3.setVisible(true);
            BarangKeluarT3.setSize(415, 180);

    }//GEN-LAST:event_mnTransaksiBkeluar2ActionPerformed

    private void mnTransaksiBkeluar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnTransaksiBkeluar3ActionPerformed
BarangKeluarT4.setLocationRelativeTo(null);
            BarangKeluarT4.setVisible(true);
            BarangKeluarT4.setSize(415, 180);

    }//GEN-LAST:event_mnTransaksiBkeluar3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
  String reportSource = null;
        String reportDest = null;

        try {
            HashMap parameter = new HashMap();
            parameter.put("tanggal",txtTahun9.getText());

            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
            reportSource = System.getProperty("user.dir") + "/report/BarangKeluarT1.jrxml";
            reportDest = System.getProperty("user.dir") + "/report/BarangKeluarT1.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
  String reportSource = null;
        String reportDest = null;

        try {
            HashMap parameter = new HashMap();
            parameter.put("tanggal",txtTahun10.getText());

            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
            reportSource = System.getProperty("user.dir") + "/report/BarangKeluarT2.jrxml";
            reportDest = System.getProperty("user.dir") + "/report/BarangKeluarT2.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
          String reportSource = null;
        String reportDest = null;

        try {
            HashMap parameter = new HashMap();
            parameter.put("tanggal",txtTahun11.getText());

            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
            reportSource = System.getProperty("user.dir") + "/report/BarangKeluarT3.jrxml";
            reportDest = System.getProperty("user.dir") + "/report/BarangKeluarT3.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
          String reportSource = null;
        String reportDest = null;

        try {
            HashMap parameter = new HashMap();
            parameter.put("tanggal",txtTahun12.getText());

            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_gudang_dishub");
            reportSource = System.getProperty("user.dir") + "/report/BarangKeluarT4.jrxml";
            reportDest = System.getProperty("user.dir") + "/report/BarangKeluarT4.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_jButton12ActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        int ascii = evt.getKeyCode();
        if (ascii==10){
            txtPassword.requestFocus();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void bLogin2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLogin2ActionPerformed
        // TODO add your handling code here:
     cekUser();
        jDialog1.setVisible(false);
    }//GEN-LAST:event_bLogin2ActionPerformed

    private void bBatal3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBatal3ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_bBatal3ActionPerformed

    private void mnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLogoutActionPerformed
        // TODO add your handling code here:
        setMenuLogout();
    }//GEN-LAST:event_mnLogoutActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BarangKeluarT1;
    private javax.swing.JDialog BarangKeluarT2;
    private javax.swing.JDialog BarangKeluarT3;
    private javax.swing.JDialog BarangKeluarT4;
    private javax.swing.JDialog BarangMasukT1;
    private javax.swing.JDialog BarangMasukT2;
    private javax.swing.JDialog BarangMasukT3;
    private javax.swing.JDialog BarangMasukT4;
    private javax.swing.JMenuItem PBT1;
    private javax.swing.JMenuItem PBT2;
    private javax.swing.JMenuItem PBT3;
    private javax.swing.JMenuItem PBT4;
    private javax.swing.JDialog PersediaanBarang1;
    private javax.swing.JDialog PersediaanBarang2;
    private javax.swing.JDialog PersediaanBarang3;
    private javax.swing.JDialog PersediaanBarang4;
    private javax.swing.JButton bBatal3;
    private javax.swing.JButton bLogin2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu28;
    private javax.swing.JMenu jMenu29;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem mnBarang;
    private javax.swing.JMenuItem mnBarangKeluar;
    private javax.swing.JMenuItem mnBarangMasuk;
    private javax.swing.JMenuItem mnGanti;
    private javax.swing.JMenuItem mnIsiGudang;
    private javax.swing.JMenuItem mnKategori;
    private javax.swing.JMenuItem mnLogin;
    private javax.swing.JMenuItem mnLogout;
    private javax.swing.JMenuItem mnPelanggan;
    private javax.swing.JMenuItem mnPemasok;
    private javax.swing.JMenuItem mnTambah;
    private javax.swing.JMenuItem mnTransaksiBkeluar;
    private javax.swing.JMenuItem mnTransaksiBkeluar1;
    private javax.swing.JMenuItem mnTransaksiBkeluar2;
    private javax.swing.JMenuItem mnTransaksiBkeluar3;
    private javax.swing.JMenuItem mnTransaksiBmasuk;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtTahun1;
    private javax.swing.JTextField txtTahun10;
    private javax.swing.JTextField txtTahun11;
    private javax.swing.JTextField txtTahun12;
    private javax.swing.JTextField txtTahun2;
    private javax.swing.JTextField txtTahun3;
    private javax.swing.JTextField txtTahun4;
    private javax.swing.JTextField txtTahun5;
    private javax.swing.JTextField txtTahun6;
    private javax.swing.JTextField txtTahun7;
    private javax.swing.JTextField txtTahun8;
    private javax.swing.JTextField txtTahun9;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
