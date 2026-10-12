/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_mainPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Menu_subKriteria extends javax.swing.JPanel {

     private Connection conn = new koneksi().connect();
     private DefaultTableModel tabmode;
     private int id_sub;
    
    /**
     * Creates new form Menu_kriteria
     */
    public Menu_subKriteria() {
        initComponents();
        t_subkriteria.setDefaultEditor(Object.class, null);
        warna_header();
        isiComboBox();
        Riset();
       t_namakriteria.setEnabled(false);
        b_hapus.setVisible(false);
        b_batal.setVisible(false);
        tampilData(null);
    }
    
        protected void Riset(){
        b_hapus.setVisible(false);
        b_batal.setVisible(false);
        b_tambah.setText("TAMBAH");
        b_tambah.setBackground(new Color (0,112,192));
    }
        private void kosong() {
            id_sub = 0;
            t_keterangan.setText("");
            t_nilai.setText("");
        }
        

    protected void tambah() {
    try {

        String kode = c_kodekriteria.getSelectedItem().toString();

        String sqlCari = "SELECT id_kriteria FROM kriteria WHERE kode_kriteria=?";
        PreparedStatement psCari = conn.prepareStatement(sqlCari);
        psCari.setString(1, kode);

        ResultSet rs = psCari.executeQuery();

        if (rs.next()) {

            int idKriteria = rs.getInt("id_kriteria");

            String sql = "INSERT INTO sub_kriteria (id_kriteria, keterangan, nilai) VALUES (?, ?, ?)";

            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, idKriteria);
            stat.setString(2, t_keterangan.getText());
            stat.setInt(3, Integer.parseInt(t_nilai.getText()));

            stat.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            tampilData("");

        } else {
            JOptionPane.showMessageDialog(null, "Kode kriteria tidak ditemukan!");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan : " + e.getMessage());
    }
}
    
    
  
        
        
        
    //hapus data
    protected void hapus() {
        try {

            int jawab = JOptionPane.showConfirmDialog(
                    null,
                    "Hapus data ini?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION);

            if (jawab == JOptionPane.YES_OPTION) {

                String sql = "DELETE FROM sub_kriteria WHERE id_sub=?";

                PreparedStatement stat = conn.prepareStatement(sql);

                stat.setInt(1, id_sub);

                stat.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

                kosong();
              tampilData("");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

        // Edit Data
      protected void edit() {
    try {

        String sql = "UPDATE sub_kriteria SET keterangan=?, nilai=? WHERE id_sub=?";

        PreparedStatement stat = conn.prepareStatement(sql);

        stat.setString(1, t_keterangan.getText());
        stat.setInt(2, Integer.parseInt(t_nilai.getText()));
        stat.setInt(3, id_sub);

        stat.executeUpdate();

        JOptionPane.showMessageDialog(null, "Data berhasil diubah");

        kosong();
        tampilData("");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
    
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_menukriteria = new javax.swing.JPanel();
        pn_subKriteria = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        b_tambah = new componen_tambahan.RoundButton();
        b_hapus = new componen_tambahan.RoundButton();
        b_batal = new componen_tambahan.RoundButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_subkriteria = new javax.swing.JTable();
        t_cari = new javax.swing.JTextField();
        b_cari = new javax.swing.JButton();
        pn_tambahdata = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        b_simpan = new componen_tambahan.RoundButton();
        b_batal1 = new componen_tambahan.RoundButton();
        jLabel34 = new javax.swing.JLabel();
        t_namakriteria = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        t_keterangan = new javax.swing.JTextField();
        c_kodekriteria = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        t_nilai = new javax.swing.JTextField();

        setLayout(new java.awt.CardLayout());

        pn_menukriteria.setBackground(new java.awt.Color(255, 255, 255));
        pn_menukriteria.setLayout(new java.awt.CardLayout());

        pn_subKriteria.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Master >Sub Kriteria");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sub_kriteria.png"))); // NOI18N

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Sub Kriteria ");

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

        t_subkriteria.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        t_subkriteria.setForeground(new java.awt.Color(51, 51, 51));
        t_subkriteria.setModel(new javax.swing.table.DefaultTableModel(
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
        t_subkriteria.setRowHeight(30);
        t_subkriteria.setRowMargin(2);
        t_subkriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_subkriteriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_subkriteria);

        t_cari.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        t_cari.setForeground(new java.awt.Color(102, 102, 102));
        t_cari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        t_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_cariActionPerformed(evt);
            }
        });
        t_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_cariKeyReleased(evt);
            }
        });

        b_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cari.png"))); // NOI18N
        b_cari.setText("Cari");
        b_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_subKriteriaLayout = new javax.swing.GroupLayout(pn_subKriteria);
        pn_subKriteria.setLayout(pn_subKriteriaLayout);
        pn_subKriteriaLayout.setHorizontalGroup(
            pn_subKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_subKriteriaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_subKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_subKriteriaLayout.createSequentialGroup()
                        .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 524, Short.MAX_VALUE)
                        .addComponent(t_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_cari))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_subKriteriaLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        pn_subKriteriaLayout.setVerticalGroup(
            pn_subKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_subKriteriaLayout.createSequentialGroup()
                .addGroup(pn_subKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_subKriteriaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pn_subKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(pn_subKriteriaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(pn_subKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_cari))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pn_menukriteria.add(pn_subKriteria, "card2");

        pn_tambahdata.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Master > Sub Kriteria");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sub_kriteria.png"))); // NOI18N

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Tambah Sub Kriteria");

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

        jLabel34.setBackground(new java.awt.Color(0, 0, 0));
        jLabel34.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(153, 153, 153));
        jLabel34.setText("Kode Kriteria");

        t_namakriteria.setBackground(new java.awt.Color(240, 238, 238));
        t_namakriteria.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        t_namakriteria.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

        jLabel35.setBackground(new java.awt.Color(0, 0, 0));
        jLabel35.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(153, 153, 153));
        jLabel35.setText("Nama Kriteria");

        jLabel36.setBackground(new java.awt.Color(0, 0, 0));
        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(153, 153, 153));
        jLabel36.setText("Keterangan");

        t_keterangan.setBackground(new java.awt.Color(240, 238, 238));
        t_keterangan.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        t_keterangan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

        c_kodekriteria.setBackground(new java.awt.Color(153, 153, 153));
        c_kodekriteria.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        c_kodekriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_kodekriteriaActionPerformed(evt);
            }
        });

        jLabel38.setBackground(new java.awt.Color(0, 0, 0));
        jLabel38.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(153, 153, 153));
        jLabel38.setText("Nilai");

        t_nilai.setBackground(new java.awt.Color(240, 238, 238));
        t_nilai.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        t_nilai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout pn_tambahdataLayout = new javax.swing.GroupLayout(pn_tambahdata);
        pn_tambahdata.setLayout(pn_tambahdataLayout);
        pn_tambahdataLayout.setHorizontalGroup(
            pn_tambahdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_tambahdataLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_tambahdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_tambahdataLayout.createSequentialGroup()
                        .addComponent(b_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_batal1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(981, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_tambahdataLayout.createSequentialGroup()
                        .addGroup(pn_tambahdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(c_kodekriteria, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pn_tambahdataLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(t_namakriteria)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t_keterangan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t_nilai, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(20, 20, 20))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(c_kodekriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(t_namakriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(t_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(t_nilai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(340, Short.MAX_VALUE))
        );

        pn_menukriteria.add(pn_tambahdata, "card2");

        add(pn_menukriteria, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void b_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_tambahActionPerformed
        if(b_tambah.getText().equals("TAMBAH")){
            pn_menukriteria.removeAll();
            pn_menukriteria.add(pn_tambahdata);
            pn_menukriteria.repaint();
            pn_menukriteria.revalidate();
            b_simpan.setText("SIMPAN");
            
        
            isiComboBox();
        } else if(b_tambah.getText().equals("EDIT")){
            pn_menukriteria.removeAll();
            pn_menukriteria.add(pn_tambahdata);
            pn_menukriteria.repaint();
            pn_menukriteria.revalidate();
            b_simpan.setText("SIMPAN");
           
        }
        
    }//GEN-LAST:event_b_tambahActionPerformed

    private void b_batal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_batal1ActionPerformed
        pn_menukriteria.removeAll();
        pn_menukriteria.add(pn_subKriteria);
        pn_menukriteria.repaint();
        pn_menukriteria.revalidate();
        Riset();
        tampilData("");
    }//GEN-LAST:event_b_batal1ActionPerformed

    private void t_subkriteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_subkriteriaMouseClicked
            if (b_tambah.getText().equals("TAMBAH")) {
             b_tambah.setText("EDIT");
             b_hapus.setVisible(true);
             b_batal.setVisible(true);
             b_tambah.setBackground(new Color(40, 167, 69));
            }

             int bar = t_subkriteria.getSelectedRow();

             if (bar != -1) {

                 // Ambil ID untuk proses edit
                 id_sub = Integer.parseInt(tabmode.getValueAt(bar, 0).toString());

                 String a = tabmode.getValueAt(bar, 1).toString(); // kode_kriteria
                 String b = tabmode.getValueAt(bar, 2).toString(); // nama_kriteria
                 String c = tabmode.getValueAt(bar, 3).toString(); // keterangan
                 String d = tabmode.getValueAt(bar, 4).toString(); // nilai

                 c_kodekriteria.setSelectedItem(a);
                 t_namakriteria.setText(b);
                 t_keterangan.setText(c);
                 t_nilai.setText(d);

             } else {
                 JOptionPane.showMessageDialog(null, "Pilih data yang akan diedit");
             }
         
    }//GEN-LAST:event_t_subkriteriaMouseClicked

    private void b_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_batalActionPerformed
    b_hapus.setVisible(false);
    b_batal.setVisible(false);
    
     b_tambah.setText("TAMBAH");
     b_tambah.setBackground(new Color (0,112,192));
    

    tampilData("");
    kosong();
    }//GEN-LAST:event_b_batalActionPerformed

    private void t_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_cariActionPerformed

    private void b_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_simpanActionPerformed
       if(b_tambah.getText().equals("TAMBAH")){
         tambah();
         kosong();
     } else if(b_tambah.getText().equals("EDIT")){
         edit();
     }   
    }//GEN-LAST:event_b_simpanActionPerformed

    private void b_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_hapusActionPerformed
       hapus();
    }//GEN-LAST:event_b_hapusActionPerformed

    private void b_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cariActionPerformed
       tampilData(t_cari.getText());
    }//GEN-LAST:event_b_cariActionPerformed

    private void c_kodekriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_kodekriteriaActionPerformed
    String kode = (String) c_kodekriteria.getSelectedItem();
    if (kode != null && !kode.isEmpty()) {
        isiNamaKriteria(kode);
    } else {
        t_namakriteria.setText("");
    }

    }//GEN-LAST:event_c_kodekriteriaActionPerformed

    private void t_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_cariKeyReleased
        tampilData(t_cari.getText());
    }//GEN-LAST:event_t_cariKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componen_tambahan.RoundButton b_batal;
    private componen_tambahan.RoundButton b_batal1;
    private javax.swing.JButton b_cari;
    private componen_tambahan.RoundButton b_hapus;
    private componen_tambahan.RoundButton b_simpan;
    private componen_tambahan.RoundButton b_tambah;
    private javax.swing.JComboBox<String> c_kodekriteria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pn_menukriteria;
    private javax.swing.JPanel pn_subKriteria;
    private javax.swing.JPanel pn_tambahdata;
    private javax.swing.JTextField t_cari;
    private javax.swing.JTextField t_keterangan;
    private javax.swing.JTextField t_namakriteria;
    private javax.swing.JTextField t_nilai;
    private javax.swing.JTable t_subkriteria;
    // End of variables declaration//GEN-END:variables

    
    // Header Tabel
    protected void warna_header(){
              t_subkriteria.setShowVerticalLines(true);
              t_subkriteria.setGridColor(java.awt.Color.GRAY);
          // Ubah warna header 
        javax.swing.table.JTableHeader header = t_subkriteria.getTableHeader();
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

    // Mengisi Combo Box
       private void isiComboBox() {
    try {
        Connection conn = new koneksi().connect();

        String query = "SELECT kode_kriteria, nama_kriteria FROM kriteria";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        c_kodekriteria.removeAllItems();
        c_kodekriteria.addItem("");

        while (rs.next()) {
            c_kodekriteria.addItem(rs.getString("kode_kriteria"));
        }

        rs.close();
        pst.close();
        conn.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
                "Gagal isi combo box: " + e.getMessage());
    }
}
       
    private void isiNamaKriteria(String kode) {
    try {
        Connection conn = new koneksi().connect();

        String query = "SELECT nama_kriteria FROM kriteria WHERE kode_kriteria = ?";

        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, kode);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            t_namakriteria.setText(rs.getString("nama_kriteria"));
        } else {
            t_namakriteria.setText("");
        }

        rs.close();
        pst.close();
        conn.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
                "Gagal mengambil nama kriteria: " + e.getMessage());
    }
}
    
    private void tampilData(String keyword) {

    Object[] Baris = {
        "ID SUB",
        "Kode Kriteria",
        "Nama Kriteria",
        "Keterangan",
        "Nilai"
    };

    tabmode = new DefaultTableModel(null, Baris);
    t_subkriteria.setModel(tabmode);

    String sql = "SELECT s.id_sub, k.kode_kriteria, k.nama_kriteria, "
               + "s.keterangan, s.nilai "
               + "FROM sub_kriteria s "
               + "JOIN kriteria k ON s.id_kriteria = k.id_kriteria ";

    if (keyword != null && !keyword.trim().isEmpty()) {
        sql += "WHERE k.kode_kriteria LIKE ? "
             + "OR k.nama_kriteria LIKE ? "
             + "OR s.keterangan LIKE ? ";
    }

    try {

        PreparedStatement stat = conn.prepareStatement(sql);

        if (keyword != null && !keyword.trim().isEmpty()) {
            String cari = "%" + keyword + "%";

            stat.setString(1, cari);
            stat.setString(2, cari);
            stat.setString(3, cari);
        }

        ResultSet hasil = stat.executeQuery();

        while (hasil.next()) {

            String id = hasil.getString("id_sub");
            String kode = hasil.getString("kode_kriteria");
            String nama = hasil.getString("nama_kriteria");
            String ket = hasil.getString("keterangan");
            String nilai = hasil.getString("nilai");

            String[] data = {
                id,
                kode,
                nama,
                ket,
                nilai
            };

            tabmode.addRow(data);
        }

        // sembunyikan kolom ID
        t_subkriteria.getColumnModel().getColumn(0).setMinWidth(0);
        t_subkriteria.getColumnModel().getColumn(0).setMaxWidth(0);
        t_subkriteria.getColumnModel().getColumn(0).setWidth(0);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
                "Gagal menampilkan data : " + e.getMessage());
    }
}
}
