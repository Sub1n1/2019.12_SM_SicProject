package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import godling.swinglib.BaseAdapter.DataChangedEventListener;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MenuItemPanel extends JPanel {
   
   public interface DataChangedEventListener extends EventListener {
      void dataChangedEvent(int quan, int position);
   }
   
   
   private DataChangedEventListener _dataChangedEvent = null;
   
   public void addDataChangedEventListener(DataChangedEventListener event) {
      _dataChangedEvent = event;
   }

   public void quanDataSetChange() {
      if (_dataChangedEvent != null) _dataChangedEvent.dataChangedEvent(quan, itemPosition);
   }


   public JLabel lbl_image;
   public JLabel lbl_menuName;
   public JLabel lbl_price;
   public JButton btn_plus;
   public JButton btn_minus;
   public JLabel lbl_quantity;
   public int quan = 0;
   public int itemPosition = 0;

   /**
    * Create the panel.
    */
   public MenuItemPanel() {
      setBackground(Color.WHITE);
      setLayout(null);

      lbl_image = new JLabel("");
      lbl_image.setBounds(20, 12, 150, 125);
      lbl_image.setHorizontalAlignment(SwingConstants.CENTER);
      add(lbl_image);

      setPreferredSize(new Dimension(535, 150));

      lbl_menuName = new JLabel("menuName");
      lbl_menuName.setBounds(177, 33, 150, 35);
      lbl_menuName.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
      lbl_menuName.setHorizontalAlignment(SwingConstants.RIGHT);
      add(lbl_menuName);

      lbl_price = new JLabel("price");
      lbl_price.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
      lbl_price.setBounds(177, 78, 150, 35);
      lbl_price.setHorizontalAlignment(SwingConstants.RIGHT);
      add(lbl_price);

      // ÇÃ·¯½º¹öÆ°
      btn_plus = new JButton("+");
      btn_plus.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ++quan;
            lbl_quantity.setText(quan + "");
            quanDataSetChange();
         }
      });
      btn_plus.setBackground(SystemColor.control);
      btn_plus.setBounds(368, 51, 43, 43);
      add(btn_plus);

      // ¸¶ÀÌ³Ê½º¹öÆ°
      btn_minus = new JButton("-");
      btn_minus.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            --quan;
            if (quan < 0) quan = 0;
            lbl_quantity.setText(quan + "");
            quanDataSetChange();
         }
      });
      btn_minus.setBackground(SystemColor.control);
      btn_minus.setBounds(468, 51, 43, 43);
      add(btn_minus);

      // ¼ö·®
      String str = Integer.toString(quan);
      lbl_quantity = new JLabel();
      lbl_quantity.setText(str);
      lbl_quantity.setBounds(418, 50, 43, 43);
      lbl_quantity.setHorizontalAlignment(SwingConstants.CENTER);
      lbl_quantity.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
      add(lbl_quantity);
      
      JLabel label = new JLabel("New label");
      label.setIcon(new ImageIcon(MenuItemPanel.class.getResource("/menuImage/menuPanelBack.jpg")));
      label.setBounds(0, 0, 535, 150);
      add(label);

   }
}