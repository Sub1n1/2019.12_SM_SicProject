package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import controller.DAO;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RealGUI {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public RealGUI() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
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

		JLabel lblNewLabel = new JLabel("\uC815\uB9D0 \uD0C8\uD1F4\uD558\uC2DC\uACA0\uC2B5\uB2C8\uAE4C?");
		lblNewLabel.setBounds(0, 0, 432, 226);
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("YES");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DAO dao = new DAO();
				String id = LoginGUI.loginMember.getId();
				String pw = LoginGUI.loginMember.getPw();
				if (dao.bye(id, pw) == 0) {
					JOptionPane.showMessageDialog(null, "Ż�� ���� - �����Ϳ� �������ּ���.");
				} else {
					JOptionPane.showMessageDialog(null, "Ż�� �Ϸ�Ǿ����ϴ�.");
				}

				LoginGUI.main(null);
				frame.dispose();

			}
		});
		btnNewButton.setBounds(0, 168, 212, 27);
		frame.getContentPane().add(btnNewButton);

		JButton btnNo = new JButton("NO");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommonPayGUI common = new CommonPayGUI();
				frame.dispose();
			}
		});
		btnNo.setBounds(220, 168, 212, 27);
		frame.getContentPane().add(btnNo);
	}
}
