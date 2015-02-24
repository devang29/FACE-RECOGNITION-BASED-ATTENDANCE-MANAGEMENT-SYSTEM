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
public class department {
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    public department()
    {
       try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","bunty77");
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stmt.executeQuery("select * from department");
        }
        catch(ClassNotFoundException | SQLException cnfe)
        {
            System.out.println(cnfe.getMessage());
        }
    }
    
    public boolean insert(String deptid,String deptname)
    {
        boolean i=false;
        try 
        {
            
            rs.moveToInsertRow();
            rs.updateString(1, deptid);
            rs.updateString(2, deptname);
            
            rs.insertRow();
            i=true;
        } 
        catch (SQLException ex) {i=false;}         
        return i;
    }
    
    public boolean delete(String deptid)
    {
        boolean i=false;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(deptid))
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
    
    public String[] getAllDept()
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
    
    public String getDept(String deptid)
    {
        String data=null;
        
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(deptid))
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
    
    public String getDeptID(String dept)
    {
        String data=null;
        
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(2);
                if(id.equals(dept))
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
    
    public boolean update(String deptid,String deptname)
    {
        boolean i=false;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(deptid))
                {
                    rs.updateString(2, deptname);
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
