/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assigment1.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class Connect {

    private static String dbUrl = "jdbc:sqlserver://DUONGHOANG;"
            + "databasename=Fpoly;"
            + "trustServerCertificate=true";
    private static String Username = "sa";
    private static String Password = "123";
    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(dbUrl, Username, Password);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "");
        }
        return null;
    }
}
