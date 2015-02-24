/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business_logic;

/**
 *
 * @author Ray24
 */
import java.sql.*;
public class subject {
    
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    
    public subject()
    {
        MySQL sql=new MySQL();
       try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(sql.HOST,sql.USERNAME,sql.PASSWORD);
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stmt.executeQuery("select * from subject");
        }
        catch(ClassNotFoundException | SQLException cnfe)
        {
            System.out.println(cnfe.getMessage());
        }
    }
    
    public boolean insert(String subid,String subname)
    {
        boolean i=false;
        try 
        {
            rs.moveToInsertRow();
            rs.updateString(1, subid);
            rs.updateString(2, subname);
            
            rs.insertRow();
            i=true;
        } 
        catch (SQLException ex) {i=false;}         
        return i;
    }
    
    public boolean delete(String subid)
    {
        boolean i=false;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(subid))
                {
                    rs.deleteRow();
                    i=true;
                    break;
                }
            }
        } 
        catch (SQLException ex) {
            i=false;
        }
        return i;
    }
    
    public String[] getAllSub()
    {
        String data[]=null;
        int i=0;
        try {
            rs.last();
            data=new String[rs.getRow()];
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
              data[i]=rs.getString(2);
              i=i+1;
            }
        } 
        catch (SQLException ex) {
            data=null;
        }
        return data;
    }
    
    public String getSub(String subid)
    {
        String data=null;
        
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(subid))
                {
                    data=rs.getString(2);
                    break;
                }
                else{data=null;}
            }
        } 
        catch (SQLException ex) {
            data=null;
        }
        return data;
    }
    
    public String getSubID(String sub)
    {
        String data=null;
        
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(2);
                if(id.equals(sub))
                {
                    data=rs.getString(1);
                    break;
                }
                else{data=null;}
            }
        } 
        catch (SQLException ex) {
            data=null;
        }
        return data;
    }
    
    public boolean update(String subid,String subname)
    {
        boolean i=false;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(subid))
                {
                    rs.updateString(2, subname);
                    rs.updateRow();
                    i=true;
                    break;
                }
            }
        } 
        catch (SQLException ex) {
            i=false;
        }
        return i;
    }
    
}
