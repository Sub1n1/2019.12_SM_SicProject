package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import controller.DAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class MypageGUI {

	private JFrame frame;
	private final JLabel lblwall = new JLabel("");
	private JTextField txf_pw;
	private JTextField txf_pw2;

	/**
	 * Create the application.
	 */
	public MypageGUI() {
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
		lblwall.setIcon(new ImageIcon(MypageGUI.class.getResource("/image/mypageback.jpg")));
		lblwall.setBounds(0, 0, 600, 850);
		frame.getContentPane().add(lblwall);
		// 창 화면 중간에 띄우기
		// 프레임의 사이즈를 구합니다.
		Dimension frameSize = frame.getSize();
		// 내 모니터의 크기를 구합니다.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		/*
		 * 그래서 프레임의 위치를 (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2 이렇게 설정한다.
		 */
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		JButton btn_repw = new JButton("");
		btn_repw.setBackground(SystemColor.menu);
		btn_repw.setOpaque(false);
		btn_repw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = LoginGUI.loginMember.getId();
				String pw = txf_pw.getText();
				String pw2 = txf_pw2.getText();
				DAO dao = new DAO();
				String pwans = dao.pwcheck(id);
				if (pw.equals(pw2) && pw.equals(pwans)) {
					UPDATEpwGUI update = new UPDATEpwGUI();
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 동일하지 않습니다. 다시 입력해주세요.", "MYPAGE",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_repw.setBounds(35, 579, 534, 57);
		frame.getContentPane().add(btn_repw);

		JButton btn_bye = new JButton("");
		btn_bye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = LoginGUI.loginMember.getId();
				String pw = txf_pw.getText();
				String pw2 = txf_pw2.getText();
				DAO dao = new DAO();
				String pwans = dao.pwcheck(id);
				if (pw.equals(pw2) && pw.equals(pwans)) {
					RealGUI real = new RealGUI();
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 동일하지 않습니다. 다시 입력해주세요.", "MYPAGE",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btn_bye.setBackground(SystemColor.menu);
		btn_bye.setOpaque(false);
		btn_bye.setBounds(35, 678, 534, 57);
		frame.getContentPane().add(btn_bye);

		JButton btn_up_plan = new JButton("");
		btn_up_plan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = LoginGUI.loginMember.getId();
				String pw = txf_pw.getText();
				String pw2 = txf_pw2.getText();
				DAO dao = new DAO();
				String pwans = dao.pwcheck(id);
				if (pw.equals(pw2) && pw.equals(pwans)) {
					Update_planGUI up = new Update_planGUI();
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 동일하지 않습니다. 다시 입력해주세요.", "MYPAGE",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_up_plan.setBackground(SystemColor.menu);
		btn_up_plan.setOpaque(false);
		btn_up_plan.setBounds(35, 769, 534, 57);
		frame.getContentPane().add(btn_up_plan);

		txf_pw = new JTextField();
		txf_pw.setFont(new Font("한컴 고딕", Font.BOLD, 25));
		txf_pw.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txf_pw.setBounds(146, 413, 283, 44);
		frame.getContentPane().add(txf_pw);
		txf_pw.setColumns(10);

		txf_pw2 = new JTextField();
		txf_pw2.setFont(new Font("한컴 고딕", Font.BOLD, 25));
		txf_pw2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txf_pw2.setColumns(10);
		txf_pw2.setBounds(195, 469, 283, 44);
		frame.getContentPane().add(txf_pw2);
		
		
		
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(MypageGUI.class.getResource("/image/whit.png")));
				btnNewButton.setBackground(SystemColor.menu);
				btnNewButton.setOpaque(false);
				
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CommonPayGUI com = new CommonPayGUI();
						frame.dispose();
					}
				});
				btnNewButton.setForeground(Color.WHITE);
				btnNewButton.setFont(new Font("한컴 고딕", Font.BOLD, 25));
				btnNewButton.setBounds(445, 160, 139, 101);
				frame.getContentPane().add(btnNewButton);
	}
}
