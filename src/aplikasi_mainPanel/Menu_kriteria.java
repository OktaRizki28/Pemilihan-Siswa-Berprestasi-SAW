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
public class Menu_kriteria extends javax.swing.JPanel {

     private Connection conn = new koneksi().connect();
     private DefaultTableModel tabmode;
    
    /**
     * Creates new form Menu_kriteria
     */
    public Menu_kriteria() {
        initComponents();
        t_kriteria.setDefaultEditor(Object.class, null);
        warna_header();
        datatabel();
        Riset();
        
        
        
        b_hapus.setVisible(false);
        b_batal.setVisible(false);
    }
    
        protected void Riset(){
        b_hapus.setVisible(false);
        b_batal.setVisible(false);
        b_tambah.setText("TAMBAH");
        b_tambah.setBackground(new Color (0,112,192));
    }
        
    protected void kosong(){
        t_namakriteria.setText("");
        t_bobot.setText("");
    }
    
        
    //Tabel
    protected void datatabel(){
             
        Object [] Baris = {"Kode Kriteria","Nama Kriteria","Bobot (%)","Atribut"};
        tabmode = new DefaultTableModel(null,Baris);
        t_kriteria.setModel(tabmode);
        String sql = " SELECT * FROM kriteria";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("kode_kriteria");
                String b = hasil.getString("nama_kriteria");
                String c = hasil.getString("bobot");
                String d = hasil.getString("atribut");
            
                String[] data={a,b,c,d};
                tabmode.addRow(data);
       
            } 
            
        }catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage()); 
           
        }
    }
    
    
    
    //Button Tambah Kriteria
        protected void tambah(){
             String sql = "INSERT INTO kriteria (kode_kriteria, nama_kriteria, bobot, atribut) VALUES (?,?, ?, ?)";
        try {
        PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,t_kodekriteria.getText());
            stat.setString(2,t_namakriteria.getText());
            stat.setDouble(3, Double.parseDouble(t_bobot.getText()));
            stat.setString(4, c_atribut.getSelectedItem().toString());
        
        
         stat.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        kosong();
//        aktif();
        t_kodekriteria.requestFocus();
        datatabel();
                        
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "Data Gagal Disimpan: " + e.getMessage());
        }
        }
        
        
        //hapus data
  private void hapus() {
    int ok = JOptionPane.showConfirmDialog(
            null,
            "Apakah Anda yakin ingin menghapus data ini?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION);

    if (ok == JOptionPane.YES_OPTION) {

        String sql = "DELETE FROM kriteria WHERE kode_kriteria = ?";

        try {
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, t_kodekriteria.getText());

            int hasil = stat.executeUpdate();

            if (hasil > 0) {
                JOptionPane.showMessageDialog(null,
                        "Data berhasil dihapus");

                kosong();
                t_kodekriteria.requestFocus();
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
        
        // Edit Data
        protected void edit (){
        try {
                 // Memeriksa apakah ID tidak kosong
                if (t_kodekriteria.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Kode Kriteria tidak boleh kosong.");
                    return;
                }
                
                String sql = "UPDATE kriteria SET nama_kriteria=?,bobot=?, atribut=? WHERE kode_kriteria=?";
                PreparedStatement stat = conn.prepareStatement(sql);
                
                    stat.setString(4, t_kodekriteria.getText());
                    stat.setString(1, t_namakriteria.getText());
                    stat.setDouble(2, Double.parseDouble(t_bobot.getText()));
                    stat.setString(3, (String) c_atribut.getSelectedItem());
               
                    stat.executeUpdate();

                 JOptionPane.showMessageDialog(null, "Data berhasil diubah");

               
               } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah: " + e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
            } 
        }
    
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_menukriteria = new javax.swing.JPanel();
        pn_kriteria = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        b_tambah = new componen_tambahan.RoundButton();
        b_hapus = new componen_tambahan.RoundButton();
        b_batal = new componen_tambahan.RoundButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_kriteria = new javax.swing.JTable();
        t_cari = new javax.swing.JTextField();
        b_cari = new javax.swing.JButton();
        pn_tambahdata = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        b_simpan = new componen_tambahan.RoundButton();
        b_batal1 = new componen_tambahan.RoundButton();
        t_kodekriteria = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        t_namakriteria = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        t_bobot = new javax.swing.JTextField();
        c_atribut = new javax.swing.JComboBox<>();

        setLayout(new java.awt.CardLayout());

        pn_menukriteria.setBackground(new java.awt.Color(255, 255, 255));
        pn_menukriteria.setLayout(new java.awt.CardLayout());

        pn_kriteria.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Master > Kriteria");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/kriteria.png"))); // NOI18N

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Kriteria SAW");

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

        t_kriteria.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        t_kriteria.setForeground(new java.awt.Color(51, 51, 51));
        t_kriteria.setModel(new javax.swing.table.DefaultTableModel(
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
        t_kriteria.setRowHeight(30);
        t_kriteria.setRowMargin(2);
        t_kriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_kriteriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_kriteria);

        t_cari.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        t_cari.setForeground(new java.awt.Color(102, 102, 102));
        t_cari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        t_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_cariActionPerformed(evt);
            }
        });

        b_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cari.png"))); // NOI18N
        b_cari.setText("Cari");
        b_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_kriteriaLayout = new javax.swing.GroupLayout(pn_kriteria);
        pn_kriteria.setLayout(pn_kriteriaLayout);
        pn_kriteriaLayout.setHorizontalGroup(
            pn_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_kriteriaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_kriteriaLayout.createSequentialGroup()
                        .addGroup(pn_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 495, Short.MAX_VALUE)
                        .addGroup(pn_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pn_kriteriaLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn_kriteriaLayout.createSequentialGroup()
                                .addComponent(t_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b_cari))))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
        );
        pn_kriteriaLayout.setVerticalGroup(
            pn_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_kriteriaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(pn_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_cari))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        pn_menukriteria.add(pn_kriteria, "card2");

        pn_tambahdata.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Master > Kriteria");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/kriteria.png"))); // NOI18N

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Tambah Kriteria");

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

        t_kodekriteria.setBackground(new java.awt.Color(240, 238, 238));
        t_kodekriteria.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        t_kodekriteria.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

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
        jLabel36.setText("Bobot Kriteria (%)");

        jLabel37.setBackground(new java.awt.Color(0, 0, 0));
        jLabel37.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(153, 153, 153));
        jLabel37.setText("Atribut");

        t_bobot.setBackground(new java.awt.Color(240, 238, 238));
        t_bobot.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        t_bobot.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

        c_atribut.setBackground(new java.awt.Color(153, 153, 153));
        c_atribut.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        c_atribut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Benefit", "Cost" }));

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
                        .addContainerGap(952, Short.MAX_VALUE))
                    .addGroup(pn_tambahdataLayout.createSequentialGroup()
                        .addGroup(pn_tambahdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t_kodekriteria, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_tambahdataLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(t_namakriteria, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t_bobot)
                            .addComponent(c_atribut, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(5, 5, 5)
                .addComponent(t_kodekriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(t_namakriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(t_bobot, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(c_atribut, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(451, Short.MAX_VALUE))
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
         
            kosong();
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
        pn_menukriteria.add(pn_kriteria);
        pn_menukriteria.repaint();
        pn_menukriteria.revalidate();
        Riset();
        datatabel();
    }//GEN-LAST:event_b_batal1ActionPerformed

    private void t_kriteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_kriteriaMouseClicked
             if (b_tambah.getText().equals("TAMBAH")){
            b_tambah.setText("EDIT");
            b_hapus.setVisible(true);
            b_batal.setVisible(true);
            b_tambah.setBackground(new Color(40, 167, 69));
         
            
            //SELECT DATA
         int bar = t_kriteria.getSelectedRow();
             if (bar != -1) {
           
            String a = tabmode.getValueAt(bar, 0).toString();
            String b = tabmode.getValueAt(bar, 1).toString();
            String c = tabmode.getValueAt(bar, 2).toString();
            String d = tabmode.getValueAt(bar, 3).toString();
   

            t_kodekriteria.setText(a);
            t_namakriteria.setText(b);
            t_bobot.setText(c);
            c_atribut.setSelectedItem(d);
            
             } else {
            System.out.println("No row selected.");
            JOptionPane.showMessageDialog(null, "Please select a row to edit.");
        }
    }
         
    }//GEN-LAST:event_t_kriteriaMouseClicked

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

         datatabel();
         kosong();
     } else if(b_tambah.getText().equals("EDIT")){
         edit();
     }   
    }//GEN-LAST:event_b_simpanActionPerformed

    private void b_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_hapusActionPerformed
       hapus();
    }//GEN-LAST:event_b_hapusActionPerformed

    private void b_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cariActionPerformed
      Object [] Baris = {"Kode Kriteria","Nama Kriteria","Bobot","Atribut"};
    tabmode = new DefaultTableModel(null, Baris);
    t_kriteria.setModel(tabmode);

    // Dapatkan istilah pencarian dari kolom teks
        String searchTerm = t_cari.getText();

    
    String sql = "SELECT * FROM kriteria WHERE kode_kriteria LIKE ? "
            + "OR nama_kriteria LIKE ?"
            + "OR atribut LIKE ?"
            + "OR CAST(bobot AS CHAR) LIKE ?";
    try {
        PreparedStatement stat = conn.prepareStatement(sql);
        String wildcardTerm = "%" + searchTerm + "%";
        stat.setString(1, wildcardTerm); // For kode kriteria
        stat.setString(2, wildcardTerm); // For nama kriteria
        stat.setString(3, wildcardTerm);  
        stat.setString(4, wildcardTerm);
        ResultSet hasil = stat.executeQuery();

        // Hapus hasil sebelumnya
        tabmode.setRowCount(0);

        //Isi tabel dengan hasil
        boolean hasResults = false;
        while (hasil.next()) {
            hasResults = true;
                String a = hasil.getString("kode_kriteria");
                String b = hasil.getString("nama_kriteria");
                String c = hasil.getString("bobot");
                String d = hasil.getString("atribut");
              
                String[] data={a,b,c,d};
                tabmode.addRow(data);
        }

        if (!hasResults) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }

    }//GEN-LAST:event_b_cariActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componen_tambahan.RoundButton b_batal;
    private componen_tambahan.RoundButton b_batal1;
    private javax.swing.JButton b_cari;
    private componen_tambahan.RoundButton b_hapus;
    private componen_tambahan.RoundButton b_simpan;
    private componen_tambahan.RoundButton b_tambah;
    private javax.swing.JComboBox<String> c_atribut;
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
    private javax.swing.JPanel pn_kriteria;
    private javax.swing.JPanel pn_menukriteria;
    private javax.swing.JPanel pn_tambahdata;
    private javax.swing.JTextField t_bobot;
    private javax.swing.JTextField t_cari;
    private javax.swing.JTextField t_kodekriteria;
    private javax.swing.JTable t_kriteria;
    private javax.swing.JTextField t_namakriteria;
    // End of variables declaration//GEN-END:variables

    protected void warna_header(){
              t_kriteria.setShowVerticalLines(true);
              t_kriteria.setGridColor(java.awt.Color.GRAY);
          // Ubah warna header 
        javax.swing.table.JTableHeader header = t_kriteria.getTableHeader();
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
    
}

