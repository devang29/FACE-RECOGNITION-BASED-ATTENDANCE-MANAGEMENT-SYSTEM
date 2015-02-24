/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Server_Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import reports.DataBean;
import reports.DataBeanMaker;

/**
 *
 * @author Ray24
 */
public class Faculty_Home extends javax.swing.JFrame {

    /**
     * Creates new form Faculty_Home
     */
    public String facid=null,facname=null;
    public String[] deptid=null,subid=null;
    public Socket s=null;
    public ObjectInputStream ois=null;
    public ObjectOutputStream oos=null;
    JRViewer jv=null;
    DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    public Faculty_Home() {
        initComponents();
        date_end.setFormats(df);
        date_start.setFormats(df);
        jv=new JRViewer(null);
        
            this.panel_report.setLayout(new BorderLayout());
            //jv.setSize(panel_report.getSize());
            this.panel_report.add(jv,BorderLayout.CENTER);
        this.setSize(getToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        SwingUtilities.updateComponentTreeUI(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_details = new javax.swing.JPanel();
        lbl_welcome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbl_dept_name = new javax.swing.JLabel();
        lbl_img = new javax.swing.JLabel();
        but_logout = new javax.swing.JButton();
        but_logout1 = new javax.swing.JButton();
        panel_dtls = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cmb_dept = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cmb_sem = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        cmb_div = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        date_end = new org.jdesktop.swingx.JXDatePicker();
        date_start = new org.jdesktop.swingx.JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
        cmb_sub = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        panel_report = new javax.swing.JPanel();
        but_logout2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        panel_details.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lbl_welcome.setText("Welcome User:");

        lbl_dept_name.setText("Department");

        lbl_img.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout panel_detailsLayout = new javax.swing.GroupLayout(panel_details);
        panel_details.setLayout(panel_detailsLayout);
        panel_detailsLayout.setHorizontalGroup(
            panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_detailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_detailsLayout.createSequentialGroup()
                        .addComponent(lbl_dept_name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(lbl_img, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_detailsLayout.setVerticalGroup(
            panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_detailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_img, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_detailsLayout.createSequentialGroup()
                        .addComponent(lbl_welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_dept_name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        but_logout.setBackground(new java.awt.Color(131, 142, 222));
        but_logout.setText("Logout");
        but_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_logoutActionPerformed(evt);
            }
        });

        but_logout1.setBackground(new java.awt.Color(131, 142, 222));
        but_logout1.setText("Generate Report");
        but_logout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_logout1ActionPerformed(evt);
            }
        });

        panel_dtls.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel4.setText("Department :");

        cmb_dept.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Department" }));

        jLabel5.setText("Semester :");

        cmb_sem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Semester", "1", "2", "3", "4", "5", "6", "7", "8" }));

        jLabel6.setText("Division :");

        cmb_div.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Division", "1", "2", "2S" }));

        jLabel7.setText("Starting Date:");

        jLabel8.setText("Ending Date :");

        jLabel2.setText("Subjects List :");

        cmb_sub.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Subject" }));

        jLabel3.setText("Select Appropriate Parameter to Generate Report");

        javax.swing.GroupLayout panel_dtlsLayout = new javax.swing.GroupLayout(panel_dtls);
        panel_dtls.setLayout(panel_dtlsLayout);
        panel_dtlsLayout.setHorizontalGroup(
            panel_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_dtlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panel_dtlsLayout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(date_start, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(date_end, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel_dtlsLayout.createSequentialGroup()
                            .addGroup(panel_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addGap(34, 34, 34)
                            .addGroup(panel_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panel_dtlsLayout.createSequentialGroup()
                                    .addComponent(cmb_sem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(165, 165, 165))
                                .addGroup(panel_dtlsLayout.createSequentialGroup()
                                    .addComponent(cmb_div, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGroup(panel_dtlsLayout.createSequentialGroup()
                        .addGroup(panel_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(19, 19, 19)
                        .addGroup(panel_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_dept, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_sub, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_dtlsLayout.setVerticalGroup(
            panel_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_dtlsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmb_sub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmb_dept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmb_sem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmb_div, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_dtlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(date_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panel_report.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panel_reportLayout = new javax.swing.GroupLayout(panel_report);
        panel_report.setLayout(panel_reportLayout);
        panel_reportLayout.setHorizontalGroup(
            panel_reportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        panel_reportLayout.setVerticalGroup(
            panel_reportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        but_logout2.setBackground(new java.awt.Color(131, 142, 222));
        but_logout2.setText("Change Password");
        but_logout2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_logout2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(but_logout1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(but_logout2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(but_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel_dtls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_report, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_report, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_dtls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(but_logout1)
                            .addComponent(but_logout)
                            .addComponent(but_logout2))
                        .addGap(19, 19, 19))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void but_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_logoutActionPerformed

        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            oos.writeObject("break");
        } catch (IOException ex) {
            System.out.println("Error"+ex);
        }
        this.dispose();
        System.exit(0);

    }//GEN-LAST:event_but_logoutActionPerformed

    private void but_logout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_logout1ActionPerformed
        try {
            // TODO add your handling code here:
            if(date_start.getDate()!=null&&date_end.getDate()!=null&&cmb_sub.getSelectedIndex()!=0&&cmb_dept.getSelectedIndex()!=0&&cmb_sem.getSelectedIndex()!=0&&cmb_div.getSelectedIndex()!=0)
            {
            jv.removeAll();
            panel_report.removeAll();
            SwingUtilities.updateComponentTreeUI(jv);
            SwingUtilities.updateComponentTreeUI(panel_report);
            oos.writeObject("ReportGen");
            oos.writeObject(facid);
            oos.writeObject(facname);
            oos.writeObject(cmb_dept.getSelectedItem());
            oos.writeObject(cmb_sem.getSelectedItem());
            oos.writeObject(cmb_div.getSelectedItem());
            oos.writeObject(cmb_sub.getSelectedItem());
            oos.writeObject(date_start.getDate().toString());
            oos.writeObject(date_end.getDate().toString());
            CachedRowSet rs=(CachedRowSet) ois.readObject();
            //System.out.println("ResultSet goit....");

        HashMap m=new HashMap();
        
              m.put("dept_name",cmb_dept.getSelectedItem());
              m.put("semester",cmb_sem.getSelectedItem());
             m.put("division",cmb_div.getSelectedItem());
             //m.put("SubDataSource",jrds);
             //m.put("REPORT_DATA_SOURCE",jrds);
             m.put("subject",cmb_sub.getSelectedItem());
             m.put("faculty",facname);
             m.put("s_date",df.format(date_start.getDate()));
             m.put("e_date",df.format(date_end.getDate()));
               InputStream inputStream = getClass().getResourceAsStream("/reports/test_jasper.jrxml");
               
                DataBeanMaker dataBeanMaker = new DataBeanMaker();
                ArrayList<DataBean> dataBeanList = dataBeanMaker.getDataBeanList(rs);
              // System.out.println("Collection made...");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
               
                //Map parameters = new HashMap();
       
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,m, beanColDataSource);
        //JasperExportManager.exportReportToPdfFile(jasperPrint, "c:/test_jasper.pdf");
            jv=new JRViewer(jasperPrint);
            this.panel_report.setLayout(new BorderLayout());
            //jv.setSize(panel_report.getSize());
            this.panel_report.add(jv,BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this.jv);
            SwingUtilities.updateComponentTreeUI(this.panel_report);
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Select all the parameters...");
            }
        } catch (IOException | ClassNotFoundException | JRException ex) {
            Logger.getLogger(Faculty_Home.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_but_logout1ActionPerformed

    private void but_logout2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_logout2ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            oos.writeObject("Change Password");
        } catch (IOException ex) {
            Logger.getLogger(Student_home.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChangePassword fp=new ChangePassword();
        fp.txt_userid.setText(facid);
        fp.txt_userid.setFocusable(false);
        fp.s=this.s;
        fp.ois=this.ois;
        fp.oos=this.oos;
        fp.setVisible(true);
    }//GEN-LAST:event_but_logout2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Faculty_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Faculty_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Faculty_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Faculty_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Faculty_Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton but_logout;
    private javax.swing.JButton but_logout1;
    private javax.swing.JButton but_logout2;
    public javax.swing.JComboBox cmb_dept;
    private javax.swing.JComboBox cmb_div;
    private javax.swing.JComboBox cmb_sem;
    public javax.swing.JComboBox cmb_sub;
    private org.jdesktop.swingx.JXDatePicker date_end;
    private org.jdesktop.swingx.JXDatePicker date_start;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JLabel lbl_dept_name;
    public javax.swing.JLabel lbl_img;
    public javax.swing.JLabel lbl_welcome;
    private javax.swing.JPanel panel_details;
    private javax.swing.JPanel panel_dtls;
    private javax.swing.JPanel panel_report;
    // End of variables declaration//GEN-END:variables
}