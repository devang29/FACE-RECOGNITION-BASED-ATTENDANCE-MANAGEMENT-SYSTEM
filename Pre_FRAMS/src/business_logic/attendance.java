/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business_logic;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Ray24
 */
public class attendance {
    Connection con=null;
    Statement stmt=null,stmt1=null,stmt2=null,stmt3=null;
    ResultSet rs=null,rs1=null;
    student st=null;
    public attendance()
    {
        MySQL sql=new MySQL();
        st=new student();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(sql.HOST,sql.USERNAME,sql.PASSWORD);
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            stmt2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            stmt3=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stmt.executeQuery("select * from attendance");  
            
        }
        catch(ClassNotFoundException | SQLException cnfe)
        {
            System.out.println(cnfe.getMessage());
        }
    }
    
     public void trigger(String deptid,int sem,String div)
    {
        
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date d=new Date();
        String date = (String) df.format(d);//.toString().subSequence(0,8);
        int period=this.getPeroidfromTime();
        System.out.println("Trigger--date--"+date+"period--"+period);
        ResultSet ss=null,ss1=null;
        String stid[]=st.getStudentIDbyDeptandSem(deptid,sem,div);
        if(period==0)
        {
            return;
        }
    try{
        
        if(stid.length!=0)
        {
            for(int h=0;h<stid.length;h++)
            {
              try{
       
    ss=stmt1.executeQuery("select DAYNAME('"+date+"') from subject");
       
    ss.next();
     
    String day=ss.getString(1);
    
    if(!day.equals("Sunday"))
    {
    System.out.println("Day:"+day);
    ss1=stmt2.executeQuery("select * from schedule");
    while(ss1.next())
    {
       String dy=ss1.getString(4);
       String depti=ss1.getString(1);
       String dv=ss1.getString(3);
       int s=ss1.getInt(2);
       int periodNo=ss1.getInt(6);
       if(dy.equals(day)&&period==periodNo&&dv.equals(div)&&depti.equals(deptid)&&s==sem)
       {
       if(rs.isClosed())
       {
           rs=stmt.executeQuery("select * from attendance");
       }
       rs.moveToInsertRow();
       rs.updateString(1, stid[h]);
       rs.updateString(2, ss1.getString(7));
       rs.updateString(3,"absent");
       rs.updateString(4, ss1.getString(8));
       rs.updateInt(5,period);
       rs.updateString(6,date);
       rs.insertRow();
           
       }
    }
    }
    //rs1=stmt.executeQuery("select * from attendance");
    }
    catch(SQLException e)
    {
        //e.printStackTrace();
    }
    
            }
        }
    
    
    }
    catch(Exception e)
    {
   // e.printStackTrace();
    }
    
    }
     
    public int getPeroidfromTime()
    {
        int pn=0;
        Date d=new Date();
        String s=new Time(d.getTime()).toString();
        float t=Float.parseFloat(s.substring(0,2))+Float.parseFloat(s.substring(3,5))/100;

        System.out.println(t);
        if(7.55<t && 8.55>t)
        {
            pn=1;
        }
        else if(8.55<t && 9.55>t)
        {
            pn=2;
        }
        else if(10.00<t && 11.00>t)
        {
            pn=3;
        }
        else if(11.00<t && 12.00>t)
        {
            pn=4;
        }
        else if(13.05<t && 14.05>t)
        {
            pn=5;
        }
        else
        {
            pn=0;
        }
        return pn;
    } 
    
     public void markAttendance(String stuid)
    {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date d=new Date();
        String date = (String) df.format(d);//.toString().subSequence(0,8);
        
        int period=this.getPeroidfromTime();
        System.out.println("Date="+date+" period="+period);
        ResultSet ss=null,ss1=null;
        
        if(period==0)
        {
            return;
        }
    
              try{
                  
       
    ss=stmt1.executeQuery("select DAYNAME('"+date+"') from subject");
       
    ss.next();
     
    String day=ss.getString(1);
//    if(!rs1.isBeforeFirst())
//    {
//        rs1.beforeFirst();
//    }
    if(!day.equals("Sunday"))
    {
    System.out.println("Day:"+day);
    int r=stmt3.executeUpdate("update attendance set attendance='present' where stu_id='"+stuid+"' and period_no="+period+" and date='"+date+"'");
//    while(rs1.next())
//    {
//       String stid=rs1.getString(1);
//       String da=rs1.getString(6);
//       int periodno=rs1.getInt(5);
       if(r==1)
       {
//            rs1.updateString(3,"present");
//            rs1.updateRow();
            System.out.println("present marked");
       }
//    }
    }
    }
    catch(SQLException e)
    {
        e.printStackTrace();
    }
    
  }
    
    
}
