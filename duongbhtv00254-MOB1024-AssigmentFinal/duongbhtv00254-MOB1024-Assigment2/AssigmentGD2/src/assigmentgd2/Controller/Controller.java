/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assigmentgd2.Controller;

import assigmentgd2.Model.Grade;
import assigmentgd2.Model.Students;
import assigmentgd2.Model.Users;
import assigmentgd2.QuanLyDiem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author duong
 */
public class Controller {

    ///Connect
    private static String dbUrl = "jdbc:sqlserver://DUONGHOANG;databasename=Fpoly;trustServerCertificate=true";
    private static String username = "sa";
    private static String password = "123";
    ///sqlQuery users
    private static String sql_SelectAllUsers = "select * from USERS";
    ///sqlQuery Students
    private static String sql_SelectAllStudents = "select * from STUDENTS";
    private static String sql_InsertStudents = "INSERT INTO STUDENTS (MASV, Hoten, Email, SoDT, GioiTinh, DiaChi, Hinh) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static String sql_DeleteStuents = "delete from STUDENTS where MaSV = ?;";
    private static String sql_UpdateStudents = "update STUDENTS set Hoten = ?, Email = ?, SoDT = ?, Gioitinh = ?, Diachi = ?, Hinh = ? where MaSV = ?;";
    private static String sql_getStudentsByMaSV = "select MaSV, Hoten from STUDENTS where MaSV = ?;";
    ///sqlQuery Grade
    private static String sql_SelectAllGrade = "select Id, STUDENTS.MASV, STUDENTS.Hoten, Tienganh, Tinhoc, GDTC \n"
            + "from Grade join STUDENTS on Grade.MaSV = STUDENTS.MASV\n"
            + "group by Id, STUDENTS.MASV, STUDENTS.Hoten, Tienganh, Tinhoc, GDTC;";
    private static String sql_InsertGrade = "insert into Grade values(?,?,?,?,?);";
    private static String sql_DeleteGrade = "delete from Grade where Id = ?;";
    private static String sql_UpdateGrade = "Update Grade set MaSV = ?, Tienganh = ?, Tinhoc = ?,GDTC = ? where Id = ?;";
    private static String sql_getGradebyMaSV = "select Id, STUDENTS.MASV, STUDENTS.Hoten, Tienganh, Tinhoc, GDTC \n"
            + "from Grade join STUDENTS on Grade.MaSV = STUDENTS.MASV\n"
            + "where STUDENTS.MASV like ?';";

    ///controller login
    public List<Users> getAllUsers() {
        List<Users> listusers = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            String sql = sql_SelectAllUsers;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Users u = new Users();
                u.setUsername(rs.getString("username"));
                u.setPasswords(rs.getString("passwords"));
                u.setRoles(rs.getString("roles"));
                listusers.add(u);
            }
            conn.close();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listusers;
    }

    ///controller QuanLySinhVien
    public List<Students> getAllStudents() {
        List<Students> listsd = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            String sql = sql_SelectAllStudents;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Students sd = new Students();
                sd.setMASV(rs.getString("MaSV"));
                sd.setHoten(rs.getString("Hoten"));
                sd.setEmail(rs.getString("Email"));
                sd.setSoDT(rs.getString("SoDT"));
                sd.setGioiTinh(rs.getBoolean("Gioitinh"));
                sd.setDiaChi(rs.getString("Diachi"));
                sd.setHinh(rs.getString("Hinh"));
                listsd.add(sd);
            }
            conn.close();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listsd;
    }

    public int insertsd(Students sd) {
        int rs = 0;
        try {
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            PreparedStatement pst = conn.prepareStatement(sql_InsertStudents);
            pst.setString(1, sd.getMASV());
            pst.setString(2, sd.getHoten());
            pst.setString(3, sd.getEmail());
            pst.setString(4, sd.getSoDT());
            pst.setBoolean(5, sd.isGioiTinh());
            pst.setString(6, sd.getDiaChi());
            pst.setString(7, sd.getHinh());
            rs = pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int Deletesd(String MaSV) {
        int rs = 0;
        try {
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            PreparedStatement pst = conn.prepareStatement(sql_DeleteStuents);
            pst.setString(1, MaSV);
            rs = pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int Updatesd(Students sd) {
        int rs = 0;
        try {
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            PreparedStatement pst = conn.prepareStatement(sql_UpdateStudents);
            pst.setString(1, sd.getHoten());
            pst.setString(2, sd.getEmail());
            pst.setString(3, sd.getSoDT());
            pst.setBoolean(4, sd.isGioiTinh());
            pst.setString(5, sd.getDiaChi());
            pst.setString(6, sd.getHinh());
            pst.setString(7, sd.getMASV());
            rs = pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    ///controller QuanLyDiem
    public Students getMaSVHotenByMaSV(String MaSV) {
        Students sd = new Students();
        try {
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            String sql = sql_getStudentsByMaSV;
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, MaSV);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                sd.setMASV(rs.getString("MaSV"));
                sd.setHoten(rs.getString("Hoten"));
            }
            pst.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sd;
    }

    public List<Grade> getAllGrade() {
        List<Grade> listg = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            String sql = sql_SelectAllGrade;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Grade g = new Grade();
                g.setId(rs.getInt("Id"));
                String masv = rs.getString("MaSV");
                g.setStudents(this.getMaSVHotenByMaSV(masv));
                g.setTiengAnh(rs.getFloat("Tienganh"));
                g.setTinhoc(rs.getShort("Tinhoc"));
                g.setGDTC(rs.getFloat("GDTC"));
                listg.add(g);
            }
            st.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listg;
    }

    public int insertGrade(Grade g) {
        int rs = 0;
        try {
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            PreparedStatement pst = conn.prepareStatement(sql_InsertGrade);
            pst.setInt(1, g.getId());
            pst.setString(2, g.getStudents().getMASV());
            pst.setFloat(3, g.getTiengAnh());
            pst.setFloat(4, g.getTinhoc());
            pst.setFloat(5, g.getGDTC());
            rs = pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int DeleteGrade(int Id) {
        int rs = 0;
        try {
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            PreparedStatement pst = conn.prepareStatement(sql_DeleteGrade);
            pst.setInt(1, Id);
            rs = pst.executeUpdate();
            conn.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int UpdateGrade(Grade g) {
        int rs = 0;
        try {
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            PreparedStatement pst = conn.prepareStatement(sql_UpdateGrade);
            pst.setString(1, g.getStudents().getMASV());
            pst.setFloat(2, g.getTiengAnh());
            pst.setFloat(3, g.getTinhoc());
            pst.setFloat(4, g.getGDTC());
            pst.setInt(5, g.getId());
            rs = pst.executeUpdate();
            conn.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

}
