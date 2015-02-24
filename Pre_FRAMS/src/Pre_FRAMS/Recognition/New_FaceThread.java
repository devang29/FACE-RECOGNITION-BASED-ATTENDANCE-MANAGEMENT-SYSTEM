/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pre_FRAMS.Recognition;

import Luxand.FSDK.*;
import Luxand.*;
import Luxand.FSDKCam.*;
import business_logic.attendance;
import com.googlecode.javacv.CanvasFrame;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Jinkal
 */
public class New_FaceThread implements Runnable
{
    attendance att=null;
    String dir=null,file=null;
     public New_FaceThread(String dir,String file)
    {
        this.dir=dir;
        this.file=file;
        try {
            int r = FSDK.ActivateLibrary("pgOuS+8O/Dzd5Nx0ICc5aGqEsVMp2WvgccrSy7V533igDo2t+rYe/nhl/JPGbhKj7KWxGq8lpnbpDvzZMwLSjr65biVR0cOcLe7A7MXDM+IoZV9UA6SbDOYeTsCtSQNTU9Hyvsrf/RaZO9375FagzEOwpr5SCKn3/J19qxUi/Rk=");
            if (r != FSDK.FSDKE_OK){
                System.out.println("Please run the License Key Wizard (Start - Luxand - FaceSDK - License Key Wizard) Error activating FaceSDK"); 
                System.exit(r);
            }
        } 
        catch(java.lang.UnsatisfiedLinkError e) {
            System.out.println("Link Error");
            System.exit(1);
        }    
            
        FSDK.Initialize();
        this.run();
    }
public String searchIm(String dir,String file)
{
String mc=null;
File f=new File(dir);
try{
String sf[]=f.list();
for(int i=0;i<sf.length;i++)
{
File ff=new File(f,sf[i]);
    //System.out.println(""+sf[i]);
if(ff.isFile() && !ff.getName().equals("Thumb.db"))
{

boolean status=check(dir+"\\"+ff.getName(),file);

if(status==true)
{
    mc=dir+"\\"+ff.getName();
    String sid=ff.getName();
    sid=sid.substring(0,sid.length()-4);
    System.out.println(sid);
    att=new attendance();
    att.markAttendance(sid);
    System.out.println("Matched With::"+sid);
    break;
}
}
}

}
catch(Exception e)
{
e.printStackTrace();
}

return mc;
}

public void run()
{
String s=searchIm(dir,file);

System.out.println("Recognized:-"+s);
try
{   
 if(!(s==null)){
CanvasFrame cf=new CanvasFrame("RecognizedImage");
 
    JLabel jl=new JLabel();
    //BufferedImage b=ImageIO.read(new File(dir+"\\"+so.getRecognizedId()+"\\"+so.getRecognizedId()+"-0.jpg")); 
    jl.setIcon(new ImageIcon(s));
     cf.add(jl);
     cf.setSize(250, 250);
     
   cf.setVisible(true);
Thread.sleep(1000);
   cf.dispose();
}
//    File f=new File("face_IDs.txt");
//    ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f));
//    oos.writeObject(face_Ids);
   
}
catch(Exception e)
{
e.printStackTrace();
System.out.println("Exception in Creating FaceId file"+e.getMessage());
}

}
    public boolean check(String Train,String Test)
    {
        HImage main = new HImage();
        HImage test= new HImage();
        FSDK_FaceTemplate.ByReference template_main=new FSDK_FaceTemplate.ByReference();
        FSDK_FaceTemplate.ByReference template_test=new FSDK_FaceTemplate.ByReference();
        
        int i=FSDK.LoadImageFromFile(test,Test);
        int j=FSDK.LoadImageFromFile(main,Train);
        float MatchingThreshold[]=new float[1], Similarity[]=new float[1];
        FSDK.SetFaceDetectionParameters(false, false, 100);
         

        int tp1 = FSDK.GetFaceTemplate(main,template_main);
        int tp2=FSDK.GetFaceTemplate(test,template_test);
       
        FSDK.GetMatchingThresholdAtFRR(0.95f,MatchingThreshold);
        FSDK.MatchFaces(template_main, template_test, Similarity);
       
        
        
        if (Similarity[0] > MatchingThreshold[0]) 
        {
            System.out.println("Same Person\n"+Train);
            return true;
        }
        return false;
        
    }
    
}
