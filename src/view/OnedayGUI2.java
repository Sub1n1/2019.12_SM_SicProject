package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.DAO;

import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OnedayGUI2 {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public OnedayGUI2() {
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

		DAO dao = new DAO();
		String id = LoginGUI.loginMember.getId();
		int plan = dao.get_plan(id);
		int oneday = dao.join_oneday(plan, id);
		String to = Integer.toString(oneday);

		JButton btnOk = new JButton("Ok!");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnOk.setFont(new Font("���� ���", Font.BOLD, 25));
		btnOk.setBackground(SystemColor.menu);
		btnOk.setBounds(420, 266, 105, 39);
		frame.getContentPane().add(btnOk);

		JLabel lblNewLabel_1 = new JLabel(to);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("���� ���", Font.BOLD, 25));
		lblNewLabel_1.setBounds(303, 200, 222, 67);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(OnedayGUI2.class.getResource("/image/oneday.jpg")));
		lblNewLabel.setBounds(0, 0, 600, 400);
		frame.getContentPane().add(lblNewLabel);
	}

}
