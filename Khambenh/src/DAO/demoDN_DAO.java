/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import database.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
/**
 *
 * @author LAPTOP
 */
public class demoDN_DAO {
        public static Connection con =null;
        public static ResultSet rs = null;
        public static PreparedStatement pre = null;
        public static String testConnect(){
            
            try {
                con = Connect.getConnection();
                return "Ket noi thanh cong";
            } catch (Exception e) {
                return "Ket noi that bai";
            }
        }
//        public static ResultSet Kiemtra_DN(String user,String pass){
//       
//           String sql="SELECT * FROM taikhoan where taikhoan=? and matkhau=?";  
//            try {
//                
//                pre = con.prepareStatement(sql);
//                pre.setString(1, user);
//                pre.setString(2, pass);
//                
//                return  rs = pre.executeQuery();
//            } catch (SQLException ex) {
//               return rs =null;
//            }
//        }
//            public boolean Kiemtra_DN(String user,String pass){
//            Connect ab = new Connect();
//           
//        try {
//            String sql = "select user,matkhau from taikhoang where user=? and matkhau=?";
//            PreparedStatement stm  = con.prepareStatement(sql);
//            stm.setString(2,user);
//            stm.setString(3, pass);
//            ResultSet rs = stm.executeQuery();
//            if(rs.next()){
//                return true;
//            }
//            else
//                return false;
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(demoDN_DAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return false;
//        }
}
