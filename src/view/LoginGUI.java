package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.thehowtotutorial.splashscreen.JSplash;

import controller.DAO;
import model.UserVO;

public class LoginGUI {

	private JFrame frame;
	private JTextField txf_id;
	private JTextField txf_pw;
	public static UserVO loginMember = null;
	public static UserVO joinmember = null;

	/**
	 * Create the application.
	 */
	public static void main(String[] args) {
		try {
			JSplash splash = new JSplash(LoginGUI.class.getResource("/image/realMain.jpg"), true, true, false, "1.0",
					null, Color.BLACK, Color.BLACK);

			splash.splashOn();
//		      splash.setProgress(20, "lnit");
			Thread.sleep(1000);
//		      splash.setProgress(80, "bye");
			Thread.sleep(1000);
			splash.splashOff();
		} catch (Exception e) {

		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					LoginGUI window = new LoginGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Font font = new Font("", Font.BOLD, 20);

		frame = new JFrame();
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

//		setResizable(false); // 프레임 크기 조절을 할수 없게 만듭니다.
//		setVisible(true); // 프레임을 보여줍니다.

		JLabel lbl_id = new JLabel("\uC544\uC774\uB514 :");
		lbl_id.setFont(new Font("한컴 고딕", Font.BOLD, 30));
		lbl_id.setBounds(100, 437, 114, 44);
		frame.getContentPane().add(lbl_id);

		JLabel lbl_pw = new JLabel("\uBE44\uBC00\uBC88\uD638 :");
		lbl_pw.setFont(new Font("한컴 고딕", Font.BOLD, 30));
		lbl_pw.setBounds(100, 523, 404, 43);
		frame.getContentPane().add(lbl_pw);

		JLabel lblwall = new JLabel(new ImageIcon(LoginGUI.class.getResource("/image/loginbackground.jpg")));
		lblwall.setForeground(new Color(255, 255, 255));
		lblwall.setBounds(0, 0, 600, 850);
		frame.getContentPane().add(lblwall);

		JButton btn_login = new JButton("");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txf_id.getText();
				String pw = txf_pw.getText();
				DAO dao = new DAO();
				loginMember = dao.login(id, pw);
				if (loginMember == null) {
					JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 다시 확인해주세요.", "login", JOptionPane.ERROR_MESSAGE);
				} else {
					CommonPayGUI com = new CommonPayGUI();
					frame.dispose();

				}
			}
		});
		btn_login.setBackground(SystemColor.menu);
		btn_login.setOpaque(false);
		btn_login.setFont(new Font("Dialog", Font.PLAIN, 20));
		btn_login.setBounds(31, 674, 534, 47);
		frame.getContentPane().add(btn_login);

		JButton btn_join = new JButton("");
		btn_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JoinGUI join = new JoinGUI();
				frame.dispose();
			}
		});
		btn_join.setBackground(SystemColor.menu);
		btn_join.setOpaque(false);
		btn_join.setFont(new Font("Dialog", Font.PLAIN, 20));
		btn_join.setBounds(69, 736, 152, 45);
		frame.getContentPane().add(btn_join);

		JButton btn_find = new JButton("");
		btn_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindIDPWGUI find = new FindIDPWGUI();
				frame.dispose();
			}
		});
		btn_find.setBackground(SystemColor.menu);
		btn_find.setOpaque(false);
		btn_find.setBounds(269, 742, 257, 36);
		frame.getContentPane().add(btn_find);

		txf_id = new JTextField();
		txf_id.setFont(new Font("한컴 고딕", Font.BOLD, 25));
		txf_id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txf_id.setColumns(10);
		txf_id.setBounds(224, 438, 310, 43);
		frame.getContentPane().add(txf_id);

		txf_pw = new JTextField();
		txf_pw.setFont(new Font("한컴 고딕", Font.BOLD, 25));
		txf_pw.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txf_pw.setColumns(10);
		txf_pw.setBounds(250, 524, 284, 43);
		frame.getContentPane().add(txf_pw);

	}
}
