package Pre_FRAMS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ray24
 */

import business_logic.MySQL;
import java.awt.Dimension;
import java.awt.Frame;
import static java.awt.Frame.getFrames;
import java.awt.Toolkit;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
 
/**
 *
 * @author Hans Kristanto
 */
public class sample_report {
 
    Connection conn = null;
 
    public sample_report(){
        MySQL sql=new MySQL();
        String HOST = sql.HOST;//"jdbc:mysql://localhost:3306/project";
        String USERNAME = sql.USERNAME;//"root";
        String PASSWORD = sql.PASSWORD;//"bunty77";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    public void showReport(String name){
         
        //Path to your .jasper file in your package
        String reportName = "/reports/"+name+".jasper";
         
        //Get a stream to read the file
        InputStream is = getClass().getResourceAsStream(reportName);
 
        try {
     //Fill the report with parameter, connection and the stream reader    
            JasperPrint jp = JasperFillManager.fillReport(is,null, conn);
         
     //Viewer for JasperReport
            JRViewer jv = new JRViewer(jp);
     
     //Insert viewer to a JFrame to make it showable
            JFrame jf = new JFrame();
            jf.getContentPane().add(jv);
            jf.validate();
            jf.setVisible(true);
            jf.setSize(Toolkit.getDefaultToolkit().getScreenSize());
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            } });
            
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {                                   
        // TODO add your handling code here:
        Frame[] f=getFrames();
        for(int i=0;i<f.length;i++)
        {
            String sd=f[i].getName();
            if(sd.equals("Admin_main"))
            {
                f[i].setEnabled(true);
            }
        }
    }    
    public void showReport(String name,Map param){
         
        //Path to your .jasper file in your package
        String reportName = "/reports/"+name+".jasper";
         
        //Get a stream to read the file
        InputStream is = getClass().getResourceAsStream(reportName);
 
        try {
     //Fill the report with parameter, connection and the stream reader    
            JasperPrint jp = JasperFillManager.fillReport(is,param, conn);
            
     //Viewer for JasperReport
            JRViewer jv = new JRViewer(jp);
            
     //Insert viewer to a JFrame to make it showable
            JFrame jf = new JFrame();
            jf.getContentPane().add(jv);
            jf.validate();
            jf.setVisible(true);
            jf.setSize(Toolkit.getDefaultToolkit().getScreenSize());
            jf.setLocationRelativeTo(null);
            //jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            } });
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
 
}

