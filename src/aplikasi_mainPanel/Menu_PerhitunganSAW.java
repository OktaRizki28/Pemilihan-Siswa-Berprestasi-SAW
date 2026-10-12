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
public class Menu_PerhitunganSAW extends javax.swing.JPanel {

     private Connection conn = new koneksi().connect();
     private DefaultTableModel tabmode;

     
    public Menu_PerhitunganSAW() {
        initComponents();
        t_keputusan.setDefaultEditor(Object.class, null);
        t_normalisasi.setDefaultEditor(Object.class, null);
        warna_header();
        kosong();
       tampilBobot();
    }
    
    private void kosong(){
        lb_info.setText("");
        lb_info2.setText("");
        lb_info3.setText("");
        
        lb_info.setVisible(false);
        lb_info2.setVisible(false);
        lb_info3.setVisible(false);
    }
    
    private void aktif(){
        lb_info.setVisible(true);
        lb_info2.setVisible(true);
        lb_info3.setVisible(true);
        
        // Tampilkan informasi proses
        lb_info.setText("Perhitungan selesai <3");
        lb_info2.setText("Jumlah siswa : " + t_keputusan.getRowCount());
        SimpleDateFormat sdf =new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("id", "ID"));
        lb_info3.setText("Tanggal : " + sdf.format(new Date()));
 
    }
        

        
 private void tampilMatriksKeputusan() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("No");
        model.addColumn("NIS");
        model.addColumn("Nama");
        model.addColumn("C1");
        model.addColumn("C2");
        model.addColumn("C3");
        model.addColumn("C4");
        model.addColumn("C5");

        try {

            String sql =
            "SELECT s.id_siswa,s.nis,s.nama_siswa," +
            "MAX(CASE WHEN p.id_kriteria=1 THEN p.nilai END) AS C1," +
            "MAX(CASE WHEN p.id_kriteria=2 THEN p.nilai END) AS C2," +
            "MAX(CASE WHEN p.id_kriteria=3 THEN p.nilai END) AS C3," +
            "MAX(CASE WHEN p.id_kriteria=4 THEN p.nilai END) AS C4," +
            "MAX(CASE WHEN p.id_kriteria=5 THEN p.nilai END) AS C5 " +
            "FROM penilaian p " +
            "JOIN siswa s ON s.id_siswa=p.id_siswa " +
            "GROUP BY s.id_siswa";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            int no = 1;

            while (rs.next()) {

                // Nilai asli
                String akademik = rs.getString("C1");
                String absensi = rs.getString("C2");
                String sikap = rs.getString("C3");
                String keaktifan = rs.getString("C4");
                String nonAkademik = rs.getString("C5");

                // Konversi ke bobot
                int c1 = konversiAkademik(Integer.parseInt(akademik));
                int c2 = konversiAbsensi(Integer.parseInt(absensi));
                int c3 = getNilaiSubKriteria(sikap);
                int c4 = getNilaiSubKriteria(keaktifan);
                int c5 = konversiNonAkademik(Integer.parseInt(nonAkademik));

                model.addRow(new Object[]{

                    no++,
                    rs.getString("nis"),
                    rs.getString("nama_siswa"),
                    c1,
                    c2,
                    c3,
                    c4,
                    c5

                });

            }

            t_keputusan.setModel(model);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

}
    
    private void hitungNormalisasi(){

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("No");
        model.addColumn("NIS");
        model.addColumn("Nama");
        model.addColumn("C1");
        model.addColumn("C2");
        model.addColumn("C3");
        model.addColumn("C4");
        model.addColumn("C5");

        try{

            int row = t_keputusan.getRowCount();

            double maxC1=0;
            double maxC2=0;
            double maxC3=0;
            double maxC4=0;
            double maxC5=0;

            // mencari nilai maksimum

            for(int i=0;i<row;i++){

                maxC1=Math.max(maxC1,
                        Double.parseDouble(t_keputusan.getValueAt(i,3).toString()));

                maxC2=Math.max(maxC2,
                        Double.parseDouble(t_keputusan.getValueAt(i,4).toString()));

                maxC3=Math.max(maxC3,
                        Double.parseDouble(t_keputusan.getValueAt(i,5).toString()));

                maxC4=Math.max(maxC4,
                        Double.parseDouble(t_keputusan.getValueAt(i,6).toString()));

                maxC5=Math.max(maxC5,
                        Double.parseDouble(t_keputusan.getValueAt(i,7).toString()));

            }

            for(int i=0;i<row;i++){

                double c1=Double.parseDouble(
                        t_keputusan.getValueAt(i,3).toString())/maxC1;

                double c2=Double.parseDouble(
                        t_keputusan.getValueAt(i,4).toString())/maxC2;

                double c3=Double.parseDouble(
                        t_keputusan.getValueAt(i,5).toString())/maxC3;

                double c4=Double.parseDouble(
                        t_keputusan.getValueAt(i,6).toString())/maxC4;

                double c5=Double.parseDouble(
                        t_keputusan.getValueAt(i,7).toString())/maxC5;

            model.addRow(new Object[]{i+1,t_keputusan.getValueAt(i,1),t_keputusan.getValueAt(i,2),c1,c2,c3,c4,c5});

            }

            t_normalisasi.setModel(model);

        }catch(Exception e){

            JOptionPane.showMessageDialog(null,e.getMessage());

    }

}
        
    private void hitungRanking() {

        try {

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM hasil ORDER BY nilai_preferensi DESC");

            int ranking = 1;

            while (rs.next()) {

                PreparedStatement ps = conn.prepareStatement("UPDATE hasil SET ranking=? WHERE id_hasil=?");

                ps.setInt(1, ranking++);
                ps.setInt(2, rs.getInt("id_hasil"));

                ps.executeUpdate();

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

}
        
   private void hitungPreferensi() {

    try {

        // Hapus hasil lama
        Statement st = conn.createStatement();
        st.executeUpdate("DELETE FROM hasil");

        // Ambil bobot dari tabel kriteria
        double[] bobot = ambilBobot();

        for (int i = 0; i < t_normalisasi.getRowCount(); i++) {

            String nis = t_normalisasi.getValueAt(i, 1).toString();

            int idSiswa = getIdSiswa(nis);

            double[] nilai = new double[5];

            nilai[0] = Double.parseDouble(t_normalisasi.getValueAt(i, 3).toString());
            nilai[1] = Double.parseDouble(t_normalisasi.getValueAt(i, 4).toString());
            nilai[2] = Double.parseDouble(t_normalisasi.getValueAt(i, 5).toString());
            nilai[3] = Double.parseDouble(t_normalisasi.getValueAt(i, 6).toString());
            nilai[4] = Double.parseDouble(t_normalisasi.getValueAt(i, 7).toString());

            double preferensi = 0;

            for (int j = 0; j < bobot.length; j++) {

                preferensi += nilai[j] * bobot[j];

            }

            String sql ="INSERT INTO hasil(id_siswa, nilai_preferensi, ranking) VALUES(?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idSiswa);
            ps.setDouble(2, preferensi);
            ps.setInt(3, 0); 
            ps.executeUpdate();

        }

        hitungRanking();

        JOptionPane.showMessageDialog(null,"Perhitungan SAW Berhasil");

    } catch (Exception e) {

        JOptionPane.showMessageDialog(null, e.getMessage());

    }

}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_menuPerhitungan = new javax.swing.JPanel();
        pn_perhitungan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        b_tambah = new componen_tambahan.RoundButton();
        pn_keputusan = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_keputusan = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pn_normalisasi = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        t_normalisasi = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        pn_komponen = new javax.swing.JPanel();
        panelRound1 = new componen_tambahan.PanelRound();
        lb_info = new javax.swing.JLabel();
        lb_info2 = new javax.swing.JLabel();
        lb_info3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelRound2 = new componen_tambahan.PanelRound();
        lb1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panelRound3 = new componen_tambahan.PanelRound();
        jLabel10 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        panelRound4 = new componen_tambahan.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        lb3 = new javax.swing.JLabel();
        panelRound5 = new componen_tambahan.PanelRound();
        jLabel8 = new javax.swing.JLabel();
        lb4 = new javax.swing.JLabel();
        panelRound6 = new componen_tambahan.PanelRound();
        jLabel11 = new javax.swing.JLabel();
        lb5 = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        pn_menuPerhitungan.setBackground(new java.awt.Color(255, 255, 255));
        pn_menuPerhitungan.setLayout(new java.awt.CardLayout());

        pn_perhitungan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("SAW > Perhitungan SAW");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/perhitungan.png"))); // NOI18N

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Perhitungan SAW");

        b_tambah.setBackground(new java.awt.Color(0, 112, 192));
        b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/proses.png"))); // NOI18N
        b_tambah.setText("PROSES PERHITUNGAN SAW");
        b_tambah.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        b_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_tambahActionPerformed(evt);
            }
        });

        pn_keputusan.setBackground(new java.awt.Color(255, 255, 255));

        t_keputusan.setForeground(new java.awt.Color(51, 51, 51));
        t_keputusan.setModel(new javax.swing.table.DefaultTableModel(
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
        t_keputusan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_keputusanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_keputusan);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 255));
        jLabel2.setText("Matriks Keputusan (X)");

        jLabel5.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 0));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/info real.png"))); // NOI18N
        jLabel5.setText("Data diambil dari menu input penilaian (skala 1-5)");

        javax.swing.GroupLayout pn_keputusanLayout = new javax.swing.GroupLayout(pn_keputusan);
        pn_keputusan.setLayout(pn_keputusanLayout);
        pn_keputusanLayout.setHorizontalGroup(
            pn_keputusanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1406, Short.MAX_VALUE)
            .addGroup(pn_keputusanLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pn_keputusanLayout.setVerticalGroup(
            pn_keputusanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_keputusanLayout.createSequentialGroup()
                .addGroup(pn_keputusanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(pn_keputusanLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_normalisasi.setBackground(new java.awt.Color(255, 255, 255));
        pn_normalisasi.setPreferredSize(new java.awt.Dimension(452, 306));

        t_normalisasi.setForeground(new java.awt.Color(51, 51, 51));
        t_normalisasi.setModel(new javax.swing.table.DefaultTableModel(
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
        t_normalisasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_normalisasiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(t_normalisasi);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 255));
        jLabel3.setText("Matriks Normalisasi (R)");

        javax.swing.GroupLayout pn_normalisasiLayout = new javax.swing.GroupLayout(pn_normalisasi);
        pn_normalisasi.setLayout(pn_normalisasiLayout);
        pn_normalisasiLayout.setHorizontalGroup(
            pn_normalisasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1406, Short.MAX_VALUE)
            .addGroup(pn_normalisasiLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pn_normalisasiLayout.setVerticalGroup(
            pn_normalisasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_normalisasiLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pn_komponen.setBackground(new java.awt.Color(245, 245, 245));
        pn_komponen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        panelRound1.setBackground(new java.awt.Color(0, 131, 231));

        lb_info.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lb_info.setForeground(new java.awt.Color(255, 255, 255));
        lb_info.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_info.setText("jLabel5");

        lb_info2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lb_info2.setForeground(new java.awt.Color(255, 255, 255));
        lb_info2.setText("jLabel5");

        lb_info3.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lb_info3.setForeground(new java.awt.Color(255, 255, 255));
        lb_info3.setText("jLabel5");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_info2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_info, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(lb_info3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(lb_info, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lb_info2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lb_info3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 153, 255));
        jLabel7.setText("Bobot Kriteria");

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));

        lb1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lb1.setForeground(new java.awt.Color(51, 153, 255));
        lb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb1.setText("jLabel8");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Nilai Akademik (C1)");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(lb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Absensi (C2)");

        lb2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lb2.setForeground(new java.awt.Color(51, 153, 255));
        lb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb2.setText("jLabel10");

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(lb2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        panelRound4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Sikap (C3)");

        lb3.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lb3.setForeground(new java.awt.Color(51, 153, 255));
        lb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb3.setText("jLabel12");
        lb3.setToolTipText("");

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb3, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        panelRound5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Keaktifan (C4)");

        lb4.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lb4.setForeground(new java.awt.Color(51, 153, 255));
        lb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb4.setText("jLabel8");

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(lb4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        panelRound6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Nilai Non-Akademik (C5)");

        lb5.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lb5.setForeground(new java.awt.Color(51, 153, 255));
        lb5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb5.setText("jLabel11");

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(lb5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout pn_komponenLayout = new javax.swing.GroupLayout(pn_komponen);
        pn_komponen.setLayout(pn_komponenLayout);
        pn_komponenLayout.setHorizontalGroup(
            pn_komponenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_komponenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_komponenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pn_komponenLayout.setVerticalGroup(
            pn_komponenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_komponenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_komponenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_komponenLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(4, 4, 4)
                        .addGroup(pn_komponenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelRound6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout pn_perhitunganLayout = new javax.swing.GroupLayout(pn_perhitungan);
        pn_perhitungan.setLayout(pn_perhitunganLayout);
        pn_perhitunganLayout.setHorizontalGroup(
            pn_perhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_perhitunganLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_perhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_komponen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pn_normalisasi, javax.swing.GroupLayout.DEFAULT_SIZE, 1406, Short.MAX_VALUE)
                    .addComponent(pn_keputusan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_perhitunganLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(pn_perhitunganLayout.createSequentialGroup()
                        .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        pn_perhitunganLayout.setVerticalGroup(
            pn_perhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_perhitunganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_perhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(12, 12, 12)
                .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pn_keputusan, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pn_normalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(pn_komponen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pn_menuPerhitungan.add(pn_perhitungan, "card2");

        add(pn_menuPerhitungan, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void b_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_tambahActionPerformed
     // Cek apakah tabel penilaian kosong
    if (dataPenilaianKosong()) {
        JOptionPane.showMessageDialog(
                this,
                "Data penilaian belum diinput!\nSilakan input data penilaian terlebih dahulu.",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
        return;
    }
 
    tampilMatriksKeputusan();
    hitungNormalisasi();
    hitungPreferensi();
    aktif();
    }//GEN-LAST:event_b_tambahActionPerformed

    private void t_keputusanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_keputusanMouseClicked
      
    }//GEN-LAST:event_t_keputusanMouseClicked

    private void t_normalisasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_normalisasiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_t_normalisasiMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componen_tambahan.RoundButton b_tambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JLabel lb_info;
    private javax.swing.JLabel lb_info2;
    private javax.swing.JLabel lb_info3;
    private componen_tambahan.PanelRound panelRound1;
    private componen_tambahan.PanelRound panelRound2;
    private componen_tambahan.PanelRound panelRound3;
    private componen_tambahan.PanelRound panelRound4;
    private componen_tambahan.PanelRound panelRound5;
    private componen_tambahan.PanelRound panelRound6;
    private javax.swing.JPanel pn_keputusan;
    private javax.swing.JPanel pn_komponen;
    private javax.swing.JPanel pn_menuPerhitungan;
    private javax.swing.JPanel pn_normalisasi;
    private javax.swing.JPanel pn_perhitungan;
    private javax.swing.JTable t_keputusan;
    private javax.swing.JTable t_normalisasi;
    // End of variables declaration//GEN-END:variables
    
    // Header Tabel
    protected void warna_header(){
              t_keputusan.setShowVerticalLines(true);
              t_keputusan.setGridColor(java.awt.Color.GRAY);
              t_normalisasi.setShowVerticalLines(true);
              t_normalisasi.setGridColor(java.awt.Color.GRAY);
          // Ubah warna header 
        javax.swing.table.JTableHeader header = t_keputusan.getTableHeader();
        javax.swing.table.JTableHeader header1 = t_normalisasi.getTableHeader();
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
        
         header1.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
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
    
  private double[] ambilBobot() {

    double[] bobot = new double[5];

    try {

        String sql = "SELECT bobot FROM kriteria ORDER BY id_kriteria";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        int i = 0;

        while (rs.next()) {

            // Konversi persen menjadi desimal
            bobot[i] = rs.getDouble("bobot") / 100.0;

            i++;
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(null, e.getMessage());

    }

    return bobot;
}
  
    //id siswa
    private int getIdSiswa(String nis) {

    int id = 0;

    try {

        String sql =
        "SELECT id_siswa FROM siswa WHERE nis=?";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        ps.setString(1, nis);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            id = rs.getInt("id_siswa");

        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(null, e.getMessage());

    }

    return id;

}
//    mengecek data penilaian
    private boolean dataPenilaianKosong() {

        try {

            String sql = "SELECT COUNT(*) AS jumlah FROM penilaian";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt("jumlah") == 0;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        return true;
    }
    
    private void tampilBobot() {
    try {
        String sql = "SELECT kode_kriteria, bobot FROM kriteria ORDER BY id_kriteria";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            String kode = rs.getString("kode_kriteria");
            double bobot = rs.getDouble("bobot");

            String persen = (bobot) + "%";

            switch (kode) {

                case "C1":
                    lb1.setText(persen);
                    break;

                case "C2":
                    lb2.setText(persen);
                    break;

                case "C3":
                    lb3.setText(persen);
                    break;

                case "C4":
                    lb4.setText(persen);
                    break;

                case "C5":
                    lb5.setText(persen);
                    break;
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
        //Mengkonversi nilai ke skala yang sudah ditentukan
     private int getNilaiSubKriteria(String keterangan) {
            int nilai = 0;

            try {
                String sql = "SELECT nilai FROM sub_kriteria WHERE keterangan=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, keterangan);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    nilai = rs.getInt("nilai");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

            return nilai;
        }
            // mengkonversi nilai akademik
            private int konversiAkademik(int nilai){

            if(nilai >= 90){
                return 5;
            }else if(nilai >= 80){
                return 4;
            }else if(nilai >= 70){
                return 3;
            }else if(nilai >= 60){
                return 2;
            }else{
                return 1;
            }

        }
            //konversi untuk absensi
            private int konversiAbsensi(int nilai){

            if(nilai >= 96){
                return 5;
            }else if(nilai >= 91){
                return 4;
            }else if(nilai >= 86){
                return 3;
            }else if(nilai >= 80){
                return 2;
            }else{
                return 1;
            }

        }
            //konversi nilai non akademik
            private int konversiNonAkademik(int nilai){

            if(nilai >= 90){
                return 5;
            }else if(nilai >= 80){
                return 4;
            }else if(nilai >= 70){
                return 3;
            }else if(nilai >= 60){
                return 2;
            }else{
                return 1;
            }

        }
}



