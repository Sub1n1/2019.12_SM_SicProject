package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import controller.DAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class UPDATEpwGUI2 {

	private JFrame frame;
	private JTextField txf_pw;
	private JTextField txf_pw2;

	/**
	 * Create the application.
	 */
	public UPDATEpwGUI2() {
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

		JLabel lblwall = new JLabel("");
		lblwall.setIcon(new ImageIcon(UPDATEpwGUI2.class.getResource("/image/UPDATEPW.jpg")));
		lblwall.setBounds(0, 0, 600, 400);
		frame.getContentPane().add(lblwall);

		txf_pw = new JTextField();
		txf_pw.setFont(new Font("한컴 고딕", Font.BOLD, 25));
		txf_pw.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txf_pw.setBounds(226, 126, 273, 39);
		frame.getContentPane().add(txf_pw);
		txf_pw.setColumns(10);

		txf_pw2 = new JTextField();
		txf_pw2.setFont(new Font("한컴 고딕", Font.BOLD, 25));
		txf_pw2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txf_pw2.setColumns(10);
		txf_pw2.setBounds(269, 177, 273, 40);
		frame.getContentPane().add(txf_pw2);

		JButton btn_update = new JButton("");
		btn_update.setBackground(SystemColor.menu);
		btn_update.setOpaque(false);
		btn_update.addActionListener(new ActionListener() {
			String pw = txf_pw.getText();
			String pw2 = txf_pw2.getText();

			public void actionPerformed(ActionEvent arg0) {
				if (pw.equals(pw2)) {
					String id = FindIDPWGUI.id2;
					String pw = txf_pw2.getText();
					DAO dao = new DAO();
					if (dao.update_Pw( id, pw) == 0) {
						JOptionPane.showMessageDialog(null, "비밀번호 변경 오류 - 고객센터에 문의하세요", "비밀번호 변경", JOptionPane.ERROR_MESSAGE);
					}else {
						dao.update_date(id);
						JOptionPane.showMessageDialog(null, "비밀번호 변경 성공");
					}
					LoginGUI.main(null);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다. 다시 확인해주세요.", "비밀번호 변경", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_update.setBounds(32, 320, 538, 56);
		frame.getContentPane().add(btn_update);
	}
}
