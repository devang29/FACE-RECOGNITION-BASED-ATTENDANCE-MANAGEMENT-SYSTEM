/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business_logic;

import java.sql.*;

/**
 *
 * @author Ray24
 */
public class schedule 
{
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
     public schedule()
    {
       MySQL sql=new MySQL();
       try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(sql.HOST,sql.USERNAME,sql.PASSWORD);
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stmt.executeQuery("select * from schedule");
        }
        catch(ClassNotFoundException | SQLException cnfe)
        {
            System.out.println(cnfe.getMessage());
        }
    }
    
    public boolean insert(String deptid,int sem,String div,String day,int classno,int period,String subid,String facid,int dayid)
    {
        boolean i=false;
        try 
        {            
            rs.moveToInsertRow();
            rs.updateString(1, deptid);
            rs.updateInt(2, sem);
            rs.updateString(3, div);
            rs.updateString(4, day);
            rs.updateInt(5,classno);
            rs.updateInt(6, period);
            rs.updateString(7,subid);
            rs.updateString(8,facid);
            rs.updateInt(9, dayid);
                        
            rs.insertRow();
            i=true;
        } 
        catch (SQLException ex) {i=false;
            System.out.println("SQLError :"+ex.getMessage());
        }         
        return i;
    }
    
    public boolean update(String deptid,int sem,String div,String day,int classno,int period,String subid,String facid,int dayid)
    {
        boolean i=false;
        try 
        {   
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String did=rs.getString(1);
                int s=rs.getInt(2);
                String dv=rs.getString(3);
                String dy=rs.getString(4);
            if(did.equals(deptid))
            {
                if(s==sem)
                {
                 if(dv.equals(div))   
                 {
                     if(dy.equals(day))
                     {
                         if(rs.getInt(6)==period)
                         {
                             rs.updateInt(5,classno);
                             rs.updateString(7,subid);
                             rs.updateString(8,facid);
                             rs.updateInt(9, dayid);
                        
                             rs.updateRow();
                             i=true;
                             break;
                         }
                     }
                 }
                }
            }
            }
        } 
        catch (SQLException ex) {i=false;
            System.out.println("SQLError :"+ex.getMessage());
        }         
        return i;
    }
    
    public boolean delete(String deptid,int sem,String div)
    {
        boolean i=false;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String did=rs.getString(1);
                int s=rs.getInt(2);
                String dv=rs.getString(3);
                
            if(did.equals(deptid))
            {
                if(s==sem)
                {
                 if(dv.equals(div))   
                 {
                    rs.deleteRow();
                    i=true;
                    break; 
                 }
                }
            }
            }
        } 
        catch (SQLException ex) {
            i=false;
        }
        return i;
    }
}
