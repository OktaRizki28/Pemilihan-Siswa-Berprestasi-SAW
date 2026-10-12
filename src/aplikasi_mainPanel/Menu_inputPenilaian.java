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
public class Menu_inputPenilaian extends javax.swing.JPanel {

     private Connection conn = new koneksi().connect();
     private DefaultTableModel tabmode;
     private int id_penilaian;
    
    /**
     * Creates new form Menu_kriteria
     */
    public Menu_inputPenilaian() {
        initComponents();
        t_penilaian.setDefaultEditor(Object.class, null);
        warna_header();
        datatabel();
        isiComboBox();
        Sikap();
        Keaktifan();
        Riset();
       t_nama.setEnabled(false);
        b_hapus.setVisible(false);
        b_batal.setVisible(false);
    }
    
        protected void Riset(){
        b_hapus.setVisible(false);
        b_batal.setVisible(false);
        b_tambah.setText("TAMBAH");
        b_tambah.setBackground(new Color (0,112,192));
    }
    
        private void kosong() {
            id_penilaian = 0;
            t_akademik.setText("");
            t_absensi.setText("");
            c_sikap.setSelectedItem("");
            c_keaktifan.setSelectedItem("");
            t_nonAkademik.setText("");
        
        }
        
        
        // Tabel Penilaian
       private void datatabel() {

           Object[] Baris = {
               "NO",
               "NIS",
               "Nama Siswa",
               "Akademik",
               "Absensi(%)",
               "Sikap",
               "Keaktifan",
               "Non Akademik"
           };

           tabmode = new DefaultTableModel(null, Baris);
           t_penilaian.setModel(tabmode);

           try {

               String sql =
               "SELECT s.nis, s.nama_siswa, " +
               "MAX(CASE WHEN p.id_kriteria=1 THEN p.nilai END) AS Akademik, " +
               "MAX(CASE WHEN p.id_kriteria=2 THEN p.nilai END) AS Absensi, " +
               "MAX(CASE WHEN p.id_kriteria=3 THEN p.nilai END) AS Sikap, " +
               "MAX(CASE WHEN p.id_kriteria=4 THEN p.nilai END) AS Keaktifan, " +
               "MAX(CASE WHEN p.id_kriteria=5 THEN p.nilai END) AS NonAkademik " +
               "FROM penilaian p " +
               "JOIN siswa s ON p.id_siswa = s.id_siswa " +
               "GROUP BY s.id_siswa " +
               "ORDER BY s.nis";

               Statement st = conn.createStatement();
               ResultSet rs = st.executeQuery(sql);

               int no = 1;

               while (rs.next()) {

                   String[] data = {
                       String.valueOf(no++),
                       rs.getString("nis"),
                       rs.getString("nama_siswa"),
                       rs.getString("Akademik"),
                       rs.getString("Absensi"),
                       rs.getString("Sikap"),
                       rs.getString("Keaktifan"),
                       rs.getString("NonAkademik")
                   };

                   tabmode.addRow(data);
               }

           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, e.getMessage());
           }
       }
    
  protected void tambah() {
    try {

        int idSiswa = ambilIdSiswa();

        String cek = "SELECT * FROM penilaian WHERE id_siswa=?";

        PreparedStatement psCek = conn.prepareStatement(cek);
        psCek.setInt(1, idSiswa);

        ResultSet rsCek = psCek.executeQuery();

        if (rsCek.next()) {
            JOptionPane.showMessageDialog(null, "Siswa sudah dinilai!");
            return;
        }

        String[] nilai = {
            t_akademik.getText(),
            t_absensi.getText(),
            c_sikap.getSelectedItem().toString(),
            c_keaktifan.getSelectedItem().toString(),
            t_nonAkademik.getText()
        };

        String sql = "INSERT INTO penilaian(id_siswa,id_kriteria,nilai) VALUES(?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        for (int i = 0; i < nilai.length; i++) {

            ps.setInt(1, idSiswa);
            ps.setInt(2, i + 1);
            ps.setString(3, nilai[i]);

            ps.executeUpdate();
        }

        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");

        datatabel();
        kosong();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(null, "Gagal : " + e.getMessage());

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

                   int idSiswa = ambilIdSiswa();

                   String sql ="DELETE FROM penilaian WHERE id_siswa=?";

                   PreparedStatement stat =conn.prepareStatement(sql);

                   stat.setInt(1, idSiswa);

                   stat.executeUpdate();

                   JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

                   kosong();
                   datatabel();
               }

           } catch (Exception e) {JOptionPane.showMessageDialog(null,e.getMessage());
           }
}
 // Edit Data
    protected void edit() {
        try {

            int idSiswa = ambilIdSiswa();

            // Nilai asli
            String[] nilai = {
                t_akademik.getText(),
                t_absensi.getText(),
                c_sikap.getSelectedItem().toString(),
                c_keaktifan.getSelectedItem().toString(),
                t_nonAkademik.getText()
            };

            String sql = "UPDATE penilaian SET nilai=? WHERE id_siswa=? AND id_kriteria=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i = 0; i < nilai.length; i++) {

                ps.setString(1, nilai[i]);
                ps.setInt(2, idSiswa);
                ps.setInt(3, i + 1);

                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Data berhasil diubah");

            kosong();
            datatabel();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_menuPenilaian = new javax.swing.JPanel();
        pn_penilaian = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        b_tambah = new componen_tambahan.RoundButton();
        b_hapus = new componen_tambahan.RoundButton();
        b_batal = new componen_tambahan.RoundButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_penilaian = new javax.swing.JTable();
        t_cari = new javax.swing.JTextField();
        b_cari = new javax.swing.JButton();
        pn_tambahdata = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        b_simpan = new componen_tambahan.RoundButton();
        b_batal1 = new componen_tambahan.RoundButton();
        jLabel34 = new javax.swing.JLabel();
        t_nama = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        c_nis = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        t_akademik = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        t_nonAkademik = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        c_sikap = new javax.swing.JComboBox<>();
        c_keaktifan = new javax.swing.JComboBox<>();
        t_absensi = new javax.swing.JTextField();

        setLayout(new java.awt.CardLayout());

        pn_menuPenilaian.setBackground(new java.awt.Color(255, 255, 255));
        pn_menuPenilaian.setLayout(new java.awt.CardLayout());

        pn_penilaian.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("SAW > Penilaian");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Penilian.png"))); // NOI18N

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Penilaian");

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

        t_penilaian.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        t_penilaian.setForeground(new java.awt.Color(51, 51, 51));
        t_penilaian.setModel(new javax.swing.table.DefaultTableModel(
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
        t_penilaian.setRowHeight(26);
        t_penilaian.setRowMargin(2);
        t_penilaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_penilaianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_penilaian);

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

        javax.swing.GroupLayout pn_penilaianLayout = new javax.swing.GroupLayout(pn_penilaian);
        pn_penilaian.setLayout(pn_penilaianLayout);
        pn_penilaianLayout.setHorizontalGroup(
            pn_penilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_penilaianLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_penilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_penilaianLayout.createSequentialGroup()
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_penilaianLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        pn_penilaianLayout.setVerticalGroup(
            pn_penilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_penilaianLayout.createSequentialGroup()
                .addGroup(pn_penilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_penilaianLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pn_penilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(pn_penilaianLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(pn_penilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_cari))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pn_menuPenilaian.add(pn_penilaian, "card2");

        pn_tambahdata.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("SAW > Penilaian");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Penilian.png"))); // NOI18N

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Tambah Penilaian");

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
        jLabel34.setText("NIS ");

        t_nama.setBackground(new java.awt.Color(240, 238, 238));
        t_nama.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        t_nama.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

        jLabel35.setBackground(new java.awt.Color(0, 0, 0));
        jLabel35.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(153, 153, 153));
        jLabel35.setText("Nama Siswa");

        c_nis.setBackground(new java.awt.Color(153, 153, 153));
        c_nis.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        c_nis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_nisActionPerformed(evt);
            }
        });

        jLabel38.setBackground(new java.awt.Color(0, 0, 0));
        jLabel38.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(153, 153, 153));
        jLabel38.setText("Nilai Akademik (C1)");

        t_akademik.setBackground(new java.awt.Color(240, 238, 238));
        t_akademik.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        t_akademik.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

        jLabel39.setBackground(new java.awt.Color(0, 0, 0));
        jLabel39.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(153, 153, 153));
        jLabel39.setText("Absensi (C2)");

        jLabel40.setBackground(new java.awt.Color(0, 0, 0));
        jLabel40.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(153, 153, 153));
        jLabel40.setText("Sikap (C3)");

        jLabel41.setBackground(new java.awt.Color(0, 0, 0));
        jLabel41.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(153, 153, 153));
        jLabel41.setText("Keaktifan (C4)");

        t_nonAkademik.setBackground(new java.awt.Color(240, 238, 238));
        t_nonAkademik.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        t_nonAkademik.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        t_nonAkademik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_nonAkademikActionPerformed(evt);
            }
        });

        jLabel42.setBackground(new java.awt.Color(0, 0, 0));
        jLabel42.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(153, 153, 153));
        jLabel42.setText("Nilai Non Akademik (C5)");

        c_sikap.setBackground(new java.awt.Color(153, 153, 153));
        c_sikap.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        c_sikap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_sikapActionPerformed(evt);
            }
        });

        c_keaktifan.setBackground(new java.awt.Color(153, 153, 153));
        c_keaktifan.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        c_keaktifan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_keaktifanActionPerformed(evt);
            }
        });

        t_absensi.setBackground(new java.awt.Color(240, 238, 238));
        t_absensi.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        t_absensi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

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
                    .addGroup(pn_tambahdataLayout.createSequentialGroup()
                        .addGroup(pn_tambahdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c_nis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_tambahdataLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(t_nama, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t_akademik)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t_nonAkademik)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(c_sikap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(c_keaktifan, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t_absensi))
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
                .addComponent(c_nis, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(t_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(t_akademik, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(t_absensi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(c_sikap, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(c_keaktifan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(t_nonAkademik, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pn_menuPenilaian.add(pn_tambahdata, "card2");

        add(pn_menuPenilaian, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void b_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_tambahActionPerformed
        if(b_tambah.getText().equals("TAMBAH")){
            pn_menuPenilaian.removeAll();
            pn_menuPenilaian.add(pn_tambahdata);
            pn_menuPenilaian.repaint();
            pn_menuPenilaian.revalidate();
            b_simpan.setText("SIMPAN");
            
        
            isiComboBox();
        } else if(b_tambah.getText().equals("EDIT")){
            pn_menuPenilaian.removeAll();
            pn_menuPenilaian.add(pn_tambahdata);
            pn_menuPenilaian.repaint();
            pn_menuPenilaian.revalidate();
            b_simpan.setText("SIMPAN");
           
        }
        
    }//GEN-LAST:event_b_tambahActionPerformed

    private void b_batal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_batal1ActionPerformed
        pn_menuPenilaian.removeAll();
        pn_menuPenilaian.add(pn_penilaian);
        pn_menuPenilaian.repaint();
        pn_menuPenilaian.revalidate();
        Riset();
        datatabel();
    }//GEN-LAST:event_b_batal1ActionPerformed

    private void t_penilaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_penilaianMouseClicked
            if (b_tambah.getText().equals("TAMBAH")) {
             b_tambah.setText("EDIT");
             b_hapus.setVisible(true);
             b_batal.setVisible(true);
             b_tambah.setBackground(new Color(40, 167, 69));
            }

             int bar = t_penilaian.getSelectedRow();

             if (bar != -1) {

                    String nis = tabmode.getValueAt(bar, 1).toString();
                    String nama = tabmode.getValueAt(bar, 2).toString();
                    String c1 = tabmode.getValueAt(bar, 3).toString();
                    String c2 = tabmode.getValueAt(bar, 4).toString();
                    String c3 = tabmode.getValueAt(bar, 5).toString();
                    String c4 = tabmode.getValueAt(bar, 6).toString();
                    String c5 = tabmode.getValueAt(bar, 7).toString();

                    c_nis.setSelectedItem(nis);
                    t_nama.setText(nama);
                    t_akademik.setText(c1);
                    t_absensi.setText(c2);
                    c_sikap.setSelectedItem(c3);
                    c_keaktifan.setSelectedItem(c4);
                    t_nonAkademik.setText(c5);

             } else {
                 JOptionPane.showMessageDialog(null, "Pilih data yang akan diedit");
             }
         
    }//GEN-LAST:event_t_penilaianMouseClicked

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
         kosong();
     } else if(b_tambah.getText().equals("EDIT")){
         edit();
     }   
    }//GEN-LAST:event_b_simpanActionPerformed

    private void b_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_hapusActionPerformed
       hapus();
    }//GEN-LAST:event_b_hapusActionPerformed

    private void b_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cariActionPerformed
     Object [] Baris = { "NO","NIS","Nama Siswa","Akademik","Absensi(%)","Sikap","Keaktifan","Non Akademik"};
     tabmode = new DefaultTableModel(null, Baris);
     t_penilaian.setModel(tabmode);

    // Dapatkan istilah pencarian dari kolom teks
        String searchTerm = t_cari.getText();

    String sql =
                "SELECT s.nis, s.nama_siswa, " +
                "MAX(CASE WHEN p.id_kriteria=1 THEN p.nilai END) AS C1, " +
                "MAX(CASE WHEN p.id_kriteria=2 THEN p.nilai END) AS C2, " +
                "MAX(CASE WHEN p.id_kriteria=3 THEN p.nilai END) AS C3, " +
                "MAX(CASE WHEN p.id_kriteria=4 THEN p.nilai END) AS C4, " +
                "MAX(CASE WHEN p.id_kriteria=5 THEN p.nilai END) AS C5 " +
                "FROM penilaian p " +
                "JOIN siswa s ON p.id_siswa=s.id_siswa " +
                "WHERE s.nis LIKE ? " +
                "OR s.nama_siswa LIKE ? " +
                "OR s.kelas LIKE ? " +
                "GROUP BY s.id_siswa";
    
    try {
        PreparedStatement stat = conn.prepareStatement(sql);
        String wildcardTerm = "%" + searchTerm + "%";
        stat.setString(1, wildcardTerm); // For kode 
        stat.setString(2, wildcardTerm); // For nama 
        stat.setString(3, wildcardTerm);  
        
        ResultSet hasil = stat.executeQuery();

        // Hapus hasil sebelumnya
        tabmode.setRowCount(0);

        //Isi tabel dengan hasil
        boolean hasResults = false;
        int no = 1;
        while (hasil.next()) {
            hasResults = true;
            String a = hasil.getString("nis");
            String b = hasil.getString("nama_siswa");
            String c1 = hasil.getString("C1");
            String c2 = hasil.getString("C2");
            String c3 = hasil.getString("C3");
            String c4 = hasil.getString("C4");
            String c5 = hasil.getString("C5");

            String[] data = {String.valueOf(no++),a,b,c1,c2,c3,c4,c5};
            tabmode.addRow(data);
        }

        if (!hasResults) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }

    }//GEN-LAST:event_b_cariActionPerformed

    private void c_nisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_nisActionPerformed
    String kode = (String) c_nis.getSelectedItem();
    if (kode != null && !kode.isEmpty()) {
        isiNamaSiswa(kode);
    } else {
        t_nama.setText("");
    }

    }//GEN-LAST:event_c_nisActionPerformed

    private void c_sikapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_sikapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_sikapActionPerformed

    private void c_keaktifanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_keaktifanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_keaktifanActionPerformed

    private void t_nonAkademikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_nonAkademikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_nonAkademikActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componen_tambahan.RoundButton b_batal;
    private componen_tambahan.RoundButton b_batal1;
    private javax.swing.JButton b_cari;
    private componen_tambahan.RoundButton b_hapus;
    private componen_tambahan.RoundButton b_simpan;
    private componen_tambahan.RoundButton b_tambah;
    private javax.swing.JComboBox<String> c_keaktifan;
    private javax.swing.JComboBox<String> c_nis;
    private javax.swing.JComboBox<String> c_sikap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pn_menuPenilaian;
    private javax.swing.JPanel pn_penilaian;
    private javax.swing.JPanel pn_tambahdata;
    private javax.swing.JTextField t_absensi;
    private javax.swing.JTextField t_akademik;
    private javax.swing.JTextField t_cari;
    private javax.swing.JTextField t_nama;
    private javax.swing.JTextField t_nonAkademik;
    private javax.swing.JTable t_penilaian;
    // End of variables declaration//GEN-END:variables

    
    // Header Tabel
    protected void warna_header(){
              t_penilaian.setShowVerticalLines(true);
              t_penilaian.setGridColor(java.awt.Color.GRAY);
          // Ubah warna header 
        javax.swing.table.JTableHeader header = t_penilaian.getTableHeader();
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

        String query = "SELECT nis, nama_siswa, kelas FROM siswa";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        c_nis.removeAllItems();
        c_nis.addItem("");

        while (rs.next()) {
            c_nis.addItem(rs.getString("nis"));
        }

        rs.close();
        pst.close();
        conn.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
                "Gagal isi combo box: " + e.getMessage());
    }
}
     //mengisi nama siswa   
    private void isiNamaSiswa(String kode) {
    try {
        Connection conn = new koneksi().connect();

        String query = "SELECT nama_siswa FROM siswa WHERE nis = ?";

        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, kode);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            t_nama.setText(rs.getString("nama_siswa"));
        } else {
            t_nama.setText("");
        }

        rs.close();
        pst.close();
        conn.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
                "Gagal mengambil nama siswa: " + e.getMessage());
    }
}

    //mengkonversi txt sikap
    private void Sikap() {
        try {
            c_sikap.removeAllItems();

            String sql = "SELECT keterangan FROM sub_kriteria WHERE id_kriteria = 3";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                c_sikap.addItem(rs.getString("keterangan"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    //mengkonversi txt keaktifan
    private void Keaktifan() {
        try {
            c_keaktifan.removeAllItems();

            String sql = "SELECT keterangan FROM sub_kriteria WHERE id_kriteria = 4";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                c_keaktifan.addItem(rs.getString("keterangan"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    

            // untuk tabel clicked
            private String getKeteranganSubKriteria(int nilai, int idKriteria){

            String ket = "";

            try {

                String sql =
                "SELECT keterangan FROM sub_kriteria "
                + "WHERE id_kriteria=? AND nilai=?";

                PreparedStatement ps =
                conn.prepareStatement(sql);

                ps.setInt(1, idKriteria);
                ps.setInt(2, nilai);

                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    ket = rs.getString("keterangan");
                }

            } catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }

            return ket;
    }
    private int ambilIdSiswa() {

    int idSiswa = 0;

    try {

        String sql =
        "SELECT id_siswa FROM siswa WHERE nis=?";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        ps.setString(1,
        c_nis.getSelectedItem().toString());

        ResultSet rs =
        ps.executeQuery();

        if(rs.next()){
            idSiswa = rs.getInt("id_siswa");
        }

    } catch(Exception e){
        JOptionPane.showMessageDialog(null,e.getMessage());
    }

    return idSiswa;
}
}

