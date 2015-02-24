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
import business_logic.department;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.*;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
 
/**
 *
 * @author Hans Kristanto
 */
public class aggregate_att {
 
    Connection conn = null;
 ResultSet rs=null,rs1=null,rs4=null;
 Statement stmt=null,stmt1=null;
 HashMap m=new HashMap();
   
    public aggregate_att(){
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
            stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             stmt1=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
           // int r=stmt.executeUpdate("create table dummy_a(stu_id varchar(20),s_name varchar(55),percentage varchar(10))");
        int r=stmt.executeUpdate("TRUNCATE dummy_a");
             rs4=stmt.executeQuery("select * from dummy_a");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    public JPanel showReport(String deptid,int sem,String div)
    {
        JRViewer jv = null;
        department dept=new department();
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
        String reportName = "/reports/aggregate_attendance.jasper";
         
        //Get a stream to read the file
        InputStream is = getClass().getResourceAsStream(reportName);
 
        try {
            m.put("dept_name",dept.getDept(""+deptid));
            m.put("semester",""+sem);
             m.put("division",div);
             m.put("REPORT_CONNECTION",conn);

            //Fill the report with parameter, connection and the stream reader    
         rs=stmt1.executeQuery("select * from student where dept_id='"+deptid+"' and semester='"+sem+"' and division='"+div+"'");
         while(rs.next())
         {
          String s=rs.getString("stu_id");
         rs1=st.executeQuery("select * from attendance where stu_id='"+s+"'");
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
            JasperPrint jp = JasperFillManager.fillReport(is,m, conn);
         
     //Viewer for JasperReport
            jv = new JRViewer(jp);
     
     
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jv;
    } 
}

