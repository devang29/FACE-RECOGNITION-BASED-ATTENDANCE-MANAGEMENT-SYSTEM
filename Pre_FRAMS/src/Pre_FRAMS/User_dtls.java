/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pre_FRAMS;

import javax.swing.JOptionPane;
import business_logic.*;
import java.awt.Color;
/**
 *
 * @author Ray24
 */
public class User_dtls extends javax.swing.JPanel {

    /**
     * Creates new form User_dtls
     */
    user us=null;
    public User_dtls() {
        initComponents();
        us=new user();
        this.setBackground(Color.WHITE);
        panel_usemgmt.setBackground(Color.decode("#838EDE"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_usemgmt = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_adminid = new javax.swing.JTextField();
        txt_fname = new javax.swing.JTextField();
        txt_lname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_view = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();

        panel_usemgmt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Admin ID :");

        jLabel2.setText("First Name :");

        jLabel3.setText("Last Name :");

        btn_view.setBackground(new java.awt.Color(131, 142, 222));
        btn_view.setText("View");
        btn_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewActionPerformed(evt);
            }
        });

        btn_save.setBackground(new java.awt.Color(131, 142, 222));
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_update.setBackground(new java.awt.Color(131, 142, 222));
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(131, 142, 222));
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_reset.setBackground(new java.awt.Color(131, 142, 222));
        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_usemgmtLayout = new javax.swing.GroupLayout(panel_usemgmt);
        panel_usemgmt.setLayout(panel_usemgmtLayout);
        panel_usemgmtLayout.setHorizontalGroup(
            panel_usemgmtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_usemgmtLayout.createSequentialGroup()
                .addGroup(panel_usemgmtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_usemgmtLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_usemgmtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_usemgmtLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btn_view)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_save)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_update))
                            .addGroup(panel_usemgmtLayout.createSequentialGroup()
                                .addGroup(panel_usemgmtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addGap(45, 45, 45)
                                .addGroup(panel_usemgmtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_lname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(txt_fname, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_adminid)))))
                    .addGroup(panel_usemgmtLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btn_delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_reset)))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        panel_usemgmtLayout.setVerticalGroup(
            panel_usemgmtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_usemgmtLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_usemgmtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_adminid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_usemgmtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_usemgmtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_usemgmtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_view)
                    .addComponent(btn_save)
                    .addComponent(btn_update))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_usemgmtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_delete)
                    .addComponent(btn_reset))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_usemgmt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_usemgmt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        txt_adminid.setText("");
        txt_fname.setText("");
        txt_lname.setText("");
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        String uid=JOptionPane.showInputDialog(this,"Enter AdminID :");
        if(uid!=null)
        {
        boolean flg=us.delete(uid);
        if(flg==false)
        {
            JOptionPane.showMessageDialog(this,"AdminID does not exist...");
        } 
        else
        {
            JOptionPane.showMessageDialog(this,"Deleted...");
        }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewActionPerformed
        // TODO add your handling code here:
        String uid=JOptionPane.showInputDialog(this,"Enter AdminID :");
        if(uid!=null)
        {
        String fname=us.getFName(uid);
        String lname=us.getLName(uid);
        
        if(fname!=null|lname!=null)
        {
            txt_adminid.setText(uid);
            txt_fname.setText(fname);
            txt_lname.setText(lname);
        } 
        else
        {
            JOptionPane.showMessageDialog(this,"AdminID does not exist...");
        }
        }
    }//GEN-LAST:event_btn_viewActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        if(!txt_adminid.getText().equals("")&&!txt_fname.getText().equals("")&&!txt_lname.getText().equals(""))
        {
            boolean flg=us.insert(txt_adminid.getText(),txt_fname.getText(),txt_lname.getText(),txt_adminid.getText(),"Admin");
            if(flg==true)
            {
                JOptionPane.showMessageDialog(this,"Inserted successfully...");                
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Can not insert...");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Enter all the details...");
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        if(!txt_adminid.getText().equals("")&&!txt_fname.getText().equals("")&&!txt_lname.getText().equals(""))
        {
            boolean flg=us.update(txt_adminid.getText(),txt_fname.getText(),txt_lname.getText(),txt_adminid.getText(),"Admin");
            if(flg==true)
            {
                JOptionPane.showMessageDialog(this,"Updated successfully...");                
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Can not update...");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Enter all the details...");
        }
    }//GEN-LAST:event_btn_updateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton btn_view;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel panel_usemgmt;
    private javax.swing.JTextField txt_adminid;
    private javax.swing.JTextField txt_fname;
    private javax.swing.JTextField txt_lname;
    // End of variables declaration//GEN-END:variables
}
