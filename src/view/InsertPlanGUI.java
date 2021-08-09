package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import controller.DAO;
import model.UserVO;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertPlanGUI {

   private JFrame frame;
   private JTextField textField;
   public static String join = null;

   /**
    * Create the application.
    */
   public InsertPlanGUI() {
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
      // â ȭ�� �߰��� ����
      // �������� ����� ���մϴ�.
      Dimension frameSize = frame.getSize();
      // �� ������� ũ�⸦ ���մϴ�.
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      /*
       * �׷��� �������� ��ġ�� (�����ȭ�� ���� - ������ȭ�� ����) / 2, (�����ȭ�� ���� - ������ȭ�� ����) / 2 �̷��� �����Ѵ�.
       */
      frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

      JLabel label = new JLabel("\uC6D0");
      label.setFont(new Font("���� ���", Font.BOLD, 25));
      label.setBounds(462, 212, 76, 62);
      frame.getContentPane().add(label);

      JButton btnNewButton = new JButton("\uC644\uB8CC");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            DAO dao = new DAO();
            String id = JoinGUI.joinmember.getId();
            int plan = 0;
            try {
               plan = Integer.parseInt(textField.getText());
            } catch (Exception e2) {
               plan = 0;
            }
            if (dao.join_plan(id, plan) == 0) {
               JOptionPane.showMessageDialog(null, "�� �� �ĺ� �Է� ���� - �����ͷ� �����ϼ���");
            } else {
               JoinGUI.joinmember.setPlan(plan);
               join = id;
               OnedayGUI oneday = new OnedayGUI();
               frame.dispose();
            }
         }
      });
      btnNewButton.setBackground(SystemColor.menu);
      btnNewButton.setFont(new Font("���� ���", Font.BOLD, 25));
      btnNewButton.setBounds(412, 286, 105, 39);
      frame.getContentPane().add(btnNewButton);

      JLabel lblNewLabel = new JLabel("");
      lblNewLabel.setIcon(new ImageIcon(InsertPlanGUI.class.getResource("/image/plan.jpg")));
      lblNewLabel.setBounds(0, 0, 600, 400);
      frame.getContentPane().add(lblNewLabel);

      textField = new JFormattedTextField(new NumberFormatter());
      textField = new JTextField();
      textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      textField.setFont(new Font("���� ���", Font.PLAIN, 25));
      textField.setBounds(354, 214, 128, 45);
      frame.getContentPane().add(textField);
      textField.setColumns(10);
   }
}