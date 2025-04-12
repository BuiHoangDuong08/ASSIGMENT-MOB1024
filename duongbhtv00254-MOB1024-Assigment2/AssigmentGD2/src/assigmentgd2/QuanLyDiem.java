/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package assigmentgd2;

import assigmentgd2.Controller.Controller;
import assigmentgd2.Model.Grade;
import assigmentgd2.Model.Students;
import com.formdev.flatlaf.FlatLightLaf;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Vector;

/**
 *
 * @author duong
 */
public class QuanLyDiem extends javax.swing.JFrame {

    static List<Grade> listg = new ArrayList<>();
    static Controller ctl = new Controller();
    static int current;

    public QuanLyDiem() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.LoadDataTable();
    }

    public void LoadDataTable() {
        listg = ctl.getAllGrade();
        String[] title = {"Id", "Mã SV", "Họ tên", "Tiếng anh", "Tin học", "GDTC", "Điểm TB"};
        DefaultTableModel model = new DefaultTableModel(title, 0);
        for (Grade g : listg) {
            float DiemTb = (g.getTiengAnh() + g.getTinhoc() + g.getGDTC()) / 3;
            String DiemTB = String.format("%.1f", DiemTb);
            model.addRow(new Object[]{g.getId(), g.getStudents().getMASV(), g.getStudents().getHoten(), g.getTiengAnh(), g.getTinhoc(), g.getGDTC(), DiemTB});
        }
        this.tbl_DanhSachDiemSinhVien.setModel(model);
    }

    public void ShowDetail() {
        int index = this.tbl_DanhSachDiemSinhVien.getSelectedRow();
        Grade g = listg.get(index);
        this.txt_ID.setText(String.valueOf(g.getId()));
        this.txt_MaSV.setText(g.getStudents().getMASV());
        this.txt_Hoten.setText(g.getStudents().getHoten());
        this.txt_Tienganh.setText(String.valueOf(g.getTiengAnh()));
        this.txtTinhoc.setText(String.valueOf(g.getTinhoc()));
        this.txt_GDTC.setText(String.valueOf(g.getGDTC()));
        float DiemTb = (g.getTiengAnh() + g.getTinhoc() + g.getGDTC()) / 3;
        String DiemTB = String.format("%.1f", DiemTb);
        this.lbl_diemtb.setText(String.valueOf(DiemTB));
    }

    public Grade getFrom() {
        Grade g = new Grade();
        g.setId(Integer.parseInt(this.txt_ID.getText()));
        Students sv = new Students();
        sv.setMASV(this.txt_MaSV.getText());
        g.setStudents(sv);
        g.setTiengAnh(Float.parseFloat(this.txt_Tienganh.getText()));
        g.setTinhoc(Float.parseFloat(this.txtTinhoc.getText()));
        g.setGDTC(Float.parseFloat(this.txt_Tienganh.getText()));
        return g;
    }

    public void setFrom(int i) {
        Grade g = listg.get(i);
        this.txt_ID.setText(String.valueOf(g.getId()));
        this.txt_MaSV.setText(g.getStudents().getMASV());
        this.txt_Hoten.setText(g.getStudents().getHoten());
        this.txt_Tienganh.setText(String.valueOf(g.getTiengAnh()));
        this.txtTinhoc.setText(String.valueOf(g.getTinhoc()));
        this.txt_GDTC.setText(String.valueOf(g.getGDTC()));
        float DiemTb = (g.getTiengAnh() + g.getTinhoc() + g.getGDTC()) / 3;
        String DiemTB = String.format("%.1f", DiemTb);
        this.lbl_diemtb.setText(DiemTB);
    }

    public void search() {
        String dbUrl = "jdbc:sqlserver://DUONGHOANG;databasename=Fpoly;trustServerCertificate=true";
        String username = "sa";
        String password = "123";
        String[] title = {"Id", "Mã SV", "Họ tên", "Tiếng anh", "Tin học", "GDTC", "Điểm TB"};
        DefaultTableModel model = new DefaultTableModel(title, 0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            String sql = "select Id, STUDENTS.MaSV, STUDENTS.Hoten, Tienganh, Tinhoc, GDTC\n"
                    + "from Grade join STUDENTS on Grade.MaSV = STUDENTS.MaSV ";
            
            if (this.txt_TimMaSV.getText().length() > 0) {
                sql = sql + "where Grade.MaSV like '%" + this.txt_TimMaSV.getText() + "%';";
            }
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.isBeforeFirst() == false) {
                JOptionPane.showMessageDialog(this, "Students are not available!");
                return;
            }
            while (rs.next()) {
                Vector data = new Vector();
                float DiemTb = (rs.getFloat("Tienganh") + rs.getFloat("Tinhoc") + rs.getFloat("GDTC")) / 3;
                String DiemTB = String.format("%.1f", DiemTb);
                data.add(rs.getInt("Id"));
                data.add(rs.getString("MaSV"));
                data.add(rs.getString("Hoten"));
                data.add(rs.getFloat("Tienganh"));
                data.add(rs.getFloat("Tinhoc"));
                data.add(rs.getFloat("GDTC"));
                data.add(DiemTB);
                model.addRow(data);
            }
            this.tbl_DanhSachDiemSinhVien.setModel(model);
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_TimMaSV = new javax.swing.JTextField();
        btn_TimMaSV = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        txt_MaSV = new javax.swing.JTextField();
        txt_Hoten = new javax.swing.JTextField();
        txt_Tienganh = new javax.swing.JTextField();
        txtTinhoc = new javax.swing.JTextField();
        txt_GDTC = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btn_Prev = new javax.swing.JButton();
        btn_Last = new javax.swing.JButton();
        btn_Next = new javax.swing.JButton();
        btn_First = new javax.swing.JButton();
        txt_Update = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        btn_Save = new javax.swing.JButton();
        btn_New = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DanhSachDiemSinhVien = new javax.swing.JTable();
        lbl_diemtb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Điểm");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã SV:");

        txt_TimMaSV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_TimMaSV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_TimMaSV.setText("Search");
        btn_TimMaSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimMaSVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TimMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_TimMaSV)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_TimMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimMaSV))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("ID:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Mã SV:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Họ tên:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tiếng anh:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Tin học:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("GDTC:");

        txt_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_MaSV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_MaSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaSVActionPerformed(evt);
            }
        });

        txt_Hoten.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_Tienganh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTinhoc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_GDTC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Điểm TB:");

        btn_Prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigmentgd2/icon/previous_icon_128297 - Copy.png"))); // NOI18N
        btn_Prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PrevActionPerformed(evt);
            }
        });

        btn_Last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigmentgd2/icon/Picture2.png"))); // NOI18N
        btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LastActionPerformed(evt);
            }
        });

        btn_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigmentgd2/icon/Picture1.png"))); // NOI18N
        btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NextActionPerformed(evt);
            }
        });

        btn_First.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigmentgd2/icon/iconfinder-previous-arrow-back-left-music-4593162_122281.png"))); // NOI18N
        btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FirstActionPerformed(evt);
            }
        });

        txt_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigmentgd2/icon/Edit.png"))); // NOI18N
        txt_Update.setText("Update");
        txt_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_UpdateActionPerformed(evt);
            }
        });

        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigmentgd2/icon/Delete.png"))); // NOI18N
        btn_Delete.setText("Delete");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        btn_Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigmentgd2/icon/Save.png"))); // NOI18N
        btn_Save.setText("Save");
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });

        btn_New.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigmentgd2/icon/Add.png"))); // NOI18N
        btn_New.setText("New");

        tbl_DanhSachDiemSinhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Mã SV", "Họ tên", "Tiếng anh", "Tin học", "GDTC", "Điểm tb"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_DanhSachDiemSinhVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DanhSachDiemSinhVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_DanhSachDiemSinhVien);

        lbl_diemtb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_diemtb.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_MaSV))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Hoten))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Tienganh))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTinhoc))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_GDTC))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_diemtb)))
                        .addGap(82, 82, 82)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_Delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_Update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_New, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btn_First)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Next)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Prev)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Last))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4))
                            .addComponent(txt_MaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_Hoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_Tienganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTinhoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_GDTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lbl_diemtb)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btn_New)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Save)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Delete)
                        .addGap(18, 18, 18)
                        .addComponent(txt_Update)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_First)
                    .addComponent(btn_Next)
                    .addComponent(btn_Prev)
                    .addComponent(btn_Last))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(93, 93, 93))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_MaSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaSVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MaSVActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        // TODO add your handling code here:
        Grade g = this.getFrom();
        int rs = ctl.insertGrade(g);
        if (rs > 0) {
            JOptionPane.showMessageDialog(this, "Lưu thành công");
            this.LoadDataTable();
        }
    }//GEN-LAST:event_btn_SaveActionPerformed

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        // TODO add your handling code here:
        int rs = ctl.DeleteGrade(Integer.parseInt(this.txt_ID.getText()));
        if (rs > 0) {
            JOptionPane.showMessageDialog(this, "Xóa thanh công");
            this.LoadDataTable();
        }
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void tbl_DanhSachDiemSinhVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DanhSachDiemSinhVienMouseClicked
        // TODO add your handling code here:
        this.ShowDetail();
    }//GEN-LAST:event_tbl_DanhSachDiemSinhVienMouseClicked

    private void txt_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_UpdateActionPerformed
        // TODO add your handling code here:
        Grade g = this.getFrom();
        int rs = ctl.UpdateGrade(g);
        if (rs > 0) {
            JOptionPane.showMessageDialog(this, "cập nhập thành công");
            this.LoadDataTable();
        }
    }//GEN-LAST:event_txt_UpdateActionPerformed

    private void btn_TimMaSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimMaSVActionPerformed
        // TODO add your handling code here:
        this.search();
    }//GEN-LAST:event_btn_TimMaSVActionPerformed

    private void btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FirstActionPerformed
        // TODO add your handling code here:
        current = 0;
        this.setFrom(current);
    }//GEN-LAST:event_btn_FirstActionPerformed

    private void btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NextActionPerformed
        // TODO add your handling code here:
        listg = ctl.getAllGrade();
        if (current == listg.size() - 1) {
            JOptionPane.showMessageDialog(this, "Đang ở cuối danh sách");
            return;
        }

        current++;
        setFrom(current);

    }//GEN-LAST:event_btn_NextActionPerformed

    private void btn_PrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PrevActionPerformed
        // TODO add your handling code here:
        if (current == 0) {
            JOptionPane.showMessageDialog(this, "Đang ở đầu danh sách");
        }
        current--;
        this.setFrom(current);
    }//GEN-LAST:event_btn_PrevActionPerformed

    private void btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LastActionPerformed
        // TODO add your handling code here:
        listg = ctl.getAllGrade();
        current = listg.size() - 1;
        this.setFrom(current);
    }//GEN-LAST:event_btn_LastActionPerformed

    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyDiem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_First;
    private javax.swing.JButton btn_Last;
    private javax.swing.JButton btn_New;
    private javax.swing.JButton btn_Next;
    private javax.swing.JButton btn_Prev;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_TimMaSV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_diemtb;
    private javax.swing.JTable tbl_DanhSachDiemSinhVien;
    private javax.swing.JTextField txtTinhoc;
    private javax.swing.JTextField txt_GDTC;
    private javax.swing.JTextField txt_Hoten;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_MaSV;
    private javax.swing.JTextField txt_Tienganh;
    private javax.swing.JTextField txt_TimMaSV;
    private javax.swing.JButton txt_Update;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel DefaultTableModel(String[] title, int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
