/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_mainPanel;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

/**
 *
 * @author admin
 */
public class Menu_datasiswa extends javax.swing.JPanel {
    
     private Connection conn = new koneksi().connect();
     private DefaultTableModel tabmode;

    /**
     * Creates new form Menu_datasiswa
     */
    public Menu_datasiswa() {
        initComponents();
        warna_header();
        datatabel();
        t_siswa.setDefaultEditor(Object.class, null);
        Riset();
        
        
    }
    protected void Riset(){
        b_hapus.setVisible(false);
        b_batal.setVisible(false);
        b_tambah.setText("TAMBAH");
        b_tambah.setBackground(new Color (0,112,192));
    }
    
    protected void kosong(){
        t_nama.setText("");
        t_nis.setText("");
        c_kelas.setSelectedItem(0);
        rjk1.setSelected(false);
         rjk2.setSelected(false);
    }
        //Tabel
   protected void datatabel() {

    Object[] Baris = {"NO", "NIS", "Nama Siswa","Kelas","Jenis Kelamin"};
    tabmode = new DefaultTableModel(null, Baris);
    t_siswa.setModel(tabmode);

    String sql = "SELECT * FROM siswa ORDER BY FIELD(kelas,'I','II','III','IV','V','VI'), nama_siswa";

    int no = 1;

    try {

        Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);

        while (hasil.next()) {

            String[] data = {
                String.valueOf(no++),               
                hasil.getString("nis"),             
                hasil.getString("nama_siswa"),      
                hasil.getString("kelas"),            
                hasil.getString("jenis_kelamin")
            };

            tabmode.addRow(data);

        }

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(null,
                "Error retrieving data: " + ex.getMessage());

    }
}
    
    
        //Button Tambah Kriteria
        protected void tambah(){
             String sql = "INSERT INTO siswa (nis, nama_siswa, jenis_kelamin, kelas) VALUES (?,?, ?, ?)";
        try {
        PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,t_nis.getText());
            stat.setString(2,t_nama.getText());
            
            String Jkel=" ";
                    if(rjk1.isSelected()) Jkel="laki-laki";
                    else Jkel="Perempuan";
                    stat.setString(3,Jkel);
            
            stat.setString(4, c_kelas.getSelectedItem().toString());
        
        
         stat.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        kosong();
//        aktif();
        t_siswa.requestFocus();
        datatabel();
                        
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "Data Gagal Disimpan: " + e.getMessage());
        }
        }
        
        
               // Edit Data
        protected void edit (){
        try {
                 // Memeriksa apakah ID tidak kosong
                if (t_nis.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "NIS tidak boleh kosong.");
                    return;
                }
                t_nis.setEditable(false);
                String sql = "UPDATE siswa SET nama_siswa=?,jenis_kelamin=?, kelas=? WHERE nis=?";
                PreparedStatement stat = conn.prepareStatement(sql);
                
                    stat.setString(4, t_nis.getText());
                    stat.setString(1, t_nama.getText());
                    String jkel = rjk1.isSelected() ? "Laki-Laki" : "Perempuan";
                    stat.setString(2, jkel);
                    stat.setString(3, c_kelas.getSelectedItem().toString());
                    stat.executeUpdate();

                 JOptionPane.showMessageDialog(null, "Data berhasil diubah");
                 datatabel();
                 kosong();
               
               } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah: " + e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
            } 
        }
    
private void hapus() {
    int ok = JOptionPane.showConfirmDialog(
            null,
            "Apakah Anda yakin ingin menghapus data ini?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION);

    if (ok == JOptionPane.YES_OPTION) {

        String sql = "DELETE FROM siswa WHERE nis = ?";

        try {
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, t_nis.getText());

            int hasil = stat.executeUpdate();

            if (hasil > 0) {
                JOptionPane.showMessageDialog(null,
                        "Data berhasil dihapus");

                kosong();
                t_nis.requestFocus();
                datatabel();

            } else {
                JOptionPane.showMessageDialog(null,
                        "Data tidak ditemukan!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Data gagal dihapus : " + e.getMessage());
        }
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_menusiswa = new javax.swing.JPanel();
        pn_datasiswa = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        b_tambah = new componen_tambahan.RoundButton();
        b_hapus = new componen_tambahan.RoundButton();
        b_batal = new componen_tambahan.RoundButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_siswa = new javax.swing.JTable();
        t_cari = new javax.swing.JTextField();
        b_cari = new javax.swing.JButton();
        pn_tambahdata = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        b_simpan = new componen_tambahan.RoundButton();
        b_batal1 = new componen_tambahan.RoundButton();
        t_nis = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        t_nama = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        rjk2 = new javax.swing.JRadioButton();
        rjk1 = new javax.swing.JRadioButton();
        c_kelas = new javax.swing.JComboBox<>();

        setLayout(new java.awt.CardLayout());

        pn_menusiswa.setBackground(new java.awt.Color(255, 255, 255));
        pn_menusiswa.setLayout(new java.awt.CardLayout());

        pn_datasiswa.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Master > Data Siswa");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_siswa.png"))); // NOI18N

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Data Siswa");

        b_tambah.setBackground(new java.awt.Color(0, 112, 192));
        b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tambah.png"))); // NOI18N
        b_tambah.setText("TAMBAH");
        b_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_tambahActionPerformed(evt);
            }
        });

        b_hapus.setBackground(new java.awt.Color(255, 51, 51));
        b_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Remove.png"))); // NOI18N
        b_hapus.setText("HAPUS");
        b_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_hapusActionPerformed(evt);
            }
        });

        b_batal.setBackground(new java.awt.Color(255, 153, 0));
        b_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cancel.png"))); // NOI18N
        b_batal.setText("BATAL");
        b_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_batalActionPerformed(evt);
            }
        });

        t_siswa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        t_siswa.setForeground(new java.awt.Color(51, 51, 51));
        t_siswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        t_siswa.setRowHeight(26);
        t_siswa.setRowMargin(2);
        t_siswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_siswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_siswa);

        t_cari.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        t_cari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        t_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_cariActionPerformed(evt);
            }
        });
        t_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_cariKeyPressed(evt);
            }
        });

        b_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cari.png"))); // NOI18N
        b_cari.setText("Cari");
        b_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_datasiswaLayout = new javax.swing.GroupLayout(pn_datasiswa);
        pn_datasiswa.setLayout(pn_datasiswaLayout);
        pn_datasiswaLayout.setHorizontalGroup(
            pn_datasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_datasiswaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_datasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_datasiswaLayout.createSequentialGroup()
                        .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 495, Short.MAX_VALUE)
                .addGroup(pn_datasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_datasiswaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_datasiswaLayout.createSequentialGroup()
                        .addComponent(t_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_cari)))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_datasiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pn_datasiswaLayout.setVerticalGroup(
            pn_datasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_datasiswaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_datasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_datasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(pn_datasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_cari))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        pn_menusiswa.add(pn_datasiswa, "card2");

        pn_tambahdata.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Master > Data Siswa");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_siswa.png"))); // NOI18N

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Data Siswa");

        b_simpan.setBackground(new java.awt.Color(0, 112, 192));
        b_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tambah.png"))); // NOI18N
        b_simpan.setText("SIMPAN");
        b_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_simpanActionPerformed(evt);
            }
        });

        b_batal1.setBackground(new java.awt.Color(255, 153, 0));
        b_batal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cancel.png"))); // NOI18N
        b_batal1.setText("BATAL");
        b_batal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_batal1ActionPerformed(evt);
            }
        });

        t_nis.setBackground(new java.awt.Color(240, 238, 238));
        t_nis.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        t_nis.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

        jLabel34.setBackground(new java.awt.Color(0, 0, 0));
        jLabel34.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(153, 153, 153));
        jLabel34.setText("NIS");

        t_nama.setBackground(new java.awt.Color(240, 238, 238));
        t_nama.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        t_nama.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

        jLabel35.setBackground(new java.awt.Color(0, 0, 0));
        jLabel35.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(153, 153, 153));
        jLabel35.setText("Nama Siswa");

        jLabel36.setBackground(new java.awt.Color(0, 0, 0));
        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(153, 153, 153));
        jLabel36.setText("Jenis Kelamin");

        jLabel37.setBackground(new java.awt.Color(0, 0, 0));
        jLabel37.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(153, 153, 153));
        jLabel37.setText("Kelas");

        rjk2.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        rjk2.setText("Perempuan");

        rjk1.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        rjk1.setText("Laki-Laki");

        c_kelas.setBackground(new java.awt.Color(153, 153, 153));
        c_kelas.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        c_kelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "I", "II", "III", "IV", "V", "VI" }));
        c_kelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_kelasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_tambahdataLayout = new javax.swing.GroupLayout(pn_tambahdata);
        pn_tambahdata.setLayout(pn_tambahdataLayout);
        pn_tambahdataLayout.setHorizontalGroup(
            pn_tambahdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_tambahdataLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_tambahdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_tambahdataLayout.createSequentialGroup()
                        .addGroup(pn_tambahdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t_nis, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_tambahdataLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 863, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(t_nama, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))
                    .addGroup(pn_tambahdataLayout.createSequentialGroup()
                        .addGroup(pn_tambahdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_tambahdataLayout.createSequentialGroup()
                                .addComponent(rjk1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rjk2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn_tambahdataLayout.createSequentialGroup()
                                .addComponent(b_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b_batal1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(pn_tambahdataLayout.createSequentialGroup()
                        .addComponent(c_kelas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        pn_tambahdataLayout.setVerticalGroup(
            pn_tambahdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_tambahdataLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_tambahdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_tambahdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_tambahdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_batal1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(t_nis, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(t_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(c_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_tambahdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rjk2)
                    .addComponent(rjk1))
                .addContainerGap(462, Short.MAX_VALUE))
        );

        pn_menusiswa.add(pn_tambahdata, "card2");

        add(pn_menusiswa, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void b_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_tambahActionPerformed
        if(b_tambah.getText().equals("TAMBAH")){
            pn_menusiswa.removeAll();
            pn_menusiswa.add(pn_tambahdata);
            pn_menusiswa.repaint();
            pn_menusiswa.revalidate();
            b_simpan.setText("SIMPAN");
//            otomatis();
//            kosong();
        } else if(b_tambah.getText().equals("EDIT")){
            pn_menusiswa.removeAll();
            pn_menusiswa.add(pn_tambahdata);
            pn_menusiswa.repaint();
            pn_menusiswa.revalidate();
            b_simpan.setText("SIMPAN");
            t_nis.setEditable(false);
           
        }
        
    }//GEN-LAST:event_b_tambahActionPerformed

    private void b_batal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_batal1ActionPerformed
        pn_menusiswa.removeAll();
        pn_menusiswa.add(pn_datasiswa);
        pn_menusiswa.repaint();
        pn_menusiswa.revalidate();
        Riset();
    }//GEN-LAST:event_b_batal1ActionPerformed

    private void t_siswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_siswaMouseClicked
          if (b_tambah.getText().equals("TAMBAH")){
            b_tambah.setText("EDIT");
            b_hapus.setVisible(true);
            b_batal.setVisible(true);
            b_tambah.setBackground(new Color(40, 167, 69));
         
            
            //SELECT DATA
         int bar = t_siswa.getSelectedRow();
             if (bar != -1) {
           
            String a = tabmode.getValueAt(bar, 1).toString();
            String b = tabmode.getValueAt(bar, 2).toString();
            String c = tabmode.getValueAt(bar, 3).toString();
            String d = tabmode.getValueAt(bar, 4).toString();
   

            t_nis.setText(a);
            t_nama.setText(b);
            
             rjk1.setSelected(true);

           if(c.equals("Laki-Laki")){
                rjk1.setSelected(true);
            }else if(c.equals("Perempuan")){
                rjk2.setSelected(true);
            }
            c_kelas.setSelectedItem(d);
   
                    
             } else {
            System.out.println("No row selected.");
            JOptionPane.showMessageDialog(null, "Please select a row to edit.");
        }
    }
       
    }//GEN-LAST:event_t_siswaMouseClicked

    private void b_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_batalActionPerformed
     b_hapus.setVisible(false);
    b_batal.setVisible(false);
    
     b_tambah.setText("TAMBAH");
     b_tambah.setBackground(new Color (0,112,192));
    
    datatabel();
    kosong();
    
    }//GEN-LAST:event_b_batalActionPerformed

    private void t_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_cariActionPerformed

    private void b_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_simpanActionPerformed
      if(b_tambah.getText().equals("TAMBAH")){
         tambah();
     } else if(b_tambah.getText().equals("EDIT")){
         edit();
         
     }
    }//GEN-LAST:event_b_simpanActionPerformed

    private void b_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_hapusActionPerformed
    hapus();
    }//GEN-LAST:event_b_hapusActionPerformed

    private void b_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cariActionPerformed
    cariData();
    }//GEN-LAST:event_b_cariActionPerformed

    private void t_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_cariKeyPressed
    String searchTerm = t_cari.getText().trim();

    if (searchTerm.equals("")) {
        datatabel(); // tampilkan semua data
    } else {
        cariData(); // method pencarian
    }
    }//GEN-LAST:event_t_cariKeyPressed

    private void c_kelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_kelasActionPerformed

    }//GEN-LAST:event_c_kelasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componen_tambahan.RoundButton b_batal;
    private componen_tambahan.RoundButton b_batal1;
    private javax.swing.JButton b_cari;
    private componen_tambahan.RoundButton b_hapus;
    private componen_tambahan.RoundButton b_simpan;
    private componen_tambahan.RoundButton b_tambah;
    private javax.swing.JComboBox<String> c_kelas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pn_datasiswa;
    private javax.swing.JPanel pn_menusiswa;
    private javax.swing.JPanel pn_tambahdata;
    private javax.swing.JRadioButton rjk1;
    private javax.swing.JRadioButton rjk2;
    private javax.swing.JTextField t_cari;
    private javax.swing.JTextField t_nama;
    private javax.swing.JTextField t_nis;
    private javax.swing.JTable t_siswa;
    // End of variables declaration//GEN-END:variables

    protected void warna_header(){
             t_siswa.setShowVerticalLines(true);
              t_siswa.setGridColor(java.awt.Color.GRAY);
          // Ubah warna header 
        javax.swing.table.JTableHeader header =  t_siswa.getTableHeader();
        header.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
          @Override
         public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
        java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
         c.setBackground(new java.awt.Color(6, 169, 245)); 
        c.setForeground(java.awt.Color.WHITE);           
        setHorizontalAlignment(javax.swing.SwingConstants.LEFT); 
        c.setFont(c.getFont().deriveFont(java.awt.Font.BOLD, 13f));
        return c;
         }
        });  
    }
 // Mencari Data
// Mencari Data
private void cariData() {

    tabmode.setRowCount(0);

    String sql = "SELECT * FROM siswa "
            + "WHERE nis LIKE ? "
            + "OR nama_siswa LIKE ? "
            + "OR jenis_kelamin LIKE ? "
            + "OR kelas LIKE ? "
            + "ORDER BY FIELD(kelas,'I','II','III','IV','V','VI'), nama_siswa";

    try {

        PreparedStatement stat = conn.prepareStatement(sql);

        String cari = "%" + t_cari.getText().trim() + "%";

        stat.setString(1, cari);
        stat.setString(2, cari);
        stat.setString(3, cari);
        stat.setString(4, cari);

        ResultSet hasil = stat.executeQuery();

        int no = 1; // Nomor dimulai dari 1

        while (hasil.next()) {

            tabmode.addRow(new Object[]{
                no++,
                hasil.getString("nis"),
                hasil.getString("nama_siswa"),
                hasil.getString("kelas"),
                hasil.getString("jenis_kelamin")
            });

        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
}

