/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_Client;

/**
 *
 * @author Jinkal
 */
import java.awt.FlowLayout;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;



public class PieChart extends JPanel {

  private static final long serialVersionUID = 1L;

  public PieChart(String chartTitle,int absent,int present) {
        //super(applicationTitle);
        // This will create the dataset 
        PieDataset dataset = createDataset(absent,present);
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(200,200));
        // add it to our application
        this.setLayout(new FlowLayout());
        this.add(chartPanel);

    }
    
    
/**
     * Creates a sample dataset 
     */

    private  PieDataset createDataset(int absent,int present) {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Absent",absent);
        result.setValue("Present",present);
        
        return result;
        
    }
    
    
/**
     * Creates a chart
     */

    private JFreeChart createChart(PieDataset dataset, String title) {
        
        JFreeChart chart = ChartFactory.createPieChart3D(title,          // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
        
    }
} 
