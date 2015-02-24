/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business_logic;

import com.sun.rowset.CachedRowSetImpl;
import java.io.InputStream;
import java.sql.*;
import java.util.Vector;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Ray24
 */
public class student {
    
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    
    public student()
    {
        MySQL sql=new MySQL();
       try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(sql.HOST,sql.USERNAME,sql.PASSWORD);
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stmt.executeQuery("select * from student");
        }
        catch(ClassNotFoundException | SQLException cnfe)
        {
            System.out.println(cnfe.getMessage());
        }
    }
    
    public boolean insert(String stuid,int sem,String deptid,String div,InputStream img,int imglen,Long mobile,String email)
    {
        boolean i=false;
        try 
        {            
            rs.moveToInsertRow();
            rs.updateString(1, stuid);
            rs.updateInt(2, sem);
            rs.updateString(3, deptid);
            rs.updateString(4, div);
            rs.updateBinaryStream(5, img,imglen);
            rs.updateLong(6, mobile);
            rs.updateString(7, email);
                        
            rs.insertRow();
            i=true;
        } 
        catch (SQLException ex) {i=false;
            System.out.println("SQLError :"+ex.getMessage());
        }         
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
                    break;
                }
            }
        } 
        catch (SQLException ex) {
            i=false;
        }
        return i;
    }
    
     public String[] getStudentIDbyDeptandSem(String deptid,int sem,String div)
    {
        String data[]=null;
        int i=0;
        
        try {
            //rs.last();
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(3);
                int s=rs.getInt(2);
                String d=rs.getString(4);
                if(id.equals(deptid))
                {
                    if(s==sem)
                    {
                        if(d.equals(div))
                        {
                            //data[i]=rs.getString(1);
                            i=i+1;
                        }
                    }
                }
            }
            data=new String[i];
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            i=0;
            while(rs.next())
            {
                String id=rs.getString(3);
                int s=rs.getInt(2);
                String d=rs.getString(4);
                if(id.equals(deptid))
                {
                    if(s==sem)
                    {
                        if(d.equals(div))
                        {
                            data[i]=rs.getString(1);
                            i=i+1;
                        }
                    }
                }
            }
        } 
        catch (SQLException ex) {
            data=null;
        }
        return data;
    }
     
    public int getSemester(String stuid)
    {
        int i=0;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(stuid))
                {
                    i=rs.getInt(2);
                    break;
                }
            }
        } 
        catch (SQLException ex) {
            i=0;
        }
        return i;
    }
    
    public String getDeptID(String stuid)
    {
        String i=null;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(stuid))
                {
                    i=rs.getString(3);
                    break;
                }
            }
        } 
        catch (SQLException ex) {
            i=null;
        }
        return i;
    }
    
    public String getDivision(String stuid)
    {
        String i=null;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(stuid))
                {
                    i=rs.getString(4);
                    break;
                }
            }
        } 
        catch (SQLException ex) {
            i=null;
        }
        return i;
    }
    
    public InputStream getImage(String stuid)
    {
        InputStream i=null;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(stuid))
                {
                    i=rs.getBinaryStream(5);
                    break;
                }
            }
        } 
        catch (SQLException ex) {
            i=null;
        }
        return i;
    }
    
    public String getMobile(String stuid)
    {
        String i=null;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(stuid))
                {
                    i=rs.getString(6);
                    break;
                }
            }
        } 
        catch (SQLException ex) {
            i=null;
        }
        return i;
    }
    
    public String getEmail(String stuid)
    {
        String i=null;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(stuid))
                {
                    i=rs.getString(7);
                    break;
                }
            }
        } 
        catch (SQLException ex) {
            i=null;
        }
        return i;
    }
    
    public boolean update(String stuid,int sem,String deptid,String div,InputStream img,int imglen,Long mobile,String email)
    {
        boolean i=false;
        try 
        {  
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(stuid))
                {
                    rs.updateInt(2, sem);
                    rs.updateString(3, deptid);
                    rs.updateString(4, div);
                    rs.updateBinaryStream(5, img,imglen);
                    rs.updateLong(6, mobile);
                    rs.updateString(7, email);
                    rs.updateRow();
                    
                    i=true;
                    break;
                }
            }
        } 
        catch (SQLException ex) {i=false;}         
        return i;
    }
     public Vector getJTableResultSet(String un) {
        
int present_counter=0,absent_counter=0;
double percentage=0;
    Statement stmt = null,stmt1=null,stmt2=null;
    ResultSet rs1 = null,rs2=null,rs=null;
    CachedRowSetImpl crsi = null;
Vector sub_name=new Vector();
     Vector att=new Vector();
     Vector total=new Vector();
    String subQuery="select sub_id from stu_sub where stu_id='"+un+"'"; 
         
     try{
int i=0;
         
          stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
stmt2 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
         
          rs2=stmt1.executeQuery(subQuery);
          while(rs2.next()){
             String id=rs2.getString(1); 
         
             String sqlQuery = "select * from attendance"; 
          rs1 = stmt.executeQuery(sqlQuery);
          
          while(rs1.next())
          {
         
          if(rs1.getString(3).equals("absent")&&rs1.getString(1).equals(un)&&rs1.getString(2).equals(id))
          {
            
          absent_counter++;
          }
          if(rs1.getString(3).equals("present")&&rs1.getString(1).equals(un)&&rs1.getString(2).equals(id))
          {
                 present_counter++;
          }
          }
         
          rs=stmt2.executeQuery("select sub_name from subject where sub_id='"+id+"'");
       rs.next();
          sub_name.add(i, rs.getString(1));
        if(present_counter+absent_counter==0)absent_counter=1;
                
        percentage=((double)present_counter/(double)(present_counter+absent_counter))*100;      
        att.add(i,percentage);

          }
      
     total.add(0,sub_name);
     total.add(1,att);
            
       return total;      

      }catch(Exception exp){

            exp.printStackTrace();
return total;
      }
       
    }

   public CachedRowSet getResultSet(String un) {
        


    PreparedStatement pstmt = null;
    ResultSet rs1 = null;
    CachedRowSetImpl crsi = null;
    String sqlQuery = "select * from student where stu_id='"+un+"'"; 


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
    
}
