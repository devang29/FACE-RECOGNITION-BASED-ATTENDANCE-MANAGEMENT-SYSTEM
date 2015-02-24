/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pre_FRAMS;

/**
 *
 * @author Ray24
 */
import business_logic.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import javax.sql.rowset.CachedRowSet;
import reports.Sample_Faculty;

class StudentThread extends Thread
{
   Socket s=null;
   ObjectOutputStream oos=null;
   ObjectInputStream ois=null;
   Connection con=null;
   Statement stmt=null;
   ResultSet rs=null;
   public StudentThread(Socket s)
   {
    this.s=s;
	try
	 {
	   oos=new ObjectOutputStream(s.getOutputStream());
	   oos.writeObject("Good Morning...");
	   ois=new ObjectInputStream(s.getInputStream());
           
	 }
	 catch(IOException ioe)
	 {
	   System.out.println("Write Error : "+ioe.getMessage());
	 }
	 this.start();
   }
  public void run()
   {
     try
	 {
             String action=(String) ois.readObject();
           if(action.equals("Forgot Password"))
           {
               user lg=new user();
               String id=(String)ois.readObject();
               String email=(String)ois.readObject();
               student st=new student();
               if(st.getEmail(id).equals(email))
               {
                   oos.writeObject("true");
                   String pwd=lg.getPassword(id);
                   SendEmailOperation seo=new SendEmailOperation();
                   String email_body="Hello "+lg.getFName(id)+" "
                           +lg.getLName(id)+"\n\n"
                           + "Your UserID :-"+id+"\nPassword :-"+pwd
                           + "\n\nThank and Regards,"
                           + "\nFRAMS TEAM.";
                   seo.sendEmail(email, "PassWord Recovery", email_body);
               }
               else
               {
                   oos.writeObject("false");
               }
           }
           if(action.equals("Login"))
           {
	   String un=(String)ois.readObject();
           String pass=(String)ois.readObject();
	   String ustype=(String)ois.readObject();
           user lg=new user();
           String d=lg.check(un, pass, ustype);
	   //System.out.println(d);
           oos.writeObject(d);
           
           if(d.equals("Success"))
           {
               if(ustype.equals("Student"))
               {
                   student stu=new student();
                   department dept=new department();
                   //System.out.println(lg.getFName(un)+" "+lg.getLName(un)+" "+dept.getDept(""+stu.getDeptID(un))+" "+stu.getSemester(un));
                   oos.writeObject(lg.getFName(un));
                   oos.writeObject(lg.getLName(un));
                   oos.writeObject(dept.getDept(""+stu.getDeptID(un)));
                   oos.writeObject(stu.getResultSet(un));
                   PieChart_dtls ds=new PieChart_dtls();
                   ds.getcount(un);
                   oos.writeObject(ds.absent);
                   oos.writeObject(ds.present);
                   oos.writeObject(stu.getJTableResultSet(un));
                   while(true)
                   {
                   String msg=(String)ois.readObject();
                   if(msg.equals("Change Password"))
                   {
                       while(true)
                       {
                           lg=new user();
                       String old_pwd=(String)ois.readObject();
                       if(old_pwd.equals("break"))
                       {
                           break;
                       }
                       if(lg.getPassword(un).equals(old_pwd))
                       {
                           oos.writeObject("Success");
                           String newp=(String)ois.readObject();
                           if(newp.equals("break"))
                           {
                              break;
                           }
                           boolean flg=lg.update(un,lg.getFName(un),lg.getLName(un),newp, ustype);
                           oos.writeObject(""+flg);
                           
                       }
                       else
                       {
                           oos.writeObject("Failure");
                       }
                       }
                   }
                   else if(msg.equals("break"))
                   {
                       break;
                   }
                   }
               }
               else if(ustype.equals("Faculty"))
               {
                   faculty fac=new faculty();
                   subject sub=new subject();
                   faculty_sub fac_sub=new faculty_sub();
                   department dept=new department();
                   String deptname[]=dept.getAllDept(),deptid[]=null;
                   
                   String subname[]=null;
                   String subid[]=fac_sub.getSubIDbyFacultyID(un);
                   if(deptname.length!=0)
                   {
                       deptid=new String[deptname.length];
                       for(int i=0;i<deptname.length;i++)
                       {
                           deptid[i]=dept.getDeptID(deptname[i]);
                       }
                   }
                   
                   if(subid.length!=0)
                   {
                       subname=new String[subid.length];
                       for(int i=0;i<subid.length;i++)
                       {
                           subname[i]=sub.getSub(subid[i]);
                       }
                   }
                   oos.writeObject(lg.getFName(un));
                   oos.writeObject(lg.getLName(un));
                   oos.writeObject(dept.getDept(fac.getDeptID(un)));
                   oos.writeObject(fac.getResultSet(un));
                   oos.writeObject(deptid);
                   oos.writeObject(deptname);
                   oos.writeObject(subid);
                   oos.writeObject(subname);
                   while(true)
                   {
                   String msg=(String)ois.readObject();
                   if(msg.equals("ReportGen"))
                   {
                       String rfacid=(String)ois.readObject();
                       String rfacname=(String)ois.readObject();
                       String rdeptname=(String)ois.readObject();
                       int sem=Integer.parseInt((String)ois.readObject());
                       String div=(String)ois.readObject();
                       String rdeptid=dept.getDeptID(rdeptname);
                       String rsubname=(String)ois.readObject();
                       String rsubid=sub.getSubID(rsubname);
                       String s_date=(String)ois.readObject();
                       String e_date=(String)ois.readObject();
                       Sample_Faculty sf=new Sample_Faculty();
                     //  System.out.println(rdeptid+" "+rdeptname+" "+sem+" "+div+" "+rsubid+" "+rsubname+" "+rfacid+" "+rfacname+" "+s_date+" "+e_date);
                       CachedRowSet Report = sf.showReport(rdeptid, rdeptname,sem,div, rsubid, rsubname, rfacid, rfacname, s_date, e_date);
                       oos.writeObject(Report);
                   }
                   if(msg.equals("Change Password"))
                   {
                       while(true)
                       {
                           lg=new user();
                       String old_pwd=(String)ois.readObject();
                       if(old_pwd.equals("break"))
                       {
                           break;
                       }
                       //lg=new user();
                       if(lg.getPassword(un).equals(old_pwd))
                       {
                           oos.writeObject("Success");
                           String newp=(String)ois.readObject();
                           if(newp.equals("break"))
                           {
                              break;
                           }
                           boolean flg=lg.update(un,lg.getFName(un),lg.getLName(un),newp, ustype);
                           oos.writeObject(""+flg);
                           //lg=new user();
                       }
                       else
                       {
                           oos.writeObject("Failure");
                       }
                       
                       }
                   }
                   if(msg.equals("break"))
                   {
                       break;
                   }
                   }
               }
           }
           }
	 }
	 catch(IOException | ClassNotFoundException e)
	 {
           //java.util.logging.Logger.getLogger(StudentThread.class.getName()).log(java.util.logging.Level.SEVERE, null, e);  
	   //System.out.println("Error :"+e.getMessage());
	 }
   }
}
