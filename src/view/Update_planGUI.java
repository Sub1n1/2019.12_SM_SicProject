package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JTextField;

import controller.DAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Update_planGUI {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Create the application.
	 */
	public Update_planGUI() {
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

		JButton button = new JButton("\uC644\uB8CC");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAO dao = new DAO();
				String id = LoginGUI.loginMember.getId();
				int plan = 0;
				try {
					plan = Integer.parseInt(textField.getText());
				} catch (Exception e2) {
					plan = 0;
				}
				if (dao.update_plan(id, plan) == 0) {
					JOptionPane.showMessageDialog(null, "�� �� �ĺ� �Է� ���� - �����ͷ� �����ϼ���");
				} else {
					OnedayGUI2 oneday = new OnedayGUI2();
					frame.dispose();
				}
			}
		});
		button.setFont(new Font("���� ���", Font.BOLD, 25));
		button.setBackground(SystemColor.menu);
		button.setBounds(412, 286, 105, 39);
		frame.getContentPane().add(button);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Update_planGUI.class.getResource("/image/plan.jpg")));
		lblNewLabel.setBounds(0, 0, 600, 400);
		frame.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(354, 214, 128, 45);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}

}
