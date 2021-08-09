package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import godling.swinglib.JListView;
import godling.swinglib.MenuListAdapter;
import controller.DAO;
import model.EventVO;
import model.MenuVO;
import model.RestaurantVO;

public class DetailsGUI {

	public JFrame frame;

	public static RestaurantVO restaurant = null;
	public static EventVO event = null;
	public static ArrayList<MenuVO> menuList = null;
	public static int total = 0;

	DAO dao = new DAO();

	/**
	 * Create the application.
	 */
	public DetailsGUI(String restaurantID) {
		System.out.println("restaurantID => " + restaurantID);
		restaurant = dao.getRstrInfo(restaurantID);
		event = dao.getEventInfo(restaurantID);
		menuList = dao.MenuList(restaurantID);
		initialize();
		frame.setVisible(true);
		// frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		

		// ½Ä´ç ÀÌ¸§ ¶óº§
		String name = restaurant.getName();
		frame.getContentPane().setLayout(null);
		JLabel lbl_restaurantName = new JLabel("\uC2DD\uB2F9\uC774\uB984");
		lbl_restaurantName.setForeground(Color.WHITE);
		lbl_restaurantName.setBackground(null);
		lbl_restaurantName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_restaurantName.setBounds(12, 29, 560, 45);
		lbl_restaurantName.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 24));
		lbl_restaurantName.setText(name);
		frame.getContentPane().add(lbl_restaurantName);

		// ½Ä´ç ÁÖ¼Ò ¶óº§
		String address = restaurant.getAddress();
		JLabel lbl_address = new JLabel("\uC8FC\uC18C");
		lbl_address.setForeground(Color.WHITE);
		lbl_address.setBounds(22, 101, 550, 29);
		lbl_address.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		lbl_address.setText(address);
		frame.getContentPane().add(lbl_address);

		// ½Ä´ç ÀüÈ­¹øÈ£ ¶óº§
		String tel = "Tel. " + restaurant.getTel();
		JLabel lbl_tel = new JLabel("Tel");
		lbl_tel.setForeground(Color.WHITE);
		lbl_tel.setBounds(24, 140, 548, 29);
		lbl_tel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		lbl_tel.setText(tel);
		frame.getContentPane().add(lbl_tel);

		// ½Ä´ç ÀÌº¥Æ® Á¤º¸
		String eventInfo = event.getEvent();
		JLabel lbl_event = new JLabel("Event");
		lbl_event.setForeground(Color.WHITE);
		lbl_event.setBounds(24, 202, 548, 29);
		lbl_event.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		lbl_event.setText(eventInfo);
		frame.getContentPane().add(lbl_event);

		JListView listView = new JListView();
		listView.setBounds(26, 265, 548, 502);
		frame.getContentPane().add(listView);

		JLabel lblNewLabel_1 = new JLabel("PRICE");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(78, 793, 71, 29);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("000000");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("±¼¸²", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(158, 795, 113, 29);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\\");
		lblNewLabel_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		lblNewLabel_3.setBounds(281, 790, 57, 39);
		frame.getContentPane().add(lblNewLabel_3);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 768, 600, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(154, 20, 126, 40);
		panel.add(panel_1);

		JButton btnNewButton = new JButton("PURCHASE");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(72, 61, 139));
		btnNewButton.setBounds(354, 15, 166, 45);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = LoginGUI.loginMember.getId();
				int todayTotal = dao.order_logUpdate(id, "", total);
				int oneday = dao.get_oneday(id);
				int cha = oneday - total;
				dao.order(id, cha);
				System.out.println(dao.get_oneday(id));
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 14));
		lblNewLabel.setIcon(new ImageIcon(DetailsGUI.class.getResource("/image/menu_background.jpg")));
		lblNewLabel.setBounds(0, 0, 600, 850);
		frame.getContentPane().add(lblNewLabel);


		MenuListAdapter adapter = new MenuListAdapter(menuList);

		listView.setAdapter(adapter);
		adapter.addDataChangedEventListener(new MenuListAdapter.ChangedEventListener() {

			@Override
			public void dataChangedEvent() {
				int sum = 0;
				for (MenuVO m : menuList) {
					sum += m.getSum();
				}
				lblNewLabel_2.setText(sum + "");
				total = sum;
			}
		});

		for (int i = 0; i < menuList.size(); i++) {
			menuList.get(i).getMenu_name();
			menuList.get(i).getPrice();
		}

		adapter.notifyDataSetChange();

		listView.setBounds(26, 265, 555, 502);

		frame.getContentPane().add(listView);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}
		});
		btnNewButton_1.setBackground(SystemColor.menu);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_1.setBounds(451, 189, 105, 45);
		frame.getContentPane().add(btnNewButton_1);

		frame.setBounds(100, 100, 616, 890);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}