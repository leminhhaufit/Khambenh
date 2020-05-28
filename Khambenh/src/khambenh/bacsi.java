/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khambenh;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.BasicConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import Helper.XMLConvert;
import Sender.MessageSender;
import Xuly.BenhnhanDAO;
import config.AppConfig;
import config.TopicSubcriber;
import entity.Benhnhan;

/**
 *
 * @author LAPTOP
 */
public class bacsi extends javax.swing.JFrame {

	public static DefaultTableModel datmodelBN;
	BenhnhanDAO dsbn = new BenhnhanDAO();
	private TopicSubcriber subcriber;
    /**
     * Creates new form bacsi3
     */
    public bacsi() {
        initComponents();
        String[] headers = {"Ma so benh nhan", "CMND", "Ho Ten","Dia Chi"};
		datmodelBN = new DefaultTableModel(headers, 0);
		database.Connect.getConnection();
		capnhapTableBenhnhan();
		try {
        	subcriber = TopicSubcriber.getInstance();
    		subcriber.Connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		jTable1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = jTable1.getSelectedRow();
			       if (row >= 0) {

			           txtmsbn.setText(jTable1.getValueAt(row, 0).toString());
			           txtcmnd.setText(jTable1.getValueAt(row, 1).toString());
			           txthoten.setText(jTable1.getValueAt(row, 2).toString());
			           txtdiachi.setText(jTable1.getValueAt(row, 3).toString());
			           //tbGioihang.setValueAt(tbLinhkien.getValueAt(row, 4), 0, 0);
			       }
				
			}
		});
		
		btncapnhat.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!(txtmsbn.getText().equalsIgnoreCase("") && txtcmnd.getText().equalsIgnoreCase("")
		                && txthoten.getText().equalsIgnoreCase("") && txtdiachi.getText().equalsIgnoreCase("")
		                )) {
		        			String ms = String.valueOf(txtmsbn.getText());
		        			String cmnd = String.valueOf(txtcmnd.getText());
		        			String hoten = String.valueOf(txthoten.getText());
		        			String diachi = String.valueOf(txtdiachi.getText());
		        			
		              if (dsbn.SuaTTBN(ms,cmnd,hoten,diachi)) {
		            	  JOptionPane.showMessageDialog(btncapnhat, "Cập nhật thành công");
		                        
								
		                        txtmsbn.setText("");txtcmnd.setText(""); 
								  txthoten.setText(""); txtdiachi.setText("");
								 
		                        
		                        
		                		  
		                		 
		                    }

		        }else {
		                    JOptionPane.showMessageDialog(btncapnhat, "Không để trống");
		                }
				
			}
		});
		
    }
    public void capnhapTableBenhnhan() {
        BenhnhanDAO dsbn = new BenhnhanDAO();
        
        List<Benhnhan> ls = dsbn.doctubangBN();
        for (Benhnhan bn : ls) {
            String[] rowData = {bn.getMaso(),bn.getSocm(),bn.getHoten(),bn.getDiachi()};
            datmodelBN.addRow(rowData);
        }
        jTable1.setModel(datmodelBN);
    }
    
    private void addData()
    {
    	try {
    		BasicConfigurator.configure();
        	//thiết lập môi trường cho JJNDI
        	Properties settings=new Properties();
        	settings.setProperty(Context.INITIAL_CONTEXT_FACTORY,
        	"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        	settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
        	//tạo context
        	Context ctx=new InitialContext(settings);
        	//lookup JMS connection factory
        	Object obj=ctx.lookup("ConnectionFactory");
        	ConnectionFactory factory=(ConnectionFactory)obj;
        	//lookup destination
        	Destination destination
        	=(Destination) ctx.lookup("dynamicQueues/thanthidet");
        	//tạo connection
        	Connection con=factory.createConnection("admin","admin");
        	//nối đến MOM
        	con.start();
        	//tạo session
        	Session session=con.createSession(
        	/*transaction*/false,
        	/*ACK*/Session.CLIENT_ACKNOWLEDGE
        	);
        	//tạo consumer
        	MessageConsumer receiver = session.createConsumer(destination);
            receiver.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					/*
					 * try { ObjectMessage om= (ObjectMessage) message;
					 * 
					 * XMLConvert<Benhnhan> convert = new XMLConvert<Benhnhan>(); Benhnhan benhnhan
					 * = convert.xml2Object(message.Te) lstBenhNhan.Invoke(new Action(()=>
					 * lstBenhNhan.Items.Add(benhnhan.hoten) )); ds.Add(benhnhan); } catch
					 * (Exception e) { // TODO: handle exception }
					 */
					
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
   private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
	   int row = jTable1.getSelectedRow();
       if (row >= 0) {

           txtmsbn.setText(jTable1.getValueAt(row, 0).toString());
           txtcmnd.setText(jTable1.getValueAt(row, 1).toString());
           txthoten.setText(jTable1.getValueAt(row, 2).toString());
           txtdiachi.setText(jTable1.getValueAt(row, 3).toString());
           //tbGioihang.setValueAt(tbLinhkien.getValueAt(row, 4), 0, 0);
       }
    }//GEN-LAST:event_jTable1MouseClicked


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtmsbn = new javax.swing.JTextField();
        txtcmnd = new javax.swing.JTextField();
        txthoten = new javax.swing.JTextField();
        txtdiachi = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btncapnhat = new javax.swing.JButton();
        btnGoikham = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sach benh nhan"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "MSBN", "CMND", "Ho ten", "Dia chi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(219, 219, 219))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thong tin benh nhan"));

        jLabel1.setText("Ma so benh nhan");

        jLabel2.setText("So CMND");

        jLabel3.setText("Ho ten");

        jLabel4.setText("Dia chi");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Noi dung kham benh"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtmsbn, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtcmnd))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtdiachi))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txthoten))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtmsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtcmnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btncapnhat.setText("Cap nhat thong tin kham benh");

        btnGoikham.setText("Goi kham");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnGoikham))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncapnhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncapnhat))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnGoikham)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(bacsi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bacsi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bacsi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bacsi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bacsi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGoikham;
    private javax.swing.JButton btncapnhat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtcmnd;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtmsbn;
    // End of variables declaration//GEN-END:variables
}
