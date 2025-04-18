/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assigment1.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class Repository {
    
    
    public String getRole(String username, String password){
        String sql = "select roles \n" +
                                    "from USERS\n" +
                                    "where username = ? and passwords = ?";
        try{
            Connection con = Connect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            String role = "NO";
            while(rs.next()){
                role = rs.getString("roles");
            }
            return role;
        }catch(Exception e){
            
        }
        return "NO";
    }
    
    public static void main(String[] args) {
        Repository repo = new Repository();
        System.out.println(repo.getRole("HoangDuong", "duongit1"));
    }
}
