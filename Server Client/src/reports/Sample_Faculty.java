package reports;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author devang
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ray24
 */
import com.sun.rowset.CachedRowSetImpl;
import java.awt.Dimension;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.*;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
 
/**
 *
 * @author Hans Kristanto
 */
public class Sample_Faculty {
 
    Connection conn = null;
 ResultSet rs=null,rs1=null,rs4=null;
 Statement stmt=null,stmt1=null;
 HashMap m=new HashMap();
   
    public Sample_Faculty(){
        
        String HOST = "jdbc:mysql://localhost:3306/project";
        String USERNAME = "root";
        String PASSWORD = "bunty77";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             stmt1=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
           // int r=stmt.executeUpdate("create table dummy_a(stu_id varchar(20),s_name varchar(55),percentage varchar(10))");
        int r=stmt.executeUpdate("TRUNCATE dummy_a");
         rs4=stmt.executeQuery("select * from dummy_a");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    public CachedRowSet showReport(String deptid,String deptname,int sem,String div,String subid,String subname,String facid,String facname,String sdate,String edate){
        
        CachedRowSetImpl crsi = null;
        //JRViewer jv=null;
        Statement st=null,st2=null;
        int pre_count=0,absent_count=0;
        double percentage=0;
        ResultSet rs2=null;
     try{ st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
     st2=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
     
     }
     catch(Exception e)
     {
     e.printStackTrace();
     }
        //Path to your .jasper file in your package
        //String reportName = ".\\reports\\faculty_sub_name.jasper";
         
        //Get a stream to read the file
        //InputStream is = this.getClass().getClassLoader().getResourceAsStream(reportName);
 
        try {
//              m.put("dept_name",deptname);
//              m.put("semester",""+sem);
//             m.put("division",div);
//             m.put("REPORT_CONNECTION",conn);
//             m.put("subject",subname);
//             m.put("faculty",facname);
//             m.put("s_date",sdate);
//             m.put("e_date",edate);
             

            //Fill the report with parameter, connection and the stream reader    
         rs=stmt1.executeQuery("select * from student where dept_id='CS' and semester='"+sem+"' and division='"+div+"'");
         while(rs.next())
         {
          String s=rs.getString("stu_id");
         rs1=st.executeQuery("select * from attendance where stu_id='"+s+"' and sub_id='"+subid+"' and faculty_id='"+facid+"'");
         while(rs1.next())
         {
         if(rs1.getString("attendance").equals("absent")){ absent_count++;}
         else{pre_count++;}
         }
         percentage=((double)pre_count/(double)(pre_count+absent_count))*100;
         rs2=st2.executeQuery("select * from user where user_id='"+s+"'");
         rs2.next();
         String sname=rs2.getString("f_name")+" "+rs2.getString("l_name");
         rs4.moveToInsertRow();
         rs4.updateString(1, s);
         rs4.updateString(2, sname);
         rs4.updateString(3, ""+percentage);
         rs4.insertRow();
         }
         Statement stm=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);;
         ResultSet rslt=stm.executeQuery("select * from dummy_a");
         
         crsi = new CachedRowSetImpl();
          crsi.populate(rslt);  
//            JasperPrint jp = JasperFillManager.fillReport(is,m,new JRResultSetDataSource(rslt));
         
     //Viewer for JasperReport
//            jv = new JRViewer(jp);
     
     //Insert viewer to a JFrame to make it showable
//            JFrame jf = new JFrame();
//            jf.getContentPane().add(jv);
//            jf.validate();
//            jf.setVisible(true);
//            jf.setSize(new Dimension(1000,800));
//            jf.setLocation(300,100);
//            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return crsi;
    }
    
    
 
    public static void main(String[] args) {
 
       Sample_Faculty ma = new Sample_Faculty();
        //ma.initConnection();
        ma.showReport("CS","Computer",6,"2","SUB1022","Information Security","FAC1001","Amit Chauhan","2014-04-01","2014-04-30");
       //CS Computer Science & Engineering 6 2 SUB1022 Information Security FAC1001 Amit Chauhan
    }
 
}

