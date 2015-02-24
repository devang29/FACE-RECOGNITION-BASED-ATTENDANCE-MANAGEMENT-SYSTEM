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
public class faculty_sub {
    
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    
    public faculty_sub()
    {
        MySQL sql=new MySQL();
       try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(sql.HOST,sql.USERNAME,sql.PASSWORD);
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stmt.executeQuery("select * from fac_sub");
        }
        catch(ClassNotFoundException | SQLException cnfe)
        {
            System.out.println(cnfe.getMessage());
        }
    }
    
    public boolean insert(String facid,String subid)
    {
        boolean i=false;
        try 
        {
            
            rs.moveToInsertRow();
            rs.updateString(1, facid);
            rs.updateString(2, subid);
            
            rs.insertRow();
            i=true;
        } 
        catch (SQLException ex) {i=false;}         
        return i;
    }
    
    public boolean delete(String facid,String subid)
    {
        boolean i=false;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                String s=rs.getString(2);
                if(id.equals(facid))
                {
                    if(s.equals(subid))
                {
                    rs.deleteRow();
                    i=true;
                    break;
                }
                }
            }
        } 
        catch (SQLException ex) {
            i=false;
        }
        return i;
    }
    
    public String[] getSubIDbyFacultyID(String facid)
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
              if(id.equals(facid))
              {    
             // data[i]=rs.getString(2);
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
              if(id.equals(facid))
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
    
    public String[] getFacultyIDbySubID(String subid)
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
                //    data[i]=rs.getString(1);
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
