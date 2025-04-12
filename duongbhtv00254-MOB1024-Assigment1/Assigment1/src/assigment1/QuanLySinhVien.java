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

public class QuanLySinhVien extends javax.swing.JFrame {

    static ArrayList<SinhVien> list = new ArrayList<>();
    private static String dbUrl = "jdbc:sqlserver://DUONGHOANG;"
            + "databasename=Fpoly;"
            + "trustServerCertificate=true";
    private static String Username = "sa";
    private static String Password = "123";
    private static Connection connection;

    public QuanLySinhVien() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.LoadDatabase();
        this.InitTable();
    }

    public void LoadDatabase() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbUrl, Username, Password);
            System.out.println("Ket noi thanh cong");
            String sqlQuery = "select * from STUDENTS";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sqlQuery);
            while (rs.next()) {
                SinhVien sv = new SinhVien();
                sv.setMASV(rs.getString("MASV"));
                sv.setHoten(rs.getString("Hoten"));
                sv.setEmail(rs.getString("Email"));
                sv.setSoDT(rs.getString("SoDT"));
                sv.setGioiTinh(rs.getBoolean("GioiTinh"));
                sv.setDiaChi(rs.getString("DiaChi"));
                sv.setHinh(rs.getString("Hinh"));
                list.add(sv);
            }
            connection.close();
            st.close();
            rs.close();
        } catch (Exception e) {

        }
    }

    public void fillTable() {
        String[] title = {"Mã SV", "Họ tên", "Email", "Số ĐT", "Giới Tính", "Địa chỉ"};
        DefaultTableModel model = new DefaultTableModel(title, 0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbUrl, Username, Password);
            String sqlQuery = "select * from STUDENTS";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sqlQuery);
            while (rs.next()) {
                Object[] row = new Object[]{rs.getString("MASV"), rs.getString("Hoten"), rs.getString("Email"), rs.getString("SoDT"), rs.getBoolean("GioiTinh"), rs.getString("DiaChi"), rs.getString("Hinh")};
                model.addRow(row);
                this.tbl_DanhSachSinhVien.setModel(model);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "");
        }
    }

    public void InitTable() {
        String[] title = {"Mã SV", "Họ tên", "Email", "Số ĐT", "Giới Tính", "Địa chỉ"};
        DefaultTableModel model = new DefaultTableModel(title, 0);
        for (SinhVien sv : list) {
            Object[] row = new Object[]{sv.getMASV(), sv.getHoten(), sv.getEmail(), sv.getSoDT(), sv.isGioiTinh() ? "Nam" : "Nu", sv.getDiaChi(), sv.getHinh()};
            model.addRow(row);
        }
        this.tbl_DanhSachSinhVien.setModel(model);
    }

    public void ShowDetail() {
        int index = this.tbl_DanhSachSinhVien.getSelectedRow();
        SinhVien sv = list.get(index);
        this.txt_MaSV.setText(sv.getMASV());
        this.txt_HoTen.setText(sv.getHoten());
        this.txt_Email.setText(sv.getEmail());
        this.txt_SoDT.setText(sv.getSoDT());
        this.txt_GioiTinh.setText(sv.isGioiTinh() ? "Nam" : "Nu");
        this.txta_DiaChi.setText(sv.getDiaChi());
        this.txt_Hinh.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_MaSV = new javax.swing.JTextField();
        txt_HoTen = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        txt_SoDT = new javax.swing.JTextField();
        txt_GioiTinh = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txta_DiaChi = new javax.swing.JTextArea();
        btn_New = new javax.swing.JButton();
        btn_Save = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_DanhSachSinhVien = new javax.swing.JTable();
        txt_Hinh = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Sinh Viên");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mã SV:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Họ Tên:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Email:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Số ĐT:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Giới Tính:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Địa Chỉ");

        txt_HoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_HoTenActionPerformed(evt);
            }
        });

        txt_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_EmailActionPerformed(evt);
            }
        });

        txt_SoDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SoDTActionPerformed(evt);
            }
        });

        txt_GioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_GioiTinhActionPerformed(evt);
            }
        });

        txta_DiaChi.setColumns(20);
        txta_DiaChi.setRows(5);
        jScrollPane1.setViewportView(txta_DiaChi);

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

        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigment1/icon/Delete.png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assigment1/icon/Edit.png"))); // NOI18N
        btn_Update.setText("Update");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        tbl_DanhSachSinhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã SV", "Họ tên", "Email", "Số ĐT", "Giới tính", "Địa chỉ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_DanhSachSinhVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DanhSachSinhVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_DanhSachSinhVien);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_MaSV, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(txt_HoTen)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Email)
                            .addComponent(txt_SoDT)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_GioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(txt_Hinh, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_New, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Update))))
                .addContainerGap(165, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_MaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_SoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(txt_Hinh, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_GioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_New)
                            .addComponent(btn_Save))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_delete)
                            .addComponent(btn_Update))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_SoDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SoDTActionPerformed

    private void txt_GioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_GioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_GioiTinhActionPerformed

    private void tbl_DanhSachSinhVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DanhSachSinhVienMouseClicked
        // TODO add your handling code here:
        this.ShowDetail();

    }//GEN-LAST:event_tbl_DanhSachSinhVienMouseClicked

    private void btn_NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NewActionPerformed
        // TODO add your handling code here:
        this.txt_MaSV.setText(null);
        this.txt_HoTen.setText(null);
        this.txt_SoDT.setText(null);
        this.txt_Email.setText(null);
        this.txt_GioiTinh.setText(null);
        this.txta_DiaChi.setText(null);
    }//GEN-LAST:event_btn_NewActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbUrl, Username, Password);
            String sqlQuery = "delete from STUDENTS where MASV = ?;";
            PreparedStatement pst = connection.prepareStatement(sqlQuery);
            pst.setString(1, this.txt_MaSV.getText());
            pst.executeUpdate();
            this.fillTable();
            JOptionPane.showMessageDialog(this, "Delete thanh cong");
            JOptionPane.showMessageDialog(this, "Ho ten: " + this.txt_MaSV.getText()
                    + "\n Ma SV: " + this.txt_MaSV.getText()
                    + "\n Email: " + this.txt_Email.getText()
                    + "\n So Dt: " + this.txt_SoDT.getText()
                    + "\n Gioi tinh: " + this.txt_GioiTinh.getText()
                    + "\n Dia chi: " + this.txta_DiaChi.getText());
            pst.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "");
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbUrl, Username, Password);
            String sqlQuery = "insert into STUDENTS values(?,?,?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sqlQuery);
            pst.setString(1, this.txt_MaSV.getText());
            pst.setString(2, this.txt_HoTen.getText());
            pst.setString(3, this.txt_Email.getText());
            pst.setString(4, this.txt_SoDT.getText());
            pst.setBoolean(5, this.txt_GioiTinh.getText().equals("Nam"));
            pst.setString(6, this.txta_DiaChi.getText());
            pst.setString(7, this.txt_Hinh.getText());
            pst.executeUpdate();
            this.LoadDatabase();
            this.fillTable();
            JOptionPane.showMessageDialog(this, "Luu thanh cong");
            JOptionPane.showMessageDialog(this, "Ho ten: " + this.txt_MaSV.getText()
                    + "\n Ma SV: " + this.txt_MaSV.getText()
                    + "\n Email: " + this.txt_Email.getText()
                    + "\n So Dt: " + this.txt_SoDT.getText()
                    + "\n Gioi tinh: " + this.txt_GioiTinh.getText()
                    + "\n Dia chi: " + this.txta_DiaChi.getText());
            pst.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "");
        }
    }//GEN-LAST:event_btn_SaveActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbUrl, Username, Password);
            String sqlQuery = "update STUDENTS set Hoten = ?, Email = ?, SoDT = ?, GioiTinh = ?, DiaChi = ?, Hinh= ? where MASV = ?";
            PreparedStatement pst = connection.prepareStatement(sqlQuery);
            pst.setString(1, this.txt_HoTen.getText());
            pst.setString(2, this.txt_Email.getText());
            pst.setString(3, this.txt_SoDT.getText());
            pst.setBoolean(4, this.txt_GioiTinh.getText().equals("Nam"));
            pst.setString(5, this.txta_DiaChi.getText());
            pst.setString(6, this.txt_Hinh.getText());
            pst.setString(7, this.txt_MaSV.getText());
            pst.executeUpdate();
            this.LoadDatabase();
            this.fillTable();
            JOptionPane.showMessageDialog(this, "Cap nhap thanh cong");
            JOptionPane.showMessageDialog(this, "Ho ten: " + this.txt_MaSV.getText()
                    + "\n Ma SV: " + this.txt_MaSV.getText()
                    + "\n Email: " + this.txt_Email.getText()
                    + "\n So Dt: " + this.txt_SoDT.getText()
                    + "\n Gioi tinh: " + this.txt_GioiTinh.getText()
                    + "\n Dia chi: " + this.txta_DiaChi.getText());
            pst.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "");
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void txt_HoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_HoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_HoTenActionPerformed

    private void txt_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmailActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLySinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLySinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLySinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLySinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLySinhVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_New;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_Update;
    private javax.swing.JButton btn_delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_DanhSachSinhVien;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_GioiTinh;
    private javax.swing.JTextField txt_Hinh;
    private javax.swing.JTextField txt_HoTen;
    private javax.swing.JTextField txt_MaSV;
    private javax.swing.JTextField txt_SoDT;
    private javax.swing.JTextArea txta_DiaChi;
    // End of variables declaration//GEN-END:variables
}
