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
		// â ȭ�� �߰��� ����
		// �������� ����� ���մϴ�.
		Dimension frameSize = frame.getSize();
		// �� ������� ũ�⸦ ���մϴ�.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		/*
		 * �׷��� �������� ��ġ�� (�����ȭ�� ���� - ������ȭ�� ����) / 2, (�����ȭ�� ���� - ������ȭ�� ����) / 2 �̷��� �����Ѵ�.
		 */
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		JLabel lblwall = new JLabel("");
		lblwall.setIcon(new ImageIcon(UPDATEpwGUI2.class.getResource("/image/UPDATEPW.jpg")));
		lblwall.setBounds(0, 0, 600, 400);
		frame.getContentPane().add(lblwall);

		txf_pw = new JTextField();
		txf_pw.setFont(new Font("���� ���", Font.BOLD, 25));
		txf_pw.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txf_pw.setBounds(226, 126, 273, 39);
		frame.getContentPane().add(txf_pw);
		txf_pw.setColumns(10);

		txf_pw2 = new JTextField();
		txf_pw2.setFont(new Font("���� ���", Font.BOLD, 25));
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
						JOptionPane.showMessageDialog(null, "��й�ȣ ���� ���� - �����Ϳ� �����ϼ���", "��й�ȣ ����", JOptionPane.ERROR_MESSAGE);
					}else {
						dao.update_date(id);
						JOptionPane.showMessageDialog(null, "��й�ȣ ���� ����");
					}
					LoginGUI.main(null);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �ٸ��ϴ�. �ٽ� Ȯ�����ּ���.", "��й�ȣ ����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_update.setBounds(32, 320, 538, 56);
		frame.getContentPane().add(btn_update);
	}
}
