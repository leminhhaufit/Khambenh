/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author HMQIWT
 */
public class Connect {
    Connection connection;
   // public static Connection con = null;
    
    public static Connection getConnection(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/benhnhan","root","sapassword");
            //String all ="jdbc:sqlserver://localhost:1433;databasename=benhnhan;user=sa;password=sapassword";
            //con = DriverManager.getConnection(all);
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ket noi khong duoc","thong bao",1);
            return null;
        }
    }
    
    
}
