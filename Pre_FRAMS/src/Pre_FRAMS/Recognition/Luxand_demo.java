/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pre_FRAMS.Recognition;

import Luxand.*;
import Luxand.FSDK.*;
import Luxand.FSDKCam.*;
/**
 *
 * @author Ray24
 */
public class Luxand_demo {

    /**
     * @param args the command line arguments
     */
    FSDK_FaceTemplate.ByReference template_main=new FSDK_FaceTemplate.ByReference();
    FSDK_FaceTemplate.ByReference template_test=new FSDK_FaceTemplate.ByReference();
   static String Train=null;
   static String Test= null;
  static String matched="failure";
  public Luxand_demo(String train,String test)
   {
   this.Train=train;
   this.Test=test;
   }
  
  
    
    public Luxand_demo()
    {
        
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
    }
    public static void main(String[] args)  {
        // TODO code application logic here
        Luxand_demo ld=new Luxand_demo();
        HImage main = new HImage();
        HImage test= new HImage();
        //FSDK.CreateEmptyImage(test);
        int i=FSDK.LoadImageFromFile(test,Test);
        int j=FSDK.LoadImageFromFile(main,Train);
        
        if(i==FSDK.FSDKE_OK && j==FSDK.FSDKE_OK)
        {
            ld.check(main,test);
        }
        
    }
    public void check(FSDK.HImage main,FSDK.HImage test)
    {
        float MatchingThreshold[]=new float[1], Similarity[]=new float[1];
        FSDK.SetFaceDetectionParameters(false, false, 100);

        int tp1 = FSDK.GetFaceTemplate(main,template_main);
        int tp2=FSDK.GetFaceTemplate(test,template_test);
       
        FSDK.GetMatchingThresholdAtFRR(0.95f,MatchingThreshold);
        FSDK.MatchFaces(template_main, template_test, Similarity);
       // System.out.println("similarity="+Similarity[0]);
        //System.out.println("Thresold="+MatchingThreshold[0]);
        
        if (Similarity[0] > MatchingThreshold[0]) 
        {
            System.out.println("Same Person\n"+Train);
            matched="success";
        }
        else
        {
            matched="failure";
           // System.out.println("Different Person\n");
        }
        
    }
    public String getMatchedStatus()
    {
    return this.matched;
    }
}
