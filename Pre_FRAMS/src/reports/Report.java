/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

/**
 *
 * @author Jinkal
 */
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

//import com.chetty.reporting.beans.DataBean;
//import com.chetty.reporting.business.DataBeanMaker;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

public class Report {
        @SuppressWarnings("unchecked")
        public static void main(String[] args) throws Exception {
                InputStream inputStream = new FileInputStream (".\\src\\reports\\test_jasper.jrxml");
               
                DataBeanMaker dataBeanMaker = new DataBeanMaker();
                ArrayList<DataBean> dataBeanList = dataBeanMaker.getDataBeanList(null);
               
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
               
                Map parameters = new HashMap();
       
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        //JasperExportManager.exportReportToPdfFile(jasperPrint, "c:/test_jasper.pdf");
            JRViewer jv=new JRViewer(jasperPrint);
        JFrame jf = new JFrame();
            jf.getContentPane().add(jv);
            jf.validate();
            jf.setVisible(true);
            jf.setSize(new Dimension(1000,800));
            jf.setLocation(300,100);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
}

