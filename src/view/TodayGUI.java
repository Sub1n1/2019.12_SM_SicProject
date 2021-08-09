package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.DAO;
import godling.swinglib.JListView;
import godling.swinglib.RstrNameAdapter;
import model.RestaurantVO;

public class TodayGUI {
	DAO dao = new DAO();

	private JFrame frame;
	private CardLayout cardLayout;

	private JPanel panel;
	private Panel card1;

	private ArrayList<RestaurantVO> _items1;
	private ArrayList<RestaurantVO> _items2;
	private ArrayList<RestaurantVO> _items3;
	private ArrayList<RestaurantVO> _items4;
	private ArrayList<RestaurantVO> _items5;
	
	private RstrNameAdapter _adapter1;
	private RstrNameAdapter _adapter2;
	private RstrNameAdapter _adapter3;
	private RstrNameAdapter _adapter4;
	private RstrNameAdapter _adapter5;

	/**
	 * Create the application.
	 */
	public TodayGUI() {
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
		// 창 화면 중간에 띄우기
		// 프레임의 사이즈를 구합니다.
		Dimension frameSize = frame.getSize();
		// 내 모니터의 크기를 구합니다.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		/*
		 * 그래서 프레임의 위치를 (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2 이렇게 설정한다.
		 */
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		JButton btn_5 = new JButton("");
		btn_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "1");
			}
		});
		btn_5.setBounds(0, 129, 120, 37);
		btn_5.setBackground(SystemColor.menu);
		frame.getContentPane().add(btn_5);
		btn_5.setOpaque(false);

		JButton btn_5to10 = new JButton("");
		btn_5to10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "2");
			}
		});
		btn_5to10.setBounds(121, 129, 120, 37);
		btn_5to10.setBackground(SystemColor.menu);
		frame.getContentPane().add(btn_5to10);
		btn_5to10.setOpaque(false);

		JButton btn_10to15 = new JButton("");
		btn_10to15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "3");
			}
		});
		btn_10to15.setBounds(240, 129, 120, 37);
		btn_10to15.setBackground(SystemColor.menu);
		frame.getContentPane().add(btn_10to15);
		btn_10to15.setOpaque(false);

		JButton btn_15to20 = new JButton("");
		btn_15to20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "4");
			}
		});
		btn_15to20.setBackground(SystemColor.menu);
		btn_15to20.setBounds(360, 129, 120, 37);
		frame.getContentPane().add(btn_15to20);
		btn_15to20.setOpaque(false);

		JButton btn_20 = new JButton("");
		btn_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "5");
			}
		});
		btn_20.setBackground(SystemColor.menu);
		btn_20.setBounds(480, 129, 120, 37);
		frame.getContentPane().add(btn_20);
		btn_20.setOpaque(false);

		cardLayout = new CardLayout(0, 0);

		panel = new JPanel();
		panel.setBounds(0, 164, 602, 604);
		frame.getContentPane().add(panel);
		panel.setLayout(cardLayout);

		JListView listView1 = new JListView();
		listView1.setBounds(0, 0, 602, 604);
		_items1 = dao.getRstrId(0, 5000);
		_adapter1 = new RstrNameAdapter(_items1);
		listView1.setAdapter(_adapter1);
		_adapter1.notifyDataSetChange();

		card1 = new Panel();
		card1.setLayout(null);
		card1.add(listView1);

		
		JListView listView2 = new JListView();
		listView2.setBounds(0, 0, 602, 604);
		_items2 = dao.getRstrId(5000, 10000);
		_adapter2 = new RstrNameAdapter(_items2);
		listView2.setAdapter(_adapter2);
		_adapter2.notifyDataSetChange();
		
		Panel card2 = new Panel();
		card2.setLayout(null);
		card2.add(listView2);

		
		JListView listView3 = new JListView();
		listView3.setBounds(0, 0, 602, 604);
		_items3 = dao.getRstrId(10000, 15000);
		_adapter3 = new RstrNameAdapter(_items3);
		listView3.setAdapter(_adapter3);
		_adapter3.notifyDataSetChange();

		Panel card3 = new Panel();
		card3.setLayout(null);
		card3.add(listView3);


		JListView listView4 = new JListView();
		listView4.setBounds(0, 0, 602, 604);
		_items4 = dao.getRstrId(15000, 20000);
		_adapter4 = new RstrNameAdapter(_items4);
		listView4.setAdapter(_adapter4);
		_adapter4.notifyDataSetChange();

		Panel card4 = new Panel();
		card4.setLayout(null);
		card4.add(listView4);


		JListView listView5 = new JListView();
		listView5.setBounds(0, 0, 602, 604);
		_items5 = dao.getRstrId(20000, 1000000);
		_adapter5 = new RstrNameAdapter(_items5);
		listView5.setAdapter(_adapter5);
		_adapter5.notifyDataSetChange();

		Panel card5 = new Panel();
		card5.setLayout(null);
		card5.add(listView5);


		panel.add(card1, "1");
		panel.add(card2, "2");
		panel.add(card3, "3");
		panel.add(card4, "4");
		panel.add(card5, "5");

		// 로그인한 user id 가져오기
		String id = LoginGUI.loginMember.getId();
		JLabel lbl_userName = new JLabel(id);
		lbl_userName.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_userName.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lbl_userName.setForeground(new Color(0, 0, 0));
		lbl_userName.setBounds(12, 52, 108, 31);
		frame.getContentPane().add(lbl_userName);
		int plan = dao.get_firstplan(id);
		int firstMoney = dao.join_oneday(plan,id); // 하루 적정금액으로
	    int usedMoney = dao.last_leftover(id);
		int oneday = firstMoney - usedMoney;// 로그인한 user id로
		JLabel lbl_leftMoney = new JLabel(Integer.toString(oneday));
		lbl_leftMoney.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_leftMoney.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lbl_leftMoney.setBounds(309, 52, 108, 31);
		frame.getContentPane().add(lbl_leftMoney);

		JLabel lbl_background = new JLabel("");
		lbl_background.setBounds(0, 0, 600, 850);
		lbl_background.setIcon(new ImageIcon(TodayGUI.class.getResource("/image/Todayback.jpg")));
		frame.getContentPane().add(lbl_background);
		
		JButton btn_home = new JButton("");
		btn_home.setBackground(SystemColor.menu);
		btn_home.setOpaque(false);
		btn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommonPayGUI com = new CommonPayGUI();
				frame.dispose();
			}
		});
		btn_home.setBounds(249, 771, 120, 79);
		frame.getContentPane().add(btn_home);
		
		
		JButton btn_mypage = new JButton("");
		btn_mypage.setBackground(SystemColor.menu);
		btn_mypage.setOpaque(false);
		btn_mypage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MypageGUI my = new MypageGUI();
				frame.dispose();
			}
		});
		btn_mypage.setBounds(121, 771, 120, 79);
		frame.getContentPane().add(btn_mypage);
		
		
	
		JButton btn_logout = new JButton("");
		btn_logout.setBackground(SystemColor.menu);
		btn_logout.setOpaque(false);
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI.loginMember = null;
				LoginGUI.main(null);
				frame.dispose();
			}
		});
		btn_logout.setBounds(0, 771, 120, 79);		
		frame.getContentPane().add(btn_logout);
		
		JButton btn_save = new JButton("");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "home으로 이동해 진행해주세요.", "save", JOptionPane.ERROR_MESSAGE);
			}
		});
		btn_save.setBackground(SystemColor.menu);
		btn_save.setOpaque(false);
		btn_save.setBounds(493, 771, 105, 79);
		frame.getContentPane().add(btn_save);

	}
}