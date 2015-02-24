/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pre_FRAMS.Recognition;

/**
 *
 * @author devang
 */
//
//import com.mathworks.toolbox.javabuilder.MWException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class SparseO {
 static String root_dir="C:\\Users\\devang\\Desktop\\Faces-2014-03-12\\Faces\\Computer Science\\8";
 static String file=null;
static String recognized=null;
static int eye_cordinates[][];
static Vector temp=new Vector();
//String args[];

 public SparseO(String dir,String file)
 {
 SparseO.root_dir=dir;
 SparseO.file=file;
 
 //SparseO.main(args);
 }

    /**
     * @param args the command line arguments
     */
// public static void getEyePositions()
// {
// int i=0;    
// searchIm(root_dir);
// String args[]=null;
// eye_cordinates=new int[temp.size()+1][4];
// for(i=0;i<temp.size();i++)
// {
// Eyes e=new Eyes((String)temp.get(i));
// Eyes.main(args);
// int ie[]=e.getCordinates();
//for(int j=0;j<4;j++)
//{
//eye_cordinates[i][j]=ie[j];
//
//}
// System.out.println(""+eye_cordinates[i][0]+" "+eye_cordinates[i][1]+" "+eye_cordinates[i][2]+" "+eye_cordinates[i][3]);
// }
//
// 
// Eyes e=new Eyes(file);
// Eyes.main(args);
// int es[]=e.getCordinates();
// eye_cordinates[i][0]=es[0];
// eye_cordinates[i][1]=es[1];
// eye_cordinates[i][2]=es[2];
// eye_cordinates[i][3]=es[3];
// }
 static void searchIm(String dir)
{

File f=new File(dir);
try{
String sf[]=f.list();
for(int i=0;i<sf.length;i++)
{
File ff=new File(f,sf[i]);
    //System.out.println(""+sf[i]);
if(ff.isFile() && !ff.getName().equals("Thumb.db"))
{
Luxand_demo ld=new Luxand_demo(dir+"\\"+ff.getName(),file);
   // System.out.println(""+ff.getAbsolutePath()+"\\"+ff.getName()+"-0.jpg");
String args[]=new String[5];
Luxand_demo.main(args);
String status=ld.getMatchedStatus();
if(status.equals("success"))
{
    SparseO.setRecognizedId(ff.getName());
    System.out.println("Matched With::  "+ff.getName());
    break;
}



}
}

}
catch(Exception e)
{
e.printStackTrace();
}


}
    public static void main(String[] args) {
        
        // TODO code application logic here
   //   root_dir ="C:\\Users\\devang\\Downloads\\Faces-2014-03-12\\Faces\\Computer Science\\8";
     //file ="C:\\Users\\devang\\Documents\\NetBeansProjects\\Demo\\savedFaces\\face5.jpg"; 
        
        try {
 
            searchIm(root_dir);
            
            
        //    SparseO.getEyePositions();
            
            
//            Object lhs[]=new Object[1];
//            Object rhs[]=new Object[3];
//            rhs[0]=(Object)root_dir;
//            rhs[1]=(Object)file;
//            rhs[2]=(Object)eye_cordinates;
//         
//            
//            Java_Mat.Java_mat ms=new Java_Mat.Java_mat();
//          ms.modified_sparse(lhs, rhs);
//            
//         System.out.println("REcognized::"+lhs[0]);      
//      setRecognizedId(lhs[0]);
     //       SparseC sp=new SparseC(root_dir,file);
     //     try {  sp.test();}
     //     catch(Exception e)
     //     {
     //     System.out.println("ICA2 Exception :   "+e.getMessage());
     //     e.printStackTrace();
     //     }
     //    }
     //    }
        }
        catch (Exception ex) {
            Logger.getLogger(SparseO.class.getName()).log(Level.SEVERE, null, ex);
        }
//System.exit(0);
    }
   static void setRecognizedId(String l)
    {
    SparseO.recognized=l;
    }
   String getRecognizedId()
   {
   return recognized;
   }
}