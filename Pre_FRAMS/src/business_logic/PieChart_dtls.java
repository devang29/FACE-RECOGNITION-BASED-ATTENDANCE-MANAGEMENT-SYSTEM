/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business_logic;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ray24
 */
public class PieChart_dtls {
    public int absent=0,present=0;
    static Connection con=null;
   static Statement stmt=null,stmt2=null;
    static ResultSet rs=null,rs1=null;
    public PieChart_dtls()
    {
        MySQL sql=new MySQL();
       try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(sql.HOST,sql.USERNAME,sql.PASSWORD);
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
         stmt2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
        }
        catch(ClassNotFoundException | SQLException cnfe)
        {
            System.out.println(cnfe.getMessage());
        }
    }
    public void getcount(String stuid)
    {
        try 
        {
            
            rs=stmt.executeQuery("select count(*) from attendance where attendance='absent' and stu_id='"+stuid+"'");
            if(!rs.isBeforeFirst())
            {
                rs.beforeFirst();
            }
            while(rs.next())
            {
                absent=rs.getInt(1);
            }
            
            rs=stmt.executeQuery("select count(*) from attendance where attendance='present' and stu_id='"+stuid+"'");
            if(!rs.isBeforeFirst())
            {
                rs.beforeFirst();
            }
            while(rs.next())
            {
                present=rs.getInt(1);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(PieChart_dtls.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
