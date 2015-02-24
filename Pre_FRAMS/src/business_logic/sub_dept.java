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
public class sub_dept {
    
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    
    public sub_dept()
    {
        MySQL sql=new MySQL();
       try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(sql.HOST,sql.USERNAME,sql.PASSWORD);
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stmt.executeQuery("select * from sub_dept");
        }
        catch(ClassNotFoundException | SQLException cnfe)
        {
            System.out.println(cnfe.getMessage());
        }
    }
    
    public boolean insert(String subid,String deptid,int sem)
    {
        boolean i=false;
        try 
        {
            
            rs.moveToInsertRow();
            rs.updateString(1, subid);
            rs.updateString(2, deptid);
            rs.updateInt(3, sem);
            
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
    
    public String[] getSubIDbyDept(String deptid)
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
              //data[i]=rs.getString(1);
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
    
    public String[] getSubIDbyDeptandSem(String deptid,int sem)
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
                int s=rs.getInt(3);
                if(id.equals(deptid))
                {
                    if(s==sem)
                    {
                        //data[i]=rs.getString(1);
                        i=i+1;
                    }
                }
            }
            data=new String[i];
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            i=0;
            while(rs.next())
            {
                String id=rs.getString(2);
                int s=rs.getInt(3);
                if(id.equals(deptid))
                {
                    if(s==sem)
                    {
                        data[i]=rs.getString(1);
                        i=i+1;
                    }
                }
            }
        } 
        catch (SQLException ex) {
            data=null;
        }
        return data;
    }
    
//    public boolean update(String subid,String deptid,int sem)
//    {
//        boolean i=false;
//        try {
//            if(!rs.isBeforeFirst())
//            {rs.beforeFirst();}
//            while(rs.next())
//            {
//                String id=rs.getString(1);
//                if(id.equals(subid))
//                {
//                    rs.updateString(2, deptid);
//                    rs.updateInt(3, sem);
//                    rs.updateRow();
//                    i=rs.rowUpdated();
//                    break;
//                }
//            }
//        } 
//        catch (SQLException ex) {
//            i=false;
//        }
//        return i;
//    }
    
}
