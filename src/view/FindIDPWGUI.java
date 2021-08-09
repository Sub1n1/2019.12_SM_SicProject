package view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import godling.swinglib.JListView;

import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import controller.DAO;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FindIDPWGUI {

   private JFrame frame;
   private CardLayout cardLayout;
   private JTextField txf_1_name;
   private JTextField txf_1_hintans;
   private JTextField txf_2_id;
   private JTextField txf_2_hintans;
   public static String id2;

//   private JPanel panel;

   /**
    * Create the application.
    */
   public FindIDPWGUI() {
      initialize();
      frame.setVisible(true);
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      frame = new JFrame();
      frame.setBounds(100, 100, 616, 440);
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

      cardLayout = new CardLayout(0, 0);

      JPanel pan_background = new JPanel();
      pan_background.setBounds(0, 51, 600, 350);
      frame.getContentPane().add(pan_background);
      pan_background.setLayout(cardLayout);

      JPanel pan_card1 = new JPanel();
      pan_card1.setBackground(Color.PINK);
      pan_background.add(pan_card1, "1");
      pan_card1.setLayout(null);

      JComboBox comboBox = new JComboBox();
      comboBox.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      comboBox.setForeground(Color.BLACK);
      comboBox.setModel(new DefaultComboBoxModel(new String[] { "---------\uC120\uD0DD\uD558\uC138\uC694--------",
            "\uC5B4\uBA38\uB2D8 \uC131\uD568\uC740?", "\uC544\uBC84\uB2D8 \uC131\uD568\uC740?",
            "\uC790\uC2E0\uC758 \uBCF4\uBB3C 1\uD638\uB294?",
            "\uC878\uC5C5\uD55C \uCD08\uB4F1\uD559\uAD50\uB294?" }));
      comboBox.setBounds(220, 137, 345, 38);
      pan_card1.add(comboBox);
      
            JLabel lblNewLabel_1 = new JLabel("");
            lblNewLabel_1.setIcon(new ImageIcon(FindIDPWGUI.class.getResource("/image/idcheck.jpg")));
            lblNewLabel_1.setBounds(0, 0, 600, 350);
            pan_card1.add(lblNewLabel_1);

      txf_1_name = new JTextField();
      txf_1_name.setForeground(Color.BLACK);
      txf_1_name.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      txf_1_name.setBounds(173, 72, 392, 37);
      pan_card1.add(txf_1_name);
      txf_1_name.setColumns(10);

      txf_1_hintans = new JTextField();
      txf_1_hintans.setForeground(Color.BLACK);
      txf_1_hintans.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      txf_1_hintans.setColumns(10);
      txf_1_hintans.setBounds(220, 219, 322, 37);
      pan_card1.add(txf_1_hintans);

      JPanel pan_card2 = new JPanel();
      pan_card2.setBackground(Color.RED);
      pan_background.add(pan_card2, "2");
      pan_card2.setLayout(null);

      JComboBox comboBox_1 = new JComboBox();
      comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "---------\uC120\uD0DD\uD558\uC138\uC694--------",
            "\uC5B4\uBA38\uB2D8 \uC131\uD568\uC740?", "\uC544\uBC84\uB2D8 \uC131\uD568\uC740?",
            "\uC790\uC2E0\uC758 \uBCF4\uBB3C 1\uD638\uB294?",
            "\uC878\uC5C5\uD55C \uCD08\uB4F1\uD559\uAD50\uB294?" }));
      comboBox_1.setForeground(Color.BLACK);
      comboBox_1.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      comboBox_1.setBounds(220, 135, 345, 38);
      pan_card2.add(comboBox_1);

      JLabel lblNewLabel_2 = new JLabel("");
      lblNewLabel_2.setIcon(new ImageIcon(FindIDPWGUI.class.getResource("/image/pwcheck.jpg")));
      lblNewLabel_2.setBounds(0, 0, 600, 350);
      pan_card2.add(lblNewLabel_2);

      txf_2_id = new JTextField();
      txf_2_id.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      txf_2_id.setForeground(Color.BLACK);
      txf_2_id.setBounds(162, 72, 397, 36);
      pan_card2.add(txf_2_id);
      txf_2_id.setColumns(10);

      txf_2_hintans = new JTextField();

      JButton bfind = new JButton("");
      bfind.setBounds(30, 268, 543, 59);
      pan_card1.add(bfind);
      bfind.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String hp = txf_1_name.getText();
            int hinttype = 0;
            try {
               hinttype = comboBox.getSelectedIndex();
            } catch (Exception e2) {
               hinttype = 0;
            }
            String hintans = txf_1_hintans.getText();
            DAO dao = new DAO();
            int hint = dao.hinttype_get(hp);
            if (hinttype == hint) {
               String id = dao.findId(hp, hintans);
               JOptionPane.showMessageDialog(null, "회원님의 아이디는 "+id+" 입니다.");
            }else {
               JOptionPane.showMessageDialog(null, "힌트 질문을 잘못 선택하셨습니다.", "아이디 찾기", JOptionPane.ERROR_MESSAGE);
            }
            
            

         }
      });
      bfind.setBackground(SystemColor.menu);
      bfind.setOpaque(false);
      txf_2_hintans.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      txf_2_hintans.setForeground(Color.BLACK);
      txf_2_hintans.setColumns(10);
      txf_2_hintans.setBounds(220, 219, 345, 36);
      pan_card2.add(txf_2_hintans);

      JButton bfind2 = new JButton("");
      bfind2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {

            DAO dao = new DAO();

            String id = txf_2_id.getText();
            int hinttype = 0;
            try {
               hinttype = comboBox_1.getSelectedIndex();
            } catch (Exception e2) {
               hinttype = 0;
            }
            String hint_ans = txf_2_hintans.getText();
            if (dao.find_pw(id, hinttype, hint_ans) != null) {
               id2 = id;
               UPDATEpwGUI2 up = new UPDATEpwGUI2();
               frame.dispose();
            } else {
               JOptionPane.showMessageDialog(null, "오류 - 고객센터로 문의하세요", "비밀번호 찾기", JOptionPane.ERROR_MESSAGE);
            }
         }
      });
      bfind2.setBounds(22, 267, 543, 59);
      pan_card2.add(bfind2);
      bfind2.setOpaque(false);
      bfind2.setBackground(SystemColor.menu);

      JLabel lblNewLabel = new JLabel("");
      lblNewLabel.setIcon(new ImageIcon(FindIDPWGUI.class.getResource("/image/IDCHECKlbl.jpg")));
      lblNewLabel.setBounds(0, 0, 600, 400);
      frame.getContentPane().add(lblNewLabel);

      JButton btn_findPW = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
      btn_findPW.setLocation(300, 0);
      btn_findPW.setSize(300, 50);
      btn_findPW.setBackground(SystemColor.menu);
      frame.getContentPane().add(btn_findPW);
      btn_findPW.setOpaque(false);

      JButton btn_findID = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
      btn_findID.setBounds(0, 0, 300, 51);
      frame.getContentPane().add(btn_findID);
      btn_findID.setBackground(SystemColor.menu);
      btn_findID.setOpaque(false);
      btn_findID.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            cardLayout.show(pan_background, "1");
         }
      });
      btn_findPW.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            cardLayout.show(pan_background, "2");
         }
      });
   }
}