/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package assigment1;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QuanLyDiem extends javax.swing.JFrame {

    static ArrayList<Diem> list = new ArrayList<>();
    private static String dbUrl = "jdbc:sqlserver://DUONGHOANG;"
            + "databasename=Fpoly;"
            + "trustServerCertificate=true";
    private static String Username = "sa";
    private static String Password = "123";
    private static Connection connection;
    private static int current = 0;

    public QuanLyDiem() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.loadDatabase();
        this.InitTable();

    }

    public void loadDatabase() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbUrl, Username, Password);
            System.out.println("Ket noi thanh cong");
            String sqlQuery = "select id,STUDENTS.Hoten, STUDENTS.MASV, TiengAnh, Tinhoc, GDTC "
                    + "from GRADE join STUDENTS on GRADE.MaSV = STUDENTS.MASV "
                    + "group by id,STUDENTS.Hoten, STUDENTS.MASV, TiengAnh, Tinhoc, GDTC;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sqlQuery);
            while (rs.next()) {
                Diem diem = new Diem();
                diem.setId(rs.getInt("ID"));
                diem.setMASV(rs.getString("MASV"));
                diem.setHoten(rs.getString("Hoten"));
                diem.setTiengAnh(rs.getFloat("Tienganh"));
                diem.setTinhoc(rs.getFloat("Tinhoc"));
                diem.setGDTC(rs.getFloat("GDTC"));
                list.add(diem);
            }
            st.close();
            rs.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "");
        }
    }

    public void FillTable() {
        String[] title = {"ID", "Mã SV", "Họ tên", "Tiếng anh", "Tin học", "Giáo dục TC", "Điểm TB"};
        DefaultTableModel model = new DefaultTableModel(title, 0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbUrl, Username, Password);
            String sqlQuery = "select id,STUDENTS.Hoten, STUDENTS.MASV, TiengAnh, Tinhoc, GDTC "
                    + "from GRADE join STUDENTS on GRADE.MaSV = STUDENTS.MASV "
                    + "group by id,STUDENTS.Hoten, STUDENTS.MASV, TiengAnh, Tinhoc, GDTC;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sqlQuery);
            while (rs.next()) {
                double DiemTb = (rs.getFloat("Tienganh") + rs.getFloat("Tinhoc") + rs.getFloat("GDTC")) / 3;
                Object[] row = new Object[]{rs.getInt("ID"), rs.getString("MASV"), rs.getString("Hoten"), rs.getFloat("Tienganh"), rs.getFloat("Tinhoc"), rs.getFloat("GDTC"), DiemTb};
                model.addRow(row);

                this.tbl_DanhsachDiem.setModel(model);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "");
        }
    }

    public void InitTable() {
        String[] title = {"ID", "Mã SV", "Họ tên", "Tiếng anh", "Tin học", "GDTC", "Điểm TB"};
        DefaultTableModel model = new DefaultTableModel(title, 0);
        for (Diem diem : list) {
            String diemTB = String.format("%.1f", diem.getDiemTB());
            Object[] row = new Object[]{diem.getId(), diem.getMASV(), diem.getHoten(), diem.getTiengAnh(), diem.getTinhoc(), diem.getGDTC(), diemTB};
            model.addRow(row);
        }
        this.tbl_DanhsachDiem.setModel(model);
    }

    public void ShowDeTail() {
        int index = this.tbl_DanhsachDiem.getSelectedRow();
        Diem diem = list.get(index);
        this.txt_ID.setText(String.valueOf(diem.getId()));
        this.txt_MaSV.setText(diem.getMASV());
        this.txt_Hoten.setText(diem.getHoten());
        this.txt_TiengAnh.setText(String.valueOf(diem.getTiengAnh()));
        this.txt_TinHoc.setText(String.valueOf(diem.getTinhoc()));
        this.txt_GiaoDucCT.setText(String.valueOf(diem.getGDTC()));
        String diemTB = String.format("%.1f", diem.getDiemTB());
        this.lbl_DiemTB.setText(String.valueOf(diemTB));
    }

    public void display(int i) {
        Diem diem = list.get(i);
        this.txt_ID.setText(String.valueOf(diem.getId()));
        this.txt_MaSV.setText(diem.getMASV());
        this.txt_Hoten.setText(diem.getHoten());
        this.txt_TiengAnh.setText(String.valueOf(diem.getTiengAnh()));
        this.txt_TinHoc.setText(String.valueOf(diem.getTinhoc()));
        this.txt_GiaoDucCT.setText(String.valueOf(diem.getGDTC()));
        this.lbl_DiemTB.setText(String.valueOf(diem.getDiemTB()));
    }

   

    public void reset() {
        this.txt_MaSV.setText(null);
        this.txt_TiengAnh.setText(null);
        this.txt_TinHoc.setText(null);
        this.txt_GiaoDucCT.setText(null);
        this.txt_Hoten.setText(null);
        this.lbl_DiemTB.setText(null);
        this.txt_ID.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_QuanLyDiemSinhVien = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_TimKiem = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_MaSV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_TiengAnh = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_TinHoc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_GiaoDucCT = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lbl_DiemTB = new javax.swing.JLabel();
        btn_New = new javax.swing.JButton();
        btn_Save = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_prev = new javax.swing.JButton();
        brn_last = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DanhsachDiem = new javax.swing.JTable();
        txt_TimMaSV = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        txt_Hoten = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_QuanLyDiemSinhVien.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_QuanLyDiemSinhVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_QuanLyDiemSinhVien.setText("Quản Lý Điểm Sinh Viên");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mã SV:");

        btn_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigment1/icon/Search.png"))); // NOI18N
        btn_TimKiem.setText("Search");
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Họ tên:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Mã SV:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Tiếng anh:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Tin học:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Giáo dục CT:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Điểm TB");

        lbl_DiemTB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_DiemTB.setText("0");

        btn_New.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigment1/icon/Add.png"))); // NOI18N
        btn_New.setText("New");
        btn_New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NewActionPerformed(evt);
            }
        });

        btn_Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigment1/icon/Save.png"))); // NOI18N
        btn_Save.setText("Save");
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });

        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigment1/icon/Delete.png"))); // NOI18N
        btn_Delete.setText("Delete");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigment1/icon/Edit.png"))); // NOI18N
        btn_Update.setText("Update");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        btn_first.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigment1/icon/iconfinder-previous-arrow-back-left-music-4593162_122281.png"))); // NOI18N
        btn_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstActionPerformed(evt);
            }
        });

        btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigment1/icon/Picture1.png"))); // NOI18N
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        btn_prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigment1/icon/previous_icon_128297 - Copy.png"))); // NOI18N
        btn_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prevActionPerformed(evt);
            }
        });

        brn_last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigment1/icon/Picture2.png"))); // NOI18N
        brn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brn_lastActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("3 sinh viên có điểm cao nhất");

        tbl_DanhsachDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã SV", "Họ tên", "Tiếng anh", "Tin học", "Giáo dục CT", "Điểm TB"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_DanhsachDiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DanhsachDiemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_DanhsachDiem);

        txt_TimMaSV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_TimMaSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TimMaSVActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_TinHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_TiengAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txt_Hoten, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txt_MaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(44, 44, 44)
                                                .addComponent(jLabel10))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbl_DiemTB)
                                                .addGap(19, 19, 19))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(lbl_QuanLyDiemSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_GiaoDucCT, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btn_first)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btn_next)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btn_prev)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(brn_last)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_TimMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_TimKiem)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_Update, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(btn_Delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_New, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(358, 358, 358))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbl_QuanLyDiemSinhVien)
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_New)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(btn_TimKiem)
                            .addComponent(txt_TimMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel10))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txt_Hoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txt_MaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txt_TiengAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txt_TinHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txt_GiaoDucCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_first)
                            .addComponent(btn_next)
                            .addComponent(btn_prev)
                            .addComponent(brn_last))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Save)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Delete)
                            .addComponent(lbl_DiemTB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Update)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbUrl, Username, Password);
            String sqlQuery = "delete from GRADE where ID = ?;";
            PreparedStatement pst = connection.prepareStatement(sqlQuery);
            pst.setInt(1, Integer.parseInt(this.txt_ID.getText()));
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Xoa thanh cong");
            JOptionPane.showMessageDialog(this, "ID: " + this.txt_ID.getText()
                    + "\nHo ten: " + this.txt_Hoten.getText()
                    + "\nMa SV: " + this.txt_MaSV.getText()
                    + "\nTieng anh: " + this.txt_TiengAnh.getText()
                    + "\nTin hoc: " + this.txt_TinHoc.getText()
                    + "\nGDTC: " + this.txt_GiaoDucCT.getText()
                    + "\nDiem TB: " + this.lbl_DiemTB.getText());
            pst.close();
            connection.close();
            this.FillTable();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "");
        }
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void btn_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prevActionPerformed
        // TODO add your handling code here:
        if (current == 0) {
            JOptionPane.showMessageDialog(this, "Dang o dau danh sach");
            return;
        }
        current--;
        this.display(current);
    }//GEN-LAST:event_btn_prevActionPerformed

    private void tbl_DanhsachDiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DanhsachDiemMouseClicked
        // TODO add your handling code here:
        this.ShowDeTail();
    }//GEN-LAST:event_tbl_DanhsachDiemMouseClicked

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbUrl, Username, Password);
            String sqlQuery = "select id, STUDENTS.MASV, STUDENTS.Hoten, STUDENTS.MASV, Tienganh, Tinhoc, GDTC \n"
                    + "from GRADE join STUDENTS on GRADE.MASV = STUDENTS.MASV\n"
                    + "where STUDENTS.MASV = ?; ";
            PreparedStatement pst = connection.prepareStatement(sqlQuery);
            pst.setString(1, this.txt_TimMaSV.getText());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                 double DiemTB = (rs.getFloat("Tienganh") + rs.getFloat("Tinhoc") + rs.getFloat("GDTC")) / 3;
                String DiemTb = String.format("%.1f", DiemTB);
                this.txt_ID.setText(rs.getString("ID"));
                this.txt_Hoten.setText(rs.getString("Hoten"));
                this.txt_MaSV.setText(rs.getString("MASV"));
                this.txt_TiengAnh.setText(String.valueOf(rs.getFloat("Tienganh")));
                this.txt_TinHoc.setText(String.valueOf(rs.getFloat("Tinhoc")));
                this.txt_GiaoDucCT.setText(String.valueOf(rs.getFloat("GDTC")));
                this.lbl_DiemTB.setText(String.valueOf(DiemTb));
            }

            JOptionPane.showMessageDialog(this, "Da tim thay sinh vien");
            JOptionPane.showMessageDialog(this, "ID: " + this.txt_ID.getText()
                    + "\nHo ten: " + this.txt_Hoten.getText()
                    + "\nMa SV: " + this.txt_MaSV.getText()
                    + "\nTieng anh: " + this.txt_TiengAnh.getText()
                    + "\nTin hoc: " + this.txt_TinHoc.getText()
                    + "\nGDTC: " + this.txt_GiaoDucCT.getText()
                    + "\nDiem TB: " + this.lbl_DiemTB.getText());
            pst.close();
            rs.close();
            connection.close();
            this.FillTable();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "");
        }

    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void btn_NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NewActionPerformed
        // TODO add your handling code here:
        this.txt_MaSV.setText(null);
        this.txt_TiengAnh.setText(null);
        this.txt_TinHoc.setText(null);
        this.txt_GiaoDucCT.setText(null);
        this.txt_Hoten.setText(null);
        this.lbl_DiemTB.setText(null);
        this.txt_ID.setText(null);
    }//GEN-LAST:event_btn_NewActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbUrl, Username, Password);
            String sqlQuery = "insert into GRADE values (?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sqlQuery);
            pst.setInt(1, Integer.parseInt(this.txt_ID.getText()));
            pst.setString(2, this.txt_MaSV.getText());
            pst.setFloat(3, Float.parseFloat(this.txt_TiengAnh.getText()));
            pst.setFloat(4, Float.parseFloat(this.txt_TinHoc.getText()));
            pst.setFloat(5, Float.parseFloat(this.txt_GiaoDucCT.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Luu thanh cong");
            JOptionPane.showMessageDialog(this, "ID: " + this.txt_ID.getText()
                    + "\nHo ten: " + this.txt_Hoten.getText()
                    + "\nMa SV: " + this.txt_MaSV.getText()
                    + "\nTieng anh: " + this.txt_TiengAnh.getText()
                    + "\nTin hoc: " + this.txt_TinHoc.getText()
                    + "\nGDTC: " + this.txt_GiaoDucCT.getText()
                    + "\nDiem TB: " + this.lbl_DiemTB.getText());
            pst.close();
            connection.close();
            this.FillTable();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "");
        }
    }//GEN-LAST:event_btn_SaveActionPerformed

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        // TODO add your handling code here:
        current = 0;
        this.display(current);
        JOptionPane.showMessageDialog(this, "Dang o dau danh sach");
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbUrl, Username, Password);
            String sqlQuery = "update GRADE set MASV = ?, Tienganh = ?, Tinhoc = ?, GDTC = ? where ID = ?";
            PreparedStatement pst = connection.prepareStatement(sqlQuery);
            pst.setString(1, this.txt_MaSV.getText());
            pst.setFloat(2, Float.parseFloat(this.txt_TiengAnh.getText()));
            pst.setFloat(3, Float.parseFloat(this.txt_TinHoc.getText()));
            pst.setFloat(4, Float.parseFloat(this.txt_GiaoDucCT.getText()));
            pst.setInt(5, Integer.parseInt(this.txt_ID.getText()));
            pst.executeUpdate();
            this.loadDatabase();

            JOptionPane.showMessageDialog(this, "Cap nhap thanh cong");
            JOptionPane.showMessageDialog(this, "ID: " + this.txt_ID.getText()
                    + "\nHo ten: " + this.txt_Hoten.getText()
                    + "\nMa SV: " + this.txt_MaSV.getText()
                    + "\nTieng anh: " + this.txt_TiengAnh.getText()
                    + "\nTin hoc: " + this.txt_TinHoc.getText()
                    + "\nGDTC: " + this.txt_GiaoDucCT.getText()
                    + "\nDiem TB: " + this.lbl_DiemTB.getText());
            pst.close();
            connection.close();
            this.reset();
            this.FillTable();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "");
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        // TODO add your handling code here:
        if (current == list.size() - 1) {
            JOptionPane.showMessageDialog(this, "Dang o cuoi danh sach");
            return;
        }
        current++;
        this.display(current);
    }//GEN-LAST:event_btn_nextActionPerformed

    private void brn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brn_lastActionPerformed
        // TODO add your handling code here:
        current = list.size() - 1;
        this.display(current);
        JOptionPane.showMessageDialog(this, "Dang o cuoi danh sach");
    }//GEN-LAST:event_brn_lastActionPerformed

    private void txt_TimMaSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TimMaSVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TimMaSVActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyDiem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brn_last;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_New;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_Update;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_prev;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_DiemTB;
    private javax.swing.JLabel lbl_QuanLyDiemSinhVien;
    private javax.swing.JTable tbl_DanhsachDiem;
    private javax.swing.JTextField txt_GiaoDucCT;
    private javax.swing.JTextField txt_Hoten;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_MaSV;
    private javax.swing.JTextField txt_TiengAnh;
    private javax.swing.JTextField txt_TimMaSV;
    private javax.swing.JTextField txt_TinHoc;
    // End of variables declaration//GEN-END:variables
}
