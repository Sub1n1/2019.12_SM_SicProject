package view;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import controller.DAO;
import model.UserVO;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.Icon;

public class JoinGUI {

   private JFrame frame;
   private JComboBox comsex;
   private JComboBox comboBox;
   private JButton btnjoong;
   private JTextField txf_name;
   private JTextField txf_id;
   private JTextField txf_pw;
   private JTextField txf_pw2;
   private JFormattedTextField txf_age;
   private JTextField txf_job;
   private JTextField txf_hintans;
   public static UserVO joinmember;

   DAO dao = new DAO();

   int check = 0;
   private JButton btnNewButton;

   /**
    * Create the application.
    */
   public JoinGUI() {
      initialize();
      frame.setVisible(true);
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {

      frame = new JFrame();
      frame.setBounds(100, 100, 616, 890);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      ImageIcon signup = new ImageIcon("C:\\Users\\vdi02\\Desktop\\signup.png");
      // 창 화면 중간에 띄우기
      // 프레임의 사이즈를 구합니다.
      Dimension frameSize = frame.getSize();
      // 내 모니터의 크기를 구합니다.
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      /*
       * 그래서 프레임의 위치를 (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2 이렇게 설정한다.
       */
      frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

      comboBox = new JComboBox();
      comboBox.setFont(new Font("한컴 고딕", Font.PLAIN, 20));
      comboBox.setModel(new DefaultComboBoxModel(new String[] {"---------\uC120\uD0DD\uD558\uC138\uC694--------", "\uC5B4\uBA38\uB2D8 \uC131\uD568\uC740?", "\uC544\uBC84\uB2D8 \uC131\uD568\uC740?", "\uC790\uC2E0\uC758 \uBCF4\uBB3C 1\uD638\uB294?", "\uC878\uC5C5\uD55C \uCD08\uB4F1\uD559\uAD50\uB294?"}));

      comboBox.setBounds(218, 660, 334, 28);
      frame.getContentPane().add(comboBox);
      
      comsex = new JComboBox();
      comsex.setFont(new Font("한컴 고딕", Font.PLAIN, 20));
      comsex.setModel(new DefaultComboBoxModel(new String[] {"\uC131\uBCC4 \uC120\uD0DD", "\uC5EC\uC131", "\uB0A8\uC131"}));

      comsex.setBounds(163, 501, 111, 28);
      frame.getContentPane().add(comsex);

      txf_name = new JTextField();
      txf_name.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      txf_name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      txf_name.setBounds(173, 302, 246, 28);
      frame.getContentPane().add(txf_name);
      txf_name.setColumns(10);

      txf_id = new JTextField();
      txf_id.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      txf_id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      txf_id.setColumns(10);
      txf_id.setBounds(136, 347, 246, 28);
      frame.getContentPane().add(txf_id);

      txf_pw = new JTextField();
      txf_pw.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      txf_pw.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      txf_pw.setColumns(10);
      txf_pw.setBounds(158, 401, 246, 28);
      frame.getContentPane().add(txf_pw);

      txf_pw2 = new JTextField();
      txf_pw2.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      txf_pw2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      txf_pw2.setColumns(10);
      txf_pw2.setBounds(197, 449, 246, 28);
      frame.getContentPane().add(txf_pw2);

      txf_age = new JFormattedTextField(new NumberFormatter());
      txf_age.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      txf_age.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      txf_age.setColumns(10);
      txf_age.setBounds(158, 548, 246, 28);
      frame.getContentPane().add(txf_age);

      txf_job = new JTextField();
      txf_job.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      txf_job.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      txf_job.setColumns(10);
      txf_job.setBounds(158, 602, 246, 28);
      frame.getContentPane().add(txf_job);

      txf_hintans = new JTextField();
      txf_hintans.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      txf_hintans.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      txf_hintans.setColumns(10);
      txf_hintans.setBounds(218, 712, 334, 28);
      frame.getContentPane().add(txf_hintans);

      JLabel lblwall = new JLabel(new ImageIcon(JoinGUI.class.getResource("/image/joinbackground.jpg")));

      lblwall.setBounds(0, 0, 600, 850);
      frame.getContentPane().add(lblwall);

      btnjoong = new JButton("");
      btnjoong.setBackground(SystemColor.menu);
      btnjoong.setOpaque(false);
      btnjoong.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String id = txf_id.getText();
            DAO dao = new DAO();
            boolean ans = dao.Join_idcheck(id);
            if (ans == true) {
               JOptionPane.showMessageDialog(null, "이미 사용 중인 아이디입니다.", "회원가입", JOptionPane.ERROR_MESSAGE);
               txf_id.setText("");
            } else {
               JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다.");
               check = 1;
            }
         }
      });

      JButton btn_join = new JButton("");
      btn_join.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            int hintType = 0;
            try {
               hintType = comboBox.getSelectedIndex();
            } catch (Exception e2) {
               hintType = 0;
            }
            String id = txf_id.getText();
            String name = txf_name.getText();
            int age = 0;
            try {
               age = Integer.parseInt(txf_age.getText());
            } catch (Exception e2) {
               age = 0;
            }
            String sex = "";
            try {
               if (comsex.getSelectedIndex() == 1) {
                  sex = "w";
               }else if (comsex.getSelectedIndex() == 2){
                  sex = "m";
               }
            } catch (Exception e2) {
               sex = "";
            }
            String job = txf_job.getText();
            String pw = txf_pw.getText();
            String pw2 = txf_pw2.getText();
            String hintans = txf_hintans.getText();
            UserVO vo = new UserVO(id, pw2, sex, age, hintType, hintans, job, name);

            if (name.equals("") || id.equals("") || hintans.equals("") || age == 0 || job.equals("")
                  || pw.equals("") || pw2.equals("") || sex.equals("")) {
               JOptionPane.showMessageDialog(null, "모든 정보를 입력해주세요.", "회원가입", JOptionPane.ERROR_MESSAGE);
            } else {
               if (!pw.equals(pw2)) {
                  JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "회원가입", JOptionPane.ERROR_MESSAGE);
                  txf_pw.setText("");
                  txf_pw2.setText("");
               } else {
                  if (check == 1) {

                     if (dao.insertJoin(vo) != 0) {
                        joinmember = dao.login(id, pw2);
                        JOptionPane.showMessageDialog(null, "회원가입에 성공하셨습니다.");
                        InsertPlanGUI plan = new InsertPlanGUI();
                        frame.dispose();
                     } else {
                        JOptionPane.showMessageDialog(null, "오류", "회원가입", JOptionPane.ERROR_MESSAGE);
                     }

                  } else {
                     JOptionPane.showMessageDialog(null, "아이디 중복확인을 진행해주세요.", "회원가입", JOptionPane.ERROR_MESSAGE);
                  }
               }
            }
            check = 0;
         }
      });
      btn_join.setBackground(SystemColor.menu);
      btn_join.setOpaque(false);
      btn_join.setFont(new Font("나눔고딕", Font.PLAIN, 16));
      btn_join.setBounds(30, 769, 540, 52);
      frame.getContentPane().add(btn_join);
      btnjoong.setBounds(409, 347, 129, 28);
      frame.getContentPane().add(btnjoong);
      
      btnNewButton = new JButton("");
      btnNewButton.setIcon(new ImageIcon(JoinGUI.class.getResource("/image/whit.png")));
      btnNewButton.setBorderPainted(false);
      btnNewButton.setContentAreaFilled(false);
      btnNewButton.setFocusPainted(false);
      btnNewButton.setOpaque(false);
      btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
      btnNewButton.setBounds(419, 24, 151, 120);
      frame.getContentPane().add(btnNewButton);
   }
}