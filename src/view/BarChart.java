package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.TextAnchor;

import controller.DAO;

public class BarChart {
   public static void main(final String[] args) {

      BarChart demo = new BarChart();
      JFreeChart chart = demo.getChart();
      ChartFrame frame1 = new ChartFrame("Bar Chart", chart);
      frame1.setBackground(new Color(255,255,255));

      frame1.setSize(800, 400);

      frame1.setVisible(true);

   }

   public JFreeChart getChart() {
      DAO dao = new DAO();

      // ������ ����
      DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();

      int planMoney = 0;
      int usedMoney = 0;
      
      String id = LoginGUI.loginMember.getId();
      int plan = LoginGUI.loginMember.getPlan();
      planMoney = dao.join_oneday(plan, id);
      usedMoney = dao.last_leftover(id);

      dataset1.addValue(planMoney, "�ݾ�", "�����ݾ�");
      dataset1.addValue(usedMoney, "�ݾ�", "���ݾ�");

      // ������ ���� �� ����
      // ������ ����
      final BarRenderer renderer = new BarRenderer();
      // ���� �ɼ� ����
      final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
      final ItemLabelPosition p_center = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);

      Font f = new Font("���� ����� 230", Font.BOLD, 16);
      Font axisF = new Font("���� ����� 230", Font.PLAIN, 16);

      // ������ ����
      // �׷��� 1
      renderer.setBaseItemLabelGenerator(generator);
      renderer.setBaseItemLabelsVisible(true);
      renderer.setBasePositiveItemLabelPosition(p_center);
      renderer.setBaseItemLabelFont(f);
      renderer.setBasePaint(new Color(0,0,0));     
      renderer.setSeriesPaint(0, new Color(194,145,254));

      // plot ����
      final CategoryPlot plot = new CategoryPlot();
      // plot �� ������ ����
      plot.setDataset(dataset1);
      plot.setRenderer(renderer);

      // plot �⺻ ����
      plot.setBackgroundPaint(new Color(255,255,255));      
      plot.setOrientation(PlotOrientation.HORIZONTAL); // �׷��� ǥ�� ����
      plot.setRangeGridlinesVisible(true); // X�� ���̵� ���� ǥ�ÿ���
      plot.setDomainGridlinesVisible(true); // Y�� ���̵� ���� ǥ�ÿ���
      // ������ ���� ���� : dataset ��� ������� ������ ( ��, ���� ����Ѱ� �Ʒ��� �� )
      plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
   
      // X�� ����
      plot.setDomainAxis(new CategoryAxis()); // X�� ���� ����
      plot.getDomainAxis().setTickLabelFont(axisF); // X�� ���ݶ� ��Ʈ ����
      plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // ī�װ�
                                                                     
      // Y�� ����
      plot.setRangeAxis(new NumberAxis()); // Y�� ���� ����
      plot.getRangeAxis().setTickLabelFont(axisF); // Y�� ���ݶ� ��Ʈ ����

      // ���õ� plot�� �������� chart ����
      final JFreeChart chart = new JFreeChart(plot);
      chart.removeLegend();
      chart.setTitle("DAILY TOTAL"); // ��Ʈ Ÿ��Ʋ
      return chart;

   }

}