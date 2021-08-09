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
		// 창 화면 중간에 띄우기
		// 프레임의 사이즈를 구합니다.
		Dimension frameSize = frame.getSize();
		// 내 모니터의 크기를 구합니다.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		/*
		 * 그래서 프레임의 위치를 (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2 이렇게 설정한다.
		 */
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		JLabel lblNewLabel = new JLabel("\uC815\uB9D0 \uD0C8\uD1F4\uD558\uC2DC\uACA0\uC2B5\uB2C8\uAE4C?");
		lblNewLabel.setBounds(0, 0, 432, 226);
		lblNewLabel.setFont(new Font("한컴 고딕", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("YES");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DAO dao = new DAO();
				String id = LoginGUI.loginMember.getId();
				String pw = LoginGUI.loginMember.getPw();
				if (dao.bye(id, pw) == 0) {
					JOptionPane.showMessageDialog(null, "탈퇴 오류 - 고객센터에 문의해주세요.");
				} else {
					JOptionPane.showMessageDialog(null, "탈퇴가 완료되었습니다.");
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
