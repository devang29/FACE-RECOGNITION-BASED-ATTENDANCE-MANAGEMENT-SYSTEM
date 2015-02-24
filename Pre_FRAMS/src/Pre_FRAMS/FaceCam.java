/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pre_FRAMS;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import com.googlecode.javacv.*;
import com.googlecode.javacv.cpp.videoInputLib.*;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;
/**
 *
 * @author devang
 */
public class FaceCam extends JPanel implements Runnable
{
    BufferedImage S;
    FrameGrabber grabber;
    private static final int WIDTH = 640;  
 private static final int HEIGHT = 480; 
private static final int DELAY = 50;  // time (ms) between redraws of the panel

  private int CAMERA_ID = 1;

  private static final int IM_SCALE = 4;   
  private static final int SMALL_MOVE = 5;
  private static final int DETECT_DELAY = 200;   
                    // time (ms) between each face detection
  private static final int MAX_TASKS = 4;    
  private static final String FACE_CASCADE_FNM = //"/Pre_FRAMS/Recognition/haarcascade_frontalface_alt.xml";
                                                  "haarcascade_frontalface_alt.xml";



  // for saving a detected face image
  //String FACE_DIR = null,FACE_FNM = null;
  private static final int FACE_WIDTH = 250;
  private static final int FACE_HEIGHT = 250;
 private IplImage snapIm = null;
  private volatile boolean isRunning;
  private volatile boolean isFinished;

  // used for the average ms snap time information
  private int imageCount = 0;
  
   // JavaCV variables
  private CvHaarClassifierCascade classifier;
  private CvMemStorage storage;
  private CanvasFrame debugCanvas;
  private IplImage grayIm;

  // used for thread that executes the face detection
  private ExecutorService executor;
  private AtomicInteger numTasks;
       // used to record number of detection tasks
  
  private long detectStartTime = 0;

  private Rectangle faceRect; 
  private volatile boolean saveFace = false;
  private int fileCount = 0;   // used for constructing a filename for saving a face
  
 public FaceCam()
  {
    setBackground(Color.white);
    



    executor = Executors.newSingleThreadExecutor();
      /* this executor manages a single thread with an unbounded queue.
         Only one task can be executed at a time, the others wait.
      */
    numTasks = new AtomicInteger(0);    
      // used to limit the size of the executor queue
 initDetector();
    
 faceRect = new Rectangle(); 

new Thread(this).start();   // start updating the panel's image
 } // end of FaceCam()
 
 private BufferedImage loadImage(String imFnm)
  // return an image
  {
    BufferedImage image = null;
    try {
      image = ImageIO.read( new File(imFnm) );   // read in as an image
       System.out.println("Reading image " + imFnm);
    }
    catch (Exception e) {
      System.out.println("Could not read image from " + imFnm);
    }
    return image;
  }  // end of loadImage()
 
  private void initDetector()
  {
    // instantiate a classifier cascade for face detection
    classifier = new CvHaarClassifierCascade(cvLoad(FACE_CASCADE_FNM));
    if (classifier.isNull()) {
      System.out.println("\nCould not load the classifier file: " + FACE_CASCADE_FNM);
      System.exit(1);
    }

    storage = CvMemStorage.create();  // create storage used during object detection

             
  } 

  public Dimension getPreferredSize()
  // make the panel wide enough for an image
  {   return new Dimension(WIDTH, HEIGHT); }
  
   public void run()
  {
    grabber = initGrabber(CAMERA_ID); //initialize camera
    if (grabber == null)
      return;

    long duration;
    isRunning = true;
    isFinished = false;

    while (isRunning) {
	  long startTime = System.currentTimeMillis();

      snapIm = picGrab(grabber, CAMERA_ID); 

      if (((System.currentTimeMillis() - detectStartTime) > DETECT_DELAY) &&
          (numTasks.get() < MAX_TASKS))
        trackFace(snapIm); 
      imageCount++;
      repaint();

      duration = System.currentTimeMillis() - startTime;
    
      if (duration < DELAY) {
        try {
          Thread.sleep(DELAY-duration);  // wait until DELAY time has passed
        } 
        catch (Exception ex) {}
      }
    }
    
    System.out.println("Execution End");
    isFinished = true;
  }  // end of run()
  
   private FrameGrabber initGrabber(int ID)
  {
    grabber = null;
    System.out.println("Initializing grabber for " + videoInput.getDeviceName(ID) + " ...");
    try {
      grabber = FrameGrabber.createDefault(ID);
      grabber.setFormat("dshow");       // using DirectShow
      grabber.setImageWidth(WIDTH);     // default is too small: 320x240
      grabber.setImageHeight(HEIGHT);
      grabber.start();
    }
    catch(Exception e) 
    {  System.out.println("Could not start grabber");  
       System.out.println(e);
       System.exit(1);
    }
    return grabber;
  }  // end of initGrabber()



  private IplImage picGrab(FrameGrabber grabber, int ID)
  {
    IplImage im = null;
    try {
      im = grabber.grab();  // take a snap
    }
    catch(Exception e) 
    {  System.out.println("Problem grabbing image for camera " + ID);  }
    return im;
  }  // end of picGrab()
  
  
   private void closeGrabber(FrameGrabber grabber, int ID)
  {
    try {
      grabber.stop();
      grabber.release();
    }
    catch(Exception e) 
    {  System.out.println("Problem stopping grabbing for camera " + ID);  }
  }  // end of closeGrabber()

    public void paintComponent(Graphics g)
  /* Draw the image, the rectangle (and crosshairs) around a detected
     face, and the average ms snap time at the bottom left of the panel. 
     This time does NOT include the face detection task.
  */
  { 
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    

    // draw the image, stats, and detection rectangle
    if (snapIm != null) {
      g2.setColor(Color.BLUE);
      g2.drawImage(snapIm.getBufferedImage(), 0, 0, this);   // draw the snap
      drawRect(g2);
    }
    else  {  // no image yet
      g2.setColor(Color.BLUE);
      g2.drawString("Loading from camera " + CAMERA_ID + "...", 5, HEIGHT-10);
    }
  } // end of paintComponent()



  private void drawRect(Graphics2D g2)
  {  
    synchronized(faceRect) {
      if (faceRect.width == 0)
        return;

      // draw a thick yellow rectangle
      g2.setColor(Color.GREEN);
      g2.setStroke(new BasicStroke(1)); 
      g2.drawRect(faceRect.x, faceRect.y, faceRect.width, faceRect.height);

    
      
    }
  }  // end of drawRect()
  
  
  public void closeDown()
  /* Terminate run() and wait for it to finish.
     This stops the application from exiting until everything
     has finished. */
  { 
    isRunning = false;
    while (!isFinished) {
      try {
        Thread.sleep(DELAY);
        closeGrabber(grabber, CAMERA_ID);
      } 
      catch (Exception ex) {}
    }
  }
   private void trackFace(final IplImage img)
  /* Create a separate thread for the time-consuming detection task:
       find a face in the current image, store its coordinates in faceRect, and
       save the face part of the image in a file if requested. 
     Print the time taken for all of this to stdout.
  */ 
  {
    grayIm = scaleGray(img);
    numTasks.getAndIncrement();     // increment no. of tasks before entering queue
    executor.execute(new Runnable() {
      public void run()
      { 
	    detectStartTime = System.currentTimeMillis();
        CvRect rect = findFace(grayIm);
        if (rect != null) {
            if (saveFace) {
           //S=saveFaceIm();
            saveFace = false;
          }
          setRectangle(rect);
        }
        //}
        long detectDuration = System.currentTimeMillis() - detectStartTime;
       // System.out.println(" detection duration: " + detectDuration + "ms");
        numTasks.getAndDecrement();  // decrement no. of tasks since finished
      }
    });
  }  // end of trackFace()
   
     private IplImage scaleGray(IplImage img)
  /* Scale the image and convert it to grayscale. Scaling makes
     the image smaller and so faster to process, and Haar detection
     requires a grayscale image as input
  */
  {
    // convert to grayscale
    IplImage grayImg = cvCreateImage(cvGetSize(img), IPL_DEPTH_8U, 1);
    cvCvtColor(img, grayImg, CV_BGR2GRAY);  

    // scale the grayscale (to speed up face detection)
    IplImage smallImg = IplImage.create(grayImg.width()/IM_SCALE, 
                                        grayImg.height()/IM_SCALE, IPL_DEPTH_8U, 1);
    cvResize(grayImg, smallImg, CV_INTER_LINEAR);

    // equalize the small grayscale
	cvEqualizeHist(smallImg, smallImg);
    return smallImg;
  }  // end of scaleGray()



  private CvRect findFace(IplImage grayIm)
  // Find a single face using the Haar detector
  {
/*
     // show the greyscale image to check on image processing steps
     debugCanvas.showImage(grayIm);
	 debugCanvas.waitKey(0);
*/
    // System.out.println("Detecting largest face...");   // cvImage
    CvSeq faces = cvHaarDetectObjects(grayIm, classifier, storage, 1.1, 1,  // 3
                              // CV_HAAR_SCALE_IMAGE |
                              CV_HAAR_DO_ROUGH_SEARCH | CV_HAAR_FIND_BIGGEST_OBJECT); 
          // speed things up by searching for only a single, largest face subimage

    int total = faces.total();
    if (total == 0) {
     // System.out.println("No faces found");
      return null;
    }
    else if (total > 1)   // this case should not happen, but included for safety
    {}  //System.out.println("Multiple faces detected (" + total + "); using the first");
   
    else
    {}
        //System.out.println("Face detected");

    CvRect rect = new CvRect(cvGetSeqElem(faces, 0));

    cvClearMemStorage(storage);
    return rect;
  }  // end of findface()



  private void setRectangle(CvRect r)
  /* Extract the (x, y, width, height) values of the highlighted image from
     the JavaCV rectangle data structure, and store them in a Java rectangle.
     In the process, undo the scaling which was applied to the image before face 
     detection was carried out.
     Report any movement of the new rectangle compared to the previous one.
     The updating of faceRect is in a synchronized block since it may be used 
     for drawing or image saving at the same time in other threads.
  */
  {  
    synchronized(faceRect) {
      int xNew = r.x()*IM_SCALE;
      int yNew = r.y()*IM_SCALE;
      int widthNew = r.width()*IM_SCALE;
      int heightNew = r.height()*IM_SCALE;

      // calculate movement of the new rectangle compared to the previous one
      int xMove = (xNew + widthNew/2) - (faceRect.x + faceRect.width/2);
      int yMove = (yNew + heightNew/2) - (faceRect.y + faceRect.height/2);

      // report movement only if it is 'significant'
      if ((Math.abs(xMove)> SMALL_MOVE) || (Math.abs(yMove) > SMALL_MOVE))
      {}// System.out.println("Movement (x,y): (" + xMove + "," + yMove + ")" );

      faceRect.setRect( xNew, yNew, widthNew, heightNew);
    }
  }  // end of setRectangle()

   public BufferedImage resizeImage(BufferedImage im,double width,double height)
  // resize to at least a standard size, then convert to grayscale 
  {
    // resize the image so *at least* FACE_WIDTH*FACE_HEIGHT size
    int imWidth = im.getWidth();
    int imHeight = im.getHeight();
    System.out.println("Original (w,h): (" + imWidth + ", " + imHeight + ")");

    double widthScale = width / ((double) imWidth);
    double heightScale = height / ((double) imHeight);
    double scale = (widthScale > heightScale) ? widthScale : heightScale;

    int nWidth = (int)Math.round(imWidth* scale);
    int nHeight = (int)Math.round(imHeight* scale);

    // convert to grayscale while resizing
 //   BufferedImage grayIm = new BufferedImage(nWidth, nHeight, 
   //                                         BufferedImage.TYPE_BYTE_GRAY);  
    BufferedImage img=new BufferedImage(nWidth, nHeight,BufferedImage.SCALE_SMOOTH);
    Graphics2D g2 = img.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(im, 0, 0, nWidth, nHeight,  0, 0, imWidth, imHeight, null);  
    g2.dispose();  

    System.out.println("Scaled gray (w,h): (" + nWidth + ", " + nHeight + ")");
    //return grayIm;
    return img;
  }  // end of resizeImage()

  public BufferedImage CaptureFace()
  {
       BufferedImage faceIm = null;
       BufferedImage clipIm = null;
     
    synchronized(faceRect) {
      if (faceRect.width == 0) {
        System.out.println("No face selected");
        return null;
      }
      
      BufferedImage im = picGrab(grabber, CAMERA_ID).getBufferedImage();
      try {
        clipIm = im.getSubimage(faceRect.x, faceRect.y, faceRect.width, faceRect.height);
      }
      catch(RasterFormatException e) {
        System.out.println("Could not clip the image");
      }
    }
    if (clipIm != null)
    {
     BufferedImage grayIm= resizeImage(clipIm,250,250);  
  
     int xOffset = (grayIm.getWidth() - FACE_WIDTH)/2;
    int yOffset = (grayIm.getHeight() - FACE_HEIGHT)/2;
    
    try {
      faceIm = grayIm.getSubimage(xOffset, yOffset, FACE_WIDTH, FACE_HEIGHT);
      System.out.println("Clipped image to face dimensions: (" + 
                                      FACE_WIDTH + ", " + FACE_HEIGHT + ")");
    }
    catch(RasterFormatException e) {
      System.out.println("Could not clip the image");
      faceIm = this.grayIm.getBufferedImage();
    }
String fnm="./fac.jpg";
      try {
          //new File(FACE_DIR).mkdirs();
      ImageIO.write(faceIm, "jpg", new File(fnm));
      System.out.println("Saved image to " + fnm);
     //this.fileCount++;
     //if(fileCount>5)
     //{fileCount=1;}
    } 
    catch (IOException e) {
    System.out.println("Could not save image to " + fnm);
    } 
    }
   
   return faceIm;
   }
  public BufferedImage saveFaceIm(String FACE_DIR,String FACE_FNM)
   {
       BufferedImage faceIm = null;
       BufferedImage clipIm = null;
     
    synchronized(faceRect) {
      if (faceRect.width == 0) {
        System.out.println("No face selected");
        return null;
      }
      
      BufferedImage im = picGrab(grabber, CAMERA_ID).getBufferedImage();
      try {
        clipIm = im.getSubimage(faceRect.x, faceRect.y, faceRect.width, faceRect.height);
      }
      catch(RasterFormatException e) {
        System.out.println("Could not clip the image");
      }
    }
    if (clipIm != null)
    {
     BufferedImage grayIm= resizeImage(clipIm,250,250);  
  
     int xOffset = (grayIm.getWidth() - FACE_WIDTH)/2;
    int yOffset = (grayIm.getHeight() - FACE_HEIGHT)/2;
    
    try {
      faceIm = grayIm.getSubimage(xOffset, yOffset, FACE_WIDTH, FACE_HEIGHT);
      System.out.println("Clipped image to face dimensions: (" + 
                                      FACE_WIDTH + ", " + FACE_HEIGHT + ")");
    }
    catch(RasterFormatException e) {
      System.out.println("Could not clip the image");
      faceIm = this.grayIm.getBufferedImage();
    }
String fnm=FACE_DIR + "/" + FACE_FNM +"-"+ fileCount + ".jpg";
      try {
          new File(FACE_DIR).mkdirs();
      ImageIO.write(faceIm, "jpg", new File(fnm));
      System.out.println("Saved image to " + fnm);
     this.fileCount++;
     if(fileCount>5)
     {fileCount=1;}
    } 
    catch (IOException e) {
      System.out.println("Could not save image to " + fnm);
    } 
    }
   
   return faceIm;
   }

  
}
