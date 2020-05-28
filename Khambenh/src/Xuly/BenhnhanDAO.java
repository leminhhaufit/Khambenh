package Xuly;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Connect;
import entity.Benhnhan;

public class BenhnhanDAO {

	ArrayList<Benhnhan> dsbn;
	
	public BenhnhanDAO() {
		dsbn = new ArrayList<Benhnhan>();
	}
	public ArrayList<Benhnhan> doctubangBN(){
		try {
			Connection con = Connect.getConnection();
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery("select * from benhnhan");
            while(re.next()){
                String msbn = re.getString(1);
                String socmnd = re.getString(2);
                String hoten = re.getString(3);
                String diachi = re.getString(4);
                
                Benhnhan bn;
                bn = new Benhnhan(msbn, socmnd, hoten, diachi);
                //lk = new TTLinhkien(ma, ten, new TTNhacungcap(ncc),new TTNuoc(nuoc),sl,gianhap,giaban);
               // lk = new TTLinhkien(ten, ten, ncc, 0, 0, 0)
                //dslk.add(lk);
                dsbn.add(bn);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsbn;
	}
	
	public boolean themBN(String msbn,String socmnd,String hoten,String diachi) {
        Connection con = Connect.getConnection();
                  PreparedStatement pre = null;
        int n=0;
        try {
                pre = con.prepareStatement("insert into benhnhan values(?,?,?,?)");
                pre.setString(1, msbn);
                pre.setString(2, socmnd);
                pre.setString(3, hoten);
                pre.setString(4, diachi);
                
                n= pre.executeUpdate();
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return n>0;
    }
	
	public boolean SuaTTBN(String msbn,String socmnd,String hoten,String diachi) {
		Connection con = Connect.getConnection();
		PreparedStatement pre = null;
		int n=0;
		try {
			pre = con.prepareStatement("update benhnhan set socmnd=?,hoten=?,diachi=? where msbn=?");
                        pre.setString(4, msbn);
			pre.setString(1,socmnd);
			pre.setString(2, hoten);
                        pre.setString(3, diachi);
			n = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
}
