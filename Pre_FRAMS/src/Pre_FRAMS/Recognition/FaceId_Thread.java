/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pre_FRAMS.Recognition;
import com.googlecode.javacv.CanvasFrame;

import java.util.Vector;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 *
 * @author devang
 */
public class FaceId_Thread  {
static Vector face_Ids=new Vector(); 
String dir=null;
String detected_face=null;
SparseO so=null;
public FaceId_Thread(String dir,String file)
{
this.dir=dir;
this.detected_face=file;
this.run();

        
}
public void run()
{
    String arg[]=null;
so=new SparseO(dir,detected_face);
SparseO.main(arg);

String s=so.getRecognizedId();
if(!(s==null))
face_Ids.addElement(s);

System.out.println("Recognized:-"+s);
try
{   
 if(!(s==null)){
CanvasFrame cf=new CanvasFrame("RecognizedImage");
 
    JLabel jl=new JLabel();
    //BufferedImage b=ImageIO.read(new File(dir+"\\"+so.getRecognizedId()+"\\"+so.getRecognizedId()+"-0.jpg")); 
    jl.setIcon(new ImageIcon(dir+"\\"+so.getRecognizedId()));
     cf.add(jl);
     cf.setSize(250, 250);
     
   cf.setVisible(true);
Thread.sleep(1000);
   cf.dispose();
}
    File f=new File("face_IDs.txt");
    ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f));
    oos.writeObject(face_Ids);
   
}
catch(Exception e)
{
e.printStackTrace();
System.out.println("Exception in Creating FaceId file"+e.getMessage());
}

}
}
