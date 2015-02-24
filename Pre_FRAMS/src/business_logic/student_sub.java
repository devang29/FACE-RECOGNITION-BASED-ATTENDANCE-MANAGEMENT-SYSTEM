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
public class student_sub {
    
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    
    public student_sub()
    {
        MySQL sql=new MySQL();
       try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(sql.HOST,sql.USERNAME,sql.PASSWORD);
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stmt.executeQuery("select * from stu_sub");
        }
        catch(ClassNotFoundException | SQLException cnfe)
        {
            System.out.println(cnfe.getMessage());
        }
    }
    
    public boolean insert(String stuid,String subid)
    {
        boolean i=false;
        try 
        {
            
            rs.moveToInsertRow();
            rs.updateString(1, stuid);
            rs.updateString(2, subid);
            
            rs.insertRow();
            i=true;
        } 
        catch (SQLException ex) {i=false;}         
        return i;
    }
    
    public boolean update(String stuid,String subid)
    {
        boolean i=false;
        try 
        {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                String s=rs.getString(2);
                if(id.equals(stuid))
                {
                    rs.updateString(1, stuid);
                    rs.updateString(2, subid);
                    rs.updateRow();
                    i=true;
                    break;
                }
            }
            
        } 
        catch (SQLException ex) {i=false;}         
        return i;
    }
    
    public boolean delete(String stuid)
    {
        boolean i=false;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(stuid))
                {
                    rs.deleteRow();
                    i=true;
                }
            }
        } 
        catch (SQLException ex) {
            i=false;
        }
        return i;
    }
    
    public String[] getSubIDbyStudentID(String stuid)
    {
        String data[]=null;
        int i=0;
        try {
            //rs.last();
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
              String id=rs.getString(1);
              if(id.equals(stuid))
              {    
              //data[i]=rs.getString(2);
              i=i+1;
              }
            }
            data=new String[i];
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            i=0;
            while(rs.next())
            {
              String id=rs.getString(1);
              if(id.equals(stuid))
              {    
              data[i]=rs.getString(2);
              i=i+1;
              }
            }
        } 
        catch (SQLException ex) {
            data=null;
        }
        return data;
    }
    
    public String[] getStudentIDsbySubID(String subid)
    {
        String data[]=null;
        int i=0;
        try {
            //rs.last();
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
              String id=rs.getString(2);
              if(id.equals(subid))
              {    
              //data[i]=rs.getString(2);
              i=i+1;
              }
            }
            data=new String[i];
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            i=0;
            while(rs.next())
            {
              String id=rs.getString(2);
              if(id.equals(subid))
              {    
              data[i]=rs.getString(1);
              i=i+1;
              }
            }
        } 
        catch (SQLException ex) {
            data=null;
        }
        return data;
    }
}
