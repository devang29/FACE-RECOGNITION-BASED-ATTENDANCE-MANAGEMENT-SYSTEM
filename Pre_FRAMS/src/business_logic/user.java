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
public class user {
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    public user()
    {
        MySQL sql=new MySQL();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(sql.HOST,sql.USERNAME,sql.PASSWORD);
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stmt.executeQuery("select * from user");
        }
        catch(ClassNotFoundException | SQLException cnfe)
        {
          cnfe.printStackTrace();
        }
    }
    
    public boolean insert(String uid,String fname,String lname,String pwd,String usrtype)
    {
        boolean i=false;
        try 
        {
            
            rs.moveToInsertRow();
            rs.updateString(1, uid);
            rs.updateString(2, fname);
            rs.updateString(3, lname);
            rs.updateString(4, pwd);
            rs.updateString(5, usrtype);
            
            rs.insertRow();
            i=true;
        } 
        catch (SQLException ex) {i=false;}         
        return i;
    }
    
    public boolean delete(String uid)
    {
        boolean i=false;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(uid))
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
    
    public String getPassword(String uid)
    {
        String data=null;
        
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(uid))
                {
                    data=rs.getString(4);
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
    
    public String getUserType(String uid)
    {
        String data=null;
        
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(uid))
                {
                    data=rs.getString(5);
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
    
    public String getFName(String uid)
    {
        String data=null;
        
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(uid))
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
    
    public String getLName(String uid)
    {
        String data=null;
        
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(uid))
                {
                    data=rs.getString(3);
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
    
    public boolean update(String uid,String fname,String lname,String pwd,String usrtype)
    {
        boolean i=false;
        try {
            if(!rs.isBeforeFirst())
            {rs.beforeFirst();}
            while(rs.next())
            {
                String id=rs.getString(1);
                if(id.equals(uid))
                {
                    rs.updateString(2, fname);
                    rs.updateString(3, lname);
                    rs.updateString(4, pwd);
                    rs.updateString(5, usrtype);
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
    
    public String check(String un,String pass,String usrtype)
    {
          boolean flag1=false,flag2=false;
        try{
        
        while(rs.next())
        {
            
         if(un.equals(rs.getString("user_id")))
         {
             if(usrtype.equals(rs.getString("role")))
            {
             if(pass.equals(rs.getString("password")))
             {
                 flag1=true;
                 flag2=true;
                 return "Success";
                 
             }
             else
             {
                 flag1=true;
                 flag2=false;
                 return "Password is wrong";
                 
             }
         }
         else
         {
             flag1=true;
             flag2=false;
             return "UserID and Type mismatch";
         }
        }
        }
        if(flag1==false && flag2==false)
        {
            return "UserID does not exist...";
        }
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
            sq.printStackTrace();
        }
        
        return null;


    }
}
