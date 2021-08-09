package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

import controller.DAO;
import view.BarChart;
import view.PieChart;

public class CommonPayGUI {

   private JFrame frame;
   private CardLayout cardLayout = new CardLayout();
   private JPanel panel;
   private JPanel pan_cardback;
   DAO dao = new DAO();
   /**
    * Create the application.
    */
   public CommonPayGUI() {
      initialize();
      frame.setVisible(true);
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      frame = new JFrame();
      frame.getContentPane().setBackground(Color.WHITE);
      frame.setBounds(100, 100, 616, 890);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      // 창 화면 중간에 띄우기
      // 프레임의 사이즈를 구합니다.
      Dimension frameSize = frame.getSize();
      // 내 모니터의 크기를 구합니다.
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      /*
       * 그래서 프레임의 위치를 (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2 이렇게 설정한다.
       */
      frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

//         setResizable(false); // 프레임 크기 조절을 할수 없게 만듭니다.
//         setVisible(true); // 프레임을 보여줍니다.

      JLabel label_OneDay = new JLabel();
      label_OneDay.setText("탭이름1");
      label_OneDay.setPreferredSize(new Dimension(200, 60));

      JLabel label_OneMonth = new JLabel();
      label_OneMonth.setText("탭이름2");
      label_OneMonth.setPreferredSize(new Dimension(200, 60));

      pan_cardback = new JPanel();
      pan_cardback.setBounds(0, 183, 603, 583);
      frame.getContentPane().add(pan_cardback);
      pan_cardback.setLayout(cardLayout);
      pan_cardback.setBackground(null);

      Panel pan_oneday = new Panel();
      pan_oneday.setBackground(null);
      pan_cardback.add(pan_oneday, "card1");
      pan_oneday.setLayout(null);
      
      JLabel lbl_message1 = new JLabel();
      lbl_message1.setFont(new Font("한컴 고딕", Font.BOLD, 22));
      lbl_message1.setForeground(Color.WHITE);
      lbl_message1.setHorizontalAlignment(SwingConstants.CENTER);
      String ksb = LoginGUI.loginMember.getId();
      int plan =LoginGUI.loginMember.getPlan();
      int firstMoney = dao.join_oneday(plan,ksb); // 하루 적정금액으로
      int usedMoney = dao.last_leftover(ksb); // 하루동안 사용한 금액

      int leftMoney = 0;
      int overMoney = 0;

      String message = "";
      message = "실제 사용한 금액은 " + usedMoney + "원 입니다.";

      lbl_message1.setBounds(36, 385, 553, 60);
      message = "오늘 사용할 금액 : " + firstMoney + "원";
      lbl_message1.setText(message);
      pan_oneday.add(lbl_message1);

      JLabel lbl_message2 = new JLabel();
      lbl_message2.setFont(new Font("한컴 고딕", Font.BOLD, 22));
      lbl_message2.setForeground(Color.WHITE);
      lbl_message2.setHorizontalAlignment(SwingConstants.CENTER);
      lbl_message2.setBounds(36, 445, 553, 60);
      message = "실제 사용한 금액 : " + usedMoney + "원";
      lbl_message2.setText(message);
      pan_oneday.add(lbl_message2);

      JLabel lbl_message3 = new JLabel();
      lbl_message3.setFont(new Font("한컴 고딕", Font.BOLD, 22));
      lbl_message3.setForeground(Color.WHITE);
      lbl_message3.setHorizontalAlignment(SwingConstants.CENTER);
      lbl_message3.setBounds(36, 505, 553, 60);
      if (firstMoney >= usedMoney) {
         leftMoney = firstMoney - usedMoney;
         message = "남은 돈은 " + leftMoney + "원 입니다.";
      } else {
         overMoney = usedMoney - firstMoney;
         message = "계획보다 " + overMoney + "원 만큼 초과해 사용했습니다.";
      }
      lbl_message3.setText(message);
      pan_oneday.add(lbl_message3);

      BarChart barChart = new BarChart();
      JFreeChart chart = barChart.getChart();
      ChartPanel chartpan = new ChartPanel(chart);
      chart.setBackgroundPaint(new Color(255,255,255));
      chartpan.setBackground(Color.WHITE);
      chartpan.setBounds(14, 12, 575, 356);
      pan_oneday.add(chartpan);
      chartpan.setVisible(true);


      JLabel lblNewLabel_4 = new JLabel("New label");
      lblNewLabel_4.setIcon(new ImageIcon(CommonPayGUI.class.getResource("/image/log_background03.jpg")));
      lblNewLabel_4.setBounds(0, 0, 603, 583);
      pan_oneday.add(lblNewLabel_4);

      Panel pan_oneMonth = new Panel();
      pan_oneMonth.setBackground(null);
      pan_cardback.add(pan_oneMonth, "card2");
      pan_oneMonth.setLayout(null);
      
      PieChart pieChart = new PieChart("MONTH TOTAL");
      PieDataset dataset = pieChart.createDataset();
      JFreeChart chart2 = pieChart.createChart(dataset);
      ChartPanel chartpan2 = new ChartPanel(chart2);
      chartpan2.setBackground(Color.WHITE);
      chartpan2.setBounds(14, 12, 575, 356);
      pan_oneMonth.add(chartpan2);
      chartpan2.setVisible(true);
      
      String text = "";
      JLabel lbl_uesed = new JLabel("New label");
      lbl_uesed.setHorizontalAlignment(SwingConstants.CENTER);
      lbl_uesed.setFont(new Font("한컴 고딕", Font.BOLD, 22));
      lbl_uesed.setForeground(Color.WHITE);
      lbl_uesed.setBounds(14, 400, 575, 60);
      String ksb2= LoginGUI.loginMember.getId();
      int realUsed = dao.get_firstplan(ksb2)-dao.get_plan(ksb2);
      text = "현재까지 사용한 금액 : " + realUsed + "원" ;
      lbl_uesed.setText(text);
      pan_oneMonth.add(lbl_uesed);

      JLabel lbl_cnt = new JLabel("");
      lbl_cnt.setHorizontalAlignment(SwingConstants.CENTER);
      lbl_cnt.setFont(new Font("한컴 고딕", Font.BOLD, 22));
      lbl_cnt.setForeground(Color.WHITE);
      lbl_cnt.setBounds(14, 472, 575, 60);
      String jmj = LoginGUI.loginMember.getId();
      int numberOfOut = dao.get_cntOut(jmj);
      text = "이번달 외식 횟수 : " + numberOfOut + "회";
      lbl_cnt.setText(text);
      pan_oneMonth.add(lbl_cnt);

      JLabel lblNewLabel_3 = new JLabel("New label");
      lblNewLabel_3.setFont(new Font("한컴 고딕", Font.BOLD, 22));
      lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3.setIcon(new ImageIcon(CommonPayGUI.class.getResource("/image/log_background03.jpg")));
      lblNewLabel_3.setBounds(0, 0, 603, 583);
      pan_oneMonth.add(lblNewLabel_3);

      JLabel lblNewLabel_6 = new JLabel("TODAY");
      lblNewLabel_6.setForeground(Color.WHITE);
      lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_6.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
      lblNewLabel_6.setBounds(70, 149, 149, 31);
      frame.getContentPane().add(lblNewLabel_6);

      JLabel lblMonth = new JLabel("MONTH");
      lblMonth.setForeground(Color.WHITE);
      lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
      lblMonth.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
      lblMonth.setBounds(386, 149, 149, 31);
      frame.getContentPane().add(lblMonth);

      String id = LoginGUI.loginMember.getId();
      JLabel lblNewLabel_2 = new JLabel(id);
      lblNewLabel_2.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      lblNewLabel_2.setBounds(213, 45, 88, 30);
      frame.getContentPane().add(lblNewLabel_2);
      
      String name = LoginGUI.loginMember.getName();
      JLabel lblNewLabel_5 = new JLabel(name);
      lblNewLabel_5.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      lblNewLabel_5.setBounds(213, 79, 88, 30);
      frame.getContentPane().add(lblNewLabel_5);

      JLabel lblNewLabel_1 = new JLabel("");
      lblNewLabel_1.setIcon(new ImageIcon(CommonPayGUI.class.getResource("/image/log_background.jpg")));
      lblNewLabel_1.setBounds(0, 0, 600, 850);
      frame.getContentPane().add(lblNewLabel_1);

      JButton btn_oneMonth = new JButton("MONTH");
      btn_oneMonth.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      btn_oneMonth.setForeground(Color.WHITE);
      btn_oneMonth.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            cardLayout.show(pan_cardback, "card2");
         }
      });

      JButton btn_oneday = new JButton("TODAY");
      btn_oneday.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      btn_oneday.setForeground(Color.WHITE);
      btn_oneday.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            cardLayout.show(pan_cardback, "card1");
         }
      });
      btn_oneday.setBounds(0, 141, 301, 39);
      btn_oneday.setBackground(SystemColor.menu);
      btn_oneday.setOpaque(false);
      frame.getContentPane().add(btn_oneday);
      btn_oneMonth.setBounds(302, 141, 301, 39);
      btn_oneMonth.setBackground(SystemColor.menu);
      btn_oneMonth.setOpaque(false);
      frame.getContentPane().add(btn_oneMonth);
      
      JButton btn_mypage = new JButton("");
      btn_mypage.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            MypageGUI mypage = new MypageGUI();
            frame.dispose();
         }
      });
      btn_mypage.setBackground(SystemColor.menu);
      btn_mypage.setOpaque(false);
      btn_mypage.setBounds(125, 758, 122, 92);
      frame.getContentPane().add(btn_mypage);
      
      JButton btn_logout = new JButton("");
      btn_logout.setBackground(SystemColor.menu);
      btn_logout.setOpaque(false);
      btn_logout.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            LoginGUI.loginMember = null;
            LoginGUI.main(null);
            frame.dispose();
         }
      });
      btn_logout.setBounds(0, 758, 124, 92);
      frame.getContentPane().add(btn_logout);
      
      JButton btn_today = new JButton("");
      btn_today.setBackground(SystemColor.menu);
      btn_today.setOpaque(false);
      btn_today.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            TodayGUI today = new TodayGUI();
            frame.dispose();
         }
      });
      btn_today.setBounds(368, 769, 122, 92);
      frame.getContentPane().add(btn_today);
      
      JButton btn_save = new JButton("");
      btn_save.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            DAO dao = new DAO();
            String id = LoginGUI.loginMember.getId();
            if (dao.save_sicuser_update(id) == 0) {
               JOptionPane.showMessageDialog(null, "저장 오류 - 고객센터에 문의하세요");

            }else {
               JOptionPane.showMessageDialog(null, "저장되었습니다.");
               CommonPayGUI common = new CommonPayGUI();
               frame.dispose();
            } 
            
         }
      });
      btn_save.setBackground(SystemColor.menu);
      btn_save.setOpaque(false);
      btn_save.setBounds(493, 763, 110, 87);
      frame.getContentPane().add(btn_save);

      JLabel lblNewLabel = new JLabel("\uD68C\uC6D0 \uC774\uB984\uC774\uB098 \uC544\uC774\uB514 \uC774\uB7F0\uAC70");
      lblNewLabel.setBounds(0, 0, 584, 109);
   }
}