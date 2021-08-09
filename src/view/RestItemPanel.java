package view;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RestItemPanel extends JPanel {

   public JLabel lbImage;
   public JLabel lbRstrName;
   public JButton btnMenu;
   private JLabel lblNewLabel;

   public RestItemPanel() {
      setLayout(null);

      lbImage = new JLabel("New label");
      lbImage.setBounds(9, 10, 297, 141);
      add(lbImage);

      lbRstrName = new JLabel("New label");
      lbRstrName.setBounds(336, 10, 204, 60);
      add(lbRstrName);

      btnMenu = new JButton("New button");
      btnMenu.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      btnMenu.setBounds(336, 96, 204, 55);
      add(btnMenu);

      setPreferredSize(new Dimension(596, 161));

      lblNewLabel = new JLabel("");
      lblNewLabel.setIcon(new ImageIcon(RestItemPanel.class.getResource("/image/button.jpg")));
      lblNewLabel.setBounds(0, 0, 596, 230);
      add(lblNewLabel);
   }

}