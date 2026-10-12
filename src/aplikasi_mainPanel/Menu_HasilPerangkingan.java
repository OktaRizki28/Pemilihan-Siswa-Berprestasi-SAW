/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_mainPanel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author admin
 */
public class Menu_HasilPerangkingan extends javax.swing.JPanel {

     private Connection conn = new koneksi().connect();
     private DefaultTableModel tabmode;

     
    public Menu_HasilPerangkingan() {
        initComponents();
        t_hasil.setDefaultEditor(Object.class, null);
        t_keterangan.setDefaultEditor(Object.class, null);
        warna_header();
        tampilRanking();
        tampilRingkasan();
        tampilKeterangan();
        
      
    }
    
  
    

  
        
private void tampilRanking() {

    Object[] baris = {"Peringkat","NIS","Nama Siswa", "Nilai Preferensi","Keterangan"};

    tabmode = new DefaultTableModel(null, baris);
    t_hasil.setModel(tabmode);

    try {

        String sql =
        "SELECT h.ranking, s.nis, s.nama_siswa, h.nilai_preferensi " +
        "FROM hasil h " +
        "JOIN siswa s ON h.id_siswa = s.id_siswa " +
        "ORDER BY h.ranking ASC";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){

            double nilai = rs.getDouble("nilai_preferensi");

            String ket;

            if(nilai >= 0.90){
                ket = "Sangat Berprestasi";
            }else if(nilai >= 0.80){
                ket = "Berprestasi";
            }else if(nilai >= 0.70){
                ket = "Cukup";
            }else if(nilai >= 0.60){
                ket = "Kurang";
            } else {
                ket ="Sangat Kurang";
            }

            String[] data = {

                rs.getString("ranking"),
                rs.getString("nis"),
                rs.getString("nama_siswa"),
                String.format("%.4f", nilai),
                ket

            };

            tabmode.addRow(data);

        }

    } catch(Exception e){

        JOptionPane.showMessageDialog(null, e.getMessage());

    }

}
private void tampilRingkasan() {

    int jumlahSiswa = t_hasil.getRowCount();

    double nilaiMax = Double.MIN_VALUE;
    double nilaiMin = Double.MAX_VALUE;
    double total = 0;

    for (int i = 0; i < jumlahSiswa; i++) {

        double nilai = Double.parseDouble(t_hasil.getValueAt(i, 3).toString()); // kolom Nilai Preferensi

        total += nilai;

        if (nilai > nilaiMax) {
            nilaiMax = nilai;
        }

        if (nilai < nilaiMin) {
            nilaiMin = nilai;
        }
    }

    double rata = 0;

    if (jumlahSiswa > 0) {
        rata = total / jumlahSiswa;
    }

    lb1.setText(String.valueOf(jumlahSiswa));
    lb2.setText(String.format("%.4f", nilaiMax));
    lb3.setText(String.format("%.4f", nilaiMin));
    lb4.setText(String.format("%.4f", rata));
}

private void tampilKeterangan() {

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("Rentang Nilai");
    model.addColumn("Keterangan");

    model.addRow(new Object[]{"0.90 - 0.100", "Sangat Berprestasi"});
    model.addRow(new Object[]{"0.80 - 0.89", "Berprestasi"});
    model.addRow(new Object[]{"0.70 - 0.79", "Cukup"});
    model.addRow(new Object[]{"0.60 - 0.69", "Kurang"});
    model.addRow(new Object[]{"0.0 - 0.59", "Sangat Kurang"});

    t_keterangan.setModel(model);
    warna_header1();
}

 
        

        
   




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_menuPerhitungan = new javax.swing.JPanel();
        pn_perhitungan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_hasil = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        panelRound2 = new componen_tambahan.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        panelRound3 = new componen_tambahan.PanelRound();
        lb1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelRound4 = new componen_tambahan.PanelRound();
        lb2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panelRound5 = new componen_tambahan.PanelRound();
        lb3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        panelRound6 = new componen_tambahan.PanelRound();
        lb4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        panelRound7 = new componen_tambahan.PanelRound();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        t_keterangan = new javax.swing.JTable();
        b_cari = new javax.swing.JButton();
        t_cari = new javax.swing.JTextField();

        setLayout(new java.awt.CardLayout());

        pn_menuPerhitungan.setBackground(new java.awt.Color(255, 255, 255));
        pn_menuPerhitungan.setLayout(new java.awt.CardLayout());

        pn_perhitungan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("SAW > Rangking");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/hasil.png"))); // NOI18N

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Tabel Rangking Siswa Berprestasi");

        t_hasil.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        t_hasil.setForeground(new java.awt.Color(51, 51, 51));
        t_hasil.setModel(new javax.swing.table.DefaultTableModel(
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
        t_hasil.setRowHeight(26);
        t_hasil.setRowMargin(2);
        t_hasil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_hasilMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_hasil);

        jLabel5.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 0));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/info real.png"))); // NOI18N
        jLabel5.setText("Data hasil perangkingan berdasarkan perhitungan di menu perhitungan");

        panelRound2.setBackground(new java.awt.Color(245, 245, 245));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bar_chart_26px.png"))); // NOI18N
        jLabel2.setText("Ringkasan Perhitungan");

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));

        lb1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lb1.setForeground(new java.awt.Color(0, 102, 255));
        lb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb1.setText("jLabel3");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Jumlah Siswa");

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(lb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelRound4.setBackground(new java.awt.Color(255, 255, 255));
        panelRound4.setForeground(new java.awt.Color(204, 255, 204));

        lb2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lb2.setForeground(new java.awt.Color(0, 204, 0));
        lb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb2.setText("jLabel3");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 204, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Nilai Tertinggi");

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound5.setBackground(new java.awt.Color(255, 255, 255));
        panelRound5.setForeground(new java.awt.Color(255, 204, 204));

        lb3.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lb3.setForeground(new java.awt.Color(255, 51, 0));
        lb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb3.setText("jLabel3");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 51, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Nilai  Terendah");

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(lb3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound6.setBackground(new java.awt.Color(204, 204, 255));

        lb4.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lb4.setForeground(new java.awt.Color(153, 102, 255));
        lb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb4.setText("jLabel3");

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 102, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Rata-rata Nilai");

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 27, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        panelRound7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bookmark_25px.png"))); // NOI18N
        jLabel3.setText("Keterangan Nilai Preferensi");

        t_keterangan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(t_keterangan);

        javax.swing.GroupLayout panelRound7Layout = new javax.swing.GroupLayout(panelRound7);
        panelRound7.setLayout(panelRound7Layout);
        panelRound7Layout.setHorizontalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound7Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        panelRound7Layout.setVerticalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );

        b_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cari.png"))); // NOI18N
        b_cari.setText("Cari");
        b_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cariActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout pn_perhitunganLayout = new javax.swing.GroupLayout(pn_perhitungan);
        pn_perhitungan.setLayout(pn_perhitunganLayout);
        pn_perhitunganLayout.setHorizontalGroup(
            pn_perhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_perhitunganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_perhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_perhitunganLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1416, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_perhitunganLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addGroup(pn_perhitunganLayout.createSequentialGroup()
                        .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(panelRound7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(22, 22, 22))
                    .addGroup(pn_perhitunganLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(t_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_cari)
                        .addContainerGap())))
        );
        pn_perhitunganLayout.setVerticalGroup(
            pn_perhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_perhitunganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_perhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(pn_perhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(pn_perhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(pn_perhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(t_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b_cari)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_perhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound7, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_menuPerhitungan.add(pn_perhitungan, "card2");

        add(pn_menuPerhitungan, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void t_hasilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_hasilMouseClicked
      
    }//GEN-LAST:event_t_hasilMouseClicked

    private void b_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cariActionPerformed
        cariData();
    }//GEN-LAST:event_b_cariActionPerformed

    private void t_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_cariActionPerformed

    private void t_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_cariKeyPressed
        String searchTerm = t_cari.getText().trim();

        if (searchTerm.equals("")) {
            tampilRanking(); // tampilkan semua data
        } else {
            cariData(); // method pencarian
        }
    }//GEN-LAST:event_t_cariKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_cari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private componen_tambahan.PanelRound panelRound2;
    private componen_tambahan.PanelRound panelRound3;
    private componen_tambahan.PanelRound panelRound4;
    private componen_tambahan.PanelRound panelRound5;
    private componen_tambahan.PanelRound panelRound6;
    private componen_tambahan.PanelRound panelRound7;
    private javax.swing.JPanel pn_menuPerhitungan;
    private javax.swing.JPanel pn_perhitungan;
    private javax.swing.JTextField t_cari;
    private javax.swing.JTable t_hasil;
    private javax.swing.JTable t_keterangan;
    // End of variables declaration//GEN-END:variables
    
    // Header Tabel
    protected void warna_header(){
              t_hasil.setShowVerticalLines(true);
              t_hasil.setGridColor(java.awt.Color.GRAY);
          // Ubah warna header 
        javax.swing.table.JTableHeader header = t_hasil.getTableHeader();
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
        protected void warna_header1(){
              t_keterangan.setShowVerticalLines(true);
              t_keterangan.setGridColor(java.awt.Color.GRAY);
          // Ubah warna header 
        javax.swing.table.JTableHeader header = t_keterangan.getTableHeader();
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
        
   private void cariData() {

    Object[] baris = {"Peringkat","NIS","Nama Siswa","Nilai Preferensi","Keterangan"};

    tabmode = new DefaultTableModel(null, baris);
    t_hasil.setModel(tabmode);

    try {

        String sql =
        "SELECT h.ranking, s.nis, s.nama_siswa, h.nilai_preferensi " +
        "FROM hasil h " +
        "JOIN siswa s ON h.id_siswa = s.id_siswa " +
        "WHERE h.ranking LIKE ? " +
        "OR s.nis LIKE ? " +
        "OR s.nama_siswa LIKE ? " +
        "ORDER BY h.ranking ASC";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, "%" + t_cari.getText() + "%");
        ps.setString(2, "%" + t_cari.getText() + "%");
        ps.setString(3, "%" + t_cari.getText() + "%");

        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            double nilai = rs.getDouble("nilai_preferensi");

            String ket;

            if(nilai >= 0.90){
                ket = "Sangat Berprestasi";
            }else if(nilai >= 0.80){
                ket = "Berprestasi";
            }else if(nilai >= 0.70){
                ket = "Cukup";
            }else if(nilai >= 0.60){
                ket = "Kurang";
            }else{
                ket = "Sangat Kurang";
            }

            String[] data = {
                rs.getString("ranking"),
                rs.getString("nis"),
                rs.getString("nama_siswa"),
                String.format("%.4f", nilai),
                ket
            };

            tabmode.addRow(data);
        }

    } catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
    
    
}



