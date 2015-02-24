/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pre_FRAMS.Recognition;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
/**
 *
 * @author devang
 */
public class DemoThread extends JFrame {

   
    JPanel recognizedimage,buttonpanel;
    JButton saveimage,rec;
    JLabel recname,saved;
    FaceCamRec livefeed;
    String name="";
    DemoThread(String fname)
    {
    super(fname);
    this.setVisible(true);
    this.setSize(650, 500);
    this.setLayout(new BorderLayout());


    livefeed=new FaceCamRec();
    livefeed.setSize(640,480);

    this.add(livefeed,BorderLayout.CENTER);
    livefeed.initcam();

    this.addWindowListener(new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {
             //To change body of generated methods, choose Tools | Templates.
        livefeed.closeDown();
        System.exit(0);
        }


});



    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       DemoThread d=new DemoThread("Webcam");
       
        
    }
}
