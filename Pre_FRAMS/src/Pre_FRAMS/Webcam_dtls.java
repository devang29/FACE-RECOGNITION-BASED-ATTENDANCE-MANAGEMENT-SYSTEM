/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pre_FRAMS;

import Pre_FRAMS.Recognition.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 *
 * @author Ray24
 */
public class Webcam_dtls extends javax.swing.JPanel {

    
    FaceCamRec cams[]=null;
    JPanel panel_cam[]=null;
    
    public Webcam_dtls() {
        Cam_dtls cd=new Cam_dtls();
        
        initComponents();
        int count=cd.getCamlist();
        
        panel_webcams.setLayout(new FlowLayout());
        
        if (count == 0)
        {
            JOptionPane.showMessageDialog(this, "There is no camera"); 
        }
        else
        {
            cams=new FaceCamRec[1];
            panel_cam=new JPanel[1];
//            for(int i=1;i<count-1;i++)
//            {
                cams[0]=new FaceCamRec(1);
                panel_cam[0]=new JPanel();
                panel_cam[0].setLayout(new FlowLayout());
                panel_cam[0].setSize(640,480);
                
                panel_cam[0].add(cams[0]);
                panel_webcams.add(panel_cam[0]);
                SwingUtilities.updateComponentTreeUI(panel_webcams);
//                
//            //}
//            panel_webcams.setSize((count-1)*320, (count-1)*240);
//            this.setSize((count-1)*320, ((count-1)*150)+47);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_webcam_dtls = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        panel_webcams = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(400, 550));

        panel_webcam_dtls.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton5.setText("Start All Webcams");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Stop All Webcams");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_webcam_dtlsLayout = new javax.swing.GroupLayout(panel_webcam_dtls);
        panel_webcam_dtls.setLayout(panel_webcam_dtlsLayout);
        panel_webcam_dtlsLayout.setHorizontalGroup(
            panel_webcam_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_webcam_dtlsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(44, 44, 44)
                .addComponent(jButton6)
                .addGap(56, 56, 56))
        );
        panel_webcam_dtlsLayout.setVerticalGroup(
            panel_webcam_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_webcam_dtlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_webcam_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_webcams.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_webcams.setPreferredSize(new java.awt.Dimension(700, 500));

        javax.swing.GroupLayout panel_webcamsLayout = new javax.swing.GroupLayout(panel_webcams);
        panel_webcams.setLayout(panel_webcamsLayout);
        panel_webcamsLayout.setHorizontalGroup(
            panel_webcamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        panel_webcamsLayout.setVerticalGroup(
            panel_webcamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_webcam_dtls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_webcams, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_webcams, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(panel_webcam_dtls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        cams[0].closeDown();
        panel_cam[0].remove(cams[0]);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         panel_cam[0].add(cams[0]);
         panel_webcams.add(panel_cam[0]);
         SwingUtilities.updateComponentTreeUI(panel_webcams);
         cams[0].initcam();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel panel_webcam_dtls;
    private javax.swing.JPanel panel_webcams;
    // End of variables declaration//GEN-END:variables
}
