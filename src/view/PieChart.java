package view;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendRenderingOrder;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import controller.DAO;

public class PieChart extends ApplicationFrame {

   /**
    * Default constructor.
    *
    * @param title
    *            the frame title.
    */
   public PieChart(final String title) {

      super(title);
      PieDataset dataset = createDataset();
      JFreeChart chart = createChart(dataset);
      chart.setBackgroundPaint(new Color(255,255,255));
      ChartPanel chartPanel = new ChartPanel(chart);
//      chartPanel.setBackground();
      setContentPane(chartPanel);

   }

   // ****************************************************************************
   // * JFREECHART DEVELOPER GUIDE *
   // * The JFreeChart Developer Guide, written by David Gilbert, is available
   // *
   // * to purchase from Object Refinery Limited: *
   // * *
   // * http://www.object-refinery.com/jfreechart/guide.html *
   // * *
   // * Sales are used to provide funding for the JFreeChart project - please *
   // * support us so that we can continue developing free software. *
   // ****************************************************************************

   /**
    * Creates a sample dataset.
    * 
    * @return a sample dataset.
    */
   public PieDataset createDataset() {
      DAO dao = new DAO();
      int firstplan = 0;
      String id = LoginGUI.loginMember.getId();

      firstplan = dao.get_firstplan(id);

      int realUsed = 0;
      realUsed = dao.get_firstplan(id)-dao.get_plan(id);

      final DefaultPieDataset dataset = new DefaultPieDataset();

      if (firstplan >= realUsed) {
         dataset.setValue("Left", firstplan-realUsed);
         dataset.setValue("Used", realUsed);
      } else {
         dataset.setValue("Plan", firstplan);
         dataset.setValue("Over", realUsed-firstplan);
      }
      return dataset;
   }

   /**
    * Creates a sample chart.
    * 
    * @param dataset
    *            the dataset.
    * 
    *@return a chart.
    */
   @SuppressWarnings("deprecation")
   public JFreeChart createChart(final PieDataset dataset) {
      Font f = new Font("ÇÑÄÄ À±°íµñ 230", Font.BOLD, 16);

      Font axisF = new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 16);
      final JFreeChart chart = ChartFactory.createPieChart("MONTHLY TOTAL", // chart
                                                               // title
            dataset, // dataset
            true, // include legend
            true, false);
      final PiePlot plot = (PiePlot) chart.getPlot();
      plot.setLabelFont(axisF);
      plot.setBackgroundPaint(new Color(255,255,255));
      plot.setSectionPaint(0, Color.pink);
      plot.setSectionPaint(1, new Color(135,117,179));
      plot.setNoDataMessage("No data available");
      plot.setExplodePercent(1, 0.30);
      
      return chart;
   }

   /**
    * Starting point for the demonstration application.
    *
    * @param args
    *            ignored.
    */
   public static void main(final String[] args) {

      PieChart demo = new PieChart("MONTHLY TOTAL");
      demo.pack();
//      RefineryUtilities.centerFrameOnScreen(demo);
      demo.setVisible(true);

   }

}