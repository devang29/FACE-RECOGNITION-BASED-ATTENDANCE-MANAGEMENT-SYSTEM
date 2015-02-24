/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pre_FRAMS.Recognition;

import Luxand.*;
import Luxand.FSDK;
import Luxand.FSDKCam.*;
//import Luxand.FSDKCam.TCameras;
/**
 *
 * @author Ray24
 */
public class Cam_dtls {
    
    public Cam_dtls()
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
        FSDKCam.InitializeCapturing();
    }
    
    public int getCamlist()
    {
        String camnull;
        int c[]=new int[1];
        TCameras t=new TCameras();
        FSDKCam.GetCameraList(t, c);
        return c[0];
    }
}
