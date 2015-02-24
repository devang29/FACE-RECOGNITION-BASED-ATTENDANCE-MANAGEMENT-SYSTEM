package Pre_FRAMS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ray24
 */

import java.net.*;
import java.io.*;
public class mainserver implements Runnable
{

    public mainserver() {
        new Thread(this).start();
    }
    
@Override
public void run()
 { 
   ServerSocket ss=null;
   Socket s=null;
   ObjectOutputStream oos=null;
   ObjectInputStream ois=null;
   try
   {
	 ss=new ServerSocket(1444);  
	 System.out.println("Server Created...");
   }
   catch(Exception e)
   {
     System.out.println("Server not Created : "+e.getMessage());
	 return;
   }
   while(true)
   {
     System.out.println("Waiting for Connection...");
	 try
	 {
	   s=ss.accept();
	   System.out.println("Connected...");
	   new StudentThread(s);
	 }
	 catch(IOException ioe)
	 {
	   System.out.println("Write Error : "+ioe.getMessage());
	 }
	 
   }
 }
}
