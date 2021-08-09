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

      // 데이터 생성
      DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();

      int planMoney = 0;
      int usedMoney = 0;
      
      String id = LoginGUI.loginMember.getId();
      int plan = LoginGUI.loginMember.getPlan();
      planMoney = dao.join_oneday(plan, id);
      usedMoney = dao.last_leftover(id);

      dataset1.addValue(planMoney, "금액", "적정금액");
      dataset1.addValue(usedMoney, "금액", "사용금액");

      // 렌더링 생성 및 세팅
      // 렌더링 생성
      final BarRenderer renderer = new BarRenderer();
      // 공통 옵션 정의
      final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
      final ItemLabelPosition p_center = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);

      Font f = new Font("한컴 윤고딕 230", Font.BOLD, 16);
      Font axisF = new Font("한컴 윤고딕 230", Font.PLAIN, 16);

      // 렌더링 세팅
      // 그래프 1
      renderer.setBaseItemLabelGenerator(generator);
      renderer.setBaseItemLabelsVisible(true);
      renderer.setBasePositiveItemLabelPosition(p_center);
      renderer.setBaseItemLabelFont(f);
      renderer.setBasePaint(new Color(0,0,0));     
      renderer.setSeriesPaint(0, new Color(194,145,254));

      // plot 생성
      final CategoryPlot plot = new CategoryPlot();
      // plot 에 데이터 적재
      plot.setDataset(dataset1);
      plot.setRenderer(renderer);

      // plot 기본 설정
      plot.setBackgroundPaint(new Color(255,255,255));      
      plot.setOrientation(PlotOrientation.HORIZONTAL); // 그래프 표시 방향
      plot.setRangeGridlinesVisible(true); // X축 가이드 라인 표시여부
      plot.setDomainGridlinesVisible(true); // Y축 가이드 라인 표시여부
      // 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 즉, 먼저 등록한게 아래로 깔림 )
      plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
   
      // X축 세팅
      plot.setDomainAxis(new CategoryAxis()); // X축 종류 설정
      plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
      plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 카테고리
                                                                     
      // Y축 세팅
      plot.setRangeAxis(new NumberAxis()); // Y축 종류 설정
      plot.getRangeAxis().setTickLabelFont(axisF); // Y축 눈금라벨 폰트 조정

      // 세팅된 plot을 바탕으로 chart 생성
      final JFreeChart chart = new JFreeChart(plot);
      chart.removeLegend();
      chart.setTitle("DAILY TOTAL"); // 차트 타이틀
      return chart;

   }

}