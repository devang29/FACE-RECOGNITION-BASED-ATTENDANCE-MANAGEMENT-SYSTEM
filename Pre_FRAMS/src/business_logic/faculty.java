/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business_logic;

import com.sun.rowset.CachedRowSetImpl;
import java.io.InputStream;
import java.sql.*;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Ray24
 */
public class faculty {
    
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    
    public faculty()
    {
        MySQL sql=new MySQL();
       try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(sql.HOST,sql.USERNAME,sql.PASSWORD);
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stmt.executeQuery("select * from faculty");
        }
        catch(ClassNotFoundException | SQLException cnfe)
        {
            System.out.println(cnfe.getMessage());
        }
    }
    
    public CachedRowSet getResultSet(String un) {
        


    PreparedStatement pstmt = null;
    ResultSet rs1 = null;
    CachedRowSetImpl crsi = null;
    String sqlQuery = "select * from faculty where faculty_id='"+un+"'"; 


     try{

          // getting connection from connection pool
          pstmt = con.prepareStatement(sqlQuery); 

          /*Setting appropriate parameters for the Prepared Statement*/

          rs1 = pstmt.executeQuery();
  
          crsi = new CachedRowSetImpl();
          crsi.populate(rs1);       
             

      }catch(Exception exp){

            exp.printStackTrace();

      }
        return crsi;
    }
    
    public boolean insert(String facid,String deptid,InputStream img,int imglen)
    {
        boolean i=false;
        try 
        {            
            rs.moveToInsertRow();
            rs.updateString(1, facid);
            rs.updateString(2, deptid);
            rs.updateBinaryStream(3, img, imglen);
            
            rs.insertRow();
            i=true;
        } 
        catch (SQLException ex) {i=false;}         
        return i;
    }
    
    public InputStream getImage(String facid)
    {
        InputStream i=null;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(facid))
                {
                    i=rs.getBinaryStream(3);
                    break;
                }
            }
        } 
        catch (SQLException ex) {
            i=null;
        }
        return i;
    }
    
    public boolean delete(String facid)
    {
        boolean i=false;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(facid))
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
    
     public String[] getFacultyIDbyDept(String deptid)
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
                if(id.equals(deptid))
                {
             //       data[i]=rs.getString(1);
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
                if(id.equals(deptid))
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
       
    public String getDeptID(String facid)
    {
        String i=null;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(facid))
                {
                    i=rs.getString(2);
                    break;
                }
            }
        } 
        catch (SQLException ex) {
            i=null;
        }
        return i;
    }
    
    public boolean update(String facid,String deptid,InputStream img,int imglen)
    {
        boolean i=false;
        try 
        {  
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(facid))
                {
                    rs.updateString(2, deptid);
                    rs.updateBinaryStream(3, img, imglen);
                    rs.updateRow();
                    
                    i=true;
                    break;
                }
            }
        } 
        catch (SQLException ex) {i=false;}         
        return i;
    }
    
}
